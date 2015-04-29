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

import de.htwg.xiangqi.model.Piece;

@Entity
@Table(name = "SQUARE")
public class PersistentSquare implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int squareID;
	
	@Column(name = "piece")
	private Piece piece;
	
	@ManyToOne
	@JoinColumn(name = "boardid")
	public PersistentBoard board;

	public int getSquareID() {
		return squareID;
	}

	public void setSquareID(int squareID) {
		this.squareID = squareID;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

}
