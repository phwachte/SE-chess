package de.htwg.xiangqi.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.xiangqi.entities.Square;

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

}
