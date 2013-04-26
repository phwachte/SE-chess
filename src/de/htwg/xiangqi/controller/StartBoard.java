package de.htwg.xiangqi.controller;

import de.htwg.xiangqi.entities.*;
import de.htwg.xiangqi.entities.Piece.Player;

public final class StartBoard {

	private StartBoard() {
	}

	private static final int ROW = 10;
	private static final int COLUMN = 9;
	private static final int ZERO = 0;
	private static final int FOUR = 4;
	private static final int NINE = 9;

	public static void setPiecesRed(Board b) {
		Square[][] board = b.getBoard();
		board[ZERO][FOUR] = new Square(new PieceGeneral(ZERO, FOUR, Player.RED));
	}

	public static void setPiecesBlack(Board b) {
		Square[][] board = b.getBoard();
		board[NINE][FOUR] = new Square(new PieceGeneral(NINE, FOUR,
				Player.BLACK));
	}

	public static void fillBoard(Board b) {
		Square[][] board = b.getBoard();
		int i = 0;
		int j;
		while (i < ROW) {
			j = 0;
			while (j < COLUMN) {
				if (board[i][j] == null) {
					board[i][j] = new Square(null);
				}
				++j;
			}
			++i;
		}
	}
}
