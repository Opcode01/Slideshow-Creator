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

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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

	public void setDirectory(String dir)
	{
		directory = dir;
	}

	public String getDirectory()
	{
		return directory;
	}
	
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
		if (singleton == null)
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
	 * @author austinvickers
	 */
	public boolean launch()
	{
		ImageIcon slideshowIcon = new ImageIcon(getClass().getResource("Images/slideshowIcon.png"));
		
		//set up default window
		mainFrame = new JFrame();
		mainFrame.setExtendedState(mainFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		mainFrame.setTitle("Slideshow " + appType.getTitle());
		mainFrame.setIconImage(slideshowIcon.getImage());
		
		//set up quit confirmation dialog
		//Thanks to https://stackoverflow.com/questions/21330682/confirmation-before-press-yes-to-exit-program-in-java
		mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		mainFrame.addWindowListener(new WindowAdapter() {
			  public void windowClosing(WindowEvent e) {
			    int confirmed = JOptionPane.showConfirmDialog(null, 
			        "Are you sure you want to exit the program?", "Confirm Exit",
			        JOptionPane.YES_NO_OPTION);

			    if (confirmed == JOptionPane.YES_OPTION) {
			      mainFrame.dispose();
			    }
			  }
			});
		
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
			if (GetCurrentScene() != null)
				GetCurrentScene().hide();
			mainFrame.getContentPane().removeAll();

			//show scene
			currentScene = target;
			mainFrame.getContentPane().add(GetCurrentScene());
			GetCurrentScene().show();
			mainFrame.revalidate();
			mainFrame.repaint();
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
