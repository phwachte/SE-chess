package de.htwg.xiangqi.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.xiangqi.entities.Piece;
import de.htwg.xiangqi.entities.PieceElephant;
import de.htwg.xiangqi.entities.Square;
import de.htwg.xiangqi.entities.Piece.Player;

public class BoardManagerTest {

	BoardManager bm;
	Square[][] board;

	@Before
	public void setUp() {
		bm = new BoardManager();
	}

	@Test
	public void testGetBoard() {
		assertNotNull(bm.getBoard());
		board = new Square[10][9];
		assertSame(board.length, bm.getBoard().length);
	}

	@Test
	public void testMoveCounter() {
		assertEquals(1, bm.getMoveCounter());
		assertEquals(1, bm.getPlayersTurn());
		bm.increaseMoveCounter();
		assertEquals(2, bm.getMoveCounter());
		assertEquals(0, bm.getPlayersTurn());
		bm.increaseMoveCounter();
		assertEquals(3, bm.getMoveCounter());
		assertEquals(1, bm.getPlayersTurn());
		bm.increaseMoveCounter();
		assertEquals(4, bm.getMoveCounter());
		assertEquals(0, bm.getPlayersTurn());
	}
	
	@Test
	public void testSetStartBoard() {
		bm.setStartBoard();
		assertEquals('H', bm.getBoard()[9][7].getPiece().getPieceType());
		assertEquals('S', bm.getBoard()[3][6].getPiece().getPieceType());
	}
	
	@Test
	public void testChoosenPiece() {
		bm.setStartBoard();
		assertNull(bm.choosenPiece(7, 9));
		assertEquals('R', bm.choosenPiece(0, 0).getPieceType());
		assertNull(bm.choosenPiece(1, 0));
	}
	
	@Test
	public void testValidChoose() {
		Piece elephant = new PieceElephant(9, 2, Player.RED);
		assertTrue(bm.validChoose(elephant));
		bm.increaseMoveCounter();
		assertFalse(bm.validChoose(elephant));
		elephant = new PieceElephant(0, 2, Player.BLACK);
		assertTrue(bm.validChoose(elephant));
		bm.increaseMoveCounter();
		assertFalse(bm.validChoose(elephant));
	}
	
	@Test
	public void testValidMove() {
		bm.setStartBoard();
		assertFalse(bm.validMove(bm.choosenPiece(7, 1), 7, 9));
		assertTrue(bm.validMove(bm.choosenPiece(7, 1), 5, 1));
	}
	
	@Test
	public void testMovePiece() {
		bm.setStartBoard();
		assertTrue(bm.movePiece(6, 0, 5, 0));
		assertTrue(bm.movePiece(7, 1, 0, 1));
		assertFalse(bm.movePiece(9, 0, 9, 1));
	}
	
	@Test
	public void testCheckMate() {
		bm.setStartBoard();
		assertNull(bm.isCheckmate());
		bm.getBoard()[9][4].getPiece().setIsCaptured(true);
		assertEquals('G', bm.isCheckmate().getPieceType());
		bm.getBoard()[9][4].getPiece().setIsCaptured(false);
		bm.getBoard()[0][4].getPiece().setIsCaptured(true);
		assertEquals('G', bm.isCheckmate().getPieceType());
	}

}
