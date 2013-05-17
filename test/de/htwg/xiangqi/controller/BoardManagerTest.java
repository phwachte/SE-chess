package de.htwg.xiangqi.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.xiangqi.entities.Piece;
import de.htwg.xiangqi.entities.Square;

public class BoardManagerTest {

	BoardManager bm;
	Square[][] board;

	@Before
	public void setUp() {
		bm = new BoardManager();
	}

	@Test
	public void testSetStartBoard() {
		bm.setStartBoard();
		assertEquals('R', bm.getBoard()[9][8].getPiece().getPieceType());
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
	public void testChoosenPiece() {
		bm.setStartBoard();
		Piece piece = bm.choosenPiece(7, 1);
		assertEquals('C', piece.getPieceType());
		assertEquals(null, bm.choosenPiece(10, 1));
	}
	
	@Test
	public void testValidMove() {
		bm.setStartBoard();
		Piece piece = bm.choosenPiece(7, 1);
		assertEquals(true, bm.validMove(piece, 3, 1));
		assertEquals(false, bm.validMove(piece, 10, 1));
	}
	
	@Test
	public void testMovePiece() {
		bm.setStartBoard();
		assertEquals(true, bm.movePiece(9, 0, 8, 0));
		assertEquals(false, bm.movePiece(8, 0, 6, 0));
		assertEquals(true, bm.movePiece(8, 0, 8, 3));
		assertEquals(true, bm.movePiece(8, 3, 0, 3));
	}

}
