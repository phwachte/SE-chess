package de.htwg.xiangqi.controller;

import de.htwg.xiangqi.entities.Square;

public final class MoveRules {
	
	private MoveRules() {
	}
	
	private static final int ZERO = 0;
	private static final int TWO = 2;
	private static final int THREE = 3;
	private static final int FOUR = 4;
	private static final int FIVE = 5;
	private static final int SEVEN = 7;
	private static final int NINE = 9;
	
	public static boolean inRedPalace(int row, int col) {
		return col >= THREE && col <= FIVE && row >= SEVEN && row <= NINE;
	}

	public static boolean inBlackPalace(int row, int col) {
		return col >= THREE && col <= FIVE && row >= ZERO && row <= TWO;
	}
	
	public static boolean inRedHalf(int row) {
		return row >= FIVE;
	}

	public static boolean inBlackHalf(int row) {
		return row <= FOUR;
	}
	
	public static boolean occupiedPoint(Square point) {
		if (point.getPiece() != null) {
			return true;
		}
		return false;
	}

}
