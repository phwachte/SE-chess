package de.htwg.xiangqi.persistence.couchdb;

import java.util.List;

import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;

import de.htwg.xiangqi.persistence.IPersistentBoard;
import de.htwg.xiangqi.persistence.IPersistentPiece;

public class PersistentBoard extends CouchDbDocument implements IPersistentBoard {
	private static final long serialVersionUID = 1L;

	@TypeDiscriminator
	private String boardID;
	private List<IPersistentPiece> pieces;
	private int moveCounter;

	public PersistentBoard() {
	}

	public String getBoardID() {
		return boardID;
	}

	public void setBoardID(String boardID) {
		this.boardID = boardID;
	}

	public List<IPersistentPiece> getPieces() {
		return pieces;
	}

	public void setPieces(List<IPersistentPiece> pieces) {
		this.pieces = pieces;
	}

	public int getMoveCounter() {
		return this.moveCounter;
	}

	public void setMoveCounter(int moveCounter) {
		this.moveCounter = moveCounter;
	}

}
