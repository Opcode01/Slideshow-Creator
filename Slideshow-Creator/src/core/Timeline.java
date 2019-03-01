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
    	System.out.println("Timeline Settings updated!");
    }
}
