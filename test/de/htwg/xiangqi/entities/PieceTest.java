package de.htwg.xiangqi.entities;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import de.htwg.xiangqi.entities.Piece.Player;


public class PieceTest {

	Piece p;
	
	@Before
	public void setUp() {
		p = new PieceGeneral(9, 4, Player.RED);
	}
	
	@Test
	public void testPiece() {
		assertNotNull(p);
		assertEquals(9, p.getPosRow());
		assertEquals(4, p.getPosColumn());
		assertEquals(Player.RED, p.getPlayer());
	}
	
	@Test
	public void testPosition() {
		p.setPosition(1, 3);
		assertEquals(1, p.getPosRow());
		assertEquals(3, p.getPosColumn());
	}
	
	@Test
	public void testIsCaptured() {
		assertEquals(false, p.getIsCaptured());
		p.setIsCaptured(true);
		assertEquals(true, p.getIsCaptured());
	}
	
	@Test
	public void testInPalace() {
		assertEquals(false, p.inRedPalace(10, 4));
		assertEquals(false, p.inRedPalace(8, 9));
		assertEquals(false, p.inBlackPalace(-1, 4));
		assertEquals(false, p.inBlackPalace(1, 9));
	}
	
	@Test
	public void testPieceTypes() {
		assertEquals('G', p.getPieceType());
		p = new PieceAdvisor(0, 3, Player.RED);
		assertEquals('A', p.getPieceType());
		p = new PieceElephant(0, 3, Player.RED);
		assertEquals('E', p.getPieceType());
		p = new PieceHorse(0, 3, Player.RED);
		assertEquals('H', p.getPieceType());
		p = new PieceChariot(0, 3, Player.RED);
		assertEquals('R', p.getPieceType());
		p = new PieceCannon(0, 3, Player.RED);
		assertEquals('C', p.getPieceType());
		p = new PieceSoldier(0, 3, Player.RED);
		assertEquals('S', p.getPieceType());
	}
	
	@Test
	public void testEnum() {
		Piece.Player[] player = Piece.Player.values();
		Piece.Player[] testPlayer = new Piece.Player[2];
		testPlayer[0] = Piece.Player.valueOf("RED");
		testPlayer[1] = Piece.Player.valueOf(Piece.Player.class, "BLACK");
		assertArrayEquals(testPlayer, player);
	}

}
