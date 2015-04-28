package de.htwg.xiangqi.persistence.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {

	static SessionFactory sessionFactory;
	static final AnnotationConfiguration cfg;

	static {
		cfg = new AnnotationConfiguration();
		cfg.configure("/hibernate.cfg.xml");
		sessionFactory = cfg.buildSessionFactory();
	}

	private HibernateUtil() {
	}

	public static SessionFactory getInstance() {
		return sessionFactory;
	}

}
