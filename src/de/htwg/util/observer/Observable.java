package de.htwg.util.observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * the class Observable manages all the observers
 * 
 * @author P. Wachter
 * 
 */
public class Observable implements IObservable {

	private List<IObserver> subscribers = new ArrayList<IObserver>(2);

	/**
	 * add an observer to a list
	 * 
	 * @param s
	 *            the observer
	 */
	public void addObserver(IObserver s) {
		subscribers.add(s);
	}

	/**
	 * remove an observer from the list
	 * 
	 * @param s
	 *            the observer
	 */
	public void removeObserver(IObserver s) {
		subscribers.remove(s);
	}

	/**
	 * remove all observers from the list
	 */
	public void removeAllObservers() {
		subscribers.clear();
	}

	/**
	 * notify all observers after a certain event
	 */
	public void notifyObservers() {
		if (!subscribers.isEmpty()) {
			for (Iterator<IObserver> iter = subscribers.iterator(); iter
					.hasNext();) {
				IObserver observer = iter.next();
				observer.update();
			}
		}
	}
}
