package de.htwg.util.observer;

/**
 * IObservable is an interface
 * 
 * @author P. Wachter
 * 
 */
public interface IObservable {

	/**
	 * add an observer to a list
	 * 
	 * @param s
	 *            the observer
	 */
	void addObserver(IObserver s);

	/**
	 * remove an observer from the list
	 * 
	 * @param s
	 *            the observer
	 */
	void removeObserver(IObserver s);

	/**
	 * remove all observers from the list
	 */
	void removeAllObservers();

	/**
	 * notify all observers after a certain event
	 */
	void notifyObservers();
}
