/**
 * Settings.java
 * Stores the program overall settings
 * 
 * Slideshow Creator
 * Timothy Couch, Joseph Hoang, Fernando Palacios, Austin Vickers
 * CS 499 Senior Design with Dr. Rick Coleman
 * 4/11/19
 */

package core;

public final class Settings {
	
	/* These fields should be read-only to any other class */
	public final boolean isLoopingSlides;
	public final boolean isLoopingAudio;
	public final boolean isManual;
	public final int slideDuration;
	
	
	public Settings(
			boolean loopSlides, 
			boolean loopAudio, 
			boolean manualMode,
			int slideDuration
			) 
	{	
		isLoopingSlides = loopSlides;
		isLoopingAudio = loopAudio;
		isManual = manualMode;
		this.slideDuration = slideDuration;
		
	}
	
	public void PrintAll() {
		System.out.println("SlideLoop: " + isLoopingSlides);
		System.out.println("AudioLoop: " + isLoopingAudio);
		System.out.println("ManualMode: " + isManual);
		System.out.println("SlideDuration: " + slideDuration);
		System.out.println("ParentDir: " + SceneHandler.singleton.getDirectory());
	}
}
