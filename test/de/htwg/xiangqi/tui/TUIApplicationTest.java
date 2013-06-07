package de.htwg.xiangqi.tui;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import de.htwg.xiangqi.controller.BoardManager;

public class TUIApplicationTest {

	BoardManager bm;

	@Before
	public void setUp() {
		bm = new BoardManager();
	}
	
	@Test
	public void testPlayersTurn() {
		assertNotNull(TUIApplication.playersTurn(bm));
		bm.increaseMoveCounter();
		assertNotNull(TUIApplication.playersTurn(bm));
	}

	@Test
	public void testWinnerMessage() {
		bm.setStartBoard();
		assertNotNull(TUIApplication.winnerMessage('r'));
		assertNotNull(TUIApplication.winnerMessage('b'));
	}
	
	@Test
	public void testPrintBoard() {
		bm.setStartBoard();
		assertNotNull(TUIApplication.printBoard(bm));
	}
	
	@Test
	public void testRun() {
		TUIApplication tuiapp = new TUIApplication();
		tuiapp.run();
	}
	
}
