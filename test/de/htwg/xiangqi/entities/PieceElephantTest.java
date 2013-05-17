package de.htwg.xiangqi.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.xiangqi.entities.Piece.Player;

public class PieceElephantTest {

	Board b;
	Square[][] board;
	
	@Before
	public void setUp() {
		b = new Board();
		board = b.getBoard();
		b.fillBoard();
	}
	
	@Test
	public void testValidMove() {
		board[7][4] = new Square(new PieceElephant(7, 4, Player.RED));
		board[6][5] = new Square(new PieceElephant(6, 5, Player.RED));
		assertEquals(true, board[7][4].getPiece().validMove(board, 5, 2));
		assertEquals(true, board[7][4].getPiece().validMove(board, 9, 6));
		assertEquals(false, board[7][4].getPiece().validMove(board, 5, 6));
		assertEquals(false, board[7][4].getPiece().validMove(board, 7, 4));
		assertEquals(false, board[7][4].getPiece().validMove(board, 5, 8));
		assertEquals(false, board[7][4].getPiece().validMove(board, 4, 5));
		
		board[2][4] = new Square(new PieceElephant(2, 4, Player.BLACK));
		assertEquals(true, board[2][4].getPiece().validMove(board, 0, 2));
		assertEquals(false, board[2][4].getPiece().validMove(board, 5, 3));
	}

}
