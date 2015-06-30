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
import de.htwg.xiangqi.model.Square;
import de.htwg.xiangqi.persistence.BoardMethodsForDB;
import de.htwg.xiangqi.persistence.IDataAccessObject;
import de.htwg.xiangqi.persistence.IPersistentPiece;

public class CouchDBDAO implements IDataAccessObject {

	private CouchDbConnector db = null;

	public CouchDBDAO() {
		HttpClient client = null;
		try {
			client = new StdHttpClient.Builder().url("http://127.0.0.1:5984/")
					.build();
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
	public List<Board> read(String name) {
		List<Board> boards = new ArrayList<Board>();
		ViewQuery vQuery = new ViewQuery().allDocs();
		ViewResult vResult = db.queryView(vQuery);
		for (Row row : vResult.getRows()) {
			PersistentBoard pBoard = db
					.find(PersistentBoard.class, row.getId());
			boards.add(BoardMethodsForDB.copyBoard(pBoard));
		}
		return boards;
	}

	/*
	 * leer implementiert fuer couchdb
	 */
	@Override
	public void close() {
	}

	@Override
	public void createOrUpdate(Board board) {
		PersistentBoard pBoard = db.find(PersistentBoard.class,
				board.getSessionName());
		/*new database entry, else update*/
		try{
			if (pBoard == null) {
				db.create(board.getSessionName(), copyBoard(board, pBoard));
			} else {
				db.update(copyBoard(board, pBoard));
			}
		}catch(CloneNotSupportedException ex){
			ex.printStackTrace();
		}
	}

	private PersistentBoard copyBoard(Board board, PersistentBoard pBoard)
			throws CloneNotSupportedException {
		String boardID = board.getSessionName();
		Square[][] sq = board.clone().getSquareMatrix();
		/* new database entry, else update */
		if (pBoard == null) {
			pBoard = new PersistentBoard();
		}
		pBoard.setPieces(getPersistentPieceList(sq));
		pBoard.setBoardID(boardID);
		pBoard.setMoveCounter(board.getMoveCounter());
		return pBoard;
	}

	private List<IPersistentPiece> getPersistentPieceList(Square[][] sq) {
		List<IPersistentPiece> list = new ArrayList<IPersistentPiece>();
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

}