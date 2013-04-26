package de.htwg.xiangqi.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.xiangqi.entities.Piece;
import de.htwg.xiangqi.entities.PieceAdvisor;
import de.htwg.xiangqi.entities.PieceCannon;
import de.htwg.xiangqi.entities.PieceChariot;
import de.htwg.xiangqi.entities.PieceElephant;
import de.htwg.xiangqi.entities.PieceGeneral;
import de.htwg.xiangqi.entities.PieceHorse;
import de.htwg.xiangqi.entities.PieceSoldier;
import de.htwg.xiangqi.entities.Piece.Player;


public class PieceTest {

	Piece p;
	
	@Before
	public void setUp() {
		p = new PieceGeneral(0, 4, Player.RED);
	}
	
	@Test
	public void testPiece() {
		assertNotNull(p);
		assertEquals(0, p.getPosRow());
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
	public void testIsTaken() {
		assertEquals(false, p.getIsTaken());
		p.setIsTaken(true);
		assertEquals(true, p.getIsTaken());
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
		assertEquals('T', p.getPieceType());
		p = new PieceCannon(0, 3, Player.RED);
		assertEquals('C', p.getPieceType());
		p = new PieceSoldier(0, 3, Player.RED);
		assertEquals('S', p.getPieceType());
	}

}
