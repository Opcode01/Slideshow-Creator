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

	//gui icons
	private ImageIcon backIcon;
	private ImageIcon backIconHigh;
	private ImageIcon forwardIcon;
	private ImageIcon forwardIconHigh;
	private ImageIcon removeCurrentIcon;
	private ImageIcon removeCurrentIconHigh;
	
	/**	Thumbnail that displays on the player */
	private Thumbnail slideThumb;
	
	public Thumbnail getSlideThumb() {
		return slideThumb;
	}
	
	public void setSlideThumb(Thumbnail t) {
		slideThumb = t;
	}
	
	public PlayScene()
	{
		backIcon = new ImageIcon(SceneHandler.class.getResource("Images/backButton.png"));
		backIconHigh = new ImageIcon(SceneHandler.class.getResource("Images/highlightedBackButton.png"));
		forwardIcon = new ImageIcon(SceneHandler.class.getResource("Images/forwardButton.png"));
		forwardIconHigh = new ImageIcon(SceneHandler.class.getResource("Images/highlightedForwardButton.png"));
		removeCurrentIcon = new ImageIcon(SceneHandler.class.getResource("Images/removeCurrentButton.png"));
		removeCurrentIconHigh = new ImageIcon(SceneHandler.class.getResource("Images/highlightedRemoveCurrentButton.png"));
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
		this.setLayout(gridBag);
		
		// Set slide panel configurations
		slideThumb = SceneHandler.singleton.getTimeline().thumbnailsList.getThumbnail(0);
		JPanel slidePanel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				slideThumb.drawFill(g, this);
			}
		};
		slidePanel.setLayout(gridBag);
		slidePanel.setBackground(SliderColor.dark_gray);
		
		// Set constraints and add slide panel
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 0;
		this.add(slidePanel, c);
		
		// Set options panel configurations
		JPanel optionsPanel = new JPanel();
		slidePanel.setLayout(new BorderLayout());
		slidePanel.setBackground(SliderColor.light_gray);
		
		/*
		// Create back button
		JButton backButton = new JButton(backIcon);
		backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		backButton.setToolTipText("Go to Previous Image");
		backButton.setBorder(BorderFactory.createEmptyBorder());
		backButton.setContentAreaFilled(false);
		backButton.setFocusable(false);
		backButton.setRolloverIcon(backIconHigh);
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
		
		// Set constraints and add options panel
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 1;
		this.add(optionsPanel, c);
		
		this.revalidate();
	}
	
	private void slideLeft()
	{
		
	}
	
	private void slideRight()
	{
		
	}
}
