package creator;

import java.awt.BorderLayout;
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
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import core.*;

public class ArrangeScene extends Scene{
	
	/** Create options panel */
	private JPanel optionsPanel;
	
	/** Create image panel */
	private JPanel imagePanel;
	
	/** Create timeline panel */
	private JPanel timelinePanel;
	
	private Thumbnail currentSlide;
	
	/** Create container for timeline panel */
	private JPanel timelinePanelContainer;
	
	/** Create Timeline Pane */
	private JScrollPane timelineScroller;
	
	/** Timeline panel constraints */
	private GridBagConstraints timelinePanelConstraints;
	
	/** Back button */
	private JButton backButton;
	
	/** Select all button */
	private JButton settingsButton;
	
	/** Deselect all button */
	private JButton removeCurrentButton;
	
	/** Create audio button */
	private JButton audioButton;
	
	//TESTING PURPOSES ONLY 3/29/19
	//private JButton playButton;
	
	/** Back custom button image */
	private ImageIcon back;
	
	/** Settings custom button image */
	private ImageIcon settings;
	
	/** Remove current custom button image */
	private ImageIcon removeCurrent;
	
	/** Icon for adding an audio track */
	private ImageIcon audio;
	
	/** Highlighted Icon for adding an audio track */
	private ImageIcon highlightedAudio;
	
	//TESTING PURPOSES ONLY 3/29/19
	//private ImageIcon play;
	
	/** Highlighted back custom button image */
	private ImageIcon highlightedBack;
	
	/** Highlighted select all custom button image */
	private ImageIcon highlightedSettings;
	
	/** Highlighted deselect all custom button image */
	private ImageIcon highlightedRemoveCurrent;
	
	/**selected thumbnail on the timeline */
	private Thumbnail selectedThumbnail;
	
	/** Create audio text field - will be put on timeline panel later */
	private JTextField audioText;
	
	/**
	 * ArrangeScene() - sets up arrange with GUI stuff
	 *
	 * @author Fernando Palacios
	 */
	public ArrangeScene () {
		
		// Create images and add icons
		back = new ImageIcon(getClass().getResource("/creator/Images/backButton.png"));
		settings = new ImageIcon(getClass().getResource("/creator/Images/settingsButton.png"));
		removeCurrent = new ImageIcon(getClass().getResource("/creator/Images/removeCurrentButton.png"));
		audio = new ImageIcon(getClass().getResource("/creator/Images/audioButton.png"));
		highlightedBack = new ImageIcon(getClass().getResource("/creator/Images/highlightedBackButton.png"));
		highlightedSettings = new ImageIcon(getClass().getResource("/creator/Images/highlightedSettingsButton.png"));
		highlightedRemoveCurrent = new ImageIcon(getClass().getResource("/creator/Images/highlightedRemoveCurrentButton.png"));

		//clear out if it previously had stuff
		removeAll();
		// Create GridBagLayout object and constraints
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		// Set panel configurations
		this.setLayout(gridBag);

		highlightedAudio = new ImageIcon(getClass().getResource("/creator/Images/highlightedAudioButton.png"));
		
		//TESTING PURPOSES ONLY 3/29/19
		//play = new ImageIcon(getClass().getResource("/core/ButtonImages/Play.jpg"));
		
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
		    	//System.out.println(selectedThumbnail.getImagePath() + "remove");
		    	int removeIndex = timeline.thumbnailsList.indexOf(currentSlide);
		    	timeline.removeSlide(removeIndex);
		    	
		    	// Remove components and repaint 
		    	timelinePanel.removeAll();
		    	ShowImages();
		    	revalidate();
		    }
		});
////////////////////////////////////////////////////////////////////////
/**
* 	TESTING PURPOSES ONLY! - Eventually these buttons will be implemented
* 	for real once the audio timeline is done
* 
* 	@author austinvickers
* 	@date 3/31/19
*
		
		//TODO: Put this where its supposed to be
		//Create audio button - temporary until audio timeline GUI is done
		audioButton = new JButton(audio);
		audioButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		audioButton.setBorder(BorderFactory.createEmptyBorder());
		audioButton.setContentAreaFilled(false);
		audioButton.setFocusable(false);
		audioButton.setRolloverIcon(highlightedAudio);
		audioButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	SelectAudio(audioText);
		    }
		});

		//TODO: Put this where its supposed to be
		// Create text field for audio
		audioText = new JTextField(13);
		audioText.setBackground(light_gray);
		Border audioBorder = BorderFactory.createLineBorder(white, 1);
		audioText.setBorder(audioBorder);
		audioText.setForeground(white);
		audioText.setEditable(false);	
*/		
////////////////////////////////////////////////////////////////////////
/**
 *  TESTING PURPOSES ONLY - Just to make sure file loading and playing actually works
 *  Eventually, we may want a play button on each clip so that the user can preview their
 *  sound clips in the creator.
 *  
 * 	@author austinvickers
 *  @date 3/29/19
 *
		playButton = new JButton(play);
		playButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				AudioPlayer player = SceneHandler.singleton.getTimeline().audioPlayer;
				player.playAudioClipAtIndex(0);
				
			}
		});
*/	
////////////////////////////////////////////////////////////////////////	
		
		// Set options panel configurations
		optionsPanel = new JPanel();
		optionsPanel.setLayout(gridBag);
		optionsPanel.setBackground(SliderColor.medium_gray);
		
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
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 3;
		optionsPanel.add(removeCurrentButton, c);
		
/////////////////////////////////////////
/*
* 	TESTING ONLY 3/29/19 - austinvickers
*
		// TODO: Put this where its supposed to be
		// Set constraints and add audio button
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 4;
		optionsPanel.add(audioButton, c);
		

		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 5;
		optionsPanel.add(playButton, c);
*/		
/////////////////////////////////////////
		
		// Set image panel configurations
		imagePanel = new JPanel();
		imagePanel.setLayout(new BorderLayout());
		imagePanel.setBackground(SliderColor.dark_gray);
		
		// Create outer panel that houses the timeline panel for layout and whitespace
		timelinePanelContainer = new JPanel();
		timelinePanelContainer.setLayout(gridBag);
		timelinePanelContainer.setBackground(SliderColor.light_gray);
		
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
		
		/*
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
		 */
		
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
		timelinePanel.setBackground(SliderColor.light_gray);
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
	 * SelectAudio() - brings up dialogue box to select audio
	 * 
	 * @author Fernando Palacios
	 */
	public void SelectAudio(JTextField display) {
		
    	JFileChooser chooser = new JFileChooser();
    	chooser.setCurrentDirectory(new java.io.File(".")); // start at application current directory
    	
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setFileFilter(new FileNameExtensionFilter("Audio Files", new String[] { "WAV", "AIFF", "MP3", "MP4" }));
        
        AudioPlayer player = SceneHandler.singleton.getTimeline().audioPlayer;
        
    	int returnVal = chooser.showDialog(ArrangeScene.this, "Open Audio");
    	if(returnVal == JFileChooser.APPROVE_OPTION) {
    	    File audioFile = chooser.getSelectedFile();
        	player.addAudio(new Audio(audioFile));
        	display.setText(audioFile.getName());
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
					currentSlide = buttonThumb;
					
					if(keeper.isSelected())
					{
						// Clear borders
						for(JToggleButton button : thumbButtons)
						{
							button.setBorder(BorderFactory.createEmptyBorder());
							button.setSelected(false);
						}
						
						// Set border for newly selected button
						selectButton(keeper);
						//System.out.println(buttonThumb.getImagePath() + "selected");
						
						showCurrentSlide();
						//imagePanel.repaint();
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
		
		//show the first slide
		if (timeline.thumbnailsList.getSize() > 0)
		{
			currentSlide = timeline.thumbnailsList.getThumbnail(0);
			selectButton(thumbButtons[0]);
			showCurrentSlide();
		}
		else {//empty slideshow
			imagePanel.removeAll();
			imagePanel.add(new JLabel(), BorderLayout.CENTER);
			revalidate();
		}
	}
	
	private static void selectButton(JToggleButton b)
	{
		b.setSelected(true);
		b.setBorder(new LineBorder(SliderColor.aqua, 3));
	}
	
	/**
	 * creates a JLabel with the current slide thumbnail on it
	 * @return JLabel of the current slide
	 * 
	 * @author Timothy Couch
	 */
	private JLabel createSlideLabel()
	{
		JLabel label = new JLabel() {
			@Override
			public void paintComponent(Graphics g) {
				currentSlide.drawFill(g, this);
			}
		};
		label.setBorder(BorderFactory.createEmptyBorder());
		return label;
	}
	
	/**
	 * updates the slideThumb and slidePanel to the slide at currentSlideIndex
	 * 
	 * @Timothy Couch
	 */
	private void showCurrentSlide()
	{
		//update the view
		imagePanel.removeAll();
		imagePanel.add(createSlideLabel(), BorderLayout.CENTER);
		revalidate();
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