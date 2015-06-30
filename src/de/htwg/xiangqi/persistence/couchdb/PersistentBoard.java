package de.htwg.xiangqi.persistence.couchdb;

import java.util.List;

import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;

import de.htwg.xiangqi.persistence.IPersistentBoard;
import de.htwg.xiangqi.persistence.IPersistentPiece;

public class PersistentBoard extends CouchDbDocument implements IPersistentBoard {
	private static final long serialVersionUID = 1L;

	@TypeDiscriminator
	private String boardID_CDB;
	private List<IPersistentPiece> pieces_CDB;
	private int moveCounter_CDB;

	public PersistentBoard() {
	}

	public String getBoardID() {
		return boardID_CDB;
	}

	public void setBoardID(String boardID) {
		this.boardID_CDB = boardID;
	}

	public List<IPersistentPiece> getPieces() {
		return pieces_CDB;
	}

	public void setPieces(List<IPersistentPiece> pieces) {
		this.pieces_CDB = pieces;
	}

	public int getMoveCounter() {
		return this.moveCounter_CDB;
	}

	public void setMoveCounter(int moveCounter) {
		this.moveCounter_CDB = moveCounter;
	}

}
