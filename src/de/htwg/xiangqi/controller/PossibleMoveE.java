package de.htwg.xiangqi.controller;

import de.htwg.xiangqi.entities.Piece;
import de.htwg.xiangqi.entities.Piece.Player;
import de.htwg.xiangqi.entities.Square;

public final class PossibleMoveE {

	private PossibleMoveE() {
	}

	private static final int NEG_DIV = -2;
	private static final int POS_DIV = 2;

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
		if ((diffRow == NEG_DIV || diffRow == POS_DIV)
				&& (diffCol == NEG_DIV || diffCol == POS_DIV)) {
			return !MoveRules
					.occupiedPoint(board[currentRow - diffRow / POS_DIV]
							[currentCol - diffCol / POS_DIV]);
		} else {
			return false;
		}
	}

}
