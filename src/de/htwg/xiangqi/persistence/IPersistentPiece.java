package de.htwg.xiangqi.persistence;

import de.htwg.xiangqi.model.Piece.Player;

public interface IPersistentPiece {
	
	public int getPieceID();

	public void setPieceID(int pieceID);

	public int getRow();

	public void setRow(int row);

	public int getColumn();

	public void setColumn(int column);

	public char getPieceType();

	public void setPieceType(char pieceType);

	public Player getPlayer();

	public void setPlayer(Player player);

	public boolean isCaptured();

	public void setCaptured(boolean isCaptured);

}
