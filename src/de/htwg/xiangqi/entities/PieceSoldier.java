package de.htwg.xiangqi.entities;

public class PieceSoldier extends Piece {

	public PieceSoldier(int r, int c, Player p) {
		super(r, c, 'S', p);
	}

	public boolean validMove(Square[][] board, int targetRow, int targetCol) {
		int currentRow = this.getPosRow();
		int currentCol = this.getPosColumn();
		int diffRow = currentRow - targetRow;
		int diffCol = currentCol - targetCol;
		if (this.getPlayer() == Player.RED) {
			return validMoveRedS(currentRow, diffRow, diffCol);
		} else {
			return validMoveBlackS(currentRow, diffRow, diffCol);
		}
	}

	private boolean validMoveRedS(int currentRow, int diffRow, int diffCol) {
		if (this.inRedHalf(currentRow)) {
			return diffRow == 1 && diffCol == 0;
		} else {
			return validMoveHorizontally(diffRow, diffCol) || diffCol == 0 && diffRow == 1;
		}
	}

	private boolean validMoveBlackS(int currentRow, int diffRow, int diffCol) {
		if (this.inBlackHalf(currentRow)) {
			return diffRow == -1 && diffCol == 0;
		} else {
			return validMoveHorizontally(diffRow, diffCol) || diffCol == 0 && diffRow == -1;
		}
	}

	private boolean validMoveHorizontally(int diffRow, int diffCol) {
		return diffRow == 0 && (diffCol == -1 || diffCol == 1);
	}

}