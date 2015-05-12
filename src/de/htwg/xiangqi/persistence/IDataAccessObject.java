package de.htwg.xiangqi.persistence;

import java.util.List;
import de.htwg.xiangqi.model.Board;

public interface IDataAccessObject {
	
	void delete(String name);

	List<SaveGame_Wrapper> read(String boardID);
	
	void close();

	void createOrUpdate(Board board);
}

