package de.htwg.xiangqi.persistence.hibernate;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import de.htwg.xiangqi.persistence.IPersistentBoard;
import de.htwg.xiangqi.persistence.IPersistentPiece;

@Entity
@Table(name = "board")
public class PersistentBoard implements Serializable, IPersistentBoard {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private String boardID;

	@OneToMany(mappedBy = "board")
	@Column(name = "piece")
	private List<IPersistentPiece> pieces;

	@Column(name = "moveCounter")
	private int moveCounter;

	/**
	 * create a new board with ten rows and nine columns.
	 */
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
