package core;

/**
 * ThreadOnCompleteListener
 * 
 * Used by Notifying Thread so that the thread knows who to send its messages to
 * 
 * @author austinvickers, @author Eddie on StackOverflow
 * {@link https://stackoverflow.com/questions/702415/how-to-know-if-other-threads-have-finished}
 * 
 */
public interface ThreadOnCompleteListener {
    void notifyOfThreadComplete(final NotifyingThread thread);
}
