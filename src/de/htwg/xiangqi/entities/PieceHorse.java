package de.htwg.xiangqi.entities;

public class PieceHorse extends Piece {

	private static final int NEG_DIFF = -2;
	private static final int POS_DIFF = 2;

	public PieceHorse(int r, int c, Player p) {
		super(r, c, 'H', p);
	}

	public boolean validMove(Square[][] board, int targetRow, int targetCol) {
		int currentRow = this.getPosRow();
		int currentCol = this.getPosColumn();
		int diffRow = currentRow - targetRow;
		int diffCol = currentCol - targetCol;
		if (validMoveNorth(diffRow, diffCol)) {
			return !board[currentRow - 1][currentCol].occupiedPoint();
		} else if (validMoveEast(diffRow, diffCol)) {
			return !board[currentRow][currentCol + 1].occupiedPoint();
		} else if (validMoveSouth(diffRow, diffCol)) {
			return !board[currentRow + 1][currentCol].occupiedPoint();
		} else if (validMoveWest(diffRow, diffCol)) {
			return !board[currentRow][currentCol - 1].occupiedPoint();
		} else {
			return false;
		}
	}

	private boolean validMoveNorth(int diffRow, int diffCol) {
		return diffRow == POS_DIFF && (diffCol == 1 || diffCol == -1);
	}

	private boolean validMoveEast(int diffRow, int diffCol) {
		return diffCol == NEG_DIFF && (diffRow == 1 || diffRow == -1);
	}

	private boolean validMoveSouth(int diffRow, int diffCol) {
		return diffRow == NEG_DIFF && (diffCol == 1 || diffCol == -1);
	}

	private boolean validMoveWest(int diffRow, int diffCol) {
		return diffCol == POS_DIFF && (diffRow == 1 || diffRow == -1);
	}
}
