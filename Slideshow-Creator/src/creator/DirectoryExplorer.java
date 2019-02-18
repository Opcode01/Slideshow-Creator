/**
 * DirectoryExplorer.java
 * Scene in which user chooses directory or file to load
 * 
 * Slideshow Creator
 * Timothy Couch, Joseph Hoang, Fernando Palacios, Austin Vickers
 * CS 499 Senior Design with Dr. Rick Coleman
 * 2/10/19
 */
package creator;

import core.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DirectoryExplorer extends Scene {

	/**
	 * sets up directory explorer with GUI stuff
	 * 
	 * @author austinvickers
	 */
	public DirectoryExplorer() {

		this.setSize(800, 600);
				
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel label = new JLabel("This is the directory explorer scene!");
		this.add(label);
		
		//this is a test button that emulates what should happen after a person chooses a directory
		JButton testButton = new JButton("Go to select");
		this.add(testButton);
		testButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				GoToSelectScene("%USERPROFILE%/SamplePath");
			}
		});
	}
	
	/**
	 * GoToSelectScene() - sends to select scene with specified path
	 * @param dir is directory
	 * 
	 * @author Timothy Couch
	 */
	public void GoToSelectScene(String dir)
	{
		SceneHandler.singleton.setDirectory(dir);
		SceneHandler.singleton.SwitchToScene(SceneType.SELECTION);
	}
	
}
