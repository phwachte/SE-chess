package de.htwg.xiangqi.view.tui;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import de.htwg.xiangqi.controller.BoardManager;
import de.htwg.xiangqi.view.tui.XiangqiTUI;

public class XiangqiTUITest {

	BoardManager bm;
	XiangqiTUI tui;

	@Before
	public void setUp() {
		bm = new BoardManager();
		bm.setStartBoard();
		tui = new XiangqiTUI(bm);
	}
	
	@Test
	public void testPlayersTurn() {
		tui.playersTurn();
		bm.getBoard().increaseMoveCounter();
		tui.playersTurn();
	}
	
	@Test
	public void testPrintBoard() {
		assertNotNull(tui.printBoard());
	}
	
	@Test
	public void testUpdate() {
		bm.inputMove("9 0 8 5");
		tui.update();
		bm.inputMove("9 0 8 0");
		bm.getBoard().getSquareMatrix()[9][4].getPiece().setIsCaptured(true);
		tui.update();
	}
	
}
