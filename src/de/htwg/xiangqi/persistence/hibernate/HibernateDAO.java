package de.htwg.xiangqi.persistence.hibernate;

import java.util.List;

import de.htwg.xiangqi.model.Board;
import de.htwg.xiangqi.persistence.IDataAccessObject;
import de.htwg.xiangqi.persistence.SaveGame_Wrapper;

public class HibernateDAO implements IDataAccessObject {

	@Override
	public void delete(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<SaveGame_Wrapper> read(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createOrUpdate(Board obj) {
		// TODO Auto-generated method stub
		
	}

}
