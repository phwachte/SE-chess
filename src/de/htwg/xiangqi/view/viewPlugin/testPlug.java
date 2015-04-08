package de.htwg.xiangqi.view.viewPlugin;

import java.awt.Color;
import java.awt.event.ActionEvent;

import de.htwg.xiangqi.view.gui.XiangqiGUI;

public class testPlug implements viewPlugin{
	
	private XiangqiGUI gui;

	@Override
	public void constructorExtension(XiangqiGUI gui) {
		this.gui = gui;
	}

	@Override
	public void setButtonColorExtension() {
		
	}

	@Override
	public void actionPerformedExtension(ActionEvent e) {
		System.out.println("[JULIS TEST-PLUGIN]:\taction was performed!!!!\n");
	}

	@Override
	public void updateBoardExtension() {
		System.out.println("[JULIS TEST-PLUGIN]:\thallo!\n");
	}

	@Override
	public void playersTurnExtension() {
		if (gui.bm.getPlayersTurn() == 1) {
			gui.playerButton.setBackground(Color.GREEN);
		} else {
			gui.playerButton.setBackground(Color.BLUE);
		}
	}

	@Override
	public void updateExtension() {
		// TODO Auto-generated method stub
		
	}

}
