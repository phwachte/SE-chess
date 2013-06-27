package de.htwg.xiangqi.controller;

import de.htwg.util.observer.IObservable;
import de.htwg.xiangqi.model.Square;

/**
 * IBoardManager is an interface for the class Piece
 * 
 * @author P. Wachter
 * 
 */
public interface IBoardManager extends IObservable {

	/**
	 * check, if the move is valid, create an error message, if not
	 * 
	 * @param chosenRow
	 *            the index of the chosen row
	 * @param chosenCol
	 *            the index of the chosen column
	 * @param targetRow
	 *            the index of the target row
	 * @param targetCol
	 *            the index of the target column
	 * @return true, if game is finished, false, if not
	 */
	boolean inputMove(String values);

	/**
	 * @return the error message
	 */
	String getMessage();

	/**
	 * @return the board on which is played
	 */
	Square[][] getBoard();

	/**
	 * @return the count of valid moves
	 */
	int getMoveCounter();

	/**
	 * increase the count of valid moves
	 */
	void increaseMoveCounter();

	/**
	 * @return the identifying number of the player who is turning next
	 */
	int getPlayersTurn();

	/**
	 * initialize the board
	 */
	void setStartBoard();

	/**
	 * @return the identifying character of the general, who is captured, if no
	 *         general is captured the returned character is '-'
	 */
	char isCheckmate();

	/**
	 * @return the string which represents the winner message
	 */
	String winnerMessage();

	/**
	 * @param row
	 *            the index of the row
	 * @param col
	 *            the index of the column
	 * @return the string for the tui output
	 */
	String getTUIOutput(int row, int col);

	/**
	 * @param row
	 *            the index of the row
	 * @param col
	 *            the index of the column
	 * @return the string which represents the path of the icon
	 */
	String pieceAtPoint(int row, int col);

}
