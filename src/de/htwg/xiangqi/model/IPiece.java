package de.htwg.xiangqi.model;

import de.htwg.xiangqi.model.Piece.Player;

/**
 * IPiece is an interface for the class Piece
 * 
 * @author P. Wachter
 * 
 */
public interface IPiece {

	/**
	 * @return the index of the row
	 */
	int getPosRow();

	/**
	 * @return the index of the column
	 */
	int getPosColumn();

	/**
	 * @param r
	 *            the index of the row
	 * @param c
	 *            the index of the column
	 */
	void setPosition(int r, int c);

	/**
	 * @return the color of the pieces
	 */
	Player getPlayer();

	/**
	 * @return the identifying character of the piece
	 */
	char getPieceType();

	/**
	 * setter for the capture status
	 * 
	 * @param b
	 *            true, if the piece is captured, false, if not
	 */
	void setIsCaptured(boolean b);

	/**
	 * getter for the capture status
	 * 
	 * @return true, if the piece is captured, false, if not
	 */
	boolean getIsCaptured();

	/**
	 * @param row
	 *            the index of the row
	 * @param col
	 *            the index of the column
	 * @return true, if the point is in red palace, false, if not
	 */
	boolean inRedPalace(int row, int col);

	/**
	 * @param row
	 *            the index of the row
	 * @param col
	 *            the index of the column
	 * @return true, if the point is in black palace, false, if not
	 */
	boolean inBlackPalace(int row, int col);

	/**
	 * @param row
	 *            the index of the row
	 * @return true, if the point is in red half, false, if not
	 */
	boolean inRedHalf(int row);

	/**
	 * @param row
	 *            the index of the row
	 * @return true, if the point is in black half, false, if not
	 */
	boolean inBlackHalf(int row);

	/**
	 * @param board
	 *            the board on which is played
	 * @param targetRow
	 *            the index of the target row
	 * @param targetCol
	 *            the index of the target column
	 * @return true, if the move is valid, false, if not
	 */
	boolean validMove(Square[][] board, int targetRow, int targetCol);

	/**
	 * @return the string which references the icon of the piece
	 */
	String getPieceIcon();

}
