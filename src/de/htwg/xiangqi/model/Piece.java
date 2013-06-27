package de.htwg.xiangqi.model;

/**
 * class Piece represents the piece
 * 
 * @author P. Wachter
 * 
 */
public abstract class Piece implements IPiece {

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

	public int getPosRow() {
		return this.row;
	}

	public int getPosColumn() {
		return this.column;
	}

	public void setPosition(int r, int c) {
		this.row = r;
		this.column = c;
	}

	public Player getPlayer() {
		return this.player;
	}

	public char getPieceType() {
		return this.pieceType;
	}

	public void setIsCaptured(boolean b) {
		this.isCaptured = b;
	}

	public boolean getIsCaptured() {
		return this.isCaptured;
	}

	public boolean inRedPalace(int row, int col) {
		return col >= THREE && col <= FIVE && row >= SEVEN && row <= NINE;
	}

	public boolean inBlackPalace(int row, int col) {
		return col >= THREE && col <= FIVE && row >= ZERO && row <= TWO;
	}

	public boolean inRedHalf(int row) {
		return row >= FIVE;
	}

	public boolean inBlackHalf(int row) {
		return row <= FOUR;
	}

	public abstract boolean validMove(Square[][] board, int targetRow,
			int targetCol);

	public abstract String getPieceIcon();
}
