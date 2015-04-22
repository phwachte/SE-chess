package de.htwg.xiangqi.view.tui;

import org.apache.log4j.Logger;
import com.google.inject.Inject;

import de.htwg.util.observer.IObserver;
import de.htwg.xiangqi.controller.IBoardManager;

/**
 * class XiangqiTUI creates and manages a TUI for the game
 * 
 * @author P. Wachter
 * 
 */
public class XiangqiTUI implements IObserver {

	private static final int ROW = 10;
	private static final int COL = 9;
	private IBoardManager bm;
	private String newLine = System.getProperty("line.separator");
	private Logger logger = Logger.getLogger("de.htwg.xiangqi.view.tui");

	/**
	 * TUI constructor
	 * 
	 * @param bm
	 *            the BoardManager object
	 */
	@Inject
	public XiangqiTUI(IBoardManager bm) {
		this.bm = bm;
		this.bm.addObserver(this);
	}

	/**
	 * @return the string which represents the current board
	 */
	public String printBoard() {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		int j;
		sb.append("   0   1   2   3   4   5   6   7   8\n");
		while (i < ROW) {
			j = 0;
			sb.append(i).append(" ");
			while (j < COL) {
				sb.append("[");
				sb.append(bm.getTUIOutput(i, j));
				sb.append("]");
				++j;
			}
			sb.append("\n");
			++i;
		}
		return sb.toString();
	}

	/**
	 * print a message which shows whose turn it is
	 */
	public void playersTurn() {
		StringBuilder sb = new StringBuilder();
		if (bm.getPlayersTurn() == 1) {
			sb.append(bm.getBoard().getMoveCounter());
			sb.append(": Player Red, choose piece and point: ");
		} else {
			sb.append(bm.getBoard().getMoveCounter());
			sb.append(": Player Black, choose piece and point: ");
		}
		logger.info(newLine + sb.toString());
	}

	@Override
	public void update() {
		if (bm.getMessage() != null) {
			logger.info(newLine + bm.getMessage());
			playersTurn();
		} else {
			logger.info(newLine + printBoard());
			if (bm.isCheckmate() != '-') {
				logger.info(newLine + bm.winnerMessage());
			} else {
				playersTurn();
			}
		}
	}

}