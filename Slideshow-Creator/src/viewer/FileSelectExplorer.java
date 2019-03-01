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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.*;

public class FileSelectExplorer extends Scene {

	public FileSelectExplorer()
	{
		Thumbnail thumb = new Thumbnail("src/creator/TransitionImages/crossFade.png");
		
		JLabel iconLbl = new JLabel() {
			  @Override
			  public void paintComponent(Graphics g) {
				  thumb.drawFill(g, this);
				  }
			  };
			  
		this.setLayout(new BorderLayout());
		this.add(iconLbl, BorderLayout.CENTER);
		
		this.revalidate();
	}
}
