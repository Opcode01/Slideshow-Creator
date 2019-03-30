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
import java.io.File;
import java.util.ArrayList;

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
	
	/** Select all button */
	private JButton settingsButton;
	
	/** Deselect all button */
	private JButton removeCurrentButton;
	
	/** Create audio button */
	private JButton audioButton;
	
	/** Create audio track field arrayList */
	private ArrayList<JTextField> audioTrackList;
	
    /** Create remove audio button arrayList */
	private ArrayList<JButton> removeAudioButtonList;
	
	/** Selected thumbnail on the timeline */
	private Thumbnail selectedThumbnail;
	
	/** Audio file */
	private File audioFile;
	
	/** Create timeline object */
	private Timeline timeline;
	
	/** Counter for audio panel formatting */
	private int audioxCounter;
	
	/** Back custom button image */
	private ImageIcon back;
	
	/** Settings custom button image */
	private ImageIcon settings;
	
	/** Remove current custom button image */
	private ImageIcon removeCurrent;
	
	/** Highlighted back custom button image */
	private ImageIcon highlightedBack;
	
	/** Highlighted select all custom button image */
	private ImageIcon highlightedSettings;
	
	/** Highlighted deselect all custom button image */
	private ImageIcon highlightedRemoveCurrent;
	
	/** Audio custom button image */
	private ImageIcon audio;
	
	/** Highlighted audio custom button image */
	private ImageIcon highlightedAudio;
	
	/** Remove audio custom button image */
	private ImageIcon removeAudio;
	
	/** Highlighted remove audio custom button image */
	private ImageIcon highlightedRemoveAudio;
	
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
		// Create GridBagLayout object and constraints
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		// Set panel configurations
		this.setLayout(gridBag);
		
		// Create images and add icons
		// Create images and add icons
		back = new ImageIcon(getClass().getResource("/creator/Images/backButton.png"));
		settings = new ImageIcon(getClass().getResource("/creator/Images/settingsButton.png"));
		removeCurrent = new ImageIcon(getClass().getResource("/creator/Images/removeCurrentButton.png"));
		audio = new ImageIcon(getClass().getResource("/creator/Images/audioButton.png"));
		removeAudio = new ImageIcon(getClass().getResource("/creator/Images/audioButton.png"));
		highlightedBack = new ImageIcon(getClass().getResource("/creator/Images/highlightedBackButton.png"));
		highlightedSettings = new ImageIcon(getClass().getResource("/creator/Images/highlightedSettingsButton.png"));
		highlightedRemoveCurrent = new ImageIcon(getClass().getResource("/creator/Images/highlightedRemoveCurrentButton.png"));
		highlightedAudio = new ImageIcon(getClass().getResource("/creator/Images/highlightedAudioButton.png"));
		highlightedRemoveAudio = new ImageIcon(getClass().getResource("/creator/Images/highlightedAudioButton.png"));
		
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
		    	System.out.println(selectedThumbnail.getImagePath() + "remove");
		    	int removeIndex = timeline.thumbnailsList.indexOf(selectedThumbnail);
		    	timeline.removeSlide(removeIndex);
		    	
		    	// Remove components and repaint 
		    	timelinePanel.removeAll();
		    	PopulateTimeline();
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
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 3;
		optionsPanel.add(removeCurrentButton, c);
		
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
		audioConstraints = new GridBagConstraints();
		
		// Set up blank timeline panel
		SetupTimelinePanel(false);
		timelinePanelContainer.add(timelinePanel, timelinePanelConstraints);
		
		// Create scroller and set scroll pane configurations
		timelineScroller = new JScrollPane(timelinePanelContainer, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		timelineScroller.setBorder(BorderFactory.createEmptyBorder());
		timelineScroller.setPreferredSize(new Dimension(200, 400)); //235
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
		timelinePanel.setBackground(medium_gray);
		PopulateTimeline();
		
		// Create audio panel
		audioPanel = new JPanel();
		audioPanel.setLayout(new GridBagLayout());
		audioPanel.setBackground(aqua);
		PopulateAudio();
		UpdateAudio();
		
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
	private void PopulateTimeline()
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
     * PopulateAudio() - populates audio display with add audio button to allow adding of audio
     * 
     * @author Fernando Palacios
     */
	public void PopulateAudio()
	{
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
		
		// Set audio gridx counter for formatting
		audioxCounter = 0;
		
		// Set constraints for formatting
		//audioConstraints.weighty = 0;
		//audioConstraints.weightx = 0;
		audioConstraints.gridx = audioxCounter;
		audioConstraints.gridy = 0;
		
		// Add audio button to audio panel and increment counter
	    audioPanel.add(audioButton, audioConstraints);
	    ++audioxCounter;
	}
	
	/**
	 * SelectAudio() - brings up dialogue box to select audio and then calls function to add it to timeline
	 * 
	 * @author Fernando Palacios
	 */
	private void SelectAudio()
	{
    	JFileChooser chooser = new JFileChooser();
    	chooser.setCurrentDirectory(new java.io.File(".")); // start at application current directory
    	
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setFileFilter(new FileNameExtensionFilter("Audio Files", new String[] { "WAV", "AIFF", "MP3", "MP4" }));
        
    	int returnVal = chooser.showDialog(ArrangeScene.this, "Open");
    	if(returnVal == JFileChooser.APPROVE_OPTION) {
    	    audioFile = chooser.getSelectedFile();
    	    //timeline.addAudio(audioFile);
    	    audioPanel.removeAll();
        	UpdateAudio();
    	}
	}
	
	/**
	 * AddAudio() - adds visual audio textfield to display length of audiotrack
	 * 
	 * @author Fernando Palacios
	 */
	private void AddAudio()
	{
	    
	    // Create text field for audio
		JTextField audioText = new JTextField();
		audioText.setBackground(aqua);
		audioText.setForeground(Color.white);
        audioText.setEditable(false);
	    audioText.setText(audioFile.getName());
	    audioText.setPreferredSize(new Dimension(audioTrackSize, 10));
	    
	    // CReate remove audio button
	    JButton removeAudioButton = new JButton(audio);
	    removeAudioButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		removeAudioButton.setBorder(BorderFactory.createEmptyBorder());
		removeAudioButton.setContentAreaFilled(false);
		removeAudioButton.setFocusable(false);
		removeAudioButton.setRolloverIcon(highlightedAudio);
		removeAudioButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	//RemoveAudio();
		    }
		});
		
		// Add audio remove button to audio panel
	    audioPanel.add(removeAudioButton, audioConstraints);
	    ++audioxCounter;
	    
	    // Add audio track to audio panel
	    audioPanel.add(audioText, audioConstraints);
	    ++audioxCounter;
	    
	    // Re-add audio button to end of panel
	    audioPanel.add(audioButton, audioConstraints);
	}
	
	/**
	 * RemoveAudio() - removes visual audio textfield and audio from audioList
	 * 
	 * @author Fernando Palacios
	 */
	private void RemoveAudio()
	{

	}
	
    /**
     * UpdateAudio() - updates the audio display on import of slider file
     * 
     * @author Fernando Palacios
     */
	public void UpdateAudio()
	{
	    int thumbnailLength = (290 + 40) * timeline.thumbnailsList.getSize();
	    int transitionLength = (100) * timeline.transitionsList.getSize();
	    
	    float secondsToPixels = (thumbnailLength + transitionLength) / 30; //TO DO: get slideshowduration to replace number with; EX of 30 seconds for testing
	    //TO DO: int audioTrackSize = (size of audio track in seconds) * secondsToPixels
	    int audioTrackSize = Math.round(15 * secondsToPixels); //2150 should be the answer i thin
	    //TO DO: Get size of audio tracks in seconds for above using Joe's audio class
	    System.out.println(audioTrackSize);
	    
		for(int i = 0; i < timeline.audioList.getSize(); i++)
		{
		    audioFile = timeline.audioList.getAudio(i);
		}
		
	    // Add audio button to end of panel
	    audioPanel.add(audioButton, audioConstraints);
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
                	c = new Color(254, 250, 238);
                    buffered.setRGB(i, j, c.getRGB());
                }
            }
        } 
        return buffered;
    }

}