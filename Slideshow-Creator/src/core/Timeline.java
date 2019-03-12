/**
 * Timeline.java
 * Coordinates thumbnails and transitions
 * 
 * Slideshow Creator
 * Timothy Couch, Joseph Hoang, Fernando Palacios, Austin Vickers
 * CS 499 Senior Design with Dr. Rick Coleman
 * 2/13/19
 */

package core;

import java.util.ArrayList;

public class Timeline
{
    public ThumbnailsList thumbnailsList;
    public TransitionsList transitionsList;

    /**
     * Timeline - initializes an empty timeline
     * 
     * @author Timothy Couch
     */
    public Timeline()
    {
        thumbnailsList = new ThumbnailsList();
        transitionsList = new TransitionsList();
    }
    
    public void UpdateProjectSettings(Settings s) {
    	s.PrintAll();
    	
    	//Update all transition lengths
    	ArrayList<Transition> list = transitionsList.getTransitions();
    	for(Transition t : list) {
    		t.setTransitionLength(s.transitionLength);
    	}
    	
    	System.out.println("Timeline Settings updated!");
    }

    /**
     * Adds the given slide to the timeline with a default transition
     * @param thumbnail the thumbnail to add
     * 
     * @author Timothy Couch
     */
    public void addSlide(Thumbnail thumbnail)
    {
        thumbnailsList.addThumbnail(thumbnail);
        transitionsList.addTransition(new Transition(TransitionType.WIPE_LEFT, 1));
    }

    /**
     * Removes the slide at the specified index from the timeline
     * @param index thumbnail and transition combo to remove
     * @return whether successful
     * 
     * @author Timothy Couch
     */
    public boolean removeSlide(int index)
    {
        thumbnailsList.removeThumbnail(thumbnailsList.getThumbnail(index));
        transitionsList.removeTransition(transitionsList.getTransition(index));
        return true;
    }
}
