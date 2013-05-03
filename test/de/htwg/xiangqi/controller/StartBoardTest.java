package de.htwg.xiangqi.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.xiangqi.controller.StartBoard;
import de.htwg.xiangqi.entities.Board;
import de.htwg.xiangqi.entities.PieceGeneral;
import de.htwg.xiangqi.entities.Square;
import de.htwg.xiangqi.entities.Piece.Player;

public class StartBoardTest {

	Board b;
	Square[][] board;

	@Before
	public void setUp() {
		b = new Board();
	}

	@Test
	public void testFillBoard() {
		StartBoard.fillBoard(b);
		board = b.getBoard();
		assertNotNull(board);
		int i = 0;
		int j;
		while (i < 10) {
			j = 0;
			while (j < 9) {
				assertNotNull(board[i][j]);
				++j;
			}
			++i;
		}
		StartBoard.fillBoard(b);
	}

	@Test
	public void testSetPiecesRed() {
		StartBoard.setPiecesRed(b);
		board = b.getBoard();
		board[9][4] = new Square(new PieceGeneral(9, 4, Player.RED));
		assertNotNull(b.getBoard()[9][4]);
		assertEquals('G', b.getBoard()[9][4].getPiece().getPieceType());
	}

	@Test
	public void testSetPiecesBlack() {
		StartBoard.setPiecesBlack(b);
		board = b.getBoard();
		board[0][4] = new Square(new PieceGeneral(0, 4, Player.BLACK));
		assertNotNull(b.getBoard()[0][4]);
		assertEquals(0, b.getBoard()[0][4].getPiece().getPosRow());
	}
}
