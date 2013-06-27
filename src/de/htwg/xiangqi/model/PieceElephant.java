package de.htwg.xiangqi.model;

/**
 * class PieceElephant represents the piece elephant
 * 
 * @author P. Wachter
 * 
 */
public class PieceElephant extends Piece {

	private static final String RE = "images\\re.png";
	private static final String BE = "images\\be.png";
	private static final int NEG_DIV = -2;
	private static final int POS_DIV = 2;

	/**
	 * constructor for piece elephant
	 * 
	 * @param r
	 *            the index of the row
	 * @param c
	 *            the index of the column
	 * @param p
	 *            the color
	 */
	public PieceElephant(int r, int c, Player p) {
		super(r, c, 'E', p);
	}

	public boolean validMove(Square[][] board, int targetRow, int targetCol) {
		if (this.getPlayer() == Player.RED) {
			return validMoveRedE(board, targetRow, targetCol);
		} else {
			return validMoveBlackE(board, targetRow, targetCol);
		}
	}

	/**
	 * check, if the move is valid for a red elephant
	 * 
	 * @param board
	 *            the board on which is played
	 * @param @param targetRow the index of the target row
	 * @param targetCol
	 *            the index of the target column
	 * @return true, if the move is valid, false, if not
	 */
	private boolean validMoveRedE(Square[][] board, int targetRow, int targetCol) {
		if (this.inBlackHalf(targetRow)) {
			return false;
		} else {
			return validMoveE(board, targetRow, targetCol);
		}
	}

	/**
	 * check, if the move is valid for a black elephant
	 * 
	 * @param board
	 *            the board on which is played
	 * @param @param targetRow the index of the target row
	 * @param targetCol
	 *            the index of the target column
	 * @return true, if the move is valid, false, if not
	 */
	private boolean validMoveBlackE(Square[][] board, int targetRow,
			int targetCol) {
		if (this.inRedHalf(targetRow)) {
			return false;
		} else {
			return validMoveE(board, targetRow, targetCol);
		}
	}

	/**
	 * @param board
	 *            the board on which is played
	 * @param @param targetRow the index of the target row
	 * @param targetCol
	 *            the index of the target column
	 * @return true, if the move is valid, false, if not
	 */
	private boolean validMoveE(Square[][] board, int targetRow, int targetCol) {
		int currentRow = this.getPosRow();
		int currentCol = this.getPosColumn();
		int diffRow = currentRow - targetRow;
		int diffCol = currentCol - targetCol;
		if ((diffRow == NEG_DIV || diffRow == POS_DIV)
				&& (diffCol == NEG_DIV || diffCol == POS_DIV)) {
			return !board[currentRow - diffRow / POS_DIV][currentCol - diffCol
					/ POS_DIV].occupiedPoint();
		} else {
			return false;
		}
	}

	public String getPieceIcon() {
		if (this.getPlayer() == Player.RED) {
			return RE;
		} else {
			return BE;
		}
	}

}