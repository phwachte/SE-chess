package de.htwg.xiangqi.controller;

import de.htwg.xiangqi.entities.*;
import de.htwg.xiangqi.entities.Piece.Player;

public final class StartBoard {
	
	private StartBoard() {
	}

	private static final int ROW = 10;
	private static final int COLUMN = 9;
	private static final int ZERO = 0;
	private static final int ONE = 1;
	private static final int TWO = 2;
	private static final int THREE = 3;
	private static final int FOUR = 4;
	private static final int FIVE = 5;
	private static final int SIX = 6;
	private static final int SEVEN = 7;
	private static final int EIGHT = 8;
	private static final int NINE = 9;

	public static void setPiecesRed(Square[][] board) {
		board[NINE][FOUR] = new Square(new PieceGeneral(NINE, FOUR, Player.RED));
		board[NINE][THREE] = new Square(new PieceAdvisor(NINE, THREE, Player.RED));
		board[NINE][FIVE] = new Square(new PieceAdvisor(NINE, FIVE, Player.RED));
		board[NINE][TWO] = new Square(new PieceElephant(NINE, TWO, Player.RED));
		board[NINE][SIX] = new Square(new PieceElephant(NINE, SIX, Player.RED));
		board[NINE][ONE] = new Square(new PieceHorse(NINE, ONE, Player.RED));
		board[NINE][SEVEN] = new Square(new PieceHorse(NINE, SEVEN, Player.RED));
		board[NINE][ZERO] = new Square(new PieceChariot(NINE, ZERO, Player.RED));
		board[NINE][EIGHT] = new Square(new PieceChariot(NINE, EIGHT, Player.RED));
		board[SEVEN][ONE] = new Square(new PieceCannon(SEVEN, ONE, Player.RED));
		board[SEVEN][SEVEN] = new Square(new PieceCannon(SEVEN, SEVEN, Player.RED));
		board[SIX][ZERO] = new Square(new PieceSoldier(SIX, ZERO, Player.RED));
		board[SIX][TWO] = new Square(new PieceSoldier(SIX, TWO, Player.RED));
		board[SIX][FOUR] = new Square(new PieceSoldier(SIX, FOUR, Player.RED));
		board[SIX][SIX] = new Square(new PieceSoldier(SIX, SIX, Player.RED));
		board[SIX][EIGHT] = new Square(new PieceSoldier(SIX, EIGHT, Player.RED));
	}

	public static void setPiecesBlack(Square[][] board) {
		board[ZERO][FOUR] = new Square(new PieceGeneral(ZERO, FOUR, Player.BLACK));
		board[ZERO][THREE] = new Square(new PieceAdvisor(ZERO, THREE, Player.BLACK));
		board[ZERO][FIVE] = new Square(new PieceAdvisor(ZERO, FIVE, Player.BLACK));
		board[ZERO][TWO] = new Square(new PieceElephant(ZERO, TWO, Player.BLACK));
		board[ZERO][SIX] = new Square(new PieceElephant(ZERO, SIX, Player.BLACK));
		board[ZERO][ONE] = new Square(new PieceHorse(ZERO, ONE, Player.BLACK));
		board[ZERO][SEVEN] = new Square(new PieceHorse(ZERO, SEVEN, Player.BLACK));
		board[ZERO][ZERO] = new Square(new PieceChariot(ZERO, ZERO, Player.BLACK));
		board[ZERO][EIGHT] = new Square(new PieceChariot(ZERO, EIGHT, Player.BLACK));
		board[TWO][ONE] = new Square(new PieceCannon(TWO, ONE, Player.BLACK));
		board[TWO][SEVEN] = new Square(new PieceCannon(TWO, SEVEN, Player.BLACK));
		board[THREE][ZERO] = new Square(new PieceSoldier(THREE, ZERO, Player.BLACK));
		board[THREE][TWO] = new Square(new PieceSoldier(THREE, TWO, Player.BLACK));
		board[THREE][FOUR] = new Square(new PieceSoldier(THREE, FOUR, Player.BLACK));
		board[THREE][SIX] = new Square(new PieceSoldier(THREE, SIX, Player.BLACK));
		board[THREE][EIGHT] = new Square(new PieceSoldier(THREE, EIGHT, Player.BLACK));
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
