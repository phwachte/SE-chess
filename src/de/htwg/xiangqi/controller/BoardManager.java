package de.htwg.xiangqi.controller;

import de.htwg.xiangqi.entities.Board;
import de.htwg.xiangqi.entities.Square;

public class BoardManager {

	private Square[][] board;
	private int moveCounter;
	private static final int NUMBER_OF_PLAYERS = 2;

	public BoardManager() {
		Board b = new Board();
		this.board = b.getBoard();
		this.moveCounter = 1;
	}

	public Square[][] getBoard() {
		return this.board;
	}

	public int getMoveCounter() {
		return this.moveCounter;
	}

	public void increaseMoveCounter() {
		++this.moveCounter;
	}

	public int getPlayersTurn() {
		return this.moveCounter % NUMBER_OF_PLAYERS;
	}

//	public void printBoard() {
//
//	}

}
