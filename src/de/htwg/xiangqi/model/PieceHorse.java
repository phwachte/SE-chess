package de.htwg.xiangqi.model;

/**
 * class PieceHorse represents the piece horse
 * 
 * @author P. Wachter
 * 
 */
public class PieceHorse extends Piece {

	private static final String RH = "images\\rh.png";
	private static final String BH = "images\\bh.png";
	private static final int NEG_DIFF = -2;
	private static final int POS_DIFF = 2;

	/**
	 * constructor for piece horse
	 * 
	 * @param r
	 *            the index of the row
	 * @param c
	 *            the index of the column
	 * @param p
	 *            the color
	 */
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

	/**
	 * @param diffRow
	 *            the difference of the index of current row and target row
	 * @param diffCol
	 *            the difference of the index of current column and target
	 *            column
	 * @return true, if the move to the North is valid, false, if not
	 */
	private boolean validMoveNorth(int diffRow, int diffCol) {
		return diffRow == POS_DIFF && (diffCol == 1 || diffCol == -1);
	}

	/**
	 * @param diffRow
	 *            the difference of the index of current row and target row
	 * @param diffCol
	 *            the difference of the index of current column and target
	 *            column
	 * @return true, if the move to the East is valid, false, if not
	 */
	private boolean validMoveEast(int diffRow, int diffCol) {
		return diffCol == NEG_DIFF && (diffRow == 1 || diffRow == -1);
	}

	/**
	 * @param diffRow
	 *            the difference of the index of current row and target row
	 * @param diffCol
	 *            the difference of the index of current column and target
	 *            column
	 * @return true, if the move to the South is valid, false, if not
	 */
	private boolean validMoveSouth(int diffRow, int diffCol) {
		return diffRow == NEG_DIFF && (diffCol == 1 || diffCol == -1);
	}

	/**
	 * @param diffRow
	 *            the difference of the index of current row and target row
	 * @param diffCol
	 *            the difference of the index of current column and target
	 *            column
	 * @return true, if the move to the West is valid, false, if not
	 */
	private boolean validMoveWest(int diffRow, int diffCol) {
		return diffCol == POS_DIFF && (diffRow == 1 || diffRow == -1);
	}

	public String getPieceIcon() {
		if (this.getPlayer() == Player.RED) {
			return RH;
		} else {
			return BH;
		}
	}
}
