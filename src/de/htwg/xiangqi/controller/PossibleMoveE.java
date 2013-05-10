package de.htwg.xiangqi.controller;

import de.htwg.xiangqi.entities.Piece;
import de.htwg.xiangqi.entities.Piece.Player;
import de.htwg.xiangqi.entities.Square;

public final class PossibleMoveE {
	
	private PossibleMoveE() {
	}

	public static boolean possibleMoveE(Square[][] board, Piece piece, int row,
			int col) {
		if (piece.getPlayer() == Player.RED) {
			return validMoveRedE(board, piece, row, col);
		} else {
			return validMoveBlackE(board, piece, row, col);
		}
	}

	private static boolean validMoveRedE(Square[][] board, Piece piece,
			int targetRow, int targetCol) {
		if (!MoveRules.inRedHalf(targetRow)) {
			return false;
		}
		return validMoveE(board, piece, targetRow, targetCol);
	}

	private static boolean validMoveBlackE(Square[][] board, Piece piece,
			int targetRow, int targetCol) {
		if (!MoveRules.inBlackHalf(targetRow)) {
			return false;
		}
		return validMoveE(board, piece, targetRow, targetCol);
	}

	private static boolean validMoveE(Square[][] board, Piece piece,
			int targetRow, int targetCol) {
		int currentRow = piece.getPosRow();
		int currentCol = piece.getPosColumn();
		int diffRow = currentRow - targetRow;
		int diffCol = currentCol - targetCol;
		if ((diffRow == -2 || diffRow == 2) && (diffCol == -2 || diffCol == 2)) {
			return !MoveRules.occupiedPoint(
					board[currentRow - diffRow / 2][currentCol - diffCol / 2]);
		} else {
			return false;
		}
	}

}
