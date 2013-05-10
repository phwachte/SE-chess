package de.htwg.xiangqi.controller;

import de.htwg.xiangqi.entities.*;

public final class MovePiece {
	
	private MovePiece() {
	}

	public static void movePiece(Square current, Square target) {
		current.getPiece().setPosition(target.getPiece().getPosRow(),
				target.getPiece().getPosColumn());
		target.setPiece(current.getPiece());
		current.setPiece(null);
	}

	public static boolean capturePiece(Square current, Square target) {
		if (target.getPiece() != null) {
			return !(current.getPiece().getPlayer() == target.getPiece()
					.getPlayer());
		} else {
			return true;
		}
	}

	public static boolean pieceChoosen(Square current) {
		return current.getPiece() != null;
	}

	public static boolean occupiedPoint(Square target) {
		return target.getPiece() != null;
	}

}
