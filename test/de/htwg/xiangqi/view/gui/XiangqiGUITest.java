package de.htwg.xiangqi.view.gui;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.xiangqi.controller.BoardManager;
import de.htwg.xiangqi.view.gui.XiangqiGUI;

public class XiangqiGUITest {

	BoardManager bm;
	XiangqiGUI gui;
	
	@Before
	public void setUp() {
		bm = new BoardManager();
		bm.setStartBoard();
		gui = new XiangqiGUI(bm);
	}
	
	@Test
	public void testUpdate() {
		assertNull(bm.getMessage());
		bm.inputMove("9 0 8 5");
		gui.update();
		assertNotNull(bm.getMessage());
		bm.inputMove("9 0 8 0");
		gui.update();
		assertNull(bm.getMessage());
		bm.getBoard()[0][4].getPiece().setIsCaptured(true);
		gui.update();
	}

}
