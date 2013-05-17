package de.htwg.xiangqi.entities;

public class PieceCannon extends Piece {

	private Square[][] board;

	public PieceCannon(int r, int c, Player p) {
		super(r, c, 'C', p);
	}

	public boolean validMove(Square[][] board, int targetRow, int targetCol) {
		this.board = board;
		int currentRow = this.getPosRow();
		int currentCol = this.getPosColumn();
		int diffRow = currentRow - targetRow;
		int diffCol = currentCol - targetCol;
		if (validMoveVertically(diffRow, diffCol)) {
			int i = ((targetRow < currentRow) ? targetRow : currentRow) + 1;
			int upperLimit = (targetRow > currentRow) ? targetRow : currentRow;
			int blockerCount = 0;
			while (i < upperLimit) {
				if (board[i][currentCol].occupiedPoint()) {
					++blockerCount;
				}
				++i;
			}
			return validCaptureMove(targetRow, targetCol, blockerCount)
					|| (blockerCount == 0 && !board[targetRow][targetCol]
							.occupiedPoint());
		} else if (validMoveHorizontally(diffRow, diffCol)) {
			int i = ((targetCol < currentCol) ? targetCol : currentCol) + 1;
			int upperLimit = (targetCol > currentCol) ? targetCol : currentCol;
			int blockerCount = 0;
			while (i < upperLimit) {
				if (board[currentRow][i].occupiedPoint()) {
					++blockerCount;
				}
				++i;
			}
			return validCaptureMove(targetRow, targetCol, blockerCount)
					|| (blockerCount == 0 && !board[targetRow][targetCol]
							.occupiedPoint());
		} else {
			return false;
		}
	}

	private boolean existingScreen(int blockerCount) {
		if (blockerCount != 1) {
			return false;
		} else {
			return true;
		}
	}

	private boolean enemyOnTarget(Piece piece) {
		return this.getPlayer() != piece.getPlayer();
	}

	private boolean validCaptureMove(int targetRow, int targetCol,
			int blockerCount) {
		if (this.board[targetRow][targetCol].occupiedPoint()) {
			Piece piece = this.board[targetRow][targetCol].getPiece();
			return existingScreen(blockerCount) && enemyOnTarget(piece);
		} else {
			return false;
		}
	}

	private boolean validMoveVertically(int diffRow, int diffCol) {
		return diffRow != 0 && diffCol == 0;
	}

	private boolean validMoveHorizontally(int diffRow, int diffCol) {
		return diffRow == 0 && diffCol != 0;
	}
}