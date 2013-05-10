package de.htwg.xiangqi.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import de.htwg.xiangqi.entities.Piece.Player;
import de.htwg.xiangqi.entities.PieceElephant;
import de.htwg.xiangqi.entities.Square;

public class PossibleMoveETest {

	Square[][] board;
	
	@Before
	public void setUp() {
		board = new Square[10][9];
		StartBoard.fillBoard(board);
	}
	
	@Test
	public void testPossibleMoveRedE() {
		assertNotNull(new PossibleMoveE());
		board[7][4] = new Square(new PieceElephant(7, 4, Player.RED));
		board[6][5] = new Square(new PieceElephant(6, 5, Player.RED));
		assertEquals(true, PossibleMoveE.possibleMoveE(board, board[7][4].getPiece(), 5, 2));
		assertEquals(true, PossibleMoveE.possibleMoveE(board, board[7][4].getPiece(), 9, 6));
		assertEquals(false, PossibleMoveE.possibleMoveE(board, board[7][4].getPiece(), 5, 6));
		assertEquals(false, PossibleMoveE.possibleMoveE(board, board[7][4].getPiece(), 7, 4));
		assertEquals(false, PossibleMoveE.possibleMoveE(board, board[7][4].getPiece(), 5, 8));
		assertEquals(false, PossibleMoveE.possibleMoveE(board, board[7][4].getPiece(), 4, 5));
	}

	@Test
	public void testPossibleMoveBlackA() {
		board[2][4] = new Square(new PieceElephant(2, 4, Player.BLACK));
		assertEquals(true, PossibleMoveE.possibleMoveE(board, board[2][4].getPiece(), 0, 2));
		assertEquals(false, PossibleMoveE.possibleMoveE(board, board[2][4].getPiece(), 5, 3));
	}
}
