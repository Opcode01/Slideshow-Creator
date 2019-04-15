package core;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Notifying Thread 
 * 
 * Notifies anyone set up as a ThreadOnCompleteListener when a thread of this type completes
 * 
 * @author austinvickers, @author Eddie on StackOverflow
 * {@link https://stackoverflow.com/questions/702415/how-to-know-if-other-threads-have-finished}
 * 
 */

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
		}catch(InterruptedException ie) {
			//Do not notify the listeners
			return;
		} 
		
		notifyListeners();
	}
	
	public abstract void doRun() throws InterruptedException;
	
}
