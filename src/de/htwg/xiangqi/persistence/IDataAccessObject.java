package de.htwg.xiangqi.persistence;

public interface IDataAccessObject {

	void createOrUpdate(Object obj);
	
	Object read(Object obj);
	
	void delete(Object obj);
}
