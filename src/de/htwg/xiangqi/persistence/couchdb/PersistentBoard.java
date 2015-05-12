package de.htwg.xiangqi.persistence.couchdb;

import java.util.List;

import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;

public class PersistentBoard extends CouchDbDocument {

	@TypeDiscriminator
	private String boardID;
	private List<PersistentPiece> pieces;
	private int moveCounter;

	public PersistentBoard() {
	}

	public String getBoardID() {
		return boardID;
	}

	public void setBoardID(String boardID) {
		this.boardID = boardID;
	}

	public List<PersistentPiece> getPieces() {
		return pieces;
	}

	public void setPieces(List<PersistentPiece> pieces) {
		this.pieces = pieces;
	}

	public int getMoveCounter() {
		return this.moveCounter;
	}

	public void setMoveCounter(int moveCounter) {
		this.moveCounter = moveCounter;
	}

}
