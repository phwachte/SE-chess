package de.htwg.xiangqi.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BoardManagerTest {

	BoardManager bm;
	@Before
	public void setUp() {
		bm = new BoardManager();
	}
	
	@Test
	public void testGetBoard() {
		assertNotNull(bm.getBoard());
	}
	
	@Test
	public void testRedTurn() {
		assertEquals(true, bm.getRedTurn());
		bm.setRedTurn(false);
		assertEquals(false, bm.getRedTurn());
		bm.setRedTurn(true);
		assertEquals(true, bm.getRedTurn());
	}

}
