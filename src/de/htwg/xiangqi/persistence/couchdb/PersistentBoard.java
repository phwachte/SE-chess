package de.htwg.xiangqi.persistence.couchdb;

import java.util.List;

import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;

import de.htwg.xiangqi.persistence.IPersistentBoard;
import de.htwg.xiangqi.persistence.IPersistentPiece;

public class PersistentBoard extends CouchDbDocument implements IPersistentBoard {
	private static final long serialVersionUID = 1L;

	@TypeDiscriminator
	private String boardIDCDB;
	private List<IPersistentPiece> piecesCDB;
	private int moveCounterCDB;

	public PersistentBoard() {
	}

	public String getBoardID() {
		return boardIDCDB;
	}

	public void setBoardID(String boardID) {
		this.boardIDCDB = boardID;
	}

	public List<IPersistentPiece> getPieces() {
		return piecesCDB;
	}

	public void setPieces(List<IPersistentPiece> pieces) {
		this.piecesCDB = pieces;
	}

	public int getMoveCounter() {
		return this.moveCounterCDB;
	}

	public void setMoveCounter(int moveCounter) {
		this.moveCounterCDB = moveCounter;
	}

}
