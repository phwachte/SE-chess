package de.htwg.xiangqi.entities;

import static org.junit.Assert.*;

//import org.junit.Before;
import org.junit.Test;

import de.htwg.xiangqi.entities.Board;
import de.htwg.xiangqi.entities.Square;

public class BoardTest {
	
	Board a, b;
	Square[][] board;
	
	@Test
	public void testBoard() {
		assertEquals(null, a);
		a = new Board();
		assertNotNull(a);
		assertNotNull(a.getBoard());
		board = new Square[10][9];
		assertSame(board.length, a.getBoard().length);
	}
}