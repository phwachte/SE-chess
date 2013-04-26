package de.htwg.xiangqi.entities;

public abstract class Piece {

	public enum Player {
		RED, BLACK
	}

	private int row;
	private int column;
	private char pieceType;
	private Player player;
	private boolean isTaken;

	protected Piece(int r, int c, char pt, Player p) {
		this.row = r;
		this.column = c;
		this.pieceType = pt;
		this.player = p;
		this.isTaken = false;
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

	public void setIsTaken(boolean b) {
		this.isTaken = b;
	}

	public boolean getIsTaken() {
		return this.isTaken;
	}

	public char getPieceType() {
		return this.pieceType;
	}
}
