package de.htwg.xiangqi.persistence.impl;

import java.util.Date;
import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

import de.htwg.xiangqi.model.Board;
import de.htwg.xiangqi.persistence.IDataAccessObject;

public class DB4O_Board implements IDataAccessObject {
	
	private ObjectContainer db;
	
	public DB4O_Board() {
		db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "xiangqi.db");
	}

	@Override
	public void createOrUpdate(Object obj) {
		try {
			Date d = new Date();
			db.store(new Board_Wrapper(d.toString(), (Board)obj));
		} finally {
			db.close();
		}
	}

	@Override
	public Object read(final Object obj) {
		/*TODO native or prototype*/
		List <Board_Wrapper> list = db.query(new Predicate<Board_Wrapper>(){
			public boolean match(Board_Wrapper bw){
				return bw.getName().equals((String)obj);
			}
		});
		
		return list.get(0).getBoard();
	}

	@Override
	public void delete(Object obj) {
		try {
			/*TODO get the right non wrapper object to delete, then delete wrapper out of db*/
			db.delete(obj);
		} finally {
			db.close();
		}
	}
	
	
	
	private class Board_Wrapper{
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
	
	

}
