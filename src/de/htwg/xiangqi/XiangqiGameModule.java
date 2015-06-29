package de.htwg.xiangqi;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

import de.htwg.xiangqi.controller.IBoardManager;
import de.htwg.xiangqi.persistence.IDataAccessObject;
import de.htwg.xiangqi.view.viewPlugin.IviewPlugin;
import de.htwg.xiangqi.view.viewPlugin.VoidPlugin;

/**
 * class XiangqiGameModel for dependency injection
 * 
 * @author P. Wachter
 * 
 */
public class XiangqiGameModule extends AbstractModule {

	/**
	 * constructor 
	 */
	public XiangqiGameModule() {
	}

	/**
	 * method to configure
	 */
	@Override
	protected void configure() {
		bind(IBoardManager.class).to(de.htwg.xiangqi.controller.BoardManager.class);
		
		/* persistence */
		/* persistence.hibernate.HibernateDAO.class || persistence.couchdb.CouchDBDAO.class */
		bind(IDataAccessObject.class).to(de.htwg.xiangqi.persistence.db4o.DB4ODAO.class);
		
		
		/*MULTIBINDER - PLUGINS FOR VIEW*/	    
	    Multibinder<IviewPlugin> viewPluginBinder = Multibinder.newSetBinder(binder(), IviewPlugin.class);
	    viewPluginBinder.addBinding().to(VoidPlugin.class);
	}
}
