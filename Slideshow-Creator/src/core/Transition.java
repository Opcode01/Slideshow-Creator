package core;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import pkgImageTransitions.ColemanTransition;
import pkgImageTransitions.Transitions.*;

public class Transition {

	/** The type of transition */
	private TransitionType type;
	public void setTransitionType(TransitionType t) {
		type = t;
		if(t == TransitionType.NONE) {
			transition = new ColemanTransition();
		}
		else if(t == TransitionType.CROSS_DISSOLVE) {
			transition = new Trans_CrossDissolve();
		}
		else if(t == TransitionType.PUSH_DOWN) {
			transition = new Trans_PushDown();
		}
		else if(t == TransitionType.PUSH_LEFT) {
			transition = new Trans_PushLeft();
		}
		else if(t == TransitionType.PUSH_RIGHT) {
			transition = new Trans_PushRight();
		}
		else if(t == TransitionType.PUSH_UP) {
			transition = new Trans_PushUp();
		}
		else if(t == TransitionType.WIPE_DOWN) {
			transition = new Trans_WipeDown();
		}
		else if(t == TransitionType.WIPE_LEFT) {
			transition = new Trans_WipeLeft();
		}
		else if(t == TransitionType.WIPE_RIGHT) {
			transition = new Trans_WipeRight();
		}
		else if(t == TransitionType.WIPE_UP) {
			transition = new Trans_WipeUp();
		}
		else {
			transition = new ColemanTransition();
		}
	}
	public TransitionType getTransitionType() {
		return type;
	}
	
	private double transitionLength;
	public void setTransitionLength(double timeInSeconds) {
		transitionLength = timeInSeconds;
	}
	public double getTransitionLength() {
		return transitionLength;
	}
	
	/** The actual transition implementation provided by Dr.Coleman */
	private ColemanTransition transition;
	
	/** thread to run the transition on */
	private Thread transitionThread;
	
	public Thread getTransitionThread() {
		return transitionThread;
	}
	
	/**
	 * Transition() - default constructor for Transition class
	 * 
	 * @param t - the type of transition to play
	 * @param transitionLength - how long the transition lasts for in seconds (double)
	 * 
	 * @author austinvickers
	 */
	public Transition(TransitionType t, double transitionLength) {
		setTransitionType(t);
		setTransitionLength(transitionLength);
	}
	
	/**
	 * PlayTransition() - Plays this transition on the specified JPanel
	 * 
	 * @param display - the JPanel to display on
	 * @param prevImg - the image transitioning from
	 * @param nextImg - the image transitioning to
	 * 
	 * @author austinvickers
	 * @author Timothy Couch
	 */
	public void PlayTransition(JPanel display, Image prevImg, Image nextImg ) {
		if (!isRunning())
		{
			transitionThread = new Thread() {
				@Override
				public void run()
				{
					transition.DrawImageTransition(display, (BufferedImage)prevImg, (BufferedImage)nextImg, transitionLength);
				};
			};
			transitionThread.start();
		}
		else System.out.println("Transition already running! Not playing transition!");
	}
	
	/**
	 * whether this transition is currently playing a transition
	 * 
	 * @author Timothy Couch
	 */
	public boolean isRunning()
	{
		return transitionThread != null && transitionThread.isAlive();
	}
	
	/**
	 * Stop the transition while it is running
	 * 
	 * @author Timothy Couch
	 */
	public void stopTransition()
	{
		if (isRunning())
		{
			try {
				System.out.println("Joining");
				transition.abort();
				transitionThread.join();
				System.out.println("Done waiting");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
