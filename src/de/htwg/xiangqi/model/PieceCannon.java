package de.htwg.xiangqi.model;

/**
 * class PieceCannon represents the piece cannon
 * 
 * @author P. Wachter
 * 
 */
public class PieceCannon extends Piece {

	private static final String RC = "images\\rc.png";
	private static final String BC = "images\\bc.png";

	/**
	 * constructor for piece cannon
	 * 
	 * @param r
	 *            the index of the row
	 * @param c
	 *            the index of the column
	 * @param p
	 *            the color
	 */
	public PieceCannon(int r, int c, Player p) {
		super(r, c, 'C', p);
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
	 * @param blockerCount
	 *            the count of pieces between current point and target point
	 * @return true, if blockerCount equals one, false, if not
	 */
	private boolean existingScreen(int blockerCount) {
		return blockerCount == 1;
	}

	/**
	 * @param piece
	 *            the piece on target point
	 * @return true, if the piece on target is an enemy piece, false, if not
	 */
	private boolean enemyOnTarget(Piece piece) {
		return this.getPlayer() != piece.getPlayer();
	}

	/**
	 * @param board
	 *            the board on which is played
	 * @param targetRow
	 *            the index of the target row
	 * @param targetCol
	 *            the index of the target column
	 * @param blockerCount
	 *            the count of pieces between current point and target point
	 * @return return true, if there is a screen and the piece on target point
	 *         is an enemy piece
	 */
	private boolean validCaptureMove(Square[][] board, int targetRow,
			int targetCol, int blockerCount) {
		if (board[targetRow][targetCol].occupiedPoint()) {
			Piece piece = board[targetRow][targetCol].getPiece();
			return existingScreen(blockerCount) && enemyOnTarget(piece);
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
	 * @return true, if the vertically move is valid, false, if not
	 */
	private boolean validMoveVertically(Square[][] board, int currentRow,
			int currentCol, int targetRow, int targetCol) {
		int diffRow = currentRow - targetRow;
		int diffCol = currentCol - targetCol;
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

	public String getPieceIcon() {
		if (this.getPlayer() == Player.RED) {
			return RC;
		} else {
			return BC;
		}
	}
}