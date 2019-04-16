/**
 * SceneType.java
 * Enumerates the program's scenes
 * 
 * Slideshow Creator
 * Timothy Couch, Joseph Hoang, Fernando Palacios, Austin Vickers
 * CS 499 Senior Design with Dr. Rick Coleman
 * 4/11/19
 */

package core;

public enum SceneType {
	NONE("None"),
	DIRECTORY("Directory"),
	SELECTION("Selection"),
	ARRANGE("Arrange"),
	FILESELECT("File Select"),
	PLAY("Play");
	
	/**
	 * title - name of scene 
	 */
	private String title;

	public String getTitle() {
		return title;
	}
	
    SceneType(String title)
    {
        this.title = title;
    }
}
