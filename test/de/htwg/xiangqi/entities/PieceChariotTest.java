package de.htwg.xiangqi.entities;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.htwg.xiangqi.entities.Piece.Player;

public class PieceChariotTest {

	Board b;
	Square[][] board;

	@Before
	public void setUp() {
		b = new Board();
		board = b.getBoard();
		b.fillBoard();
	}

	@Test
	public void testValidMoveHorizontally() {
		board[0][4] = new Square(new PieceChariot(0, 4, Player.RED));
		board[0][2] = new Square(new PieceChariot(0, 4, Player.RED));
		assertEquals(true, board[0][4].getPiece().validMove(board, 0, 3));
		assertEquals(true, board[0][4].getPiece().validMove(board, 0, 8));
		assertEquals(false, board[0][4].getPiece().validMove(board, 6, 2));
		assertEquals(false, board[0][4].getPiece().validMove(board, 0, 4));
		assertEquals(false, board[0][4].getPiece().validMove(board, 0, 0));
	}
	
	@Test
	public void testValidMoveVertically() {
		board[4][4] = new Square(new PieceChariot(4, 4, Player.RED));
		board[8][4] = new Square(new PieceChariot(8, 4, Player.RED));
		assertEquals(true, board[4][4].getPiece().validMove(board, 2, 4));
		assertEquals(true, board[4][4].getPiece().validMove(board, 6, 4));
		assertEquals(false, board[4][4].getPiece().validMove(board, 6, 2));
		assertEquals(false, board[4][4].getPiece().validMove(board, 4, 4));
		assertEquals(false, board[4][4].getPiece().validMove(board, 9, 4));
	}
}
