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
		int currentRow = this.getPosRow();
		int currentCol = this.getPosColumn();
		return validMoveVertically(board, currentRow, currentCol, targetRow,
				targetCol)
				|| validMoveHorizontally(board, currentRow, currentCol,
						targetRow, targetCol);
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

	/**
	 * @return the string which references the icon of the piece
	 */
	public String getPieceIcon() {
		if (this.getPlayer() == Player.RED) {
			return RC;
		} else {
			return BC;
		}
	}

	@Override
	public Piece clone() throws CloneNotSupportedException{
		PieceCannon retVal = new PieceCannon(this.getPosRow(), this.getPosColumn(), this.getPlayer());
		retVal.setIsCaptured(this.getIsCaptured());
		return retVal;
	}
}