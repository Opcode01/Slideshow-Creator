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
    public Settings timelineSettings;
    
    private String directory;
    
    public void setDirectory(String dir)
    {
    	directory = dir;
    }
    
    public String getDirectory()
    {
    	return directory;
    }

    /**
     * Timeline - initializes an empty timeline
     * 
     * @author Timothy Couch
     * @author Joe Hoang
     */
    public Timeline()
    {
        thumbnailsList = new ThumbnailsList();
        transitionsList = new TransitionsList();
        timelineSettings = new Settings(false, false, false, 1, 5);
        directory = SceneHandler.singleton.getDirectory();
    }
    
    public void UpdateProjectSettings(Settings s) {
    	s.PrintAll();
    	
    	timelineSettings = s;
    	//Update all transition lengths
    	
    	ArrayList<Transition> list = transitionsList.getTransitions();
    	for(Transition t : list) 
    	{
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
        transitionsList.addTransition(new Transition(TransitionType.NONE, 1));
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
