package de.htwg.xiangqi.persistence;

public interface IDataAccessObject {

	void createOrUpdate(Object obj);
	
	void delete(String name);

	Object read(String name, boolean wrapper);
}
