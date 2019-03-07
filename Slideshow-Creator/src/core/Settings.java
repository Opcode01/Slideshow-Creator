package core;

public final class Settings {
	
	/* These fields should be read-only to any other class */
	public final boolean isLoopingSlides;
	public final boolean isLoopingAudio;
	public final boolean isManual;
	//public final String audioPath;
	public final int transitionLength;
	public final int slideDuration;
	
	
	public Settings(
			boolean loopSlides, 
			boolean loopAudio, 
			boolean manualMode, 
			int transitionLength,
			int slideDuration
			) 
	{	
		isLoopingSlides = loopSlides;
		isLoopingAudio = loopAudio;
		isManual = manualMode;
		//this.audioPath = audioPath;
		this.transitionLength = transitionLength;
		this.slideDuration = slideDuration;
		
	}
	
	public void PrintAll() {
		System.out.println("SlideLoop: " + isLoopingSlides);
		System.out.println("AudioLoop: " + isLoopingAudio);
		System.out.println("ManualMode: " + isManual);
		//System.out.println("AudioPath: " + audioPath);
		System.out.println("TransitionLength: " + transitionLength);
		System.out.println("SlideDuration: " + slideDuration);
	}
}
