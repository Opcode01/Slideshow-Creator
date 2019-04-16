/**
 * Scene.java
 * A panel for all interactivity in a set of functionality
 * Abstract class
 * 
 * Slideshow Creator
 * Timothy Couch, Joseph Hoang, Fernando Palacios, Austin Vickers
 * CS 499 Senior Design with Dr. Rick Coleman
 * 2/10/19
 */
package core;

import javax.swing.JPanel;

public abstract class Scene extends JPanel
{
	/**
	 * initialized - whether this scene has received a directory and opened the
	 * images
	 */
	private boolean initialized = false;
	
	public boolean isInitialized() {
		return initialized;
	}
	
	/**
	 * what type of scene this scene is. Set only once
	 */
	private SceneType sceneType;
	
	/**
	 * sets the scene Type. Can only be set once
	 * @param sT the SceneType to use
	 * @return true if properly set scene type. False otherwise
	 */
	public boolean setSceneType(SceneType sT) {
		if (sceneType == null)
		{
			sceneType = sT;
			return true;
		}
		return false;
	}
	
	public SceneType getSceneType() {
		return sceneType;
	}

	public Scene()
	{

	}

	/**
	 * initialize - does stuff when the program is shown the first time
	 * 
	 * @author Timothy Couch
	 */
	public void initialize() {
	}

	/**
	 * show - runs right after the scene gets opened
	 * 
	 * @author Timothy Couch
	 */
	public void show()
	{
		if (!initialized)
		{
			initialize();
			initialized = true;
		}
	}

	/**
	 * hide - runs right before the scene gets closed
	 * 
	 * @author Timothy Couch
	 */
	public void hide()
	{

	}

	/**
	 * destroy - runs when destroying this scene
	 * 
	 * @author Timothy Couch
	 */
	public void destroy() {
		initialized = false;
	}
}
