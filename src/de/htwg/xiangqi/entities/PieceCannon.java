package de.htwg.xiangqi.entities;

public class PieceCannon extends Piece {

	public PieceCannon(int r, int c, Player p) {
		super(r, c, 'C', p);
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

	private boolean existingScreen(int blockerCount) {
		return blockerCount == 1;
	}

	private boolean enemyOnTarget(Piece piece) {
		return this.getPlayer() != piece.getPlayer();
	}

	private boolean validCaptureMove(Square[][] board, int targetRow,
			int targetCol, int blockerCount) {
		if (board[targetRow][targetCol].occupiedPoint()) {
			Piece piece = board[targetRow][targetCol].getPiece();
			return existingScreen(blockerCount) && enemyOnTarget(piece);
		} else {
			return false;
		}
	}

	private boolean validMoveVertically(Square[][] board, int diffRow,
			int diffCol, int currentRow, int currentCol, int targetRow,
			int targetCol) {
		if (diffRow != 0 && diffCol == 0) {
			int i = ((targetRow < currentRow) ? targetRow : currentRow) + 1;
			int upperLimit = (targetRow > currentRow) ? targetRow : currentRow;
			int blockerCount = 0;
			while (i < upperLimit) {
				if (board[i][currentCol].occupiedPoint()) {
					++blockerCount;
				}
				++i;
			}
			return validCaptureMove(board, targetRow, targetCol, blockerCount)
					|| (blockerCount == 0 && !board[targetRow][targetCol]
							.occupiedPoint());
		} else {
			return false;
		}
	}

	private boolean validMoveHorizontally(Square[][] board, int diffRow,
			int diffCol, int currentRow, int currentCol, int targetRow,
			int targetCol) {
		if (diffRow == 0 && diffCol != 0) {
			int i = ((targetCol < currentCol) ? targetCol : currentCol) + 1;
			int upperLimit = (targetCol > currentCol) ? targetCol : currentCol;
			int blockerCount = 0;
			while (i < upperLimit) {
				if (board[currentRow][i].occupiedPoint()) {
					++blockerCount;
				}
				++i;
			}
			return validCaptureMove(board, targetRow, targetCol, blockerCount)
					|| (blockerCount == 0 && !board[targetRow][targetCol]
							.occupiedPoint());
		} else {
			return false;
		}
	}
}