/**
 * AppType.java
 * Enumerates what kind of app is being launched
 * 
 * Slideshow Creator
 * Timothy Couch, Joseph Hoang, Fernando Palacios, Austin Vickers
 * CS 499 Senior Design with Dr. Rick Coleman
 * 4/11/19
 */

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