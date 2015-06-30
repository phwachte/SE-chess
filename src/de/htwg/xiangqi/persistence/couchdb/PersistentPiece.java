package de.htwg.xiangqi.persistence.couchdb;

import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;

import de.htwg.xiangqi.model.Piece.Player;
import de.htwg.xiangqi.persistence.IPersistentPiece;

public class PersistentPiece extends CouchDbDocument implements IPersistentPiece {
	private static final long serialVersionUID = 1L;
	
	@TypeDiscriminator
	private int pieceID;
	private int row;
	private int column;
	private char pieceType;
	private Player player;
	private boolean isCaptured;

	public PersistentPiece() {
	}

	public PersistentPiece(int r, int c, char pt, Player p) {
		row = r;
		column = c;
		pieceType = pt;
		player = p;
	}

	public int getPieceID() {
		return pieceID;
	}

	public void setPieceID(int pieceID) {
		this.pieceID = pieceID;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public char getPieceType() {
		return pieceType;
	}

	public void setPieceType(char pieceType) {
		this.pieceType = pieceType;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public boolean isCaptured() {
		return isCaptured;
	}

	public void setCaptured(boolean isCaptured) {
		this.isCaptured = isCaptured;
	}

}
