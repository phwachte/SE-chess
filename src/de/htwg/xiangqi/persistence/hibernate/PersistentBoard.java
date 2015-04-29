package de.htwg.xiangqi.persistence.hibernate;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "board")
public class PersistentBoard implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int boardID;

	@OneToMany(mappedBy = "board")
	@Column(name = "squares")
	private List<PersistentSquare> squares;

	@Column(name = "moveCounter")
	private int moveCounter;

	// @Column(name = "redGeneral")
	// private Piece redGeneral;
	//
	// @Column(name = "blackGeneral")
	// private Piece blackGeneral;

	/**
	 * create a new board with ten rows and nine columns.
	 */
	public PersistentBoard() {
	}

	public List<PersistentSquare> getSquare() {
		return squares;
	}

	public void setSquare(List<PersistentSquare> square) {
		this.squares = square;
	}

	public int getBoardID() {
		return boardID;
	}

	public void setBoardID(int boardID) {
		this.boardID = boardID;
	}

	public int getMoveCounter() {
		return this.moveCounter;
	}

	public void setMoveCounter(int moveCounter) {
		this.moveCounter = moveCounter;
	}

	// public Piece getRedGeneral() {
	// return redGeneral;
	// }
	//
	// public void setRedGeneral(Piece redGeneral) {
	// this.redGeneral = redGeneral;
	// }
	//
	// public Piece getBlackGeneral() {
	// return blackGeneral;
	// }
	//
	// public void setBlackGeneral(Piece blackGeneral) {
	// this.blackGeneral = blackGeneral;
	// }

}
