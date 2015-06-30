package de.htwg.xiangqi.persistence.couchdb;

import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;

import de.htwg.xiangqi.model.Piece.Player;
import de.htwg.xiangqi.persistence.IPersistentPiece;

public class PersistentPiece extends CouchDbDocument implements IPersistentPiece {
	private static final long serialVersionUID = 1L;
	
	@TypeDiscriminator
	private int pieceIDCDB;
	private int rowCDB;
	private int columnCDB;
	private char pieceTypeCDB;
	private Player playerCDB;
	private boolean isCapturedCDB;

	public PersistentPiece() {
	}

	public PersistentPiece(int r, int c, char pt, Player p) {
		rowCDB = r;
		columnCDB = c;
		pieceTypeCDB = pt;
		playerCDB = p;
	}

	public int getPieceID() {
		return pieceIDCDB;
	}

	public void setPieceID(int pieceID) {
		this.pieceIDCDB = pieceID;
	}

	public int getRow() {
		return rowCDB;
	}

	public void setRow(int row) {
		this.rowCDB = row;
	}

	public int getColumn() {
		return columnCDB;
	}

	public void setColumn(int column) {
		this.columnCDB = column;
	}

	public char getPieceType() {
		return pieceTypeCDB;
	}

	public void setPieceType(char pieceType) {
		this.pieceTypeCDB = pieceType;
	}

	public Player getPlayer() {
		return playerCDB;
	}

	public void setPlayer(Player player) {
		this.playerCDB = player;
	}

	public boolean isCaptured() {
		return isCapturedCDB;
	}

	public void setCaptured(boolean isCaptured) {
		this.isCapturedCDB = isCaptured;
	}

}
