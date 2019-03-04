package core;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import pkgImageTransitions.ColemanTransition;
import pkgImageTransitions.Transitions.*;

public class Transition {

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
	
	//The actual transition implementation provided by Dr.Coleman
	private ColemanTransition transition;
	
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
	 */
	public void PlayTransition(JPanel display, Image prevImg, Image nextImg ) {
		transition.DrawImageTransition(display, (BufferedImage)prevImg, (BufferedImage)nextImg, transitionLength);
	}

}
