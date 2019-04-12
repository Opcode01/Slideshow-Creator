/**
 * NotifyingThread.java
 * Notifies anyone set up as a ThreadOnCompleteListener when a thread of this type completes
 * 
 * @author austinvickers, @author Eddie on StackOverflow
 * {@link https://stackoverflow.com/questions/702415/how-to-know-if-other-threads-have-finished}
 * 
 * Slideshow Creator
 * Timothy Couch, Joseph Hoang, Fernando Palacios, Austin Vickers
 * CS 499 Senior Design with Dr. Rick Coleman
 * 4/11/19
 */

package core;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public abstract class NotifyingThread implements Runnable {

	private final Set<ThreadOnCompleteListener> listeners = new CopyOnWriteArraySet<ThreadOnCompleteListener>();
	
	public final void addListener(final ThreadOnCompleteListener listener) {
		listeners.add(listener);
	}
	
	public final void removeListener(final ThreadOnCompleteListener listener) {
		listeners.remove(listener);
	}

	protected final void notifyListeners() {
		for (ThreadOnCompleteListener listener : listeners) {
			listener.notifyOfThreadComplete(this);
		}
	}
	
	@Override
	public final void run() {
		try {
			doRun();
		} finally {
			notifyListeners();
		}
		
	}
	
	public abstract void doRun();
	
}
