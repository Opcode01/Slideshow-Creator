/**
 * ViewerMain.java
 * Launches the viewer program
 * 
 * Slideshow Creator
 * Timothy Couch, Joseph Hoang, Fernando Palacios, Austin Vickers
 * CS 499 Senior Design with Dr. Rick Coleman
 * 2/7/19
 */
package viewer;

import core.*;
import creator.ArrangeScene;
import creator.DirectoryExplorer;
import creator.SelectScene;

public class ViewerMain
{
	/**
	 * main - launches the viewer program
	 * @param args
	 * 
	 * @author Timothy Couch
	 */
	public static void main(String[] args)
	{
		SceneHandler handler = new SceneHandler(AppType.VIEWER);
		handler.AddScene(SceneType.FILESELECT, new FileSelectExplorer());
		handler.AddScene(SceneType.PLAY, new PlayScene());
		handler.SwitchToScene(SceneType.FILESELECT);
	}
}