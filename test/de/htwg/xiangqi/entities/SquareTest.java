package de.htwg.xiangqi.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import de.htwg.xiangqi.entities.Square;

public class SquareTest {
	
	Square sq, sq2;

	@Test
	public void testCreateSquare() {
		assertEquals(null, sq);
		sq = new Square(null);
		assertNotNull(sq);
		assertEquals(null, sq.getPiece());
		assertEquals(null, sq2);
		sq2 = new Square();
		assertNotNull(sq2);
		assertEquals(null, sq2.getPiece());
	}
	
	@Test
	public void testSetPiece() {
		sq = new Square();
		sq.setPiece(null);
		assertEquals(null, sq.getPiece());
	}
	
	@Test
	public void testGetPiece() {
		sq = new Square(null);
		assertEquals(null, sq.getPiece());
	}

}
