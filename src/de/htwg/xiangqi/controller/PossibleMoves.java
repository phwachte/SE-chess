package de.htwg.xiangqi.controller;

import de.htwg.xiangqi.entities.Piece;
import de.htwg.xiangqi.entities.Piece.Player;

public class PossibleMoves {

	public boolean possibleMove(Piece piece, int row, int col) {
		if (!inField(row, col)) {
			return false;
		}
		boolean possible = false;
		switch (piece.getPieceType()) {
		case 'G':
			possible = possibleMoveG(piece, row, col);
			break;
		case 'A':
			possible = possibleMoveA(piece, row, col);
			break;
		case 'H':
			possible = possibleMoveH(piece, row, col);
			break;
		case 'E':
			possible = possibleMoveE(piece, row, col);
			break;
		case 'T':
			possible = possibleMoveT(piece, row, col);
			break;
		case 'C':
			possible = possibleMoveC(piece, row, col);
			break;
		case 'S':
			possible = possibleMoveS(piece, row, col);
			break;
		}
		return possible;
	}
	
	public boolean inField(int row, int col) {
		if (row <= 9 && row >= 0 && col >= 0 && col <= 8) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean possibleMoveH(Piece piece, int row, int col) {
		return false;
	}
	
	public boolean possibleMoveT(Piece piece, int row, int col) {
		return false;
	}

	public boolean possibleMoveC(Piece piece, int row, int col) {
		return false;
	}

	public boolean possibleMoveS(Piece piece, int row, int col) {
		return false;
	}
	
	public boolean possibleMoveG(Piece piece, int row, int col) {
//		int currentRow = p.getPosRow();
//		int currentCol = p.getPosColumn();
//		if (inPalace(p.getPlayer(), row, col)) {
//			if (p.getPlayer() == Player.RED) {
//				return possibleMoveRedG();
//			} else {
//				return possibleMoveBlackG();
//			}
//		} else {
//			return false;
//		}
		if (piece.getPlayer() == Player.RED) {
			return possibleMoveRedG();
		} else {
			return possibleMoveBlackG();
		}
	}

	public boolean possibleMoveA(Piece piece, int row, int col) {
		if (piece.getPlayer() == Player.RED) {
			possibleMoveRedA();
		} else {
			possibleMoveBlackA();
		}
	}

	public boolean possibleMoveE(Piece piece, int row, int col) {
		if (piece.getPlayer() == Player.RED) {
			possibleMoveRedE();
		} else {
			possibleMoveBlackE();
		}
	}

	public boolean inPalace(Player piece, int row, int col) {
		boolean retVal = false;
		if (col >= 3 && col <= 5) {
			if (piece == Player.RED && row <= 9 && row >= 7) {
				retVal = true;
			} else if (row <= 0 && row >= 2) {
				retVal = true;
			}
		}
		return retVal;
	}

	public boolean inHalfRed(int row, int col) {
		if (row >= 5) {
			return true;
		} else {
			return false;
		}
	}

	public boolean inHalfBlack(int row, int col) {
		if (row <= 4) {
			return true;
		} else {
			return false;
		}
	}
}
