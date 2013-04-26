package de.htwg.xiangqi.controller;

import de.htwg.xiangqi.entities.Board;
import de.htwg.xiangqi.entities.Square;

public class BoardManager {

	private Square[][] board;
	private boolean redTurn = true;

	public BoardManager() {
		Board b = new Board();
		this.board = b.getBoard();
	}

	public Square[][] getBoard() {
		return this.board;
	}

	public void setRedTurn(boolean turn) {
		this.redTurn = turn;
	}

	public boolean getRedTurn() {
		return this.redTurn;
	}
}
