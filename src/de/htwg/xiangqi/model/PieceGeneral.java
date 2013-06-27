package de.htwg.xiangqi.model;

/**
 * class PieceGeneral represents the piece general
 * 
 * @author P. Wachter
 * 
 */
public class PieceGeneral extends Piece {

	private static final String RG = "images\\rg.png";
	private static final String BG = "images\\bg.png";

	/**
	 * constructor for piece general
	 * 
	 * @param r
	 *            the index of the row
	 * @param c
	 *            the index of the column
	 * @param p
	 *            the color
	 */
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

	/**
	 * check, if the move is valid for a red general
	 * 
	 * @param targetRow
	 *            the index of the target row
	 * @param targetCol
	 *            the index of the target column
	 * @return true, if the move is valid, false, if not
	 */
	private boolean validMoveRedG(int targetRow, int targetCol) {
		if (!this.inRedPalace(targetRow, targetCol)) {
			return false;
		}
		return validMoveG(targetRow, targetCol);
	}

	/**
	 * check, if the move is valid for a black general
	 * 
	 * @param targetRow
	 *            the index of the target row
	 * @param targetCol
	 *            the index of the target column
	 * @return true, if the move is valid, false, if not
	 */
	private boolean validMoveBlackG(int targetRow, int targetCol) {
		if (!this.inBlackPalace(targetRow, targetCol)) {
			return false;
		}
		return validMoveG(targetRow, targetCol);
	}

	/**
	 * @param targetRow
	 *            the index of the target row
	 * @param targetCol
	 *            the index of the target column
	 * @return true, if the move is valid, false, if not
	 */
	private boolean validMoveG(int targetRow, int targetCol) {
		int currentRow = this.getPosRow();
		int currentCol = this.getPosColumn();
		int diffRow = currentRow - targetRow;
		int diffCol = currentCol - targetCol;
		return validMoveVertically(diffRow, diffCol)
				|| validMoveHorizontally(diffRow, diffCol);
	}

	/**
	 * @param diffRow
	 *            the difference of the index of current row and target row
	 * @param diffCol
	 *            the difference of the index of current column and target
	 *            column
	 * @return true, if the vertically move is valid, false, if not
	 */
	private boolean validMoveVertically(int diffRow, int diffCol) {
		return (diffRow == -1 || diffRow == 1) && diffCol == 0;
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
			return RG;
		} else {
			return BG;
		}
	}
}
