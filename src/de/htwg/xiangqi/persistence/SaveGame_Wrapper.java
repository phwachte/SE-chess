package de.htwg.xiangqi.persistence;

import de.htwg.xiangqi.controller.IBoardManager;

public class SaveGame_Wrapper{
	private String name;
	private IBoardManager saveGame;
	
	/*CONSTRUCTOR*/
	public SaveGame_Wrapper(String name, IBoardManager saveGame){
		this.name = name;
		this.saveGame = saveGame;
	}
	
	/*GETTERS AND SETTERS*/
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public IBoardManager getSaveGame() {
		return saveGame;
	}
	public void setSaveGame(IBoardManager saveGame) {
		this.saveGame = saveGame;
	}
	
	
}
