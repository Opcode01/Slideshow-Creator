/**
 * SlideShowTime.java
 * Holds various times required to set timers
 * 
 * Slideshow Creator
 * Timothy Couch, Joseph Hoang, Fernando Palacios, Austin Vickers
 * CS 499 Senior Design with Dr. Rick Coleman
 * 4/11/19
 */

package viewer;

import core.Timeline;

/**
 * class (struct-like) holding the various times required to set timers 
 * 
 * @author Timothy Couch
 */
public final class SlideShowTime {
	
	/**
	 * index of slide times are for
	 */
	public final int slideIndex;
	
	/**
	 * total length of the current slide
	 */
	public final int slideDuration;
	
	/**
	 * length of transition for current slide
	 */
	public final int transitionDuration;
	
	/**
	 * time to show the slide without any transition
	 */
	public final int showSlideDuration;
	
	/**
	 * calculates times for supplied slide index
	 * 
	 * @param slideIndex slide to calculate times for
	 */
	public SlideShowTime(Timeline timeline, int slideIndex)
	{
		this.slideIndex = slideIndex;
		slideDuration = timeline.timelineSettings.slideDuration * 1000;
		//make sure the transition length isn't longer than the slide duration
		transitionDuration = Math.min((int) (timeline.transitionsList.getTransition(slideIndex).getTransitionLength() * 1000), slideDuration);
		showSlideDuration = slideDuration - transitionDuration;
	}
}