package de.htwg.xiangqi.view.viewPlugin;

import java.awt.event.ActionEvent;

import de.htwg.xiangqi.view.gui.XiangqiGUI;

public interface IviewPlugin {
	/**
	 * gets boardmanager for the existing context
	 */
	void constructorExtension(XiangqiGUI gui); 
	void setButtonColorExtension();
	void updateBoardExtension();	
	void playersTurnExtension();
	void updateExtension();
	void actionPerformedExtension(ActionEvent e);
}
