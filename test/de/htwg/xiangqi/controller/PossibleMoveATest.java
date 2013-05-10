package de.htwg.xiangqi.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import de.htwg.xiangqi.entities.Piece;
import de.htwg.xiangqi.entities.PieceAdvisor;
import de.htwg.xiangqi.entities.Piece.Player;

public class PossibleMoveATest {

	@Test
	public void testPossibleMoveRedA() {
		assertNotNull(new PossibleMoveA());
		Piece a = new PieceAdvisor(8, 4, Player.RED);
		assertEquals(true, PossibleMoveA.possibleMoveA(a, 7, 3));
		assertEquals(true, PossibleMoveA.possibleMoveA(a, 7, 5));
		assertEquals(true, PossibleMoveA.possibleMoveA(a, 9, 3));
		assertEquals(false, PossibleMoveA.possibleMoveA(a, 8, 4));
		assertEquals(false, PossibleMoveA.possibleMoveA(a, 7, 4));
		assertEquals(false, PossibleMoveA.possibleMoveA(a, 9, 2));
	}

	@Test
	public void testPossibleMoveBlackA() {
		Piece a = new PieceAdvisor(1, 4, Player.BLACK);
		assertEquals(true, PossibleMoveA.possibleMoveA(a, 0, 3));
		assertEquals(false, PossibleMoveA.possibleMoveA(a, 2, 6));
	}
	
}
