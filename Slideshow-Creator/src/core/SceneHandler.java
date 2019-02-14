/**
 * SceneHandler.java
 * Controls and manages the overall program
 * 
 * Slideshow Creator
 * Timothy Couch, Joseph Hoang, Fernando Palacios, Austin Vickers
 * CS 499 Senior Design with Dr. Rick Coleman
 * 2/10/19
 */
package core;

import java.io.Console;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SceneHandler {
	
	/**
	 * appType - which program is running
	 */
	public AppType appType;
	
	/**
	 * mainFrame - window frame of program
	 */
	private JFrame mainFrame;
	
	private HashMap<SceneType, Scene> scenes;
	
	/**
	 * SceneHandler - creates program with specified app type
	 * @param aT AppType to open
	 * 
	 * @author Timothy Couch
	 * @author austinvickers
	 */
	public SceneHandler(AppType aT)
	{
		appType = aT;
		scenes = new HashMap<SceneType, Scene>();
		launch();
	}
	
	/**
	 * launch - opens the program's main frame
	 * @return true if successfully opened, false otherwise
	 * 
	 * @author Timothy Couch
	 */
	public boolean launch()
	{
		//set up default window
		mainFrame = new JFrame();
		mainFrame.setSize(800, 600);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setTitle("Slideshow " + appType.getTitle());
		
		JPanel defaultPanel = new JPanel();
		defaultPanel.add(new JButton("Hello World"));
		
		mainFrame.setVisible(true);
		
		return true;
	}
	/**
	 * AddScene - adds a new scene to the context. There may only be one of each type of scene.
	 * 
	 * @param type - the scene type
	 * @param scene - an instance of the scene you want to add
	 * 
	 * @author austinvickers
	 */
	public void AddScene(SceneType type, Scene scene) {
		
		if(!scenes.containsKey(type)) {
			scenes.put(type, scene);
		}
		else {
			System.out.println("That scene already exists in the context. Use SwitchToScene() to switch to it");
		}
	}
	
	/**
	 * SwitchToScene - Switches to a scene based on the type that was passed in
	 * @param target - the scene object to be switched to
	 */
	public void SwitchToScene(SceneType target) {
		
		if(scenes.containsKey(target)) {
			mainFrame.setVisible(false);
			mainFrame.getContentPane().removeAll();
			mainFrame.getContentPane().add(scenes.get(target));
			mainFrame.setVisible(true);
		}
		else {
			System.out.println("That scene does not exist in the current context.");
		}
		
	}
	
}
