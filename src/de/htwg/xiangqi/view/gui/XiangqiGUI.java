package de.htwg.xiangqi.view.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.google.inject.Inject;

import de.htwg.util.observer.IObserver;
import de.htwg.xiangqi.controller.IBoardManager;
import de.htwg.xiangqi.persistence.PersistenceLoadDialog;
import de.htwg.xiangqi.persistence.SaveGame_Wrapper;
import de.htwg.xiangqi.view.viewPlugin.IviewPlugin;

/**
 * class XiangqiGUI creates and manages a GUI for the game
 * 
 * @author P. Wachter
 * 
 */
@SuppressWarnings("serial")
public class XiangqiGUI extends JFrame implements IObserver, ActionListener {
	
	private static List<SaveGame_Wrapper> saveGames = new ArrayList<SaveGame_Wrapper>();

	private static final int ZERO = 0;
	private static final int ONE = 1;
	private static final int TWO = 2;
	private static final int THREE = 3;
	private static final int FIVE = 5;
	private static final int SEVEN = 7;
	private static final int NINE = 9;
	private static final int ROW = 10;
	private static final int COL = 9;
	private static final int PANELX = 450;
	private static final int PANELY = 250;
	private static final int MAINX = 550;
	private static final int MAINY = 720;
	private static final int TEXTY = 30;
	private static final int BUTTONSIZE = 50;
	private static final int PBR = 50;
	private static final int PBG = 110;
	private static final int PBB = 30;
	private static final int PBA = 255;
	private static final int FBRB = 20;
	private static final int FBG = 80;
	private static final int BTOP = 40;
	private static final int BSIDE = 10;

	public IBoardManager bm;
	public JButton[][] buttonArray;
	public JButton playerButton;
	public JTextField txt;
	public StringBuilder sb = new StringBuilder();
	public boolean click = false;
	public boolean end = false;
	
	/*persistence*/
	public JMenuBar jmenubar = new JMenuBar();
	private JMenu jmenu = new JMenu("Datei");
	private JMenuItem save = new JMenuItem("Save");
	private JMenuItem load = new JMenuItem("Load");
	
	/*GUICE MULTIBINDER PLUGIN SET*/
	private final Set<IviewPlugin> plugins;
	

	/**
	 * GUI constructor
	 * 
	 * @param bm
	 *            the BoardManager object
	 */
	@Inject
	public XiangqiGUI(IBoardManager bm, Set<IviewPlugin> set) {
		this.plugins = set;
		this.bm = bm;
		this.setTitle("Xiangqi");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel blackPanel = new JPanel();
		blackPanel.setLayout(new GridLayout(FIVE, NINE));
		blackPanel.setMaximumSize(new Dimension(PANELX, PANELY));
		JPanel riverPanel = new JPanel();
		riverPanel.setLayout(new GridLayout(ONE, ONE));
		riverPanel.setMaximumSize(new Dimension(PANELX, BUTTONSIZE / TWO));
		JPanel redPanel = new JPanel();
		redPanel.setLayout(new GridLayout(FIVE, NINE));
		redPanel.setMaximumSize(new Dimension(PANELX, PANELY));
		JPanel playerPanel = new JPanel();
		playerPanel.setLayout(new GridLayout(ONE, TWO));
		playerPanel.setMaximumSize(new Dimension(PANELX, BUTTONSIZE));
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new GridLayout(ONE, ONE));
		textPanel.setMaximumSize(new Dimension(PANELX, TEXTY));

		buttonArray = new JButton[ROW][COL];
		JButton point;
		for (int i = 0; i < ROW; ++i) {
			for (int j = 0; j < COL; ++j) {
				point = new JButton();
				point.setName("" + i + " " + j + " ");
				point.setMinimumSize(new Dimension(BUTTONSIZE, BUTTONSIZE));
				point.addActionListener(this);
				buttonArray[i][j] = point;
				setButtonColor(point, i, j);
				if (i < FIVE) {
					blackPanel.add(point);
				} else {
					redPanel.add(point);
				}
			}
		}
		
		/*persistence-----------------------------------------------------*/
		jmenubar.add(jmenu);
		
		save.addActionListener(this);
		load.addActionListener(this);
		
		jmenu.add(save);
		jmenu.add(load);
		
		this.setJMenuBar(jmenubar);
		/*----------------------------------------------------------------*/
		
		
		
		point = new JButton();
		point.setBackground(Color.BLUE);
		point.setBorderPainted(false);
		point.setEnabled(false);
		riverPanel.add(point);

		JLabel turn = new JLabel("Next turn:");
		playerButton = new JButton();
		playerButton.setMinimumSize(new Dimension(BUTTONSIZE + BUTTONSIZE,
				BUTTONSIZE));
		playerButton.setBackground(Color.RED);
		playerButton.setEnabled(false);
		playerPanel.add(turn);
		playerPanel.add(playerButton);

		txt = new JTextField();
		txt.setEditable(false);
		textPanel.add(txt);

		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(BorderFactory.createEmptyBorder(BTOP, BSIDE, 0, BSIDE));
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.add(blackPanel);
		mainPanel.add(riverPanel);
		mainPanel.add(redPanel);
		mainPanel.add(playerPanel);
		mainPanel.add(textPanel);

		this.add(mainPanel);
		this.setContentPane(mainPanel);
		this.setMinimumSize(new Dimension(MAINX, MAINY));
		this.setResizable(false);
		this.setVisible(true);

		this.bm.addObserver(this);
		
		/*LET ALL THE PLUGINS THAT WANT TO ADD FUNCTIONALITY ADD IT HERE*/
		for(IviewPlugin plugin : plugins){
			plugin.constructorExtension(this);
		}
	}

	private void setButtonColor(JButton point, int i, int j) {
		if (j >= THREE && j <= FIVE && i >= SEVEN && i <= NINE) {
			point.setBackground(new Color(FBRB, FBG, FBRB, PBA));
		} else if (j >= THREE && j <= FIVE && i >= ZERO && i <= TWO) {
			point.setBackground(new Color(FBRB, FBG, FBRB, PBA));
		} else {
			if (i < FIVE) {
				point.setBackground(new Color(PBR, PBG, PBB, PBA));
			} else {
				point.setBackground(new Color(PBR, PBG, PBB, PBA));
			}
		}
		
		/*LET ALL THE PLUGINS THAT WANT TO ADD FUNCTIONALITY ADD IT HERE*/
		for(IviewPlugin plugin : plugins){
			plugin.setButtonColorExtension();
		}
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		if (end) {
			return;
		}
		
		if(e.getSource() == save){
			this.bm.saveGame();
			return;
		}else if(e.getSource() == load){
			
			saveGames = this.bm.loadSaveGames();
			JDialog dialog = new PersistenceLoadDialog(this, this.bm, saveGames);
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
		
		/*LET ALL THE PLUGINS THAT WANT TO ADD FUNCTIONALITY ADD IT HERE*/
		for(IviewPlugin plugin : plugins){
			plugin.actionPerformedExtension(e);
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
		
		/*LET ALL THE PLUGINS THAT WANT TO ADD FUNCTIONALITY ADD IT HERE*/
		for(IviewPlugin plugin : plugins){
			plugin.updateBoardExtension();
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
		/*LET ALL THE PLUGINS THAT WANT TO ADD FUNCTIONALITY ADD IT HERE*/
		for(IviewPlugin plugin : plugins){
			plugin.playersTurnExtension();
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
		
		/*LET ALL THE PLUGINS THAT WANT TO ADD FUNCTIONALITY ADD IT HERE*/
		for(IviewPlugin plugin : plugins){
			plugin.updateExtension();
		}
	}

}
