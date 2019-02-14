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
    public Thumbnails thumbnails;
    public Transitions transitions;

    /**
     * Timeline - initializes an empty timeline
     * 
     * @author Timothy Couch
     */
    public Timeline()
    {
        thumbnails = new Thumbnails();
        transitions = new Transitions();
    }
}
