package de.htwg.xiangqi.model;

/**
 * class Square represents a square
 * 
 * @author P. Wachter
 * 
 */
public class Square {

	private Piece piece;

	/**
	 * create a square with piece on it
	 * 
	 * @param p
	 *            the piece
	 */
	public Square(Piece p) {
		this.piece = p;
	}

	/**
	 * set a piece to the square
	 * 
	 * @param p
	 *            the piece
	 */
	public void setPiece(Piece p) {
		this.piece = p;
	}

	/**
	 * get the piece, which is set to tht square
	 * 
	 * @return the piece
	 */
	public Piece getPiece() {
		return this.piece;
	}

	/**
	 * check, if a piece is set to the square
	 * 
	 * @return true, if there is a piece, false, if not
	 */
	public boolean occupiedPoint() {
		return this.piece != null;
	}

}
