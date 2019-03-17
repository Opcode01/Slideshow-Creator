package core;

import javax.swing.ImageIcon;

public enum TransitionType {
	NONE("None"),
	CROSS_DISSOLVE("Cross dissolve"),
	PUSH_DOWN("Push down"),
	PUSH_LEFT("Push left"),
	PUSH_RIGHT("Push right"),
	PUSH_UP("Push up"),
	WIPE_DOWN("Wipe down"),
	WIPE_LEFT("Wipe left"),
	WIPE_RIGHT("Wipe right"),
	WIPE_UP("Wipe up");
	
	/**
	 * title - name of scene 
	 */
	private String title;

	public String getTitle() {
		return title;
	}
	
    TransitionType(String title)
    {
        this.title = title;
    }
}
