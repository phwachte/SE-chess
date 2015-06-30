package de.htwg.xiangqi.model;

/**
 * class PieceAdvisor represents the piece advisor
 * 
 * @author P. Wachter
 * 
 */
public class PieceAdvisor extends Piece {

	private static final String RA = "images\\ra.png";
	private static final String BA = "images\\ba.png";

	/**
	 * constructor for piece advisor
	 * 
	 * @param r
	 *            the index of the row
	 * @param c
	 *            the index of the column
	 * @param p
	 *            the color
	 */
	public PieceAdvisor(int r, int c, Player p) {
		super(r, c, 'A', p);
	}

	/**
	 * @param board
	 *            the board on which is played
	 * @param targetRow
	 *            the index of the target row
	 * @param targetCol
	 *            the index of the target column
	 * @return true, if the move is valid, false, if not
	 */
	public boolean validMove(Square[][] board, int row, int col) {
		if (this.getPlayer() == Player.RED) {
			return validMoveRedA(row, col);
		} else {
			return validMoveBlackA(row, col);
		}
	}

	private boolean validMoveRedA(int targetRow, int targetCol) {
		if (!this.inRedPalace(targetRow, targetCol)) {
			return false;
		}
		return validMoveA(targetRow, targetCol);
	}

	private boolean validMoveBlackA(int targetRow, int targetCol) {
		if (!this.inBlackPalace(targetRow, targetCol)) {
			return false;
		}
		return validMoveA(targetRow, targetCol);
	}

	private boolean validMoveA(int targetRow, int targetCol) {
		int currentRow = this.getPosRow();
		int currentCol = this.getPosColumn();
		int diffRow = currentRow - targetRow;
		int diffCol = currentCol - targetCol;
		return (diffRow == -1 || diffRow == 1)
				&& (diffCol == -1 || diffCol == 1);
	}

	/**
	 * @return the string which references the icon of the piece
	 */
	public String getPieceIcon() {
		if (this.getPlayer() == Player.RED) {
			return RA;
		} else {
			return BA;
		}
	}

	@Override
	public Piece clone() {
		PieceAdvisor retVal = new PieceAdvisor(this.getPosRow(), this.getPosColumn(), this.getPlayer());
		retVal.setIsCaptured(this.getIsCaptured());
		return retVal;
	}

}