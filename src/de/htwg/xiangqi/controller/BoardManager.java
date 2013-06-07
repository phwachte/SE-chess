package de.htwg.xiangqi.controller;

import de.htwg.xiangqi.entities.Board;
import de.htwg.xiangqi.entities.Piece;
import de.htwg.xiangqi.entities.Piece.Player;
import de.htwg.xiangqi.entities.Square;

public class BoardManager {

	private Board b;
	private Square[][] board;
	private int moveCounter;
	private static final int NUMBER_OF_PLAYERS = 2;

	public BoardManager() {
		this.b = new Board();
		this.board = this.b.getBoard();
		this.moveCounter = 1;
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

	public Piece choosenPiece(int currentRow, int currentCol) {
		if (this.b.onBoard(currentRow, currentCol)) {
			return this.board[currentRow][currentCol].getPiece();
		}
		return null;
	}
	
	public boolean validChoose(Piece choosen) {
		if (choosen.getPlayer() == Player.RED && this.getPlayersTurn() == 1) {
			return true;
		}
		if (choosen.getPlayer() == Player.BLACK && this.getPlayersTurn() == 0) {
			return true;
		}
		return false;
	}

	public boolean validMove(Piece piece, int targetRow, int targetCol) {
		if (this.b.onBoard(targetRow, targetCol)) {
			return piece.validMove(this.board, targetRow, targetCol);
		}
		return false;
	}

	public boolean movePiece(int currentRow, int currentCol, int targetRow,
			int targetCol) {
		Square choosen = this.board[currentRow][currentCol];
		Square target = this.board[targetRow][targetCol];
		Piece toCapture = target.getPiece();
		if (toCapture != null) {
			if (validCapture(choosen, target)) {
				toCapture = target.getPiece();
				toCapture.setIsCaptured(true);
				moveTo(choosen, targetRow, targetCol);
				return true;
			} else {
				return false;
			}
		} else {
			moveTo(choosen, targetRow, targetCol);
			return true;
		}
	}

	private void moveTo(Square choosen, int targetRow, int targetCol) {
		Piece choosenPiece = choosen.getPiece();
		Square target = this.board[targetRow][targetCol];
		choosenPiece.setPosition(targetRow, targetCol);
		target.setPiece(choosen.getPiece());
		choosen.setPiece(null);
	}

	private boolean validCapture(Square choosen, Square target) {
		Piece choosenPiece = choosen.getPiece();
		Piece targetPiece = target.getPiece();
		return (choosenPiece.getPlayer() != targetPiece.getPlayer());
	}
	
	public Piece isCheckmate() {
		Piece redGeneral = b.getRedGeneral();
		Piece blackGeneral = b.getBlackGeneral();
		if (redGeneral.getIsCaptured()) {
			return redGeneral;
		} else if (blackGeneral.getIsCaptured()) {
			return blackGeneral;
		} else {
			return null;
		}
	}

}
