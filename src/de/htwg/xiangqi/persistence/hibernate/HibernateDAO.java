package de.htwg.xiangqi.persistence.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.htwg.xiangqi.model.Board;
import de.htwg.xiangqi.model.Piece;
import de.htwg.xiangqi.model.Piece.Player;
import de.htwg.xiangqi.model.PieceGeneral;
import de.htwg.xiangqi.persistence.IDataAccessObject;
import de.htwg.xiangqi.persistence.SaveGame_Wrapper;

public class HibernateDAO implements IDataAccessObject {

	@Override
	public void delete(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<SaveGame_Wrapper> read(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public void createOrUpdate(Board board) {
		Transaction tx = null;
		Session session = null;
		try {
			session = HibernateUtil.getInstance().getCurrentSession();
			tx = session.beginTransaction();
			PersistentBoard pBoard = copyBoard(board);
			session.saveOrUpdate(pBoard);
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			throw new RuntimeException(ex.getMessage());

		}
	}

	private static PersistentBoard copyBoard(Board b) {
		PersistentBoard pb = new PersistentBoard();
		pb.setMoveCounter(b.getMoveCounter());
		pb.setBoard(((Board) b.clone()).getSquareMatrix());

		short generalCount = 0;
		Piece p;
		for (int i = 0; i < Board.getMaxRow(); ++i) {
			for (int o = 0; o < Board.getMaxCol(); ++o) {
				p = b.getSquareMatrix()[i][o].getPiece();
				if (p instanceof PieceGeneral) {
					if (p.getPlayer() == Player.BLACK) {
						pb.setBlackGeneral(p);
					} else {
						pb.setRedGeneral(p);
					}
					if (++generalCount == 2) {
						break;
					}
				}
			}
			if (generalCount == 2) {
				break;
			}
		}
		return pb;
	}
}
