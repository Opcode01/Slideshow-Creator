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
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.*;

public class PlayScene extends Scene {

	private ImageIcon backIcon;
	private ImageIcon highlightedBackIcon;
	private ImageIcon removeCurrentIcon;
	private ImageIcon highlightedRemoveCurrentIcon;
	
	public PlayScene()
	{
		backIcon = new ImageIcon(SceneHandler.class.getResource("Images/backButton.png"));
		highlightedBackIcon = new ImageIcon(SceneHandler.class.getResource("Images/highlightedBackButton.png"));
		removeCurrentIcon = new ImageIcon(SceneHandler.class.getResource("Images/removeCurrentButton.png"));
		highlightedRemoveCurrentIcon = new ImageIcon(SceneHandler.class.getResource("Images/highlightedRemoveCurrentButton.png"));
	}
	
	/**
	 * Set up slideshow playback
	 * 
	 * @author Timothy Couch
	 */
	@Override
	public void initialize()
	{
		// Create GridBagLayout object and constraints
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		// Set slide panel configurations
		JPanel slidePanel = new JPanel();
		slidePanel.setLayout(gridBag);
		slidePanel.setBackground(SliderColor.light_gray);
		
		/*
		// Create back button
		JButton backButton = new JButton(backIcon);
		backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		backButton.setToolTipText("Go to Previous Image");
		backButton.setBorder(BorderFactory.createEmptyBorder());
		backButton.setContentAreaFilled(false);
		backButton.setFocusable(false);
		backButton.setRolloverIcon(highlightedBackIcon);
		backButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	slideLeft();
		    }
		});
		
		// Set constraints and add back button
		c.anchor = GridBagConstraints.NORTHWEST;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0;
		optionsPanel.add(backButton, c);
		*/
		
		// Set panel configurations
		this.setLayout(gridBag);

		///////////////////////
		//Add example image - this is approximately what you should do to set up the display image! :)
		Thumbnail testThumb = SceneHandler.singleton.getTimeline().thumbnailsList.getThumbnail(0);
		JLabel testLabel = new JLabel() {
			@Override
			public void paintComponent(Graphics g) {
				testThumb.drawFill(g, this);
			}
		};
		this.add(testLabel, c);
		///////////////////////
		
		this.revalidate();
	}
	
	private void slideLeft()
	{
		
	}
	
	private void slideRight()
	{
		
	}
}
