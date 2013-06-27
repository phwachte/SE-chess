package de.htwg.xiangqi.model;

/**
 * class PieceChariot represents the piece chariot
 * 
 * @author P. Wachter
 * 
 */
public class PieceChariot extends Piece {

	private static final String RR = "images\\rr.png";
	private static final String BR = "images\\br.png";

	/**
	 * constructor for piece chariot
	 * 
	 * @param r
	 *            the index of the row
	 * @param c
	 *            the index of the column
	 * @param p
	 *            the color
	 */
	public PieceChariot(int r, int c, Player p) {
		super(r, c, 'R', p);
	}

	public boolean validMove(Square[][] board, int targetRow, int targetCol) {
		int currentRow = this.getPosRow();
		int currentCol = this.getPosColumn();
		return validMoveVertically(board, currentRow, currentCol, targetRow,
				targetCol)
				|| validMoveHorizontally(board, currentRow, currentCol,
						targetRow, targetCol);
	}

	/**
	 * @param board
	 *            the board on which is played
	 * @param currentRow
	 *            the index of current row
	 * @param currentCol
	 *            the index of current column
	 * @param targetRow
	 *            the index of target row
	 * @param targetCol
	 *            the index of target column
	 * @return true, if the vertically move is valid, false, if not
	 */
	private boolean validMoveVertically(Square[][] board, int currentRow,
			int currentCol, int targetRow, int targetCol) {
		int diffRow = currentRow - targetRow;
		int diffCol = currentCol - targetCol;
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

	/**
	 * @param board
	 *            the board on which is played
	 * @param currentRow
	 *            the index of current row
	 * @param currentCol
	 *            the index of current column
	 * @param targetRow
	 *            the index of target row
	 * @param targetCol
	 *            the index of target column
	 * @return true, if the horizontally move is valid, false, if not
	 */
	private boolean validMoveHorizontally(Square[][] board, int currentRow,
			int currentCol, int targetRow, int targetCol) {
		int diffRow = currentRow - targetRow;
		int diffCol = currentCol - targetCol;
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

	public String getPieceIcon() {
		if (this.getPlayer() == Player.RED) {
			return RR;
		} else {
			return BR;
		}
	}
}
