package de.htwg.xiangqi;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;

import de.htwg.xiangqi.controller.IBoardManager;
import de.htwg.xiangqi.view.viewPlugin.testPlug;
import de.htwg.xiangqi.view.viewPlugin.viewPlugin;

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
	@SuppressWarnings({ "unchecked", "null" }) //else throws warning when configuring the binding for multibinder... -.-
	@Override
	protected void configure() {
		bind(IBoardManager.class).to(de.htwg.xiangqi.controller.BoardManager.class);
		
		/*MULTIBINDER - PLUGINS FOR VIEW*/	    
	    Multibinder<viewPlugin> viewPluginBinder = Multibinder.newSetBinder(binder(), viewPlugin.class);
	    viewPluginBinder.addBinding().to(testPlug.class);
	}
}
