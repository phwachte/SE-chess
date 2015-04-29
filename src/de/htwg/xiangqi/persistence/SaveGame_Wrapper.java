package de.htwg.xiangqi.persistence;

import de.htwg.xiangqi.model.Board;

public class SaveGame_Wrapper{
	private String name;
	private Board saveGame;
	
	/*CONSTRUCTOR*/
	public SaveGame_Wrapper(String name, Board saveGame){
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
	public Board getSaveGame() {
		return saveGame;
	}
	public void setSaveGame(Board saveGame) {
		this.saveGame = saveGame;
	}
	
	
	/*IMPORTANT INHERITED METHODES*/
	@Override
	public boolean equals(Object sgw){
		if(this.name.equals(((SaveGame_Wrapper) sgw).getName())){
			return true;
		}
		return false;
	}
	
	@Override
	public String toString(){
		return name;
	}
	
	
}
