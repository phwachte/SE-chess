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
		return validMoveVertically(board, diffRow, diffCol, currentRow,
				currentCol, targetRow, targetCol)
				|| validMoveHorizontally(board, diffRow, diffCol, currentRow,
						currentCol, targetRow, targetCol);
	}

	private boolean validMoveVertically(Square[][] board, int diffRow,
			int diffCol, int currentRow, int currentCol, int targetRow,
			int targetCol) {
		if ((diffRow < 0 || diffRow > 0) && diffCol == 0) {
			int i = ((targetRow < currentRow) ? targetRow : currentRow) + 1;
			int upperLimit = (targetRow > currentRow) ? targetRow : currentRow;
			while (i < upperLimit) {
				if (board[i][currentCol].occupiedPoint()) {
					return false;
				}
				++i;
			}
			return true;
		} else {
			return false;
		}
	}

	private boolean validMoveHorizontally(Square[][] board, int diffRow,
			int diffCol, int currentRow, int currentCol, int targetRow,
			int targetCol) {
		if (diffRow == 0 && (diffCol < 0 || diffCol > 0)) {
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
}
