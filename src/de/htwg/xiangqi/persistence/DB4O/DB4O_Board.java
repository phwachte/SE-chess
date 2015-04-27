package de.htwg.xiangqi.persistence.DB4O;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

import de.htwg.xiangqi.model.Board;
import de.htwg.xiangqi.persistence.IDataAccessObject;
import de.htwg.xiangqi.persistence.SaveGame_Wrapper;

public class DB4O_Board implements IDataAccessObject {

	private ObjectContainer db;

	public DB4O_Board() {
		db = Db4oEmbedded.openFile("xiangqi.db");
	}

	@Override

	public void createOrUpdate(Board board) {
			Date d = new Date();
			//db.store(new Object(){private int b = 2;public String getName(){return "*";}});
			SaveGame_Wrapper sgw = new SaveGame_Wrapper(d.toString(), (Board) board);
			db.store(sgw);
	}

	@Override
	public List<SaveGame_Wrapper> read(final String pattern) {
		List<SaveGame_Wrapper> list = db
				.query(new Predicate<SaveGame_Wrapper>() {
					public boolean match(SaveGame_Wrapper bw) {
						if (Pattern.matches(pattern, bw.getName()))
							System.out.println("found object, name: "
									+ bw.getName());
						return Pattern.matches(pattern, bw.getName());
					}
				});
		return list;
	}

	@Override
	public void delete(String name) {
			db = Db4oEmbedded.openFile("xiangqi.db");
			List<SaveGame_Wrapper> toIter = read(name);
			for (SaveGame_Wrapper sgw : toIter) {
				db.delete(sgw.getName());
			}
	}

	@Override
	public void close() {
		db.close();
	}
}
