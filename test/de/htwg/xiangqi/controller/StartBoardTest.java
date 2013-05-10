package de.htwg.xiangqi.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import de.htwg.xiangqi.entities.Board;
import de.htwg.xiangqi.entities.Square;

public class StartBoardTest {

	Board b;
	Square[][] board;

	@Before
	public void setUp() {
		b = new Board();
		board = b.getBoard();
	}

	@Test
	public void testFillBoard() {
		assertNotNull(new StartBoard());
		board[3][7] = new Square();
		StartBoard.fillBoard(board);
		assertNotNull(board);
		int i = 0;
		int j;
		while (i < 10) {
			j = 0;
			while (j < 9) {
				assertNotNull(board[i][j]);
				assertEquals(Square.class, board[i][j].getClass());
				assertEquals(null, board[i][j].getPiece());
				++j;
			}
			++i;
		}
	}

	@Test
	public void testSetPiecesRed() {
		StartBoard.setPiecesRed(board);
		assertEquals('G', board[9][4].getPiece().getPieceType());
		assertEquals('A', board[9][3].getPiece().getPieceType());
		assertEquals('A', board[9][5].getPiece().getPieceType());
		assertEquals('E', board[9][2].getPiece().getPieceType());
		assertEquals('E', board[9][6].getPiece().getPieceType());
		assertEquals('H', board[9][1].getPiece().getPieceType());
		assertEquals('H', board[9][7].getPiece().getPieceType());
		assertEquals('R', board[9][0].getPiece().getPieceType());
		assertEquals('R', board[9][8].getPiece().getPieceType());
		assertEquals('C', board[7][1].getPiece().getPieceType());
		assertEquals('C', board[7][7].getPiece().getPieceType());
		assertEquals('S', board[6][0].getPiece().getPieceType());
		assertEquals('S', board[6][2].getPiece().getPieceType());
		assertEquals('S', board[6][4].getPiece().getPieceType());
		assertEquals('S', board[6][6].getPiece().getPieceType());
		assertEquals('S', board[6][8].getPiece().getPieceType());
	}

	@Test
	public void testSetPiecesBlack() {
		StartBoard.setPiecesBlack(board);
		assertEquals('G', board[0][4].getPiece().getPieceType());
		assertEquals('A', board[0][3].getPiece().getPieceType());
		assertEquals('A', board[0][5].getPiece().getPieceType());
		assertEquals('E', board[0][2].getPiece().getPieceType());
		assertEquals('E', board[0][6].getPiece().getPieceType());
		assertEquals('H', board[0][1].getPiece().getPieceType());
		assertEquals('H', board[0][7].getPiece().getPieceType());
		assertEquals('R', board[0][0].getPiece().getPieceType());
		assertEquals('R', board[0][8].getPiece().getPieceType());
		assertEquals('C', board[2][1].getPiece().getPieceType());
		assertEquals('C', board[2][7].getPiece().getPieceType());
		assertEquals('S', board[3][0].getPiece().getPieceType());
		assertEquals('S', board[3][2].getPiece().getPieceType());
		assertEquals('S', board[3][4].getPiece().getPieceType());
		assertEquals('S', board[3][6].getPiece().getPieceType());
		assertEquals('S', board[3][8].getPiece().getPieceType());
	}
}
