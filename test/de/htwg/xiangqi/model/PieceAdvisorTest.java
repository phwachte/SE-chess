package de.htwg.xiangqi.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.xiangqi.model.Board;
import de.htwg.xiangqi.model.Piece;
import de.htwg.xiangqi.model.PieceAdvisor;
import de.htwg.xiangqi.model.Square;
import de.htwg.xiangqi.model.Piece.Player;

public class PieceAdvisorTest {
	
	Board b;
	Square[][] board;
	
	@Before
	public void setUp() {
		b = new Board();
		board = b.getSquareMatrix();
		b.fillBoard();
	}
	
	@Test
	public void testValidMove() {
		board[8][4] = new Square(new PieceAdvisor(8, 4, Player.RED));
		assertEquals(true, board[8][4].getPiece().validMove(board, 7, 3));
		assertEquals(true, board[8][4].getPiece().validMove(board, 7, 5));
		assertEquals(true, board[8][4].getPiece().validMove(board, 9, 3));
		assertEquals(false, board[8][4].getPiece().validMove(board, 8, 4));
		assertEquals(false, board[8][4].getPiece().validMove(board, 7, 4));
		assertEquals(false, board[8][4].getPiece().validMove(board, 9, 2));
		
		board[1][4] = new Square(new PieceAdvisor(1, 4, Player.BLACK));
		assertEquals(true, board[1][4].getPiece().validMove(board, 0, 3));
		assertEquals(false, board[1][4].getPiece().validMove(board, 2, 6));
	}
	
	@Test
	public void testGetPieceIcon() {
		Piece p = new PieceAdvisor(0, 0, Player.RED);
		assertNotNull(p.getPieceIcon());
		p = new PieceAdvisor(0, 0, Player.BLACK);
		assertNotNull(p.getPieceIcon());
	}

}
