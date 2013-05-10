package de.htwg.xiangqi.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import de.htwg.xiangqi.entities.Piece.Player;
import de.htwg.xiangqi.entities.PieceCannon;
import de.htwg.xiangqi.entities.Square;

public class MoveRulesTest {

	@Test
	public void testInRedPalace() {
		assertNotNull(new MoveRules());
		assertEquals(true, MoveRules.inRedPalace(7, 3));
		assertEquals(true, MoveRules.inRedPalace(7, 4));
		assertEquals(true, MoveRules.inRedPalace(7, 5));
		assertEquals(true, MoveRules.inRedPalace(8, 3));
		assertEquals(true, MoveRules.inRedPalace(8, 4));
		assertEquals(true, MoveRules.inRedPalace(8, 5));
		assertEquals(true, MoveRules.inRedPalace(9, 3));
		assertEquals(true, MoveRules.inRedPalace(9, 4));
		assertEquals(true, MoveRules.inRedPalace(9, 5));
		assertEquals(false, MoveRules.inRedPalace(6, 3));
		assertEquals(false, MoveRules.inRedPalace(10, 5));
		assertEquals(false, MoveRules.inRedPalace(7, 6));
		assertEquals(false, MoveRules.inRedPalace(9, 2));
	}
	
	@Test
	public void testInBlackPalace() {
		assertEquals(true, MoveRules.inBlackPalace(0, 3));
		assertEquals(true, MoveRules.inBlackPalace(0, 4));
		assertEquals(true, MoveRules.inBlackPalace(0, 5));
		assertEquals(true, MoveRules.inBlackPalace(1, 3));
		assertEquals(true, MoveRules.inBlackPalace(1, 4));
		assertEquals(true, MoveRules.inBlackPalace(1, 5));
		assertEquals(true, MoveRules.inBlackPalace(2, 3));
		assertEquals(true, MoveRules.inBlackPalace(2, 4));
		assertEquals(true, MoveRules.inBlackPalace(2, 5));
		assertEquals(false, MoveRules.inBlackPalace(-1, 3));
		assertEquals(false, MoveRules.inBlackPalace(3, 5));
		assertEquals(false, MoveRules.inBlackPalace(0, 6));
		assertEquals(false, MoveRules.inBlackPalace(2, 2));
	}
	
	@Test
	public void testInRedHalf() {
		assertEquals(true, MoveRules.inRedHalf(5));
		assertEquals(true, MoveRules.inRedHalf(6));
		assertEquals(true, MoveRules.inRedHalf(7));
		assertEquals(false, MoveRules.inRedHalf(4));
	}
	
	@Test
	public void testInBlackHalf() {
		assertEquals(true, MoveRules.inBlackHalf(0));
		assertEquals(true, MoveRules.inBlackHalf(2));
		assertEquals(true, MoveRules.inBlackHalf(4));
		assertEquals(false, MoveRules.inBlackHalf(5));
	}
	
	@Test
	public void testOccupiedPoint() {
		Square sq1 = new Square();
		Square sq2 = new Square(new PieceCannon(2, 7, Player.BLACK));
		assertEquals(true, MoveRules.occupiedPoint(sq2));
		assertEquals(false, MoveRules.occupiedPoint(sq1));
	}

}
