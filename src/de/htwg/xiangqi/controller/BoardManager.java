package de.htwg.xiangqi.controller;

import java.util.List;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

import de.htwg.util.observer.Observable;
import de.htwg.xiangqi.XiangqiGameModule;
import de.htwg.xiangqi.model.Board;
import de.htwg.xiangqi.model.Piece;
import de.htwg.xiangqi.model.Piece.Player;
import de.htwg.xiangqi.model.Square;
import de.htwg.xiangqi.persistence.IDataAccessObject;

/**
 * class BoardManager controls the game
 * @author P. Wachter
 */
@Singleton
public class BoardManager extends Observable implements IBoardManager {

	private static final int NUMBER_OF_PLAYERS = 2;
	private static final int ZERO = 0;
	private static final int ONE = 1;
	private static final int TWO = 2;
	private static final int THREE = 3;
	private Board b;
	private String message;
	private Injector injector;
	
	/*persistence*/
	private IDataAccessObject dao;

	/**
	 * create a BoardManager object
	 */
	@Inject
	public BoardManager() {
		this.b = new Board();
		this.injector = Guice.createInjector(new XiangqiGameModule());
		this.dao = injector.getInstance(IDataAccessObject.class);
	}

	/**
	 * check, if the move is valid, create an error message, if not
	 * 
	 * @param chosenRow
	 *            the index of the chosen row
	 * @param chosenCol
	 *            the index of the chosen column
	 * @param targetRow
	 *            the index of the target row
	 * @param targetCol
	 *            the index of the target column
	 * @return true, if game is finished, false, if not
	 */
	public boolean inputMove(String values) {
		int chosenRow = 0;
		int chosenCol = 0;
		int targetRow = 0;
		int targetCol = 0;
		String[] chosenValues = values.split(" ");
		
		try {
			chosenRow = Integer.parseInt(chosenValues[ZERO]);
			chosenCol = Integer.parseInt(chosenValues[ONE]);
			targetRow = Integer.parseInt(chosenValues[TWO]);
			targetCol = Integer.parseInt(chosenValues[THREE]);
		} catch (NumberFormatException e) {
			message = "Invalid value(s): " + values;
			notifyObservers();
			return false;
		}
		
		String input = "Input error (" + chosenRow + " " + chosenCol + " "
				+ targetRow + " " + targetCol + "):\n";
		message = null;
		if (chosenPiece(chosenRow, chosenCol) == null) {
			message = input + "No piece on chosen point!\n";
			notifyObservers();
			return false;
		}
		if (!validChoose(chosenPiece(chosenRow, chosenCol))) {
			message = input
					+ "Invalid choose of piece, choose your own piece!\n";
			notifyObservers();
			return false;
		}
		if (!validMove(chosenPiece(chosenRow, chosenCol), targetRow, targetCol)) {
			message = input + "Target point is not valid for this piece!\n";
			notifyObservers();
			return false;
		}
		if (!movePiece(chosenRow, chosenCol, targetRow, targetCol)) {
			message = input + "Taking your own piece is invalid!\n";
			notifyObservers();
			return false;
		}

		moveTo(chosenRow, chosenCol, targetRow, targetCol);
		this.b.increaseMoveCounter();

		if (isCheckmate() != '-') {
			notifyObservers();
			return true;
		}

		notifyObservers();
		return false;
	}

	/**
	 * @return the error message
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * @return the board on which is played
	 */
	public Board getBoard() {
		return this.b;
	}

	/**
	 * initialize the board
	 */
	public void setStartBoard() {
		this.b.setPiecesRed();
		this.b.setPiecesBlack();
		this.b.fillBoard();
	}
	
	/**
	 * Set the Board
	 */
	public void setBoard(Board b){
		this.b = b;
	}

	private Piece chosenPiece(int currentRow, int currentCol) {
		if (this.b.onBoard(currentRow, currentCol)) {
			return b.getSquareMatrix()[currentRow][currentCol].getPiece();
		}
		return null;
	}

	private boolean validChoose(Piece chosen) {
		if (chosen.getPlayer() == Player.RED && this.getPlayersTurn() == 1) {
			return true;
		}
		if (chosen.getPlayer() == Player.BLACK && this.getPlayersTurn() == 0) {
			return true;
		}
		return false;
	}

	private boolean validMove(Piece piece, int targetRow, int targetCol) {
		if (this.b.onBoard(targetRow, targetCol)) {
			return piece.validMove(b.getSquareMatrix(), targetRow, targetCol);
		}
		return false;
	}

	private boolean movePiece(int currentRow, int currentCol, int targetRow,
			int targetCol) {
		Square chosen = b.getSquareMatrix()[currentRow][currentCol];
		Square target = b.getSquareMatrix()[targetRow][targetCol];
		Piece toCapture = target.getPiece();
		if (toCapture != null) {
			if (validCapture(chosen, target)) {
				toCapture = target.getPiece();
				toCapture.setIsCaptured(true);
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	private void moveTo(int currentRow, int currentCol, int targetRow,
			int targetCol) {
		Square chosen = b.getSquareMatrix()[currentRow][currentCol];
		Piece chosenPiece = chosen.getPiece();
		Square target = b.getSquareMatrix()[targetRow][targetCol];
		chosenPiece.setPosition(targetRow, targetCol);
		target.setPiece(chosen.getPiece());
		chosen.setPiece(null);
	}

	private boolean validCapture(Square chosen, Square target) {
		Piece chosenPiece = chosen.getPiece();
		Piece targetPiece = target.getPiece();
		return (chosenPiece.getPlayer() != targetPiece.getPlayer());
	}

	/**
	 * @return the identifying character of the general, who is captured, if no
	 *         general is captured the returned character is '-'
	 */
	public char isCheckmate() {
		Piece redGeneral = b.getRedGeneral();
		Piece blackGeneral = b.getBlackGeneral();
		if (redGeneral.getIsCaptured()) {
			return 'r';
		} else if (blackGeneral.getIsCaptured()) {
			return 'b';
		} else {
			return '-';
		}
	}

	/**
	 * @return the string which represents the winner message
	 */
	public String winnerMessage() {
		StringBuilder sb = new StringBuilder();
		char general = isCheckmate();
		if (general == 'r') {
			sb.append("Congratulation player black, you are the winner!");
		} else {
			sb.append("Congratulation player red, you are the winner!");
		}
		return sb.toString();
	}

	/**
	 * @param row
	 *            the index of the row
	 * @param col
	 *            the index of the column
	 * @return the string for the tui output
	 */
	public String getTUIOutput(int row, int col) {
		StringBuilder sb = new StringBuilder();
		Piece piece = b.getSquareMatrix()[row][col].getPiece();
		if (piece != null) {
			if (piece.getPlayer() == Player.RED) {
				sb.append("R").append(piece.getPieceType());
			} else {
				sb.append("B").append(piece.getPieceType());
			}
		} else {
			sb.append("  ");
		}
		return sb.toString();
	}

	/**
	 * @param row
	 *            the index of the row
	 * @param col
	 *            the index of the column
	 * @return the string which represents the path of the icon
	 */
	public String pieceAtPoint(int row, int col) {
		Piece current = b.getSquareMatrix()[row][col].getPiece();
		if (current != null) {
			/*
			 * especially and solely for linux because the paths are wrong with \\ we need to use / instead
			 * */
			return current.getPieceIcon().replace("\\", "/");
		} else {
			return null;
		}
	}

	
	/*persistence*/
	@Override
	public void saveGame() {
		this.dao.createOrUpdate(this.b);
	}
	
	@Override
	public List<Board> loadSaveGames() {
		return (List<Board>) this.dao.read(".*");
	}

	@Override
	public IBoardManager loadGame(String name) {
		return (IBoardManager)this.dao.read(name);
	}

	@Override
	public int getPlayersTurn() {
		return this.b.getMoveCounter()%NUMBER_OF_PLAYERS;
	}

	@Override
	public IDataAccessObject getDAO() {
		return dao;
	}
}
