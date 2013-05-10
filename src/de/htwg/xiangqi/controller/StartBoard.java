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

	public static void setPiecesRed(Square[][] board) {
		board[NINE][FOUR] = new Square(new PieceGeneral(NINE, FOUR, Player.RED));
		board[9][3] = new Square(new PieceAdvisor(9, 3, Player.RED));
		board[9][5] = new Square(new PieceAdvisor(9, 5, Player.RED));
		board[9][2] = new Square(new PieceElephant(9, 2, Player.RED));
		board[9][6] = new Square(new PieceElephant(9, 6, Player.RED));
		board[9][1] = new Square(new PieceHorse(9, 1, Player.RED));
		board[9][7] = new Square(new PieceHorse(9, 7, Player.RED));
		board[9][0] = new Square(new PieceChariot(9, 0, Player.RED));
		board[9][8] = new Square(new PieceChariot(9, 8, Player.RED));
		board[7][1] = new Square(new PieceCannon(7, 1, Player.RED));
		board[7][7] = new Square(new PieceCannon(7, 7, Player.RED));
		board[6][0] = new Square(new PieceSoldier(6, 0, Player.RED));
		board[6][2] = new Square(new PieceSoldier(6, 2, Player.RED));
		board[6][4] = new Square(new PieceSoldier(6, 4, Player.RED));
		board[6][6] = new Square(new PieceSoldier(6, 6, Player.RED));
		board[6][8] = new Square(new PieceSoldier(6, 8, Player.RED));
	}

	public static void setPiecesBlack(Square[][] board) {
		board[ZERO][FOUR] = new Square(new PieceGeneral(ZERO, FOUR, Player.BLACK));
		board[0][3] = new Square(new PieceAdvisor(0, 3, Player.BLACK));
		board[0][5] = new Square(new PieceAdvisor(0, 5, Player.BLACK));
		board[0][2] = new Square(new PieceElephant(0, 2, Player.BLACK));
		board[0][6] = new Square(new PieceElephant(0, 6, Player.BLACK));
		board[0][1] = new Square(new PieceHorse(0, 1, Player.BLACK));
		board[0][7] = new Square(new PieceHorse(0, 7, Player.BLACK));
		board[0][0] = new Square(new PieceChariot(0, 0, Player.BLACK));
		board[0][8] = new Square(new PieceChariot(0, 8, Player.BLACK));
		board[2][1] = new Square(new PieceCannon(2, 1, Player.BLACK));
		board[2][7] = new Square(new PieceCannon(2, 7, Player.BLACK));
		board[3][0] = new Square(new PieceSoldier(3, 0, Player.BLACK));
		board[3][2] = new Square(new PieceSoldier(3, 2, Player.BLACK));
		board[3][4] = new Square(new PieceSoldier(3, 4, Player.BLACK));
		board[3][6] = new Square(new PieceSoldier(3, 6, Player.BLACK));
		board[3][8] = new Square(new PieceSoldier(3, 8, Player.BLACK));
	}

	public static void fillBoard(Square[][] board) {
		int i = 0, j;
		while (i < ROW) {
			j = 0;
			while (j < COLUMN) {
				if (board[i][j] == null) {
					board[i][j] = new Square();
				}
				++j;
			}
			++i;
		}
	}
}
