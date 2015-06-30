package de.htwg.xiangqi.model;

/**
 * class Piece represents the piece
 * 
 * @author P. Wachter
 * 
 */
public abstract class Piece implements IPiece, Cloneable {

	private static final int ZERO = 0;
	private static final int TWO = 2;
	private static final int THREE = 3;
	private static final int FOUR = 4;
	private static final int FIVE = 5;
	private static final int SEVEN = 7;
	private static final int NINE = 9;
	private int row;
	private int column;
	private final char pieceType;
	private final Player player;
	private boolean isCaptured;

	/**
	 * an identifying color for the players
	 */
	public enum Player {
		RED, BLACK
	}

	/**
	 * protected constructor
	 * 
	 * @param r
	 *            the index of the row
	 * @param c
	 *            the index of the column
	 * @param pt
	 *            the identifying character
	 * @param p
	 *            the color
	 */
	protected Piece(int r, int c, char pt, Player p) {
		this.row = r;
		this.column = c;
		this.pieceType = pt;
		this.player = p;
		this.isCaptured = false;
	}

	/**
	 * @return the index of the row
	 */
	public int getPosRow() {
		return this.row;
	}

	/**
	 * @return the index of the column
	 */
	public int getPosColumn() {
		return this.column;
	}

	/**
	 * @param r
	 *            the index of the row
	 * @param c
	 *            the index of the column
	 */
	public void setPosition(int r, int c) {
		this.row = r;
		this.column = c;
	}

	/**
	 * @return the color of the pieces
	 */
	public Player getPlayer() {
		return this.player;
	}

	/**
	 * @return the identifying character of the piece
	 */
	public char getPieceType() {
		return this.pieceType;
	}

	/**
	 * setter for the capture status
	 * 
	 * @param b
	 *            true, if the piece is captured, false, if not
	 */
	public void setIsCaptured(boolean b) {
		this.isCaptured = b;
	}

	/**
	 * getter for the capture status
	 * 
	 * @return true, if the piece is captured, false, if not
	 */
	public boolean getIsCaptured() {
		return this.isCaptured;
	}

	/**
	 * @param row
	 *            the index of the row
	 * @param col
	 *            the index of the column
	 * @return true, if the point is in red palace, false, if not
	 */
	public boolean inRedPalace(int row, int col) {
		return col >= THREE && col <= FIVE && row >= SEVEN && row <= NINE;
	}

	/**
	 * @param row
	 *            the index of the row
	 * @param col
	 *            the index of the column
	 * @return true, if the point is in black palace, false, if not
	 */
	public boolean inBlackPalace(int row, int col) {
		return col >= THREE && col <= FIVE && row >= ZERO && row <= TWO;
	}

	/**
	 * @param row
	 *            the index of the row
	 * @return true, if the point is in red half, false, if not
	 */
	public boolean inRedHalf(int row) {
		return row >= FIVE;
	}

	/**
	 * @param row
	 *            the index of the row
	 * @return true, if the point is in black half, false, if not
	 */
	public boolean inBlackHalf(int row) {
		return row <= FOUR;
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
	public abstract boolean validMove(Square[][] board, int targetRow,
			int targetCol);

	/**
	 * @return the string which references the icon of the piece
	 */
	public abstract String getPieceIcon();
	
	@Override
	public abstract Object clone() throws CloneNotSupportedException;
}
