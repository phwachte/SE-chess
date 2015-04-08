package de.htwg.xiangqi.view.viewPlugin;

import de.htwg.xiangqi.controller.BoardManager;

public class testPlug implements viewPlugin{
	
	private BoardManager bm;

	@Override
	public void constructorExtension(BoardManager bm) {
		this.bm = bm;
	}

	@Override
	public void buttonColorExtension() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBoardExtension() {
		System.out.println("[JULIS TEST-PLUGIN]: hallo!\n");
	}

	@Override
	public void playersTurnExtension() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateExtension() {
		// TODO Auto-generated method stub
		
	}

}
