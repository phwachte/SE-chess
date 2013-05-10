package de.htwg.xiangqi.controller;

import de.htwg.xiangqi.entities.Square;

public class MoveRules {
	
	public static boolean inRedPalace(int row, int col) {
		if (col >= 3 && col <= 5 && row >= 7 && row <= 9) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean inBlackPalace(int row, int col) {
		if (col >= 3 && col <= 5 && row >= 0 && row <= 2) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean inRedHalf(int row) {
		if (row >= 5) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean inBlackHalf(int row) {
		if (row <= 4) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean occupiedPoint(Square point) {
		if (point.getPiece() != null) {
			return true;
		}
		return false;
	}

}
