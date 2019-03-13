/**
 * PlayScene.java
 * Plays the slideshow selected
 * 
 * Slideshow Creator
 * Timothy Couch, Joseph Hoang, Fernando Palacios, Austin Vickers
 * CS 499 Senior Design with Dr. Rick Coleman
 * 2/28/19
 */

package viewer;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;

import javax.swing.JLabel;

import core.*;

public class PlayScene extends Scene {

	@Override
	public void initialize()
	{
		//needs to be some sort of layered layout thing for buttons to go in front :)
		this.setLayout(new BorderLayout());
		
		///////////////////////
		//Add example image - this is approximately what you should do to set up the display image! :)
		Thumbnail testThumb = SceneHandler.singleton.getTimeline().thumbnailsList.getThumbnail(0);
		JLabel testLabel = new JLabel() {
			@Override
			public void paintComponent(Graphics g) {
				testThumb.drawFill(g, this);
			}
		};
		this.add(testLabel, BorderLayout.CENTER);
		///////////////////////
		
		this.revalidate();
	}
}
