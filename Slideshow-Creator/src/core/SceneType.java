package core;

public enum SceneType {
	DIRECTORY("Directory"),
	SELECTION("Selection"),
	ARRANGE("Arrange"),
	VIEWER("Viewer");
	
	/**
	 * title - name of scene 
	 */
	private String title;
	
    SceneType(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
}
