package de.htwg.xiangqi.view.viewPlugin;

import de.htwg.xiangqi.controller.BoardManager;

public interface viewPlugin {
	
	void constructorExtension(BoardManager bm); //gets boardmanager for the existing context
	void buttonColorExtension();
	void updateBoardExtension();	
	void playersTurnExtension();
	void updateExtension();
}
