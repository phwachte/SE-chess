package de.htwg.xiangqi.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.xiangqi.model.Board;
import de.htwg.xiangqi.model.Piece;
import de.htwg.xiangqi.model.PieceGeneral;
import de.htwg.xiangqi.model.Square;
import de.htwg.xiangqi.model.Piece.Player;

public class PieceGeneralTest {

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
		board[8][4] = new Square(new PieceGeneral(8, 4, Player.RED));
		assertEquals(true, board[8][4].getPiece().validMove(board, 9, 4));
		assertEquals(true, board[8][4].getPiece().validMove(board, 8, 3));
		assertEquals(true, board[8][4].getPiece().validMove(board, 8, 5));
		assertEquals(false, board[8][4].getPiece().validMove(board, 7, 3));
		assertEquals(false, board[8][4].getPiece().validMove(board, 8, 4));
		assertEquals(false, board[8][4].getPiece().validMove(board, 6, 4));
		
		board[1][4] = new Square(new PieceGeneral(1, 4, Player.BLACK));
		assertEquals(true, board[1][4].getPiece().validMove(board, 2, 4));
		assertEquals(false, board[1][4].getPiece().validMove(board, 3, 4));
	}
	
	@Test
	public void testGetPieceIcon() {
		Piece p = new PieceGeneral(0, 0, Player.RED);
		assertNotNull(p.getPieceIcon());
		p = new PieceGeneral(0, 0, Player.BLACK);
		assertNotNull(p.getPieceIcon());
	}

}
