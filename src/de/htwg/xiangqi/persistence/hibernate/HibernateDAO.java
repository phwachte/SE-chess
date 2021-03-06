package de.htwg.xiangqi.persistence.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
import de.htwg.xiangqi.persistence.hibernate.PersistentBoard;
import de.htwg.xiangqi.persistence.hibernate.PersistentPiece;

public class HibernateDAO implements IDataAccessObject {
	
	private Logger logger = Logger.getLogger("de.htwg.xiangqi.persistence.hibernate");
	private Piece redGeneral = null;
	private Piece blackGeneral = null;

	public HibernateDAO() {
	}

	@Override
	public void delete(String boardID) {
		Transaction tx = null;
		Session session = null;
		try {
			session = HibernateUtil.getInstance().getCurrentSession();
			tx = session.beginTransaction();
			PersistentBoard pBoard = (PersistentBoard) session.get(
					PersistentBoard.class, boardID);
			for (PersistentPiece p : pBoard.getPieces()) {
				session.delete(p);
			}
			session.delete(pBoard);
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();
			}
			logger.info((String)ex.toString());
		}
	}

	@Override
	public List<Board> read(String name) {
		Session session = HibernateUtil.getInstance().getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(PersistentBoard.class);
		@SuppressWarnings("unchecked")
		List<PersistentBoard> results = criteria.list();
		List<Board> boards = new ArrayList<Board>();
		for (PersistentBoard pBoard : results) {
			Board board = copyBoard(pBoard);
			boards.add(board);
		}
		return boards;
	}
	
	private Board copyBoard(PersistentBoard pBoard) {
		Board b = new Board();
		b.setSessionName(pBoard.getBoardID());
		b.setMoveCounter(pBoard.getMoveCounter());
		Square[][] sq = new Square[Board.getMaxRow()][Board.getMaxCol()];
		for (PersistentPiece pPiece : pBoard.getPieces()) {
			setPieceToBoard(sq, pPiece);
		}
		b.setSquareMatrix(sq);
		b.setRedGeneral(redGeneral);
		b.setBlackGeneral(blackGeneral);
		b.fillBoard();
		return b;
	}

	private void setPieceToBoard(Square[][] sq, PersistentPiece pPiece) {
		Piece p = null;
		int row = pPiece.getRow();
		int col = pPiece.getColumn();
		Player player = pPiece.getPlayer();
		char pieceType = pPiece.getPieceType();
		switch (pieceType) {
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
			p = getGeneral(row, col, player);
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
	
	private Piece getGeneral(int r, int c, Player player) {
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

	@Override
	public void close() {
		Session session = HibernateUtil.getInstance().getCurrentSession();
		session.close();
	}

	@Override
	public void createOrUpdate(Board board) {
		Transaction tx = null;
		Session session = null;
		try {
			session = HibernateUtil.getInstance().getCurrentSession();
			tx = session.beginTransaction();
			PersistentBoard pBoard = copyBoard(board);

			boolean newEntry = session.get(PersistentBoard.class,
					board.getSessionName()) == null ? true : false;

			if (newEntry) {
				session.save(pBoard);
			} else {
				session.update(pBoard);
			}
			for (PersistentPiece pPiece : pBoard.getPieces()) {
				if (newEntry) {
					session.save(pPiece);
				} else {
					session.update(pPiece);
				}
			}
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();
			}
			logger.info((String)ex.toString());
		} catch (CloneNotSupportedException e) {
			logger.info((String)e.toString());
		}
	}

	private PersistentBoard copyBoard(Board board) throws CloneNotSupportedException {
		String boardID = board.getSessionName();
		Square[][] sq = board.clone().getSquareMatrix();
		PersistentBoard pBoard;
		Session session = HibernateUtil.getInstance().getCurrentSession();
		pBoard = (PersistentBoard) session.get(PersistentBoard.class, boardID);
		/*new database entry, else update*/
		if (pBoard == null) { 
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
					pPiece.setBoard(pBoard);
					list.add(pPiece);
				}
			}
		}
		return list;
	}

}
