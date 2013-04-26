package de.htwg.xiangqi.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import de.htwg.xiangqi.entities.Square;

public class SquareTest {
	
	Square sq;

	@Test
	public void testCreateSquare() {
		assertEquals(null, sq);
		sq = new Square(null);
		assertNotNull(sq);
	}
	
	@Test
	public void testGetPiece() {
		sq = new Square(null);
		assertEquals(null, sq.getPiece());
	}

}
