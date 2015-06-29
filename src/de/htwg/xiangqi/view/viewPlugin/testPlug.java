package de.htwg.xiangqi.view.viewPlugin;

import java.awt.Color;
import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

import de.htwg.xiangqi.view.gui.XiangqiGUI;

public class testPlug implements IviewPlugin{
	
	private XiangqiGUI gui;
	private Logger logger = Logger.getLogger("de.htwg.xiangqi.view.viewPlugin.testPlug");

	@Override
	public void constructorExtension(XiangqiGUI gui) {
		this.gui = gui;
	}

	@Override
	public void setButtonColorExtension() {
		//TODO auto-generated methode stub
	}

	@Override
	public void actionPerformedExtension(ActionEvent e) {
		logger.info("[JULIS TEST-PLUGIN]:\taction was performed!!!!\n");
	}

	@Override
	public void updateBoardExtension() {
		logger.info("[JULIS TEST-PLUGIN]:\thallo!\n");
	}

	@Override
	public void playersTurnExtension() {
		if (gui.getBm().getPlayersTurn() == 1) {
			gui.getPlayerButton().setBackground(Color.GREEN);
		} else {
			gui.getPlayerButton().setBackground(Color.BLUE);
		}
	}

	@Override
	public void updateExtension() {
		// TODO Auto-generated method stub
		
	}

}
