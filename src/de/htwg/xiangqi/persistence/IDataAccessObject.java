package de.htwg.xiangqi.persistence;

import java.util.List;

public interface IDataAccessObject {

	void createOrUpdate(Object obj);
	
	void delete(String name);

	List<SaveGame_Wrapper> read(String name);
}
