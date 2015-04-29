package de.htwg.xiangqi.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import de.htwg.xiangqi.model.Board;
import de.htwg.xiangqi.model.Piece;
import de.htwg.xiangqi.model.Piece.Player;
import de.htwg.xiangqi.model.PieceChariot;
import de.htwg.xiangqi.model.Square;

public class PieceChariotTest {

	Board b;
	Square[][] board;

	@Before
	public void setUp() {
		b = new Board();
		board = b.getSquareMatrix();
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
	
	@Test
	public void testGetPieceIcon() {
		Piece p = new PieceChariot(0, 0, Player.RED);
		assertNotNull(p.getPieceIcon());
		p = new PieceChariot(0, 0, Player.BLACK);
		assertNotNull(p.getPieceIcon());
	}
}
