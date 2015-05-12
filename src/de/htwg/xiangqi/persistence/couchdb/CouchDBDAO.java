package de.htwg.xiangqi.persistence.couchdb;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.ViewQuery;
import org.ektorp.ViewResult;
import org.ektorp.ViewResult.Row;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbInstance;

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
import de.htwg.xiangqi.persistence.couchdb.PersistentBoard;
import de.htwg.xiangqi.persistence.couchdb.PersistentPiece;
import de.htwg.xiangqi.persistence.IDataAccessObject;
import de.htwg.xiangqi.persistence.SaveGame_Wrapper;

public class CouchDBDAO implements IDataAccessObject {

	private CouchDbConnector db = null;

	public CouchDBDAO() {
		HttpClient client = null;
		try {
//			client = new StdHttpClient.Builder().url(
//					"http://lenny2.in.htwg-konstanz.de:5984").build();
			client = new StdHttpClient.Builder().url(
					"http://127.0.0.1:5984/").build();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		CouchDbInstance dbInstance = new StdCouchDbInstance(client);
		db = dbInstance.createConnector("xiangqi_database", true);
	}

	@Override
	public void delete(String boardID) {
		PersistentBoard pBoard = db.find(PersistentBoard.class, boardID);
		if (pBoard != null) {
			db.delete(pBoard);
		}
	}

	@Override
	public List<SaveGame_Wrapper> read(String name) {
		/* auskommentiert: für den Fall ohne SaveGame_Wrapper */
		// List<Board> boards = new ArrayList<Board>();
		// ViewQuery vQuery = new ViewQuery().allDocs();
		// ViewResult vResult = db.queryView(vQuery);
		// for (Row row : vResult.getRows()) {
		// PersistentBoard pBoard = db
		// .find(PersistentBoard.class, row.getId());
		// boards.add(copyBoard(pBoard));
		// }
		// return boards;

		List<SaveGame_Wrapper> list = new ArrayList<SaveGame_Wrapper>();
		ViewQuery vQuery = new ViewQuery().allDocs();
		ViewResult vResult = db.queryView(vQuery);
		for (Row row : vResult.getRows()) {
			PersistentBoard pBoard = db
					.find(PersistentBoard.class, row.getId());
			Board board = copyBoard(pBoard);
			SaveGame_Wrapper sgw = new SaveGame_Wrapper(board.getId(), board);
			list.add(sgw);
		}
		return list;
	}

	@Override
	public void close() {
		// gibt es für den CouchDbConnector nicht
	}

	@Override
	public void createOrUpdate(Board board) {
		PersistentBoard pBoard = db.find(PersistentBoard.class, board.getId());
		if (pBoard == null) { // new database entry, else update
			db.create(board.getId(), copyBoard(board, pBoard));
		} else {
			db.update(copyBoard(board, pBoard));
		}
	}

	private PersistentBoard copyBoard(Board board, PersistentBoard pBoard) {
		String boardID = board.getId();
		Square[][] sq = board.clone().getSquareMatrix();
		if (pBoard == null) { // new database entry, else update
			pBoard = new PersistentBoard();
		}
		pBoard.setPieces(getPersistentPieceList(sq, pBoard));
		pBoard.setBoardID(boardID);
		pBoard.setMoveCounter(board.getMoveCounter());
		return pBoard;
	}

	private List<PersistentPiece> getPersistentPieceList(Square[][] sq,
			PersistentBoard pBoard) {
		List<PersistentPiece> list = new ArrayList<PersistentPiece>();
		for (int i = 0; i < Board.getMaxRow(); ++i) {
			for (int o = 0; o < Board.getMaxCol(); ++o) {
				Piece tmp = sq[i][o].getPiece();
				if (tmp != null) {
					PersistentPiece pPiece = new PersistentPiece(
							tmp.getPosRow(), tmp.getPosColumn(),
							tmp.getPieceType(), tmp.getPlayer());
					pPiece.setCaptured(tmp.getIsCaptured());
					list.add(pPiece);
				}
			}
		}
		return list;
	}

	private Board copyBoard(PersistentBoard pBoard) {
		Board b = new Board();
		b.setId(pBoard.getBoardID());
		b.setMoveCounter(pBoard.getMoveCounter());
		Square[][] sq = new Square[Board.getMaxRow()][Board.getMaxCol()];
		Piece redGeneral = null, blackGeneral = null;
		for (PersistentPiece pPiece : pBoard.getPieces()) {
			setPieceToBoard(sq, pPiece, redGeneral, blackGeneral);
		}
		b.setSquareMatrix(sq);
		b.setRedGeneral(redGeneral);
		b.setBlackGeneral(blackGeneral);
		b.fillBoard();
		return b;
	}

	private void setPieceToBoard(Square[][] sq, PersistentPiece pPiece,
			Piece rG, Piece bG) {
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
				rG = p;
			} else {
				p = new PieceGeneral(row, col, player);
				bG = p;
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