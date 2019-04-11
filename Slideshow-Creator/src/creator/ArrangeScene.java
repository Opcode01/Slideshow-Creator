package creator;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Dimension;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.border.LineBorder;

import core.*;

public class ArrangeScene extends Scene{
	
	/** Create options panel */
	private JPanel optionsPanel;
	
	/** Create image panel */
	private JPanel imagePanel;
	
	/** Create timeline panel */
	private JPanel timelinePanel;
	
	/** Create container for timeline panel */
	private JPanel timelinePanelContainer;
	
	/** Create Timeline Pane */
	private JScrollPane timelineScroller;
	
	/** Timeline panel constraints */
	private GridBagConstraints timelinePanelConstraints;
	
	/** Back button */
	private JButton backButton;
	
	/** Settings button */
	private JButton settingsButton;
	
	/** Remove current button */
	private JButton removeCurrentButton;
	
	/** Swap thumbnail forward button */
	private JButton swapForwardButton;
	
	/** Swap thumbnail backward button */
	private JButton swapBackwardButton;
	
	/** Back custom button image */
	private ImageIcon back;
	
	/** Settings custom button image */
	private ImageIcon settings;
	
	/** Remove current custom button image */
	private ImageIcon removeCurrent;
	
	/** Move thumbnail right custom button image */
	private ImageIcon swapRight;
	
	/** Move thumbnail left custom button image */
	private ImageIcon swapLeft;
	
	/** Highlighted back custom button image */
	private ImageIcon highlightedBack;
	
	/** Highlighted select all custom button image */
	private ImageIcon highlightedSettings;
	
	/** Highlighted deselect all custom button image */
	private ImageIcon highlightedRemoveCurrent;
	
	/** Move thumbnail right highlighted custom button image */
	private ImageIcon highlightedSwapRight;
	
	/** Move thumbnail left highlighted custom button image */
	private ImageIcon highlightedSwapLeft;
	
	/** Create custom aqua color */
	private Color aqua = new Color(132, 200, 202);
	
	/** Create custom light gray color */
	private Color light_gray = new Color(31, 31, 31);
	
	/** Create custom dark_gray color */
	private Color dark_gray = new Color(0, 0, 0);
	
	/** Create custom medium_gray color */
	private Color medium_gray = new Color(41, 41, 41);
	
	/**selected thumbnail on the timeline */
	private Thumbnail selectedThumbnail;
	
	/**
	 * ArrangeScene() - sets up arrange with GUI stuff
	 *
	 * @author Fernando Palacios
	 */
	public ArrangeScene () {
		// Create GridBagLayout object and constraints
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		// Set panel configurations
		this.setLayout(gridBag);
		
		// Create images and add icons
		back = new ImageIcon(getClass().getResource("/creator/Images/backButton.png"));
		settings = new ImageIcon(getClass().getResource("/creator/Images/settingsButton.png"));
		removeCurrent = new ImageIcon(getClass().getResource("/creator/Images/removeCurrentButton.png"));
		swapRight = new ImageIcon(getClass().getResource("/creator/Images/swapRightButton.png"));
		swapLeft = new ImageIcon(getClass().getResource("/creator/Images/swapLeftButton.png"));
		highlightedBack = new ImageIcon(getClass().getResource("/creator/Images/highlightedBackButton.png"));
		highlightedSettings = new ImageIcon(getClass().getResource("/creator/Images/highlightedSettingsButton.png"));
		highlightedRemoveCurrent = new ImageIcon(getClass().getResource("/creator/Images/highlightedRemoveCurrentButton.png"));
		highlightedSwapRight = new ImageIcon(getClass().getResource("/creator/Images/highlightedSwapRightButton.png"));
		highlightedSwapLeft = new ImageIcon(getClass().getResource("/creator/Images/highlightedSwapLeftButton.png"));
		
		
		// Create back button
		backButton = new JButton(back);
		backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		backButton.setToolTipText("Back");
		backButton.setBorder(BorderFactory.createEmptyBorder());
		backButton.setContentAreaFilled(false);
		backButton.setFocusable(false);
		backButton.setRolloverIcon(highlightedBack);
		backButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	GoToSelectScene();
		    }
		});
		
		// Create settings button
		settingsButton = new JButton(settings);
		settingsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		settingsButton.setToolTipText("Project Settings");
		settingsButton.setBorder(BorderFactory.createEmptyBorder());
		settingsButton.setContentAreaFilled(false);
		settingsButton.setFocusable(false);
		settingsButton.setRolloverIcon(highlightedSettings);
		settingsButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	// Open settings pane in the center of our workspace
		    	JFrame parent = SceneHandler.singleton.getMainFrame();
		    	Coord2 point = new Coord2(
		    			parent.getX() + parent.getSize().width/2,
		    			parent.getY() + parent.getSize().height/2
		    			);
		    	SettingsPane p = new SettingsPane(parent, "Project Settings", point, new Dimension(370, 410));
		    	parent.setEnabled(false);
		    }
		});
		
		// Create remove current button
		removeCurrentButton = new JButton(removeCurrent);
		removeCurrentButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		removeCurrentButton.setToolTipText("Remove Selected Image");
		removeCurrentButton.setBorder(BorderFactory.createEmptyBorder());
		removeCurrentButton.setContentAreaFilled(false);
		removeCurrentButton.setFocusable(false);
		removeCurrentButton.setRolloverIcon(highlightedRemoveCurrent);
		removeCurrentButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	// Remove selected thumb from timeline
		    	Timeline timeline = SceneHandler.singleton.getTimeline();
		    	System.out.println(selectedThumbnail.getImagePath() + "remove");
		    	int removeIndex = timeline.thumbnailsList.indexOf(selectedThumbnail);
		    	timeline.removeSlide(removeIndex);
		    	
		    	// Remove components and repaint 
		    	timelinePanel.removeAll();
		    	ShowImages();
		    	revalidate();
		    }
		});

		swapForwardButton = new JButton(swapRight);
		swapForwardButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		swapForwardButton.setToolTipText("Swap selected thumbnail forward");
		swapForwardButton.setBorder(BorderFactory.createEmptyBorder());
		swapForwardButton.setContentAreaFilled(false);
		swapForwardButton.setFocusable(false);
		swapForwardButton.setRolloverIcon(highlightedSwapRight);
		swapForwardButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	// Swap selected thumbnail forward
		    	Timeline timeline = SceneHandler.singleton.getTimeline();
		    	if(selectedThumbnail != null) {
			    	timeline.thumbnailsList.swapForward(selectedThumbnail);
		    	}
		    	
		    	// Remove components and repaint 
		    	timelinePanel.removeAll();
		    	ShowImages();
		    	revalidate();
		    }
		});
		
		swapBackwardButton = new JButton(swapLeft);
		swapBackwardButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		swapBackwardButton.setToolTipText("Swap selected thumbnail backward");
		swapBackwardButton.setBorder(BorderFactory.createEmptyBorder());
		swapBackwardButton.setContentAreaFilled(false);
		swapBackwardButton.setFocusable(false);
		swapBackwardButton.setRolloverIcon(highlightedSwapLeft);
		swapBackwardButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	// Swap selected thumbnail forward
		    	Timeline timeline = SceneHandler.singleton.getTimeline();
		    	if(selectedThumbnail != null) {
			    	timeline.thumbnailsList.swapBackward(selectedThumbnail);
		    	}
		    	
		    	// Remove components and repaint 
		    	timelinePanel.removeAll();
		    	ShowImages();
		    	revalidate();
		    }
		});
		
		
		// Set options panel configurations
		optionsPanel = new JPanel();
		optionsPanel.setLayout(gridBag);
		optionsPanel.setBackground(light_gray);
		
		// Set constraints and add back button
		c.anchor = GridBagConstraints.NORTHWEST;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0;
		optionsPanel.add(backButton, c);
		
		// Set constraints and add settings button
		c.gridx = 0;
		c.gridy = 2;
		optionsPanel.add(settingsButton, c);
		
		// Set constraints and add remove current button
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 3;
		optionsPanel.add(removeCurrentButton, c);
		
		// Set constraints and add swap forward button
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 4;
		optionsPanel.add(swapForwardButton, c);
		
		// Set constraints and add swap backward button
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 5;
		optionsPanel.add(swapBackwardButton, c);
		
		// Set image panel configurations
		imagePanel = new JPanel();
		imagePanel.setLayout(gridBag);
		imagePanel.setBackground(dark_gray);
		
		// Create outer panel that houses the timeline panel for layout and whitespace
		timelinePanelContainer = new JPanel();
		timelinePanelContainer.setLayout(gridBag);
		timelinePanelContainer.setBackground(medium_gray);
		
		// Set up timeline panel constraints
		timelinePanelConstraints = (GridBagConstraints) c.clone();
		
		// Set up blank timeline panel
		setupTimelinePanel(false);
		timelinePanelContainer.add(timelinePanel, timelinePanelConstraints);
		
		// Create scroller and set scroll pane configurations
		timelineScroller = new JScrollPane(timelinePanelContainer, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		timelineScroller.setBorder(BorderFactory.createEmptyBorder());
		timelineScroller.setPreferredSize(new Dimension(200, 235));
		timelineScroller.getHorizontalScrollBar().setUnitIncrement(25);
		
		///////////////////////
		//Add example image - this is approximately what you should do to set up the display image! :)
		Thumbnail testThumb = new Thumbnail(getClass().getResource("/core/TransitionImages/crossFade.png"));
		JLabel testLabel = new JLabel() {
			  @Override
			  public void paintComponent(Graphics g) {
				  testThumb.drawFill(g, this);
				  //example 1 of drawing the image associated with a transition
				  //g.drawImage(SceneHandler.singleton.transitionImages.get(TransitionType.WIPE_RIGHT).getImage(), 0, 200, this);
				  }
			  };
		
		c.weightx = 0.01;
		c.fill = GridBagConstraints.BOTH;
		//c.anchor = GridBagConstraints.CENTER;
		imagePanel.add(testLabel, c);
		///////////////////////
		
		// Set constraints and add options panels
		c.insets = new Insets(0, 0, 0, 0);
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 0;
		c.weighty = 1;
		c.gridheight = 2;
		c.gridx = 0;
		c.gridy = 0;
		this.add(optionsPanel, c);
		
		// Set constraints and add image panel
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 0.70;
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 1;
		this.add(imagePanel, c);
		
		// Set constraints and add timeline panel
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 1;
		this.add(timelineScroller, c);
		
		this.revalidate();
	}
	
    /**
     * initialize() - opens the images and sets up the scene for use
     * 
     * @precondition - must run after project directory has been determined
	 * 
	 * @author Timothy Couch
     */
    @Override
    public void initialize()
    {
		super.initialize();

		setupTimelinePanel(true);
    }
	
    /**
     * setupTimelinePanel() - opens the images and sets up the scene for use
     * 
     * @param revalidate - calls revalidate if boolean is set to true otherwise not
	 * 
	 * @author Timothy Couch
	 * @author Joseph Hoang
	 * @author Fernando Palacios
     */
	public void setupTimelinePanel(boolean revalidate)
	{		
		// Create image panel with new images
		timelinePanel = new JPanel();
		timelinePanel.setLayout(new GridBagLayout());
		timelinePanel.setBackground(medium_gray);
		ShowImages();
		
		// Add to outer panel that houses the timeline panel with formatting
		timelinePanelContainer.removeAll();
		timelinePanelConstraints.weighty = 1;
		timelinePanelConstraints.weightx = 1;
		timelinePanelConstraints.gridx = 0;
		timelinePanelConstraints.gridy = 0;
		timelinePanelContainer.add(timelinePanel, timelinePanelConstraints);

		if (revalidate) {
			this.revalidate();
		}
	}
	
    /**
     * ShowImages() - creates thumbnail icons to display in scrollpane
     * 
     * @author Fernando Palacios
	 * @author Timothy Couch
	 * @author Joseph Hoang
     */
	private void ShowImages()
	{
		int i = 0; //counter for buttons
		int gridxCounter = 0; //layout counter x
		int gridyCounter = 0; //layout counter y
		
		// Get instance of timeline and create button lists for thumb and transitions
		Timeline timeline = SceneHandler.singleton.getTimeline();
		JToggleButton[] thumbButtons = new JToggleButton[timeline.thumbnailsList.getSize()];
		JButton[] transButtons = new JButton[timeline.transitionsList.getSize()];
		
		GridBagConstraints c = new GridBagConstraints();
		
		// Populate thumbnail and transition buttons lists with thumbnails and transitions from timeline
		for(i = 0; i < thumbButtons.length; i++) {
			
			// Set constraints
			c.gridx = gridxCounter++;
			c.gridy = gridyCounter;
			c.fill = GridBagConstraints.BOTH;
			c.anchor = GridBagConstraints.CENTER;
			c.insets = new Insets(20, 20, 20, 20);
			
			// Create references to thumbnail and transition
			Thumbnail buttonThumb = timeline.thumbnailsList.getThumbnail(i);
			Transition buttonTrans = timeline.transitionsList.getTransition(i);
			
			// Create transition button and configurations
			transButtons[i] = new JButton(new ImageIcon(SceneHandler.singleton.transitionImages.get(buttonTrans.getTransitionType()).getImage()));
			transButtons[i].setPreferredSize(new Dimension(100, 75));
			transButtons[i].setRolloverEnabled(true);
			transButtons[i].setRolloverIcon(new ImageIcon(TransHover(SceneHandler.singleton.transitionImages.get(buttonTrans.getTransitionType()).getImage())));
			transButtons[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			transButtons[i].setBorder(BorderFactory.createEmptyBorder());
			transButtons[i].setFocusable(false);
			transButtons[i].setContentAreaFilled(false);
			transButtons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int index = timeline.transitionsList.indexOf(buttonTrans);
					JFrame parent = SceneHandler.singleton.getMainFrame();
			    	Coord2 point = new Coord2(
			    			parent.getX() + parent.getSize().width/2,
			    			parent.getY() + parent.getSize().height/2
			    			);
			    	
					TransitionPane p = new TransitionPane(
							parent, 
							"Transition Settings", 
							point, 
							new Dimension(450, 395),
							timeline,
							index
							);
					parent.setEnabled(false);
				}
			});
			
			// Create thumb button with configurations
			thumbButtons[i] = new JToggleButton(new ImageIcon(buttonThumb.getImageTimeline()));
			JToggleButton keeper = thumbButtons [i];
			thumbButtons[i].setPreferredSize(new Dimension(290, 170));
			thumbButtons[i].setRolloverEnabled(true);
			thumbButtons[i].setRolloverIcon(new ImageIcon(ImageHover(buttonThumb.getImageTimeline())));
			thumbButtons[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			thumbButtons[i].setBorder(BorderFactory.createEmptyBorder());
			thumbButtons[i].setFocusable(false);
			thumbButtons[i].setContentAreaFilled(false);
			thumbButtons[i].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					int slideIndex = timeline.thumbnailsList.indexOf(buttonThumb);
					selectedThumbnail = buttonThumb;
					
					if(keeper.isSelected())
					{
						// Clear borders
						for(JToggleButton button : thumbButtons)
						{
							button.setBorder(BorderFactory.createEmptyBorder());
							button.setSelected(false);
						}
						
						// Set border for newly selected button
						keeper.setSelected(true);
						keeper.setBorder(new LineBorder(aqua, 3));
						System.out.println(buttonThumb.getImagePath() + "selected");
						
						///////////////////////
						//Add example image - this is approximately what you should do to set up the display image! :)
						//Thumbnail currentThumb = new Thumbnail("src/core/TransitionImages/wipeLeft.png");
						JLabel testLabel = new JLabel() {
							  @Override
							  public void paintComponent(Graphics g) {
								  buttonThumb.drawFill(g, this);
								  //example 1 of drawing the image associated with a transition
								  //g.drawImage(TransitionType.WIPE_RIGHT.getImage().getImage(), 0, 200, this);
								  }
							  };
						
						c.weightx = 0.01;
						c.fill = GridBagConstraints.BOTH;
						c.anchor = GridBagConstraints.CENTER;
						imagePanel.removeAll();
						imagePanel.add(testLabel, c);
						imagePanel.revalidate();
						imagePanel.repaint();
					}
				}
				});
			
			// Add thumbnail button to timeline Panel
			timelinePanel.add(thumbButtons[i], c);
			
			// Set constraints and add transition buttons toe timeline panel
			c.gridx = gridxCounter++;
			c.gridy = gridyCounter;
			c.fill = GridBagConstraints.NONE;
			c.insets = new Insets(0, 0, 0, 0);
			timelinePanel.add(transButtons[i], c);
		}
	}
	
    /**
     * TransHover() - darkens the image so that it adds a hovered effect
     * 
     * @param thumbnail - the thumbnail image that needs to be processed
	 * 
	 * @author Fernando Palacios
     */
    private Image TransHover(Image thumbnail) {
        Image img = thumbnail;

        BufferedImage buffered = new BufferedImage(img.getWidth(null),
        img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        buffered.getGraphics().drawImage(img, 0, 0, null);

        for (int i = 0; i < buffered.getWidth(); i++) {
            for (int j = 0; j < buffered.getHeight(); j++) {                    
                int rgb = buffered.getRGB(i, j);
                int alpha = (rgb >> 24) & 0x000000FF;
                Color c = new Color(rgb);
                if (alpha != 0) {
                	c = new Color(254, 250, 238);
                    buffered.setRGB(i, j, c.getRGB());
                }
            }
        } 
        return buffered;
    }
        
	
    /**
     * ImageHover() - darkens the image so that it adds a hovered effect
     * 
     * @param thumbnail - the thumbnail image that needs to be processed
	 * 
	 * @author Fernando Palacios
     */
    private Image ImageHover(Image thumbnail) {
        Image img = thumbnail;

        BufferedImage buffered = new BufferedImage(img.getWidth(null),
        img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        buffered.getGraphics().drawImage(img, 0, 0, null);

        for (int i = 0; i < buffered.getWidth(); i++) {
            for (int j = 0; j < buffered.getHeight(); j++) {                    
                int rgb = buffered.getRGB(i, j);
                int alpha = (rgb >> 24) & 0x000000FF;
                Color c = new Color(rgb);
                if (alpha != 0) {
                    int red = (c.getRed() - 30) <= 0 ? 0 : c.getRed() - 30;
                    int green = (c.getGreen() - 30) <= 0 ? 0
                        : c.getGreen() - 30;
                    int blue = (c.getBlue() - 30) <= 0 ? 0 : c.getBlue() - 30;
                    c = new Color(red, green, blue);
                    buffered.setRGB(i, j, c.getRGB());
                }
            }
        } 
        return buffered;
    }
	
	/**
	 * GoToSelectScene() - changes scene to selection
	 *
	 * @author Fernando Palacios
     */
	public void GoToSelectScene()
	{
		SceneHandler.singleton.SwitchToScene(SceneType.SELECTION);
		SelectScene select = (SelectScene) SceneHandler.singleton.GetSceneInstanceByType(SceneType.SELECTION);
		select.UpdateSelected();
	}

}