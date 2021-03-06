package de.htwg.xiangqi.persistence.couchdb;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
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
import de.htwg.xiangqi.model.Piece.Player;
import de.htwg.xiangqi.model.PieceAdvisor;
import de.htwg.xiangqi.model.PieceCannon;
import de.htwg.xiangqi.model.PieceChariot;
import de.htwg.xiangqi.model.PieceElephant;
import de.htwg.xiangqi.model.PieceGeneral;
import de.htwg.xiangqi.model.PieceHorse;
import de.htwg.xiangqi.model.PieceSoldier;
import de.htwg.xiangqi.model.Square;
import de.htwg.xiangqi.persistence.IDataAccessObject;

public class CouchDBDAO implements IDataAccessObject {

	private CouchDbConnector db = null;
	private Logger logger = Logger
			.getLogger("de.htwg.xiangqi.persistence.couchdb");
	private Piece redGeneral = null;
	private Piece blackGeneral = null;

	public CouchDBDAO() {
		HttpClient client = null;
		try {
			client = new StdHttpClient.Builder().url("http://127.0.0.1:5984/")
					.build();
		} catch (MalformedURLException e) {
			logger.info((String) e.toString());
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
			boards.add(copyBoardCDB(pBoard));
		}
		return boards;
	}

	private Board copyBoardCDB(PersistentBoard pBoard) {
		Board b = new Board();
		b.setSessionName(pBoard.getBoardID());
		b.setMoveCounter(pBoard.getMoveCounter());
		Square[][] sq = new Square[Board.getMaxRow()][Board.getMaxCol()];
		for (PersistentPiece pPiece : pBoard.getPieces()) {
			setPieceToBoardCDB(sq, pPiece);
		}
		b.setSquareMatrix(sq);
		b.setRedGeneral(redGeneral);
		b.setBlackGeneral(blackGeneral);
		b.fillBoard();
		return b;
	}

	private void setPieceToBoardCDB(Square[][] sq, PersistentPiece pPiece) {
		Piece p = null;
		int row = pPiece.getRow();
		int col = pPiece.getColumn();
		Player player = pPiece.getPlayer();
		char pieceTypeCDB = pPiece.getPieceType();
		switch (pieceTypeCDB) {
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
			p = getGeneralCDB(row, col, player);
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
	
	private Piece getGeneralCDB(int r, int c, Player player) {
		PieceGeneral pg;
		if (player == Player.RED) {
			pg = new PieceGeneral(r, c, player);
			redGeneral = pg;
		} else {
			pg = new PieceGeneral(r, c, player);
			blackGeneral = pg;
		}
		return pg;
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
		/* new database entry, else update */
		try {
			if (pBoard == null) {
				db.create(board.getSessionName(), copyBoard(board, pBoard));
			} else {
				db.update(copyBoard(board, pBoard));
			}
		} catch (CloneNotSupportedException ex) {
			logger.info((String) ex.toString());
		}
	}

	private PersistentBoard copyBoard(Board board, PersistentBoard pB)
			throws CloneNotSupportedException {
		PersistentBoard pBoard;
		String boardID = board.getSessionName();
		Square[][] sq = board.clone().getSquareMatrix();
		/* new database entry, else update */
		if (pB == null) {
			pBoard = new PersistentBoard();
		} else {
			pBoard = pB;
		}
		pBoard.setPieces(getPersistentPieceList(sq));
		pBoard.setBoardID(boardID);
		pBoard.setMoveCounter(board.getMoveCounter());
		return pBoard;
	}

	private List<PersistentPiece> getPersistentPieceList(Square[][] sq) {
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

}