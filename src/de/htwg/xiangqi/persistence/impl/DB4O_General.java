package de.htwg.xiangqi.persistence.impl;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

import de.htwg.xiangqi.persistence.IDataAccessObject;

public class DB4O_General implements IDataAccessObject {
	
	private ObjectContainer db;
	
	public DB4O_General() {
		db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "xiangqi.db");
	}

	@Override
	public void createOrUpdate(Object obj) {
		try {
			db.store(obj);
		} finally {
			db.close();
		}
	}

	@Override
	public Object read(Object obj) {
		
		return null;
	}

	@Override
	public void delete(Object obj) {
		try {
			db.delete(obj);
		} finally {
			db.close();
		}
	}
	
	

}
