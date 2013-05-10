package de.htwg.xiangqi.controller;

import de.htwg.xiangqi.entities.Piece;
import de.htwg.xiangqi.entities.Square;

public final class PossibleMove {
	
	private PossibleMove() {
	}
	
	private static final int ROW = 10;
	private static final int COLUMN = 9;
	
	public static boolean possibleMove(Square[][] board, Square current,
			int targetRow, int targetCol) {
		if (!inField(targetRow, targetCol)) {
			return false;
		}
		boolean possible = false;
		Piece piece = current.getPiece();
		switch (piece.getPieceType()) {
		case 'G':
			possible = PossibleMoveG.possibleMoveG(piece, targetRow, targetCol);
			break;
		case 'A':
			possible = PossibleMoveA.possibleMoveA(piece, targetRow, targetCol);
			break;
		case 'H':
			possible = possibleMoveH(piece, targetRow, targetCol);
			break;
		case 'E':
			possible = PossibleMoveE.possibleMoveE(board, piece, targetRow,
					targetCol);
			break;
		case 'R':
			possible = possibleMoveR(piece, targetRow, targetCol);
			break;
		case 'C':
			possible = possibleMoveC(piece, targetRow, targetCol);
			break;
		case 'S':
			possible = possibleMoveS(piece, targetRow, targetCol);
			break;
		}
		return possible;
	}

	public static boolean inField(int row, int col) {
		return row < ROW && row >= 0 && col >= 0 && col < COLUMN;
	}

	public static boolean possibleMoveH(Piece piece, int row, int col) {
		return false;
	}

	public static boolean possibleMoveR(Piece piece, int row, int col) {
		return false;
	}

	public static boolean possibleMoveC(Piece piece, int row, int col) {
		return false;
	}

	public static boolean possibleMoveS(Piece piece, int row, int col) {
		return false;
	}

}
