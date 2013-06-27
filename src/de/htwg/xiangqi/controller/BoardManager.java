package de.htwg.xiangqi.controller;

import com.google.inject.Inject;

import de.htwg.util.observer.Observable;
import de.htwg.xiangqi.model.Board;
import de.htwg.xiangqi.model.Piece;
import de.htwg.xiangqi.model.Square;
import de.htwg.xiangqi.model.Piece.Player;

/**
 * class BoardManager controls the game
 * 
 * @author P. Wachter
 * 
 */
public class BoardManager extends Observable implements IBoardManager {

	private static final int NUMBER_OF_PLAYERS = 2;
	private Board b;
	private Square[][] board;
	private int moveCounter;
	private String message;

	/**
	 * create a BoardManager object
	 */
	@Inject
	public BoardManager() {
		this.b = new Board();
		this.board = this.b.getBoard();
		this.moveCounter = 1;
	}

	public boolean inputMove(String values) {
		int chosenRow = 0;
		int chosenCol = 0;
		int targetRow = 0;
		int targetCol = 0;
		String[] chosenValues = values.split(" ");
		
		try {
			chosenRow = Integer.parseInt(chosenValues[0]);
			chosenCol = Integer.parseInt(chosenValues[1]);
			targetRow = Integer.parseInt(chosenValues[2]);
			targetCol = Integer.parseInt(chosenValues[3]);
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
		increaseMoveCounter();

		if (isCheckmate() != '-') {
			notifyObservers();
			return true;
		}

		notifyObservers();
		return false;
	}

	public String getMessage() {
		return this.message;
	}

	public Square[][] getBoard() {
		return this.board;
	}

	public int getMoveCounter() {
		return this.moveCounter;
	}

	public void increaseMoveCounter() {
		++this.moveCounter;
	}

	public int getPlayersTurn() {
		return this.moveCounter % NUMBER_OF_PLAYERS;
	}

	public void setStartBoard() {
		this.b.setPiecesRed();
		this.b.setPiecesBlack();
		this.b.fillBoard();
	}

	/**
	 * @param currentRow
	 *            the index of the current row
	 * @param currentCol
	 *            the index of the current column
	 * @return the piece which is set on the current point, null, if no piece is
	 *         set
	 */
	private Piece chosenPiece(int currentRow, int currentCol) {
		if (this.b.onBoard(currentRow, currentCol)) {
			return this.board[currentRow][currentCol].getPiece();
		}
		return null;
	}

	/**
	 * @param chosen
	 *            the piece which is chosen
	 * @return true, if the player chose his own piece, false, if not
	 */
	private boolean validChoose(Piece chosen) {
		if (chosen.getPlayer() == Player.RED && this.getPlayersTurn() == 1) {
			return true;
		}
		if (chosen.getPlayer() == Player.BLACK && this.getPlayersTurn() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * @param piece
	 *            the piece which is chosen
	 * @param targetRow
	 *            the index of the target row
	 * @param targetCol
	 *            the index of the target column
	 * @return true, if the move is valid, false, if not
	 */
	private boolean validMove(Piece piece, int targetRow, int targetCol) {
		if (this.b.onBoard(targetRow, targetCol)) {
			return piece.validMove(this.board, targetRow, targetCol);
		}
		return false;
	}

	/**
	 * @param currentRow
	 *            the index of the current row
	 * @param currentCol
	 *            the index of the current column
	 * @param targetRow
	 *            the index of the target row
	 * @param targetCol
	 *            the index of the target column
	 * @return true, if the move is valid, false, if not
	 */
	private boolean movePiece(int currentRow, int currentCol, int targetRow,
			int targetCol) {
		Square chosen = this.board[currentRow][currentCol];
		Square target = this.board[targetRow][targetCol];
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

	/**
	 * move a piece from one point to another point
	 * 
	 * @param currentRow
	 *            the index of the current row
	 * @param currentCol
	 *            the index of the current column
	 * @param targetRow
	 *            the index of the target row
	 * @param targetCol
	 *            the index of the target column
	 */
	private void moveTo(int currentRow, int currentCol, int targetRow,
			int targetCol) {
		Square chosen = this.board[currentRow][currentCol];
		Piece chosenPiece = chosen.getPiece();
		Square target = this.board[targetRow][targetCol];
		chosenPiece.setPosition(targetRow, targetCol);
		target.setPiece(chosen.getPiece());
		chosen.setPiece(null);
	}

	/**
	 * @param chosen
	 *            the point of the chosen piece
	 * @param target
	 *            the target point
	 * @return true, if the piece on target point is an enemy piece, false, if
	 *         not
	 */
	private boolean validCapture(Square chosen, Square target) {
		Piece chosenPiece = chosen.getPiece();
		Piece targetPiece = target.getPiece();
		return (chosenPiece.getPlayer() != targetPiece.getPlayer());
	}

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

	public String getTUIOutput(int row, int col) {
		StringBuilder sb = new StringBuilder();
		Piece piece = board[row][col].getPiece();
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

	public String pieceAtPoint(int row, int col) {
		Piece current = board[row][col].getPiece();
		if (current != null) {
			return current.getPieceIcon();
		} else {
			return null;
		}
	}

}
