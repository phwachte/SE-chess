package de.htwg.xiangqi.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import de.htwg.xiangqi.entities.Piece.Player;
import de.htwg.xiangqi.entities.Square;

public class SquareTest {
	
	Square sq;
	Piece piece = new PieceGeneral(9, 4, Player.RED);

	@Test
	public void testCreateSquare() {
		assertEquals(null, sq);
		sq = new Square(null);
		assertNotNull(sq);
	}
	
	@Test
	public void testSetGetPiece() {
		sq = new Square(null);
		assertEquals(null, sq.getPiece());
		sq.setPiece(piece);
		assertNotNull(sq.getPiece());
		assertEquals(piece, sq.getPiece());
	}
	
	@Test
	public void testOccupiedPoint() {
		sq = new Square(null);
		assertEquals(false, sq.occupiedPoint());
		sq = new Square(piece);
		assertEquals(true, sq.occupiedPoint());
	}

}
