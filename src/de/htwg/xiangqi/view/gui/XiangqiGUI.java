package de.htwg.xiangqi.view.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.google.inject.Inject;

import de.htwg.util.observer.IObserver;
import de.htwg.xiangqi.controller.IBoardManager;

/**
 * class XiangqiGUI creates and manages a GUI for the game
 * 
 * @author P. Wachter
 * 
 */
@SuppressWarnings("serial")
public class XiangqiGUI extends JFrame implements IObserver, ActionListener {

	private static final int ZERO = 0;
	private static final int TWO = 2;
	private static final int THREE = 3;
	private static final int FIVE = 5;
	private static final int SEVEN = 7;
	private static final int NINE = 9;
	private static final int ROW = 10;
	private static final int COL = 9;
	private IBoardManager bm;
	private JButton[][] buttonArray;
	private JButton playerButton;
	private JTextField txt;
	private StringBuilder sb = new StringBuilder();
	private boolean click = false;
	private boolean end = false;

	/**
	 * GUI constructor
	 * 
	 * @param bm
	 *            the BoardManager object
	 */
	@Inject
	public XiangqiGUI(final IBoardManager bm) {
		this.bm = bm;
		this.setTitle("Xiangqi");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel blackPanel = new JPanel();
		blackPanel.setLayout(new GridLayout(5, 9));
		blackPanel.setMaximumSize(new Dimension(450, 250));
		JPanel riverPanel = new JPanel();
		riverPanel.setLayout(new GridLayout(1, 1));
		riverPanel.setMaximumSize(new Dimension(450, 25));
		JPanel redPanel = new JPanel();
		redPanel.setLayout(new GridLayout(5, 9));
		redPanel.setMaximumSize(new Dimension(450, 250));
		JPanel playerPanel = new JPanel();
		playerPanel.setLayout(new GridLayout(1, 2));
		playerPanel.setMaximumSize(new Dimension(450, 50));
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new GridLayout(1, 1));
		textPanel.setMaximumSize(new Dimension(450, 30));

		buttonArray = new JButton[ROW][COL];
		JButton point;
		for (int i = 0; i < ROW; ++i) {
			for (int j = 0; j < COL; ++j) {
				point = new JButton();
				point.setName("" + i + " " + j + " ");
				point.setMinimumSize(new Dimension(50, 50));
				point.addActionListener(this);
				buttonArray[i][j] = point;
				if (j >= THREE && j <= FIVE && i >= SEVEN && i <= NINE) {
					point.setBackground(new Color(20, 80, 20, 255));
				} else if (j >= THREE && j <= FIVE && i >= ZERO && i <= TWO) {
					point.setBackground(new Color(20, 80, 20, 255));
				} else {
					if (i < FIVE) {
						point.setBackground(new Color(50, 110, 30, 255));
					} else {
						point.setBackground(new Color(50, 110, 30, 255));
					}
				}
				if (i < FIVE) {
					blackPanel.add(point);
				} else {
					redPanel.add(point);
				}
			}
		}

		point = new JButton();
		point.setBackground(Color.BLUE);
		point.setBorderPainted(false);
		point.setEnabled(false);
		riverPanel.add(point);

		JLabel turn = new JLabel("Next turn:");
		playerButton = new JButton();
		playerButton.setMinimumSize(new Dimension(100, 50));
		playerButton.setBackground(Color.RED);
		playerButton.setEnabled(false);
		playerPanel.add(turn);
		playerPanel.add(playerButton);

		txt = new JTextField();
		txt.setEditable(false);
		textPanel.add(txt);

		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 10, 0, 10));
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.add(blackPanel);
		mainPanel.add(riverPanel);
		mainPanel.add(redPanel);
		mainPanel.add(playerPanel);
		mainPanel.add(textPanel);

		this.add(mainPanel);
		this.setContentPane(mainPanel);
		this.setMinimumSize(new Dimension(550, 720));
		this.setResizable(false);
		this.setVisible(true);

		this.bm.addObserver(this);
		update();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (end) {
			return;
		}
		JButton jb = (JButton) e.getSource();
		String choose;
		if (click) {
			sb.append(jb.getName());
			choose = sb.toString();
			end = bm.inputMove(choose);
			sb = new StringBuilder();
			click = false;
		} else {
			sb.append(jb.getName());
			click = true;
		}
	}

	/**
	 * set the current icons for the buttons
	 */
	private void updateBoard() {
		String tmp;
		for (int i = 0; i < ROW; ++i) {
			for (int j = 0; j < COL; ++j) {
				tmp = bm.pieceAtPoint(i, j);
				if (tmp != null) {
					buttonArray[i][j].setIcon(new ImageIcon(bm.pieceAtPoint(i,
							j)));
				} else {
					buttonArray[i][j].setIcon(null);
				}
			}
		}
	}

	/**
	 * set the color of the button which shows whose turn it is
	 */
	private void playersTurn() {
		if (bm.getPlayersTurn() == 1) {
			playerButton.setBackground(Color.RED);
		} else {
			playerButton.setBackground(Color.BLACK);
		}
	}

	@Override
	public void update() {
		if (bm.getMessage() != null) {
			txt.setText(bm.getMessage());
		} else {
			txt.setText("");
			updateBoard();
			if (bm.isCheckmate() != '-') {
				playerButton.setBackground(null);
				txt.setText(bm.winnerMessage());
			} else {
				playersTurn();
			}
		}
	}

}
