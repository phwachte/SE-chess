package de.htwg.xiangqi.controller;

import de.htwg.xiangqi.entities.Piece;
import de.htwg.xiangqi.entities.Piece.Player;

public class PossibleMoveA {
	
	private PossibleMoveA() {
	}

	public static boolean possibleMoveA(Piece piece, int row, int col) {
		if (piece.getPlayer() == Player.RED) {
			return validMoveRedA(piece, row, col);
		} else {
			return validMoveBlackA(piece, row, col);
		}
	}

	private static boolean validMoveRedA(Piece piece, int targetRow,
			int targetCol) {
		if (!MoveRules.inRedPalace(targetRow, targetCol)) {
			return false;
		}
		return validMoveA(piece, targetRow, targetCol);
	}

	private static boolean validMoveBlackA(Piece piece, int targetRow,
			int targetCol) {
		if (!MoveRules.inBlackPalace(targetRow, targetCol)) {
			return false;
		}
		return validMoveA(piece, targetRow, targetCol);
	}

	private static boolean validMoveA(Piece piece, int targetRow, int targetCol) {
		int currentRow = piece.getPosRow();
		int currentCol = piece.getPosColumn();
		int diffRow = currentRow - targetRow;
		int diffCol = currentCol - targetCol;
		return (diffRow == -1 || diffRow == 1) && (diffCol == -1 || diffCol == 1);
	}

}
