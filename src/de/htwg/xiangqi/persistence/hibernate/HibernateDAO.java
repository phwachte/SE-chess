package de.htwg.xiangqi.persistence.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.htwg.xiangqi.model.Board;
import de.htwg.xiangqi.model.Square;
import de.htwg.xiangqi.persistence.IDataAccessObject;
import de.htwg.xiangqi.persistence.SaveGame_Wrapper;

public class HibernateDAO implements IDataAccessObject {

	@Override
	public void delete(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<SaveGame_Wrapper> read(String name) {
		
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
		Square[][] sq = b.clone().getSquareMatrix();
		
		ArrayList<PersistentSquare> list = new ArrayList<PersistentSquare>();
		for (int i = 0; i < Board.getMaxRow(); ++i) {
			for (int o = 0; o < Board.getMaxCol(); ++o) {
				Square tmp =sq[i][o];
				PersistentSquare psq =new PersistentSquare();
				psq.setPiece(tmp.getPiece());
				list.add(psq);
			}
		}
		pb.setSquare(list);

//		short generalCount = 0;
//		Piece p;
//		for (int i = 0; i < Board.getMaxRow(); ++i) {
//			for (int o = 0; o < Board.getMaxCol(); ++o) {
//				p = b.getSquareMatrix()[i][o].getPiece();
//				if (p instanceof PieceGeneral) {
//					if (p.getPlayer() == Player.BLACK) {
//						pb.setBlackGeneral(p);
//					} else {
//						pb.setRedGeneral(p);
//					}
//					if (++generalCount == 2) {
//						break;
//					}
//				}
//			}
//			if (generalCount == 2) {
//				break;
//			}
//		}
		return pb;
	}
}
