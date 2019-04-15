/**
 * ThreadOnCompleteListener.java
 * Used by NotifyingThread so that the thread knows who to send its messages to
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

public interface ThreadOnCompleteListener {
    void notifyOfThreadComplete(final NotifyingThread thread);
}
