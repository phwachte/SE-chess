package de.htwg.xiangqi.persistence.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public final class HibernateUtil {

	private static SessionFactory sessionFactory;
	static final AnnotationConfiguration CFG;

	static {
		CFG = new AnnotationConfiguration();
		CFG.configure("/hibernate.cfg.xml");
		sessionFactory = CFG.buildSessionFactory();
	}

	private HibernateUtil() {
	}

	public static SessionFactory getInstance() {
		return sessionFactory;
	}

}
