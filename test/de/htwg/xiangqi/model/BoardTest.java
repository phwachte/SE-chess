package de.htwg.xiangqi.model;

import static org.junit.Assert.*;

import org.junit.Before;
//import org.junit.Before;
import org.junit.Test;

import de.htwg.xiangqi.model.Board;
import de.htwg.xiangqi.model.Square;

public class BoardTest {
	
	Board a, b;
	Square[][] board;
	
	@Before
	public void setUp() {
		b = new Board();
		board = b.getBoard();
	}
	
	@Test
	public void testBoard() {
		assertEquals(null, a);
		a = new Board();
		assertNotNull(a);
		assertNotNull(a.getBoard());
		board = new Square[10][9];
		assertSame(board.length, a.getBoard().length);
	}
	
	@Test
	public void testOnBoard() {
		assertEquals(true, b.onBoard(5, 4));
		assertEquals(false, b.onBoard(-1, 3));
		assertEquals(false, b.onBoard(7, 11));
		assertEquals(false, b.onBoard(7, -3));
		assertEquals(false, b.onBoard(10, 5));
	}

	@Test
	public void testFillBoard() {
		board[3][7] = new Square(null);
		b.fillBoard();
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
		b.setPiecesRed();
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
		assertEquals('G', b.getRedGeneral().getPieceType());
	}

	@Test
	public void testSetPiecesBlack() {
		b.setPiecesBlack();
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
		assertEquals('G', b.getBlackGeneral().getPieceType());
	}
}