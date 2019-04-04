package creator;

import core.*;

public class TransitionPane {

	//The transition this pane is associated with
	private Transition transition;
	
	TransitionPane(Timeline t, int index){
		transition = t.transitionsList.getTransition(index);
	}
	
	/** TODO: Use these methods on the action listeners when doing frontend implementation
	 * 
	 * 	transition.setTransitionLength(timeInSeconds);
	 *  transition.setTransitionType(TransitionType);
	 */

}
