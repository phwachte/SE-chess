package de.htwg.xiangqi.persistence.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.htwg.xiangqi.model.Board;
import de.htwg.xiangqi.model.Piece;
import de.htwg.xiangqi.model.Square;
import de.htwg.xiangqi.persistence.BoardMethodsForDB;
import de.htwg.xiangqi.persistence.IDataAccessObject;
import de.htwg.xiangqi.persistence.IPersistentPiece;

public class HibernateDAO implements IDataAccessObject {

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
			for (IPersistentPiece p : pBoard.getPieces()) {
				session.delete(p);
			}
			session.delete(pBoard);
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();
			}
			ex.printStackTrace();
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
			Board board = BoardMethodsForDB.copyBoard(pBoard);
			boards.add(board);
		}
		return boards;
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
			for (IPersistentPiece pPiece : pBoard.getPieces()) {
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
			ex.printStackTrace();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
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

	private List<IPersistentPiece> getPersistentPieceList(Square[][] sq,
			PersistentBoard pBoard) {
		List<IPersistentPiece> list = new ArrayList<IPersistentPiece>();
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
