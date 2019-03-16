package creator;

import java.awt.Dimension;

import javax.swing.JFrame;

import core.*;

public class TransitionPane extends FloatingPane{

	//The transition this pane is associated with
	private Transition transition;
	
	TransitionPane(JFrame parent, String title, Coord2 position, Dimension size, Timeline t, int index){
		//Call the parent constructor
		super(parent, title, position, size);
	
		transition = t.transitionsList.getTransition(index);
	}

	
	/** TODO: Use these methods on the action listeners when doing frontend implementation
	 * 
	 * 	transition.setTransitionLength(timeInSeconds);
	 *  transition.setTransitionType(TransitionType);
	 */

}
