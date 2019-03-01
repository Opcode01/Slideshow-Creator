/**
 * FileSelectExplorer.java
 * Scene in which user chooses file to load in viewer
 * 
 * Slideshow Creator
 * Timothy Couch, Joseph Hoang, Fernando Palacios, Austin Vickers
 * CS 499 Senior Design with Dr. Rick Coleman
 * 2/28/19
 */
package viewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.*;

public class FileSelectExplorer extends Scene {

	public FileSelectExplorer()
	{
		JButton testGoPlay = new JButton("Go to play scene");
		testGoPlay.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	SceneHandler.singleton.SwitchToScene(SceneType.PLAY);
		    }
		});

		this.setLayout(new BorderLayout());
		this.add(testGoPlay, BorderLayout.CENTER);
		
		this.revalidate();
	}
}
