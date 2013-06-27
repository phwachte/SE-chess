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

	/**
	 * @param board
	 *            the board on which is played
	 * @param targetRow
	 *            the index of the target row
	 * @param targetCol
	 *            the index of the target column
	 * @return true, if the move is valid, false, if not
	 */
	public boolean validMove(Square[][] board, int targetRow, int targetCol) {
		if (this.getPlayer() == Player.RED) {
			return validMoveRedG(targetRow, targetCol);
		} else {
			return validMoveBlackG(targetRow, targetCol);
		}
	}

	private boolean validMoveRedG(int targetRow, int targetCol) {
		if (!this.inRedPalace(targetRow, targetCol)) {
			return false;
		}
		return validMoveG(targetRow, targetCol);
	}

	private boolean validMoveBlackG(int targetRow, int targetCol) {
		if (!this.inBlackPalace(targetRow, targetCol)) {
			return false;
		}
		return validMoveG(targetRow, targetCol);
	}

	private boolean validMoveG(int targetRow, int targetCol) {
		int currentRow = this.getPosRow();
		int currentCol = this.getPosColumn();
		int diffRow = currentRow - targetRow;
		int diffCol = currentCol - targetCol;
		return validMoveVertically(diffRow, diffCol)
				|| validMoveHorizontally(diffRow, diffCol);
	}

	private boolean validMoveVertically(int diffRow, int diffCol) {
		return (diffRow == -1 || diffRow == 1) && diffCol == 0;
	}

	private boolean validMoveHorizontally(int diffRow, int diffCol) {
		return diffRow == 0 && (diffCol == -1 || diffCol == 1);
	}

	/**
	 * @return the string which references the icon of the piece
	 */
	public String getPieceIcon() {
		if (this.getPlayer() == Player.RED) {
			return RG;
		} else {
			return BG;
		}
	}
}
