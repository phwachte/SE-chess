package de.htwg.xiangqi;

import com.google.inject.AbstractModule;

import de.htwg.xiangqi.controller.IBoardManager;

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
	}

}
