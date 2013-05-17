package de.htwg.xiangqi.entities;

public class PieceChariot extends Piece {

	public PieceChariot(int r, int c, Player p) {
		super(r, c, 'R', p);
	}
	
	public boolean validMove(Square[][] board, int targetRow, int targetCol) {
		int currentRow = this.getPosRow();
		int currentCol = this.getPosColumn();
		int diffRow = currentRow - targetRow;
		int diffCol = currentCol - targetCol;
		if (validMoveVertically(diffRow, diffCol)) {
			int i = ((targetRow < currentRow) ? targetRow : currentRow) + 1;
			int upperLimit = (targetRow > currentRow) ? targetRow : currentRow;
			while (i < upperLimit) {
				if (board[i][currentCol].occupiedPoint()) {
					return false;
				}
				++i;
			}
			return true;
		} else if (validMoveHorizontally(diffRow, diffCol)) {
			int i = ((targetCol < currentCol) ? targetCol : currentCol) + 1;
			int upperLimit = (targetCol > currentCol) ? targetCol : currentCol;
			while (i < upperLimit) {
				if (board[currentRow][i].occupiedPoint()) {
					return false;
				}
				++i;
			}
			return true;
		} else {
			return false;
		}
	}

	private boolean validMoveVertically(int diffRow, int diffCol) {
		return (diffRow < 0 || diffRow > 0) && diffCol == 0;
	}

	private boolean validMoveHorizontally(int diffRow, int diffCol) {
		return diffRow == 0 && (diffCol < 0 || diffCol > 0);
	}
}
