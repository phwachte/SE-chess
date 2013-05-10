package de.htwg.xiangqi.controller;

import de.htwg.xiangqi.entities.Square;

public final class MoveRules {
	
	private MoveRules() {
	}
	
	public static boolean inRedPalace(int row, int col) {
		return col >= 3 && col <= 5 && row >= 7 && row <= 9;
	}

	public static boolean inBlackPalace(int row, int col) {
		return col >= 3 && col <= 5 && row >= 0 && row <= 2;
	}
	
	public static boolean inRedHalf(int row) {
		return row >= 5;
	}

	public static boolean inBlackHalf(int row) {
		return row <= 4;
	}
	
	public static boolean occupiedPoint(Square point) {
		if (point.getPiece() != null) {
			return true;
		}
		return false;
	}

}
