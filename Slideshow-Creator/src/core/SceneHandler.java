/**
 * SceneHandler.java
 * Controls and manages the overall program
 * Singleton class
 * 
 * Slideshow Creator
 * Timothy Couch, Joseph Hoang, Fernando Palacios, Austin Vickers
 * CS 499 Senior Design with Dr. Rick Coleman
 * 2/10/19
 */
package core;

import java.util.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SceneHandler {
	
	/**
	 * the one instance of SceneHandler that exists
	 */
	public static SceneHandler singleton;

	/**
	 * appType - which program is running
	 */
	private AppType appType;
	/**
	 * directory - directory where the slideshow is working and using images
	 */
	private String directory = "";
	
	/**
	 * mainFrame - window frame of program
	 */
	private JFrame mainFrame;
	/* The dictionary of scenes in the current context */
	private HashMap<SceneType, Scene> scenes;
	/* The currently selected scene type */
	private SceneType currentScene;
	
	/**
	 * SceneHandler - creates program with specified app type
	 * @param aT AppType to open
	 * 
	 * @author Timothy Couch
	 * @author austinvickers
	 */
	public SceneHandler(AppType aT)
	{
		//the first created SceneHandler is the real one. There should never be another one, but just making sure
		if (singleton != null)
			singleton = this;
		appType = aT;
		scenes = new HashMap<SceneType, Scene>();
		currentScene = SceneType.NONE;
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
		
		mainFrame.add(defaultPanel);
		
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
	 * 
	 * @author austinvickers
	 * @author Timothy Couch
	 */
	public void SwitchToScene(SceneType target) {
		
		if(scenes.containsKey(target)) {

			//hide scene
			mainFrame.setVisible(false);
			GetCurrentScene().hide();
			mainFrame.getContentPane().removeAll();

			//show scene
			currentScene = target;
			mainFrame.getContentPane().add(GetCurrentScene());
			GetCurrentScene().show();
			mainFrame.setVisible(true);
		}
		else {
			System.out.println("That scene does not exist in the current context.");
		}	
	}
	
	/**
	 * GetCurrentScene - returns the Scene object that is currently active
	 * @return - the active Scene
	 * 
	 * @author austinvickers
	 */
	public Scene GetCurrentScene() {
		return scenes.get(currentScene);
	}
	
}
