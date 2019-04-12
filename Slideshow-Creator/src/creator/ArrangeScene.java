package creator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.GradientPaint;
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
	
	/** Selected thumbnail on the timeline */
	private Thumbnail currentSlide;
	
	/** Create audio panel */
	private JPanel audioPanel;
	
	/** Create container for timeline panel */
	private JPanel timelinePanelContainer;
	
	/** Create Timeline Pane */
	private JScrollPane timelineScroller;
	
	/** Timeline panel constraints */
	private GridBagConstraints timelinePanelConstraints;
	
		/** Timeline panel constraints */
	private GridBagConstraints audioConstraints;
	
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

	/** Create audio button */
	private JButton audioButton;
	
	/** Create audio play button */
	private JButton playButton;
		
	/** Audio file */
	private File audioFile;
	
	/** Total length of audio */
	private float audioTimelineDuration;
	
	/** Create timeline object */
	private Timeline timeline;
	
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

	/** Icon for adding an audio track */
	private ImageIcon audio;
	
	/** Icon for adding an audio /change/ track */
	private ImageIcon audioChange;
	
	/** Remove audio custom button image */
	private ImageIcon removeAudio;
	
	/** Play audio custom button image */
	private ImageIcon play;
	
	/** Play audio change custom button image */
	private ImageIcon playChange;
	
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

	/** Highlighted audio custom button image */
	private ImageIcon highlightedAudio;
	
	/** Highlighted remove audio custom button image */
	private ImageIcon highlightedRemoveAudio;
	
	/** Highlighted audio change custom button image */
	private ImageIcon highlightedAudioChange;
	
	/** Highlighted audio play change custom button image */
	private ImageIcon highlightedPlayChange;
	
	/** Highlighted audio play custom button image */
	private ImageIcon highlightedPlay;
	
	/** Create custom aqua color */
	private Color aqua = new Color(132, 200, 202);
	
	/** Create custom light gray color */
	private Color light_gray = new Color(31, 31, 31);
	
	/** Create custom dark_gray color */
	private Color dark_gray = new Color(0, 0, 0);
	
	/** Create custom medium_gray color */
	private Color medium_gray = new Color(41, 41, 41);
	
	/**
	 * ArrangeScene() - sets up arrange with GUI stuff
	 *
	 * @author Fernando Palacios
	 */
	public ArrangeScene () 
	{
		
		// Create images and add icons
		back = new ImageIcon(getClass().getResource("/creator/Images/backButton.png"));
		settings = new ImageIcon(getClass().getResource("/creator/Images/settingsButton.png"));
		removeCurrent = new ImageIcon(getClass().getResource("/creator/Images/removeCurrentButton.png"));
		swapRight = new ImageIcon(getClass().getResource("/creator/Images/swapRightButton.png"));
		swapLeft = new ImageIcon(getClass().getResource("/creator/Images/swapLeftButton.png"));
		audio = new ImageIcon(getClass().getResource("/creator/Images/audioButton.png"));
		audioChange = new ImageIcon(getClass().getResource("/creator/Images/audioChangeButton.png"));
		removeAudio = new ImageIcon(getClass().getResource("/creator/Images/removeAudioButton.png"));
		play = new ImageIcon(getClass().getResource("/creator/Images/audioPlayButton.png"));
		playChange = new ImageIcon(getClass().getResource("/creator/Images/audioChangePlayButton.png"));		
		highlightedBack = new ImageIcon(getClass().getResource("/creator/Images/highlightedBackButton.png"));
		highlightedSettings = new ImageIcon(getClass().getResource("/creator/Images/highlightedSettingsButton.png"));
		highlightedRemoveCurrent = new ImageIcon(getClass().getResource("/creator/Images/highlightedRemoveCurrentButton.png"));
		highlightedSwapRight = new ImageIcon(getClass().getResource("/creator/Images/highlightedSwapRightButton.png"));
		highlightedSwapLeft = new ImageIcon(getClass().getResource("/creator/Images/highlightedSwapLeftButton.png"));
		highlightedAudio = new ImageIcon(getClass().getResource("/creator/Images/highlightedAudioButton.png"));
		highlightedAudioChange = new ImageIcon(getClass().getResource("/creator/Images/highlightedAudioChangeButton.png"));
		highlightedRemoveAudio = new ImageIcon(getClass().getResource("/creator/Images/highlightedRemoveAudioButton.png"));
		highlightedPlay = new ImageIcon(getClass().getResource("/creator/Images/highlightedAudioPlayButton.png"));
		highlightedPlayChange = new ImageIcon(getClass().getResource("/creator/Images/highlightedAudioChangePlayButton.png"));
		
		//clear out if it previously had stuff
		removeAll();
		// Create GridBagLayout object and constraints
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		// Set panel configurations
		this.setLayout(gridBag);
	
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
		    	timeline = SceneHandler.singleton.getTimeline();
		    	//System.out.println(selectedThumbnail.getImagePath() + "remove");
		    	int removeIndex = timeline.thumbnailsList.indexOf(currentSlide);
		    	timeline.removeSlide(removeIndex);
		    	
		    	// Remove components and repaint 
		    	timelinePanel.removeAll();
		    	PopulateTimeline(false);
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
		    	if(currentSlide != null) {
			    	timeline.thumbnailsList.swapForward(currentSlide);
		    	}
		    	
		    	// Remove components and repaint 
		    	timelinePanel.removeAll();
		    	PopulateTimeline(true);
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
		    	if(currentSlide != null) {
			    	timeline.thumbnailsList.swapBackward(currentSlide);
		    	}
		    	
		    	// Remove components and repaint 
		    	timelinePanel.removeAll();
		    	PopulateTimeline(true);
		    	revalidate();
		    }
		});
		
		
	    // Create audio button
		audioButton = new JButton(audio);
		audioButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		audioButton.setBorder(BorderFactory.createEmptyBorder());
		audioButton.setContentAreaFilled(false);
		audioButton.setFocusable(false);
		audioButton.setRolloverIcon(highlightedAudio);
		audioButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	SelectAudio();
		    }
		});
		
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
		imagePanel.setLayout(new BorderLayout());
		imagePanel.setBackground(SliderColor.dark_gray);
		
		// Create outer panel that houses the timeline panel for layout and whitespace
		timelinePanelContainer = new JPanel();
		timelinePanelContainer.setLayout(gridBag);
		timelinePanelContainer.setBackground(SliderColor.light_gray);
		
		// Set up timeline panel constraints
		timelinePanelConstraints = (GridBagConstraints) c.clone();
		audioConstraints = new GridBagConstraints();
		
		// Set up blank timeline panel
		SetupTimelinePanel(false);
		timelinePanelContainer.add(timelinePanel, timelinePanelConstraints);
		
		// Create scroller and set scroll pane configurations
		timelineScroller = new JScrollPane(timelinePanelContainer, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		timelineScroller.setBorder(BorderFactory.createEmptyBorder());
		timelineScroller.setPreferredSize(new Dimension(200, 250));
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

		SetupTimelinePanel(true);
    }
	
    /**
     * SetupTimelinePanel() - opens the images and sets up the scene for use
     * 
     * @param revalidate - calls revalidate if boolean is set to true otherwise not
	 * 
	 * @author Timothy Couch
	 * @author Joseph Hoang
	 * @author Fernando Palacios
     */
	public void SetupTimelinePanel(boolean revalidate)
	{		
		// Create timeline panel with new images
		timelinePanel = new JPanel();
		timelinePanel.setLayout(new GridBagLayout());
		timelinePanel.setBackground(SliderColor.light_gray);
		PopulateTimeline(revalidate);
		
		// Create audio panel
		audioPanel = new JPanel();
		audioPanel.setLayout(new GridBagLayout());
		audioPanel.setBackground(medium_gray);
		PopulateAudio();
		
		// Make sure timeline panel container is blank by removing all
		timelinePanelContainer.removeAll();
		
		// Add timeline to outer panel
		timelinePanelConstraints.weighty = 0;
		timelinePanelConstraints.weightx = 1;
		timelinePanelConstraints.gridx = 0;
		timelinePanelConstraints.gridy = 0;
		timelinePanelContainer.add(timelinePanel, timelinePanelConstraints);
		
		// Add audio to outer panel
		timelinePanelConstraints.weighty = 1;
		timelinePanelConstraints.gridx = 0;
		timelinePanelConstraints.gridy = 1;
		timelinePanelContainer.add(audioPanel, timelinePanelConstraints);

		if (revalidate) {
			this.revalidate();
		}
	}
	
    /**
     * PopulateTimeline() - creates thumbnail icons to display in scrollpane
     * 
     * @author Fernando Palacios
	 * @author Timothy Couch
	 * @author Joseph Hoang
     */
	private void PopulateTimeline(boolean keepCurrentSelected)
	{
		int i = 0; //counter for buttons
		int gridxCounter = 0; //layout counter x
		int gridyCounter = 0; //layout counter y
		
		// Get instance of timeline and create button lists for thumb and transitions
		timeline = SceneHandler.singleton.getTimeline();
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
			JButton keeperTrans = transButtons [i];
			transButtons[i].setPreferredSize(new Dimension(100, 75));
			transButtons[i].setRolloverEnabled(true);
			transButtons[i].setRolloverIcon(new ImageIcon(TransHover(SceneHandler.singleton.transitionImages.get(buttonTrans.getTransitionType()).getImage())));
			transButtons[i].setPressedIcon(new ImageIcon(TransHover(SceneHandler.singleton.transitionImages.get(buttonTrans.getTransitionType()).getImage())));
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
					keeperTrans.setIcon(new ImageIcon(TransHover(SceneHandler.singleton.transitionImages.get(buttonTrans.getTransitionType()).getImage())));

				}
			});
			
			// Create thumb button with configurations
			thumbButtons[i] = new JToggleButton(new ImageIcon(buttonThumb.getImageTimeline()));
			JToggleButton keeper = thumbButtons [i];
			thumbButtons[i].setPreferredSize(new Dimension(290, 170));
			thumbButtons[i].setRolloverEnabled(true);
			thumbButtons[i].setRolloverIcon(new ImageIcon(ImageHover(buttonThumb.getImageTimeline())));
			thumbButtons[i].setPressedIcon(new ImageIcon(ImageHover(buttonThumb.getImageTimeline())));
			thumbButtons[i].setSelectedIcon(new ImageIcon(ImageHover(buttonThumb.getImageTimeline())));
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
			
			if(timeline.transitionsList.getSize() == (i + 1))
			{
				c.insets = new Insets(0, 0, 0, 20);
				timelinePanel.add(transButtons[i], c);
			} else {
				c.insets = new Insets(0, 0, 0, 0);
				timelinePanel.add(transButtons[i], c);
			}
		}
		
		
		//show the first slide
		if (timeline.thumbnailsList.getSize() > 0)
		{
			if(keepCurrentSelected && currentSlide != null) {
				int index = timeline.thumbnailsList.indexOf(currentSlide);
				selectButton(thumbButtons[index]);
			}
			else {
				currentSlide = timeline.thumbnailsList.getThumbnail(0);
				selectButton(thumbButtons[0]);
			}		
			showCurrentSlide();
		}
		else {//empty slideshow
			imagePanel.removeAll();
			imagePanel.add(new JLabel(), BorderLayout.CENTER);
			revalidate();
		}
	}
	
	/**
	 * selects the supplied button and draws a box around it
	 * @param b button to select
	 */
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
     * PopulateAudio() - populates audio display with add audio button to allow adding of audio
     * 
     * @author Fernando Palacios
     */
	public void PopulateAudio()
	{
		// Get instance of timeline
		timeline = SceneHandler.singleton.getTimeline();
		Settings s = timeline.timelineSettings;
		
		// Reset audio timeline duration 
		audioTimelineDuration = 0;
		
		// Calculate values for thumbnail and transition pixel width along with ratio of pixels per second
	    float thumbnailTotalLength = (290 + 40) * timeline.thumbnailsList.getSize();
	    float transitionTotalLength = ((100) * timeline.transitionsList.getSize()) + 20;
	    float secondsToPixels = (float) (((290.0 + 40.0) + (100.0)) / (float) (s.slideDuration));
	    float extraAudioLength = 27 * 2;
	    float extraLastAudioLength = 27 * 3;
	    
	    // Set counter for grid x
	    int audioxCounter = 0;
	    
	    // Set constraints
	    audioConstraints.gridx = audioxCounter;
	    audioConstraints.gridy = 0;
	    
		//Change icon of add audioButton
		if(timeline.audioPlayer.getSize() > 0) {
			audioButton.setIcon(audioChange);
			audioButton.setRolloverIcon(highlightedAudioChange);
		} else {
			audioButton.setIcon(audio);
			audioButton.setRolloverIcon(highlightedAudio);
		}
	    
		for(int i = 0; i < timeline.audioPlayer.getSize(); i++)
		{
		    Audio audioTrack = timeline.audioPlayer.getAudio(i);
		    int audioTrackSize = Math.round(audioTrack.getAudioLength() * secondsToPixels);
		    
		    System.out.println(audioTrack.getAudioLength());
		    System.out.println(secondsToPixels);
		    System.out.println(thumbnailTotalLength + transitionTotalLength);
		    System.out.println(audioTrackSize);
		    
		    // Add each track size to the total duration of the audio
		    audioTimelineDuration += audioTrackSize;
		    
		    if(audioTimelineDuration >= thumbnailTotalLength + transitionTotalLength)
		    	audioTrackSize = (int) ((thumbnailTotalLength + transitionTotalLength) - (audioTimelineDuration - audioTrackSize));
		    
			if(timeline.audioPlayer.getSize() == (i + 1)) {
				audioTrackSize -= (extraLastAudioLength);
			} else {
				audioTrackSize -= (extraAudioLength);
			}
			
			System.out.println(audioTrackSize);
		    
		    // Create text field for audio
			JTextField audioText = new JTextField();
			audioText.setBackground(aqua);
			audioText.setBorder(BorderFactory.createEmptyBorder());
			audioText.setForeground(dark_gray);
	        audioText.setEditable(false);
		    audioText.setText(audioTrack.getAudioName());
		    audioText.setPreferredSize(new Dimension(audioTrackSize, 20));
		    
		    // CReate remove audio button
		    JButton removeAudioButton = new JButton(removeAudio);
		    removeAudioButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			removeAudioButton.setBorder(BorderFactory.createEmptyBorder());
			removeAudioButton.setContentAreaFilled(false);
			removeAudioButton.setFocusable(false);
			removeAudioButton.setRolloverIcon(highlightedRemoveAudio);
			removeAudioButton.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	RemoveAudio(audioTrack);
			    }
			});
			
		    // CReate remove audio button
		    JButton playButton = new JButton(play);
		    playButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			playButton.setBorder(BorderFactory.createEmptyBorder());
			playButton.setContentAreaFilled(false);
			playButton.setFocusable(false);
			playButton.setRolloverIcon(highlightedPlay);
			playButton.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
					AudioPlayer player = SceneHandler.singleton.getTimeline().audioPlayer;
					player.playAudioClipAtIndex(0);
			    }
			});
			
			if(timeline.audioPlayer.getSize() == (i + 1)) {
				playButton.setIcon(playChange);
				playButton.setRolloverIcon(highlightedPlayChange);
			}
		    
			// Add audio remove button to audio panel
		    audioPanel.add(removeAudioButton, audioConstraints);
		    audioConstraints.gridx = ++audioxCounter;
		    
		    // Add audio track to audio panel
		    audioPanel.add(audioText, audioConstraints);
		    audioConstraints.gridx = ++audioxCounter;
		    
		    // Add audio track to audio panel
		    audioPanel.add(playButton, audioConstraints);
		    audioConstraints.gridx = ++audioxCounter;
		}
		
	    // Re-add audio button to end of panel
	    audioPanel.add(audioButton, audioConstraints);
	}
	
	/**
	 * SelectAudio() - brings up dialogue box to select audio
	 * 
	 * @author Fernando Palacios
	 */
	public void SelectAudio() {
		
		// Get instance of timeline
		timeline = SceneHandler.singleton.getTimeline();
				
		// Calculate values for thumbnail and transition pixel width along with ratio of pixels per second
		int thumbnailTotalLength = (290 + 40) * timeline.thumbnailsList.getSize();
		int transitionTotalLength = (100) * timeline.transitionsList.getSize();
		
		//If we have more audio than the length of the slides:
	    if(audioTimelineDuration >= thumbnailTotalLength + transitionTotalLength) {
	    	
	    	// Open warning pane in the center of our workspace
	    	JFrame parent = SceneHandler.singleton.getMainFrame();
	    	Coord2 point = new Coord2(
	    			parent.getX() + parent.getSize().width/2,
	    			parent.getY() + parent.getSize().height/2
	    			);
	    	WarningPane p = new WarningPane(parent, "Warning - Audio too long", point, new Dimension(400, 190));
	    	parent.setEnabled(false);
	    	return;
	    }
				
    	JFileChooser chooser = new JFileChooser();
    	chooser.setCurrentDirectory(new java.io.File(".")); // start at application current directory
    	
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setFileFilter(new FileNameExtensionFilter("Audio Files", new String[] { "WAV", "AIFF"}));
        
        AudioPlayer player = SceneHandler.singleton.getTimeline().audioPlayer;
        
    	int returnVal = chooser.showDialog(ArrangeScene.this, "Open Audio");
    	if(returnVal == JFileChooser.APPROVE_OPTION) {
    		
    	    audioFile = chooser.getSelectedFile();
    	    Audio a = new Audio(audioFile);
			player.addAudio(a);
			audioPanel.removeAll();
			PopulateAudio();
			revalidate();
    	    	
    	}
    	
	}
	
	/**
	 * RemoveAudio() - removes visual audio textfield and audio from audioList
	 * 
	 * @author Fernando Palacios
	 */
	private void RemoveAudio(Audio track)
	{
    	// Remove selected thumb from timeline
    	timeline = SceneHandler.singleton.getTimeline();
    	int removeIndex = timeline.audioPlayer.indexOf(track);
    	timeline.audioPlayer.removeAudioAtIndex(removeIndex);
    	
    	// Remove components and repaint 
    	audioPanel.removeAll();
    	PopulateAudio();
    	revalidate();
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
                	c = new Color(217, 202, 192);
                    buffered.setRGB(i, j, c.getRGB());
                }
            }
        } 
        return buffered;
    }

}