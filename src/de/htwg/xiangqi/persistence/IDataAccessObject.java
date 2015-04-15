package de.htwg.xiangqi.persistence;

public interface IDataAccessObject {

	void createOrUpdate(Object obj);
	
	void delete(Object obj);

	Object read(Object obj, boolean wrapper);
}
