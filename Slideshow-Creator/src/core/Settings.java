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
