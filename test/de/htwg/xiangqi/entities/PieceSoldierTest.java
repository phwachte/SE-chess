package de.htwg.xiangqi.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.xiangqi.entities.Piece.Player;

public class PieceSoldierTest {

	Board b;
	Square[][] board;
	
	@Before
	public void setUp() {
		b = new Board();
		board = b.getBoard();
		b.fillBoard();
	}
	
	@Test
	public void testValidMoveRed() {
		board[6][4] = new Square(new PieceSoldier(6, 4, Player.RED));
		assertEquals(true, board[6][4].getPiece().validMove(board, 5, 4));
		assertEquals(false, board[6][4].getPiece().validMove(board, 7, 4));
		assertEquals(false, board[6][4].getPiece().validMove(board, 5, 5));
		board[4][4] = new Square(new PieceSoldier(4, 4, Player.RED));
		assertEquals(true, board[4][4].getPiece().validMove(board, 4, 5));
		assertEquals(true, board[4][4].getPiece().validMove(board, 4, 3));
		assertEquals(true, board[4][4].getPiece().validMove(board, 3, 4));
		assertEquals(false, board[4][4].getPiece().validMove(board, 4, 4));
		assertEquals(false, board[4][4].getPiece().validMove(board, 3, 3));
		assertEquals(false, board[4][4].getPiece().validMove(board, 5, 4));
	}
	
	@Test
	public void testValidMoveBlack() {
		board[3][4] = new Square(new PieceSoldier(3, 4, Player.BLACK));
		assertEquals(true, board[3][4].getPiece().validMove(board, 4, 4));
		assertEquals(false, board[3][4].getPiece().validMove(board, 2, 4));
		assertEquals(false, board[3][4].getPiece().validMove(board, 4, 3));
		board[5][4] = new Square(new PieceSoldier(5, 4, Player.BLACK));
		assertEquals(true, board[5][4].getPiece().validMove(board, 5, 5));
		assertEquals(true, board[5][4].getPiece().validMove(board, 6, 4));
		assertEquals(false, board[5][4].getPiece().validMove(board, 5, 4));
		assertEquals(false, board[5][4].getPiece().validMove(board, 6, 3));
		assertEquals(false, board[5][4].getPiece().validMove(board, 4, 4));
	}

}
