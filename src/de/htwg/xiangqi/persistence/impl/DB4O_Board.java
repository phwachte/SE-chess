package de.htwg.xiangqi.persistence.impl;

import java.util.Date;
import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

import de.htwg.xiangqi.controller.IBoardManager;
import de.htwg.xiangqi.model.Board;
import de.htwg.xiangqi.persistence.SaveGame_Wrapper;
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
			db.store(new SaveGame_Wrapper(d.toString(), (IBoardManager)obj));
		} finally {
			db.close();
		}
	}

	@Override
	public Object read(final String name, boolean wrapper) {
		List <SaveGame_Wrapper> list = db.query(new Predicate<SaveGame_Wrapper>(){
			public boolean match(SaveGame_Wrapper bw){
				return bw.getName().equals(name);
			}
		});
		
		return wrapper ? list.get(0) : list.get(0).getSaveGame();
	}

	@Override
	public void delete(String name) {
		try {
			db.delete(read(name, true));
		} finally {
			db.close();
		}
	}
	
	
	
	
	
	

}
