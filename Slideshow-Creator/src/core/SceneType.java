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
