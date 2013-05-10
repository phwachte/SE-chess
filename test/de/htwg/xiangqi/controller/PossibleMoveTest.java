package de.htwg.xiangqi.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.xiangqi.entities.Square;

public class PossibleMoveTest {

	Square[][] board;
	
	@Before
	public void setUp() {
		board = new Square[10][9];
		StartBoard.fillBoard(board);
		StartBoard.setPiecesRed(board);
	}

	@Test
	public void testPossibleMove() {
		assertEquals(true, PossibleMove.possibleMove(board, board[9][4], 8, 4));
		assertEquals(false, PossibleMove.possibleMove(board, board[9][4], 8, 9));
		assertEquals(false, PossibleMove.possibleMove(board, board[9][4], 8, -1));
		assertEquals(false, PossibleMove.possibleMove(board, board[9][4], -1, 4));
		assertEquals(false, PossibleMove.possibleMove(board, board[9][4], 10, 4));
		
		assertEquals(true, PossibleMove.possibleMove(board, board[9][3], 8, 4));
		assertEquals(true, PossibleMove.possibleMove(board, board[9][2], 7, 0));
		assertEquals(false, PossibleMove.possibleMove(board, board[9][0], 8, 4));
		assertEquals(false, PossibleMove.possibleMove(board, board[9][1], 8, 4));
		assertEquals(false, PossibleMove.possibleMove(board, board[7][1], 8, 4));
		assertEquals(false, PossibleMove.possibleMove(board, board[6][4], 8, 4));
	}

}
