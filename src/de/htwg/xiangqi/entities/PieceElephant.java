package de.htwg.xiangqi.entities;

public class PieceElephant extends Piece {

	private static final int NEG_DIV = -2;
	private static final int POS_DIV = 2;
	private Square[][] board;

	public PieceElephant(int r, int c, Player p) {
		super(r, c, 'E', p);
	}

	public boolean validMove(Square[][] board, int targetRow, int targetCol) {
		this.board = board;
		if (this.getPlayer() == Player.RED) {
			return validMoveRedE(targetRow, targetCol);
		} else {
			return validMoveBlackE(targetRow, targetCol);
		}
	}

	private boolean validMoveRedE(int targetRow, int targetCol) {
		if (this.inBlackHalf(targetRow)) {
			return false;
		} else {
			return validMoveE(targetRow, targetCol);
		}
	}

	private boolean validMoveBlackE(int targetRow, int targetCol) {
		if (this.inRedHalf(targetRow)) {
			return false;
		} else {
			return validMoveE(targetRow, targetCol);
		}
	}

	private boolean validMoveE(int targetRow, int targetCol) {
		int currentRow = this.getPosRow();
		int currentCol = this.getPosColumn();
		int diffRow = currentRow - targetRow;
		int diffCol = currentCol - targetCol;
		if ((diffRow == NEG_DIV || diffRow == POS_DIV)
				&& (diffCol == NEG_DIV || diffCol == POS_DIV)) {
			return !this.board[currentRow - diffRow / POS_DIV]
					[currentCol - diffCol / POS_DIV].occupiedPoint();
		} else {
			return false;
		}

	}

}