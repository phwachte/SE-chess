package de.htwg.xiangqi.persistence.impl;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

import de.htwg.xiangqi.controller.IBoardManager;
import de.htwg.xiangqi.persistence.IDataAccessObject;
import de.htwg.xiangqi.persistence.SaveGame_Wrapper;

public class DB4O_Board implements IDataAccessObject {
	
	private ObjectContainer db;
	
	public DB4O_Board() {
		db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "xiangqi.db");
	}

	@Override
	public void createOrUpdate(Object obj) {
		try {
			Date d = new Date();
			//db.store(new Object(){private int b = 2;public String getName(){return "*";}});
			db.store(new SaveGame_Wrapper(d.toString(), (IBoardManager)obj));
		} finally{}
	}

	@Override
	public List<SaveGame_Wrapper> read(final String pattern) {
		System.out.println("Pattern is: " + pattern);
		List <SaveGame_Wrapper> list = db.query(new Predicate<SaveGame_Wrapper>(){
			public boolean match(SaveGame_Wrapper bw){
				System.out.println("found object, name: " + bw.getName());
				return Pattern.matches(pattern, bw.getName());
			}
		});
		
		System.out.println("in read: " + list.toString());
		return list;
	}

	@Override
	public void delete(String name) {
		try {
			List<SaveGame_Wrapper> toIter = read(name);
			for(SaveGame_Wrapper sgw : toIter){
				db.delete(sgw.getName());
			}
		}finally{}
	}

	@Override
	public void cloe() {
		db.close();
	}
}
