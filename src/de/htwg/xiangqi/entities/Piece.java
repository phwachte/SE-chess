package de.htwg.xiangqi.entities;

public abstract class Piece {

	public enum Player {
		RED, BLACK
	}

	private int row;
	private int column;
	private final char pieceType;
	private Player player;

	protected Piece(int r, int c, char pt, Player p) {
		this.row = r;
		this.column = c;
		this.pieceType = pt;
		this.player = p;
	}

	public int getPosRow() {
		return this.row;
	}

	public int getPosColumn() {
		return this.column;
	}

	public void setPosition(int r, int c) {
		this.row = r;
		this.column = c;
	}

	public Player getPlayer() {
		return this.player;
	}

	public char getPieceType() {
		return this.pieceType;
	}
}
