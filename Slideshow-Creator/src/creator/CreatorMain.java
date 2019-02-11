/**
 * CreatorMain.java
 * Launches the creator program
 * 
 * Slideshow Creator
 * Timothy Couch, Joseph Hoang, Fernando Palacios, Austin Vickers
 * CS 499 Senior Design with Dr. Rick Coleman
 * 2/7/19
 */
package creator;

import core.SceneHandler;

public class CreatorMain
{
	/**
	 * main - launches the creator program
	 * @param args
	 * 
	 * @author Timothy Couch
	 */
	public static void main(String[] args)
	{
		SceneHandler handler = new SceneHandler(SceneHandler.AppType.CREATOR);
	}
}