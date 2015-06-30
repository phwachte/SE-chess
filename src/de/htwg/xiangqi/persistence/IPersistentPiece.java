package de.htwg.xiangqi.persistence;

import de.htwg.xiangqi.model.Piece.Player;

public interface IPersistentPiece {
	
	int getPieceID();

	void setPieceID(int pieceID);

	int getRow();

	void setRow(int row);

	int getColumn();

	void setColumn(int column);

	char getPieceType();

	void setPieceType(char pieceType);

	Player getPlayer();

	void setPlayer(Player player);

	boolean isCaptured();

	void setCaptured(boolean isCaptured);

}
