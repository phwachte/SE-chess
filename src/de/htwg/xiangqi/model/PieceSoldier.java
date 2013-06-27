package de.htwg.xiangqi.model;

/**
 * class PieceSoldier represents the piece soldier
 * 
 * @author P. Wachter
 * 
 */
public class PieceSoldier extends Piece {

	private static final String RS = "images\\rs.png";
	private static final String BS = "images\\bs.png";

	/**
	 * constructor for piece soldier
	 * 
	 * @param r
	 *            the index of the row
	 * @param c
	 *            the index of the column
	 * @param p
	 *            the color
	 */
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

	/**
	 * check, if the move is valid for a red soldier
	 * 
	 * @param currentRow
	 *            the index of the current row
	 * @param diffRow
	 *            the difference of the index of current row and target row
	 * @param diffCol
	 *            the difference of the index of current column and target
	 *            column
	 * @return true, if the move is valid, false, if not
	 */
	private boolean validMoveRedS(int currentRow, int diffRow, int diffCol) {
		if (this.inRedHalf(currentRow)) {
			return diffRow == 1 && diffCol == 0;
		} else {
			return validMoveHorizontally(diffRow, diffCol) || diffCol == 0
					&& diffRow == 1;
		}
	}

	/**
	 * check, if the move is valid for a black soldier
	 * 
	 * @param currentRow
	 *            the index of the current row
	 * @param diffRow
	 *            the difference of the index of current row and target row
	 * @param diffCol
	 *            the difference of the index of current column and target
	 *            column
	 * @return true, if the move is valid, false, if not
	 */
	private boolean validMoveBlackS(int currentRow, int diffRow, int diffCol) {
		if (this.inBlackHalf(currentRow)) {
			return diffRow == -1 && diffCol == 0;
		} else {
			return validMoveHorizontally(diffRow, diffCol) || diffCol == 0
					&& diffRow == -1;
		}
	}

	/**
	 * @param diffRow
	 *            the difference of the index of current row and target row
	 * @param diffCol
	 *            the difference of the index of current column and target
	 *            column
	 * @return true, if the horizontally move is valid, false, if not
	 */
	private boolean validMoveHorizontally(int diffRow, int diffCol) {
		return diffRow == 0 && (diffCol == -1 || diffCol == 1);
	}

	public String getPieceIcon() {
		if (this.getPlayer() == Player.RED) {
			return RS;
		} else {
			return BS;
		}
	}

}