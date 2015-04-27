package de.htwg.xiangqi.persistence.hibernate;

import java.io.Serializable;
import javax.persistence.*;

import de.htwg.xiangqi.model.Piece;
import de.htwg.xiangqi.model.Square;

@Entity
@Table(name = "BOARD")
public class PersistentBoard implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int boardID;

	@Column(name = "boardMatrix")
	private Square[][] board;

	@Column(name = "moveCounter")
	private int moveCounter;

	@Column(name = "redGeneral")
	private Piece redGeneral;

	@Column(name = "blackGeneral")
	private Piece blackGeneral;

	/**
	 * create a new board with ten rows and nine columns.
	 */
	public PersistentBoard() {
	}

	public Square[][] getBoard() {
		return board;
	}

	public void setBoard(Square[][] board) {
		this.board = board;
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

	public Piece getRedGeneral() {
		return redGeneral;
	}

	public void setRedGeneral(Piece redGeneral) {
		this.redGeneral = redGeneral;
	}

	public Piece getBlackGeneral() {
		return blackGeneral;
	}

	public void setBlackGeneral(Piece blackGeneral) {
		this.blackGeneral = blackGeneral;
	}

}
