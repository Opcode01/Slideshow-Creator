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
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import core.*;

public class PlayScene extends Scene {
	
	/** panel showing the current slide */
	private JPanel slidePanel;

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
	
	/** Index of thumbnail currently showing */
	private int currentSlideIndex;
	
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
		//clear out if it had stuff previously
		this.removeAll();
		
		// Create GridBagLayout object and constraints
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(gridBag);
		setBackground(SliderColor.light_gray);
		
		// Set slide panel configurations
		currentSlideIndex = 0;
		slideThumb = getSlide(currentSlideIndex);
		slidePanel = new JPanel();
		slidePanel.setLayout(new BorderLayout());
		slidePanel.setBorder(BorderFactory.createEmptyBorder());
		slidePanel.setBackground(SliderColor.dark_gray);
		slidePanel.add(createSlideLabel(), BorderLayout.CENTER);
		
		// Set constraints and add slide panel
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 0;
		this.add(slidePanel, c);
		
		// Set options panel configurations
		JPanel optionsPanel = new JPanel();
		optionsPanel.setLayout(gridBag);
		optionsPanel.setBackground(SliderColor.light_gray);
		optionsPanel.setBorder(BorderFactory.createEmptyBorder());
		
		// Create exit button
		JButton exitButton = new JButton(removeCurrentIcon);
		exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exitButton.setToolTipText("Return to main menu");
		exitButton.setBorder(BorderFactory.createEmptyBorder());
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusable(false);
		exitButton.setRolloverIcon(removeCurrentIconHigh);
		exitButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	GoToDirectoryScene();
		    }
		});
		
		// Set constraints and add exit button
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0;
		optionsPanel.add(exitButton, c);
		
		//Manual controls panel
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(gridBag);
		controlPanel.setBackground(SliderColor.light_gray);
		
		//left button
		JButton leftButton = new JButton(backIcon);
		leftButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		leftButton.setToolTipText("Previous Image");
		leftButton.setBorder(BorderFactory.createEmptyBorder());
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusable(false);
		leftButton.setRolloverIcon(backIconHigh);
		leftButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	slideLeft();
		    }
		});
		
		// Set constraints and add left button
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0;
		controlPanel.add(leftButton, c);
		
		//right button
		JButton rightButton = new JButton(forwardIcon);
		rightButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rightButton.setToolTipText("Previous Image");
		rightButton.setBorder(BorderFactory.createEmptyBorder());
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusable(false);
		rightButton.setRolloverIcon(forwardIconHigh);
		rightButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	slideRight();
		    }
		});
		
		// Set constraints and add right button
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 0;
		controlPanel.add(rightButton, c);
		
		// Set constraints and add control panel to options panel
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 0;
		optionsPanel.add(controlPanel, c);
		
		// Set constraints and add options panel
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.SOUTH;
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(0, 0, 0, removeCurrentIcon.getIconWidth());
		this.add(optionsPanel, c);
		
		this.revalidate();
	}
    
	/**
	 * GoToDirectoryScene() - changes scene to directory
	 *
	 * @author Timothy Couch
     */
	private void GoToDirectoryScene()
	{
		//restart program when returning to directory to clear the scene
		SceneHandler.singleton.restartProgram();
		SceneHandler.singleton.SwitchToScene(SceneType.FILESELECT);
	}
	
	private JLabel createSlideLabel()
	{
		JLabel label = new JLabel() {
			@Override
			public void paintComponent(Graphics g) {
				slideThumb.drawFill(g, this);
			}
		};
		label.setBorder(BorderFactory.createEmptyBorder());
		return label;
	}
	
	/**
	 * gets the thumbnail at the slide index specified
	 * @param i slide index to get thumbnail
	 * @return thumbnail at index i
	 */
	private Thumbnail getSlide(int i) {
		return SceneHandler.singleton.getTimeline().thumbnailsList.getThumbnail(i);
	}
	
	/**
	 * moves the slideshow one slide to the left
	 */
	private void slideLeft()
	{
		if (currentSlideIndex > 0)
			currentSlideIndex -= 1;
		else currentSlideIndex = SceneHandler.singleton.getTimeline().thumbnailsList.getSize() - 1;
		slideThumb = getSlide(currentSlideIndex);
		slidePanel.removeAll();
		slidePanel.add(createSlideLabel(), BorderLayout.CENTER);
		revalidate();
	}
	
	/**
	 * moves the slideshow one slide to the right
	 */
	private void slideRight()
	{
		currentSlideIndex = (currentSlideIndex + 1) % SceneHandler.singleton.getTimeline().thumbnailsList.getSize();
		slideThumb = getSlide(currentSlideIndex);
		slidePanel.removeAll();
		slidePanel.add(createSlideLabel(), BorderLayout.CENTER);
		revalidate();
	}
}
