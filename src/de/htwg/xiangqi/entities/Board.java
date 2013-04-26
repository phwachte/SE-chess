package de.htwg.xiangqi.entities;

public class Board {

	private Square[][] board;
	private static final int ROW = 10;
	private static final int COLUMN = 9;

	public Board() {
		this.board = new Square[ROW][COLUMN];
	}

	public Square[][] getBoard() {
		return this.board;
	}
}
