package de.htwg.xiangqi;

import java.util.Scanner;

import org.apache.log4j.PropertyConfigurator;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.xiangqi.controller.IBoardManager;
import de.htwg.xiangqi.view.gui.XiangqiGUI;
import de.htwg.xiangqi.view.tui.XiangqiTUI;

/**
 * class XiangqiGame starts both TUI and GUI
 * 
 * @author P. Wachter
 * 
 */
public final class XiangqiGame {

	private static final int FOUR = 4;
	private static final Scanner EINGABE = new Scanner(System.in);

	private XiangqiGame() {
	}

	/**
	 * the game is started in the main method
	 * 
	 * @param args
	 *            is not used
	 */
	public static void main(String[] args) {
		 // Set up logging through log4j
		 PropertyConfigurator.configure("log4j.properties");

		Injector injector = Guice.createInjector(new XiangqiGameModule());
		IBoardManager bm = injector.getInstance(IBoardManager.class);
		bm.setStartBoard();
//		@SuppressWarnings("unused")
//		XiangqiGUI gui = injector.getInstance(XiangqiGUI.class);
//		@SuppressWarnings("unused")
//		XiangqiTUI tui = injector.getInstance(XiangqiTUI.class);
		new XiangqiGUI(bm);
		new XiangqiTUI(bm);

		StringBuilder input;
		boolean end = false;

		while (!end) {
			input = new StringBuilder();

			for (int i = 0; i < FOUR; ++i) {
				input.append(EINGABE.next());
				input.append(" ");
			}

			if (bm.isCheckmate() != '-') {
				break;
			}

			end = bm.inputMove(input.toString());
			if (end) {
				bm.removeAllObservers();
			}
		}
	}
}
