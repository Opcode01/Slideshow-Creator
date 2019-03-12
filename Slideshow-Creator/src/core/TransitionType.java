package core;

import javax.swing.ImageIcon;

public enum TransitionType {
	NONE("None"),
	CROSS_DISSOLVE("Cross dissolve", "src/core/TransitionImages/crossFade.png"),
	PUSH_DOWN("Push down"),
	PUSH_LEFT("Push left"),
	PUSH_RIGHT("Push right"),
	PUSH_UP("Push up"),
	WIPE_DOWN("Wipe down", "src/core/TransitionImages/wipeDown.png"),
	WIPE_LEFT("Wipe left", "src/core/TransitionImages/wipeLeft.png"),
	WIPE_RIGHT("Wipe right", "src/core/TransitionImages/wipeRight.png"),
	WIPE_UP("Wipe up", "src/core/TransitionImages/wipeUp.png");
	
	/**
	 * title - name of scene 
	 */
	private String title;

	public String getTitle() {
		return title;
	}

	/**
	 * the image representing this transitiontype
	 * null if not supplied or image not found
	 */
	private ImageIcon transitionImage;
	
	public ImageIcon getImage() {
		return transitionImage;
	}
	
    TransitionType(String title)
    {
        this.title = title;
    }
	
    TransitionType(String title, String imagePath)
    {
        this.title = title;
        this.transitionImage = new ImageIcon(Thumbnail.loadImage(imagePath));
    }
}
