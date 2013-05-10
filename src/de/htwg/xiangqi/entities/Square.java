package de.htwg.xiangqi.entities;

public class Square {

	private Piece piece;

	public Square() {
	}

	public Square(Piece p) {
		this.piece = p;
	}

	public void setPiece(Piece p) {
		this.piece = p;
	}

	public Piece getPiece() {
		return this.piece;
	}
}
