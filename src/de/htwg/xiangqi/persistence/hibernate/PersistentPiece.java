package de.htwg.xiangqi.persistence.hibernate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import de.htwg.xiangqi.model.Piece.Player;

@Entity
@Table(name = "piece")
public class PersistentPiece implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pieceID;
	
	@Column(name = "row_piece")
	private int row;
	
	@Column(name = "column_piece")
	private int column;
	
	@Column(name = "piece_type")
	private char pieceType;
	
	@Column(name = "player")
	private Player player;
	
	@Column(name = "is_captured")
	private boolean isCaptured;
	
	@ManyToOne
	@JoinColumn(name = "boardid")
	public PersistentBoard board;

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

	public PersistentBoard getBoard() {
		return board;
	}

	public void setBoard(PersistentBoard board) {
		this.board = board;
	}

}
