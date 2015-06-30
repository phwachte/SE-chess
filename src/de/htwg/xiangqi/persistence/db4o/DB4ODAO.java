package de.htwg.xiangqi.persistence.db4o;

import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

import de.htwg.xiangqi.model.Board;
import de.htwg.xiangqi.persistence.IDataAccessObject;

public class DB4ODAO implements IDataAccessObject {

	private static ObjectContainer db = Db4oEmbedded.openFile("xiangqi.db");
	private Logger logger = Logger
			.getLogger("de.htwg.xiangqi.persistence.db40.DB4ODAO");

	public DB4ODAO() {}

	@Override
	public void createOrUpdate(Board board) {
		db.store(board);
	}

	@Override
	public List<Board> read(final String pattern) {
		List<Board> list = db.query(new Predicate<Board>() {
			private static final long serialVersionUID = 1L;
			public boolean match(Board b) {
				if (Pattern.matches(pattern, b.getSessionName())) {
					logger.info("found object, name: " + b.getSessionName());
				}
				return Pattern.matches(pattern, b.getSessionName());
			}
		});
		return list;
	}

	@Override
	public void delete(String name) {
		db = Db4oEmbedded.openFile("xiangqi.db");
		List<Board> toIter = read(name);
		for (Board sgw : toIter) {
			db.delete(sgw.getSessionName());
		}
	}

	@Override
	public void close() {
		db.close();
	}
}
