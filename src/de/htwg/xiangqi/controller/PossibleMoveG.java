package de.htwg.xiangqi.controller;

import de.htwg.xiangqi.entities.Piece;
import de.htwg.xiangqi.entities.Piece.Player;

public final class PossibleMoveG {

	private PossibleMoveG() {
	}

	public static boolean possibleMoveG(Piece piece, int row, int col) {
		if (piece.getPlayer() == Player.RED) {
			return validMoveRedG(piece, row, col);
		} else {
			return validMoveBlackG(piece, row, col);
		}
	}

	private static boolean validMoveRedG(Piece piece, int targetRow,
			int targetCol) {
		if (!MoveRules.inRedPalace(targetRow, targetCol)) {
			return false;
		}
		return validMoveG(piece, targetRow, targetCol);
	}

	private static boolean validMoveBlackG(Piece piece, int targetRow,
			int targetCol) {
		if (!MoveRules.inBlackPalace(targetRow, targetCol)) {
			return false;
		}
		return validMoveG(piece, targetRow, targetCol);
	}

	private static boolean validMoveG(Piece piece, int targetRow, int targetCol) {
		int currentRow = piece.getPosRow();
		int currentCol = piece.getPosColumn();
		int diffRow = currentRow - targetRow;
		int diffCol = currentCol - targetCol;
		return validMoveUpDown(diffRow, diffCol) 
				|| validMoveLeftRight(diffRow, diffCol);
	}

	private static boolean validMoveUpDown(int diffRow, int diffCol) {
		return (diffRow == -1 || diffRow == 1) && diffCol == 0;
	}

	private static boolean validMoveLeftRight(int diffRow, int diffCol) {
		return diffRow == 0 && (diffCol == -1 || diffCol == 1);
	}

}
