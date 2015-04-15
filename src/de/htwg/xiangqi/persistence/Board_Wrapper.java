package de.htwg.xiangqi.persistence;

import de.htwg.xiangqi.model.Board;

public class Board_Wrapper{
	private String name;
	private Board board;
	
	/*CONSTRUCTOR*/
	public Board_Wrapper(String name, Board board){
		this.name = name;
		this.board = board;
	}
	
	/*GETTERS AND SETTERS*/
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	
	
}
