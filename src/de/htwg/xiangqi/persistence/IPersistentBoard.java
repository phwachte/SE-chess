package de.htwg.xiangqi.persistence;

import java.util.List;

public interface IPersistentBoard {

	String getBoardID();
	
	void setBoardID(String boardID);

	List<IPersistentPiece> getPieces();

	void setPieces(List<IPersistentPiece> pieces);

	int getMoveCounter();

	void setMoveCounter(int moveCounter);
}
