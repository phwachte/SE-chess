package de.htwg.xiangqi.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import de.htwg.xiangqi.entities.PieceChariot;
import de.htwg.xiangqi.entities.PieceGeneral;
import de.htwg.xiangqi.entities.PieceHorse;
import de.htwg.xiangqi.entities.Square;
import de.htwg.xiangqi.entities.Piece.Player;

public class MovePieceTest {

	Square sq1, sq2, sq3;

	@Test
	public void testMovePiece() {
		assertNotNull(new MovePiece());
		sq1 = new Square(new PieceGeneral(0, 4, Player.BLACK));
		sq2 = new Square(new PieceHorse(1, 4, Player.RED));
		assertEquals('H', sq2.getPiece().getPieceType());
		assertEquals(0, sq1.getPiece().getPosRow());
		assertEquals(4, sq1.getPiece().getPosColumn());
		MovePiece.movePiece(sq1, sq2);
		assertEquals('G', sq2.getPiece().getPieceType());
		assertEquals(1, sq2.getPiece().getPosRow());
		assertEquals(4, sq2.getPiece().getPosColumn());
		assertEquals(null, sq1.getPiece());
	}

	@Test
	public void testTakePiece() {
		sq1 = new Square(new PieceGeneral(0, 4, Player.BLACK));
		sq2 = new Square(new PieceHorse(1, 4, Player.RED));
		sq3 = new Square(new PieceChariot(0, 5, Player.BLACK));
		assertEquals(true, MovePiece.capturePiece(sq1, sq2));
		assertEquals(false, MovePiece.capturePiece(sq1, sq3));
		sq3 = new Square();
		assertEquals(true, MovePiece.capturePiece(sq1, sq3));
	}

	@Test
	public void testPieceChoosen() {
		sq1 = new Square(new PieceGeneral(0, 4, Player.BLACK));
		sq2 = new Square();
		assertEquals(true, MovePiece.pieceChoosen(sq1));
		assertEquals(false, MovePiece.pieceChoosen(sq2));
	}

	@Test
	public void testOccupiedPoint() {
		sq1 = new Square(new PieceGeneral(0, 4, Player.BLACK));
		sq2 = new Square();
		assertEquals(true, MovePiece.occupiedPoint(sq1));
		assertEquals(false, MovePiece.occupiedPoint(sq2));
	}

}
