package de.htwg.xiangqi.entities;

public class PieceGeneral extends Piece {

	public PieceGeneral(int r, int c, Player p) {
		super(r, c, 'G', p);
	}

	public boolean validMove(Square[][] board, int targetRow, int targetCol) {
		if (this.getPlayer() == Player.RED) {
			return validMoveRedG(targetRow, targetCol);
		} else {
			return validMoveBlackG(targetRow, targetCol);
		}
	}

	private boolean validMoveRedG(int targetRow, int targetCol) {
		if (!this.inRedPalace(targetRow, targetCol)) {
			return false;
		}
		return validMoveG(targetRow, targetCol);
	}

	private boolean validMoveBlackG(int targetRow, int targetCol) {
		if (!this.inBlackPalace(targetRow, targetCol)) {
			return false;
		}
		return validMoveG(targetRow, targetCol);
	}

	private boolean validMoveG(int targetRow, int targetCol) {
		int currentRow = this.getPosRow();
		int currentCol = this.getPosColumn();
		int diffRow = currentRow - targetRow;
		int diffCol = currentCol - targetCol;
		return validMoveVertically(diffRow, diffCol)
				|| validMoveHorizontally(diffRow, diffCol);
	}

	private boolean validMoveVertically(int diffRow, int diffCol) {
		return (diffRow == -1 || diffRow == 1) && diffCol == 0;
	}

	private boolean validMoveHorizontally(int diffRow, int diffCol) {
		return diffRow == 0 && (diffCol == -1 || diffCol == 1);
	}
}
