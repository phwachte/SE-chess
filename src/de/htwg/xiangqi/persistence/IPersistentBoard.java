package de.htwg.xiangqi.persistence;

import java.util.List;

public interface IPersistentBoard {

	public String getBoardID();
	
	public void setBoardID(String boardID);

	public List<IPersistentPiece> getPieces();

	public void setPieces(List<IPersistentPiece> pieces);

	public int getMoveCounter();

	public void setMoveCounter(int moveCounter);
}
