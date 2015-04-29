package de.htwg.xiangqi.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.htwg.xiangqi.model.Board;
import de.htwg.xiangqi.model.Piece;
import de.htwg.xiangqi.model.Piece.Player;
import de.htwg.xiangqi.model.PieceCannon;
import de.htwg.xiangqi.model.Square;

public class PieceCannonTest {

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
		board[7][4] = new Square(new PieceCannon(7, 4, Player.RED));
		board[4][4] = new Square(new PieceCannon(7, 4, Player.BLACK));
		assertTrue(board[7][4].getPiece().validMove(board, 7, 0));
		assertEquals(true, board[7][4].getPiece().validMove(board, 7, 8));
		assertEquals(false, board[7][4].getPiece().validMove(board, 6, 0));
		assertEquals(false, board[7][4].getPiece().validMove(board, 8, 7));
		assertEquals(false, board[7][4].getPiece().validMove(board, 9, 1));
		assertEquals(false, board[7][4].getPiece().validMove(board, 3, 7));
		assertEquals(false, board[7][4].getPiece().validMove(board, 4, 4));
	}

	@Test
	public void testValidMoveVertically() {
		board[7][4] = new Square(new PieceCannon(7, 4, Player.RED));
		board[7][0] = new Square(new PieceCannon(7, 0, Player.RED));
		board[7][8] = new Square(new PieceCannon(7, 8, Player.BLACK));
		assertEquals(true, board[7][4].getPiece().validMove(board, 3, 4));
		assertEquals(true, board[7][4].getPiece().validMove(board, 9, 4));
		assertEquals(false, board[7][4].getPiece().validMove(board, 7, 4));
		assertEquals(false, board[7][4].getPiece().validMove(board, 7, 8));
		assertEquals(false, board[7][4].getPiece().validMove(board, 7, 0));
	}

	@Test
	public void testValidCaptureMove() {
		board[5][4] = new Square(new PieceCannon(5, 4, Player.RED));
		board[3][4] = new Square(new PieceCannon(3, 4, Player.RED));
		board[7][4] = new Square(new PieceCannon(7, 4, Player.RED));
		board[0][4] = new Square(new PieceCannon(0, 4, Player.RED));
		board[9][4] = new Square(new PieceCannon(9, 4, Player.BLACK));
		assertEquals(false, board[5][4].getPiece().validMove(board, 1, 4));
		assertEquals(false, board[5][4].getPiece().validMove(board, 0, 4));
		assertEquals(true, board[5][4].getPiece().validMove(board, 9, 4));
		board[5][8] = new Square(new PieceCannon(5, 8, Player.BLACK));
		board[5][6] = new Square(new PieceCannon(5, 6, Player.RED));
		board[5][0] = new Square(new PieceCannon(5, 6, Player.BLACK));
		board[5][1] = new Square(new PieceCannon(5, 6, Player.BLACK));
		board[5][2] = new Square(new PieceCannon(5, 6, Player.BLACK));
		assertEquals(true, board[5][4].getPiece().validMove(board, 5, 8));
		assertEquals(false, board[5][4].getPiece().validMove(board, 5, 0));
	}

	@Test
	public void testExistingScreen() {
		board[5][4] = new Square(new PieceCannon(5, 4, Player.RED));
		board[3][4] = new Square(new PieceCannon(3, 4, Player.RED));
		board[2][4] = new Square(new PieceCannon(7, 4, Player.RED));
		board[0][4] = new Square(new PieceCannon(9, 4, Player.BLACK));
		assertEquals(false, board[5][4].getPiece().validMove(board, 1, 4));
		assertEquals(false, board[5][4].getPiece().validMove(board, 0, 4));
	}

	@Test
	public void testGetPieceIcon() {
		Piece p = new PieceCannon(0, 0, Player.RED);
		assertNotNull(p.getPieceIcon());
		p = new PieceCannon(0, 0, Player.BLACK);
		assertNotNull(p.getPieceIcon());
	}
}
