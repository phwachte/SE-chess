package de.htwg.xiangqi.view.viewPlugin;

import java.awt.event.ActionEvent;

import de.htwg.xiangqi.view.gui.XiangqiGUI;

public interface IviewPlugin {
	void constructorExtension(XiangqiGUI gui); //gets boardmanager for the existing context
	void setButtonColorExtension();
	void updateBoardExtension();	
	void playersTurnExtension();
	void updateExtension();
	void actionPerformedExtension(ActionEvent e);
}
