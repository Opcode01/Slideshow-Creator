package core;

/**
 * AppType - enum of which program is running
 * 
 * @author Timothy Couch
 * 
 * Special thanks to flash at https://stackoverflow.com/questions/8063852/how-can-i-associate-a-string-with-each-member-of-an-enum
 *
 */
public enum AppType {
	CREATOR("Creator"), 
	VIEWER("Viewer");
	
	/**
	 * title - name of app 
	 */
	private String title;
	
    AppType(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
}