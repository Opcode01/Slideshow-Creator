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

import java.awt.Dimension;

import javax.swing.JFrame;

import org.json.simple.parser.ParseException;

import core.*;
import creator.ArrangeScene;
import creator.DirectoryExplorer;
import creator.SelectScene;
import creator.WarningPane;

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
		try 
		{
			SceneHandler handler = new SceneHandler(AppType.VIEWER);
			handler.AddScene(SceneType.FILESELECT, new FileSelectExplorer());
			handler.AddScene(SceneType.PLAY, new PlayScene());
			handler.SwitchToScene(SceneType.FILESELECT);
		} catch (Exception e) {
			JFrame parent = SceneHandler.singleton.getMainFrame();
	    	Coord2 point = new Coord2(
	    			parent.getX() + parent.getSize().width/2,
	    			parent.getY() + parent.getSize().height/2
	    			);
	    	
			WarningPane p = new WarningPane(
	    			parent,
	    			"Warning - Invalid File Selected",
	    			"File picked is an invalid file.",
	    			point, 
	    			new Dimension(400, 190));
		}
	}
}