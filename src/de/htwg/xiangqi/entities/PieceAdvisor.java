package de.htwg.xiangqi.entities;

public class PieceAdvisor extends Piece {

	public PieceAdvisor(int r, int c, Player p) {
		super(r, c, 'A', p);
	}

	public boolean validMove(Square[][] board, int row, int col) {
		if (this.getPlayer() == Player.RED) {
			return validMoveRedA(row, col);
		} else {
			return validMoveBlackA(row, col);
		}
	}

	private boolean validMoveRedA(int targetRow, int targetCol) {
		if (!this.inRedPalace(targetRow, targetCol)) {
			return false;
		}
		return validMoveA(targetRow, targetCol);
	}

	private boolean validMoveBlackA(int targetRow, int targetCol) {
		if (!this.inBlackPalace(targetRow, targetCol)) {
			return false;
		}
		return validMoveA(targetRow, targetCol);
	}

	private boolean validMoveA(int targetRow, int targetCol) {
		int currentRow = this.getPosRow();
		int currentCol = this.getPosColumn();
		int diffRow = currentRow - targetRow;
		int diffCol = currentCol - targetCol;
		return (diffRow == -1 || diffRow == 1)
				&& (diffCol == -1 || diffCol == 1);
	}

}