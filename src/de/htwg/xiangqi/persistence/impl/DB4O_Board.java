package de.htwg.xiangqi.persistence.impl;

import java.util.Date;
import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

import de.htwg.xiangqi.controller.IBoardManager;
import de.htwg.xiangqi.persistence.IDataAccessObject;
import de.htwg.xiangqi.persistence.SaveGame_Wrapper;

public class DB4O_Board implements IDataAccessObject {

	private ObjectContainer db;

	public DB4O_Board() {
		db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),
				"xiangqi.db");
	}

	@Override
	public void createOrUpdate(Object obj) {
		try {
			Date d = new Date();
			// db.store(new Object(){private int b = 2;});
			db.store(new SaveGame_Wrapper(d.toString(), (IBoardManager) obj));
			System.out.println("bla");
		} finally {
			db.close();
			System.out.println("bla2");
		}
	}

	@Override
	public List<SaveGame_Wrapper> read(final String pattern) {
		List<SaveGame_Wrapper> list = db
				.query(new Predicate<SaveGame_Wrapper>() {
					private static final long serialVersionUID = 1L;
					public boolean match(SaveGame_Wrapper bw) {
						return bw.getName().matches(pattern);
					}
				});

		return list;
	}

	@Override
	public void delete(String name) {
		try {
			List<SaveGame_Wrapper> toIter = read(name);
			for (SaveGame_Wrapper sgw : toIter) {
				db.delete(sgw.getName());
			}
		} finally {
			db.close();
		}
	}

}
