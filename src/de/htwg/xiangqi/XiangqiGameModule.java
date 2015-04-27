package de.htwg.xiangqi;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

import de.htwg.xiangqi.controller.IBoardManager;
import de.htwg.xiangqi.persistence.IDataAccessObject;
import de.htwg.xiangqi.view.viewPlugin.IviewPlugin;
import de.htwg.xiangqi.view.viewPlugin.voidPlugin;

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
		
		/*persistence*/
		bind(IDataAccessObject.class).to(de.htwg.xiangqi.persistence.DB4O.DB4O_Board.class);
		
		/*MULTIBINDER - PLUGINS FOR VIEW*/	    
	    Multibinder<IviewPlugin> viewPluginBinder = Multibinder.newSetBinder(binder(), IviewPlugin.class);
	    viewPluginBinder.addBinding().to(voidPlugin.class);
	}
}
