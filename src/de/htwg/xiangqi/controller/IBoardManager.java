package de.htwg.xiangqi.controller;

import java.util.List;

import de.htwg.util.observer.IObservable;
import de.htwg.xiangqi.model.Board;
import de.htwg.xiangqi.persistence.IDataAccessObject;

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
	Board getBoard();

	/**
	 * @return the identifying number of the player who is turning next
	 */
	int getPlayersTurn();

	/**
	 * initialize the board
	 */
	void setStartBoard();
	
	void setBoard(Board b);

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
	
	void saveGame();
	
	List<Board> loadSaveGames();
	
	IDataAccessObject getDAO();
	
	void deleteGame(String id);
	
	void close();

}
