package de.htwg.xiangqi.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import de.htwg.xiangqi.entities.Piece;
import de.htwg.xiangqi.entities.Piece.Player;
import de.htwg.xiangqi.entities.PieceGeneral;

public class PossibleMoveGTest {

	@Test
	public void testPossibleMoveRedG() {
		assertNotNull(new PossibleMoveG());
		Piece g = new PieceGeneral(8, 4, Player.RED);
		assertEquals(true, PossibleMoveG.possibleMoveG(g, 9, 4));
		assertEquals(true, PossibleMoveG.possibleMoveG(g, 8, 3));
		assertEquals(true, PossibleMoveG.possibleMoveG(g, 8, 5));
		assertEquals(false, PossibleMoveG.possibleMoveG(g, 7, 3));
		assertEquals(false, PossibleMoveG.possibleMoveG(g, 8, 4));
		assertEquals(false, PossibleMoveG.possibleMoveG(g, 6, 4));
	}

	@Test
	public void testPossibleMoveBlackG() {
		Piece g = new PieceGeneral(1, 4, Player.BLACK);
		assertEquals(true, PossibleMoveG.possibleMoveG(g, 2, 4));
		assertEquals(false, PossibleMoveG.possibleMoveG(g, 3, 4));
	}
	
}
