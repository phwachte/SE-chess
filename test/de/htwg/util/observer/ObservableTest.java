package de.htwg.util.observer;

import org.junit.Test;

import de.htwg.xiangqi.controller.BoardManager;
import de.htwg.xiangqi.view.tui.XiangqiTUI;

public class ObservableTest {

	@Test
	public void testObservable() {
		BoardManager bm = new BoardManager();
		bm.setStartBoard();
		XiangqiTUI xtui = new XiangqiTUI(bm);
		bm.addObserver(xtui);
		bm.removeObserver(xtui);
		bm.addObserver(xtui);
		bm.notifyObservers();
		bm.removeAllObservers();
	}

}
