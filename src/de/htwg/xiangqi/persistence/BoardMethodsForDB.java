package de.htwg.xiangqi.persistence;

import de.htwg.xiangqi.model.Board;
import de.htwg.xiangqi.model.Piece;
import de.htwg.xiangqi.model.PieceAdvisor;
import de.htwg.xiangqi.model.PieceCannon;
import de.htwg.xiangqi.model.PieceChariot;
import de.htwg.xiangqi.model.PieceElephant;
import de.htwg.xiangqi.model.PieceGeneral;
import de.htwg.xiangqi.model.PieceHorse;
import de.htwg.xiangqi.model.PieceSoldier;
import de.htwg.xiangqi.model.Square;
import de.htwg.xiangqi.model.Piece.Player;

public class BoardMethodsForDB{
	
	private static Piece redGeneral = null;
	private static Piece blackGeneral = null;
	
	private BoardMethodsForDB(){}
	
	public static Board copyBoard(IPersistentBoard pBoard) {
		Board b = new Board();
		b.setSessionName(pBoard.getBoardID());
		b.setMoveCounter(pBoard.getMoveCounter());
		Square[][] sq = new Square[Board.getMaxRow()][Board.getMaxCol()];
		for (IPersistentPiece pPiece : pBoard.getPieces()) {
			setPieceToBoard(sq, pPiece);
		}
		b.setSquareMatrix(sq);
		b.setRedGeneral(redGeneral);
		b.setBlackGeneral(blackGeneral);
		b.fillBoard();
		return b;
	}

	private static void setPieceToBoard(Square[][] sq, IPersistentPiece pPiece) {
		Piece p = null;
		int row = pPiece.getRow();
		int col = pPiece.getColumn();
		Player player = pPiece.getPlayer();
		switch (pPiece.getPieceType()) {
		case 'A':
			p = new PieceAdvisor(row, col, player);
			break;
		case 'C':
			p = new PieceCannon(row, col, player);
			break;
		case 'R':
			p = new PieceChariot(row, col, player);
			break;
		case 'E':
			p = new PieceElephant(row, col, player);
			break;
		case 'G':
			if (player == Player.RED) {
				p = new PieceGeneral(row, col, player);
				redGeneral = p;
			} else {
				p = new PieceGeneral(row, col, player);
				blackGeneral = p;
			}
			break;
		case 'H':
			p = new PieceHorse(row, col, player);
			break;
		case 'S':
			p = new PieceSoldier(row, col, player);
			break;
		}
		sq[row][col] = new Square(p);
	}

}
