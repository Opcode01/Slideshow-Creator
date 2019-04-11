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
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import core.*;

public class PlayScene extends Scene {
	
	/** layered panel containing the slide panel and the transition panel on top of it */
	private JLayeredPane layeredPane;
	
	/** panel showing the current slide */
	private JPanel slidePanel;
	
	/** panel over the slidePanel showing the transitioning image */
	private JPanel transitionPanel;
	
	private static final SlideDir autoDir = SlideDir.RIGHT;
	
	/** button that controls play and pause on auto slideshow */
	private JButton playPauseButton;
	
	/** manual left advance button */
	private JButton leftButton;

	/** manual right advance button */
	private JButton rightButton;
	
	/** whether or not the slideshow is paused. Only relevant on auto playback */
	private boolean isPaused;

	//gui icons
	private ImageIcon backIcon;
	private ImageIcon backIconHigh;
	private ImageIcon backIconGray;
	private ImageIcon forwardIcon;
	private ImageIcon forwardIconHigh;
	private ImageIcon forwardIconGray;
	private ImageIcon removeCurrentIcon;
	private ImageIcon removeCurrentIconHigh;
	private ImageIcon playIcon;
	private ImageIcon playIconHigh;
	private ImageIcon pauseIcon;
	private ImageIcon pauseIconHigh;
	
	/** The gray color to use for graying out images */
	private static final Color grayColor = SliderColor.very_light_gray;
	
	/**	Thumbnail that displays on the player */
	private Thumbnail slideThumb;
	
	//slide direction constants
	private enum SlideDir {LEFT, RIGHT};
	
	public Thumbnail getSlideThumb() {
		return slideThumb;
	}
	
	/** Index of thumbnail currently showing */
	private int currentSlideIndex;
	
	/** Index of transition currently playing, -1 if none playing */
	private int currentTransitionIndex;
	
	/** reference to SceneHandler's timeline */
	private Timeline timeline;
	
	/** slide advancement and transition timer */
	private Timer slideTimer;
	
	/** time to wait between checking if the transition is finished if waiting for it to finish */
	private static final int checkTransitionFinishedInterval = 100;
	
	/**
	 * list of times for each slide
	 */
	private SlideShowTime[] slideTimes;
	
	public PlayScene()
	{
		backIcon = new ImageIcon(SceneHandler.class.getResource("Images/backButton.png"));
		backIconHigh = new ImageIcon(SceneHandler.class.getResource("Images/highlightedBackButton.png"));
		backIconGray = ImageGray(backIcon);
		forwardIcon = new ImageIcon(SceneHandler.class.getResource("Images/forwardButton.png"));
		forwardIconHigh = new ImageIcon(SceneHandler.class.getResource("Images/highlightedForwardButton.png"));
		forwardIconGray = ImageGray(forwardIcon);
		removeCurrentIcon = new ImageIcon(SceneHandler.class.getResource("Images/removeCurrentButton.png"));
		removeCurrentIconHigh = new ImageIcon(SceneHandler.class.getResource("Images/highlightedRemoveCurrentButton.png"));
		playIcon = new ImageIcon(getClass().getResource("Images/playButton.png"));
		playIconHigh = new ImageIcon(getClass().getResource("Images/highlightedPlayButton.png"));
		pauseIcon = new ImageIcon(getClass().getResource("Images/pauseButton.png"));
		pauseIconHigh = new ImageIcon(getClass().getResource("Images/highlightedPauseButton.png"));
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
		currentSlideIndex = 0;
		currentTransitionIndex = -1;
		isPaused = false;
		
		timeline = SceneHandler.singleton.getTimeline();
		
		// Create GridBagLayout object and constraints
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(gridBag);
		setBackground(SliderColor.beige_gray);
		
		//set up layered pane with slide and transition panels
		layeredPane = new JLayeredPane();
		layeredPane.setBorder(BorderFactory.createEmptyBorder());
		layeredPane.setBackground(SliderColor.dark_gray);
		
		// Set slide panel configurations
		slideThumb = getSlide(currentSlideIndex);
		slidePanel = new JPanel();
		slidePanel.setLayout(new BorderLayout());
		slidePanel.setBorder(BorderFactory.createEmptyBorder());
		slidePanel.setBackground(SliderColor.clear);
		slidePanel.add(createSlideLabel(), BorderLayout.CENTER);
		
		//set up transition panel
		transitionPanel = new JPanel();
		transitionPanel.setLayout(new BorderLayout());
		transitionPanel.setBorder(BorderFactory.createEmptyBorder());
		transitionPanel.setBackground(SliderColor.clear);
		
		//add slidePanel behind transition panel
		layeredPane.add(new JLabel("Hey"), 0);
		//layeredPane.add(transitionPanel, 1);
		//layeredPane.add(slidePanel, 2);
		
		// Set constraints and add layered pane
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 0;
		this.add(layeredPane, c);
		
		// Set options panel configurations
		JPanel optionsPanel = new JPanel();
		optionsPanel.setLayout(gridBag);
		optionsPanel.setBackground(SliderColor.beige_gray);
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
		    	stopSlideshow();
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
		controlPanel.setBackground(SliderColor.beige_gray);
		
		//use left and right buttons if the timeline advances manually
		if (timeline.timelineSettings.isManual)
		{
			//left button
			leftButton = new JButton(backIcon);
			leftButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			leftButton.setToolTipText("Previous Image");
			leftButton.setBorder(BorderFactory.createEmptyBorder());
			leftButton.setContentAreaFilled(false);
			leftButton.setFocusable(false);
			leftButton.setRolloverIcon(backIconHigh);
			leftButton.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	if (!isTransitioning())
			    		scheduleStartTransition(SlideDir.LEFT);
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
			rightButton = new JButton(forwardIcon);
			rightButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			rightButton.setToolTipText("Previous Image");
			rightButton.setBorder(BorderFactory.createEmptyBorder());
			rightButton.setContentAreaFilled(false);
			rightButton.setFocusable(false);
			rightButton.setRolloverIcon(forwardIconHigh);
			rightButton.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	if (!isTransitioning())
			    		scheduleStartTransition(SlideDir.RIGHT);
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
			
			setManualButtonIcons();
		}
		else//use play/pause button if manual
		{
			//playPause button
			playPauseButton = new JButton(pauseIcon);
			playPauseButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			playPauseButton.setToolTipText("Pause Slideshow");
			playPauseButton.setBorder(BorderFactory.createEmptyBorder());
			playPauseButton.setContentAreaFilled(false);
			playPauseButton.setFocusable(false);
			playPauseButton.setRolloverIcon(pauseIconHigh);
			playPauseButton.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
		    		togglePauseSlideshow();
			    }
			});
			
			// Set constraints and add left button
			c.fill = GridBagConstraints.NONE;
			c.anchor = GridBagConstraints.CENTER;
			c.weightx = 0;
			c.weighty = 0;
			c.gridx = 0;
			c.gridy = 0;
			controlPanel.add(playPauseButton, c);
		}
		
		//calculate timers and set up automatic slideshow when not manual
		startSlideshow();
			
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
	
	/**
	 * creates a JLabel with the current slide thumbnail on it
	 * @return JLabel of the current slide
	 * 
	 * @author Timothy Couch
	 */
	private JLabel createSlideLabel()
	{
		if (slideThumb != null)
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
		else return new JLabel();
	}
	
	/**
	 * pauses or plays the slideshow based on what it currently is
	 */
	private void togglePauseSlideshow()
	{
		//pause/play if not transitioning, only pause otherwise
		if (!isTransitioning() || !isPaused)
		{
			isPaused = !isPaused;
			System.out.println("Paused: " + isPaused);
		}
		
		//just hit pause
		if (isPaused)
		{
			//pause slideshow
			if (!isTransitioning())
				cancelTimer();
			
			//swap gui button to play
			playPauseButton.setToolTipText("Play Slideshow");
			playPauseButton.setIcon(playIcon);
			playPauseButton.setRolloverIcon(playIconHigh);
		}
		else //just hit play
		{
			//play slideshow if not at end
			if (getNextSlideIndex(autoDir) != currentSlideIndex)
				scheduleStartTransition(autoDir);
			else//restart because we're at the end
			{
				startSlideshow();
			}
			
			//swap gui button to pause
			playPauseButton.setToolTipText("Pause Slideshow");
			playPauseButton.setIcon(pauseIcon);
			playPauseButton.setRolloverIcon(pauseIconHigh);
		}
		revalidate();
	}
	
	/**
	 * update the left and right buttons to be gray or colored based on whether they have functionality at the moment
	 */
	private void setManualButtonIcons()
	{
		if (timeline.timelineSettings.isManual)
		{
			//if you can't move left
			if (isTransitioning() || currentSlideIndex == getNextSlideIndex(SlideDir.LEFT))
			{
				leftButton.setIcon(backIconGray);
				leftButton.setRolloverIcon(backIconGray);
				leftButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			else
			{
				leftButton.setIcon(backIcon);
				leftButton.setRolloverIcon(backIconHigh);
				leftButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			
			//if you can't move right
			if (isTransitioning() || currentSlideIndex == getNextSlideIndex(SlideDir.RIGHT))
			{
				rightButton.setIcon(forwardIconGray);
				rightButton.setRolloverIcon(forwardIconGray);
				rightButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			else
			{
				rightButton.setIcon(forwardIcon);
				rightButton.setRolloverIcon(forwardIconHigh);
				rightButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			revalidate();
		}
	}
	
	/**
	 * gets the thumbnail at the slide index specified
	 * @param i slide index to get thumbnail
	 * @return thumbnail at index i
	 * 
	 * @author Timothy Couch
	 */
	private Thumbnail getSlide(int i) {
		return timeline.thumbnailsList.getSize() > 0 ? timeline.thumbnailsList.getThumbnail(i) : null;
	}
	
	/**
	 * gets the transition at the slide index specified
	 * @param i slide index to get transition
	 * @return transition at index i
	 */
	private Transition getTransition(int i) {
		return timeline.transitionsList.getTransition(i);
	}
	
	/**
	 * gets the index of the next slide to the right or left. Accounts for looping or not
	 * @param dir left or right to get index
	 * @return index of next slide
	 * 
	 * @Timothy Couch
	 */
	private int getNextSlideIndex(SlideDir dir) {
		//get the numeric value of left and right
		int deltaIndex = dir == SlideDir.RIGHT ? 1 : -1;
		
		int showLength = SceneHandler.singleton.getTimeline().thumbnailsList.getSize();
		
		if (showLength > 0)
		{
			//update slide index
			if (timeline.timelineSettings.isLoopingSlides)
				//thanks to Fabian for the idea to add length to the number to loop left https://stackoverflow.com/questions/14785443/is-there-an-expression-using-modulo-to-do-backwards-wrap-around-reverse-overfl
				return (currentSlideIndex + deltaIndex + showLength) % showLength;
			else
			{
				//change slide if not first or last slide
				if ((deltaIndex < 0 && currentSlideIndex > 0) || (deltaIndex > 0 && currentSlideIndex < showLength - 1))
					return currentSlideIndex + deltaIndex;
			}
		}
		
		return currentSlideIndex;
	}
	
	/**
	 * moves the slideshow one slide to the right or left depending on the direction
	 * @param dir left or right to move slide
	 * @return true if advanced slide, false otherwise
	 * 
	 * @author Timothy Couch
	 */
	private boolean advanceSlide(SlideDir dir) {
		int prevSlideIndex = currentSlideIndex;
		currentSlideIndex = getNextSlideIndex(dir);
		currentTransitionIndex = -1;
		
		//didn't advance slide
		if (currentSlideIndex == prevSlideIndex)
			return false;
		
		showCurrentSlide();
		
		setManualButtonIcons();
		
		return true;
	}
	
	/**
	 * updates the slideThumb and slidePanel to the slide at currentSlideIndex
	 * 
	 * @Timothy Couch
	 */
	private void showCurrentSlide()
	{
		slideThumb = getSlide(currentSlideIndex);
		
		//update the view
		slidePanel.removeAll();
		slidePanel.add(createSlideLabel(), BorderLayout.CENTER);
		revalidate();
	}
	
	/**
	 * starts the current slide's transition
	 * @param dir left or right to move slide
	 * 
	 * @author Timothy Couch
	 */
	private synchronized void startTransition(SlideDir dir) {
		//if transitioning right, use current transition. If left, use previous transition
		if (dir == SlideDir.LEFT)
			currentTransitionIndex = getNextSlideIndex(dir);
		else currentTransitionIndex = currentSlideIndex;
		
		Transition transition = getTransition(currentTransitionIndex);
		
		slidePanel.removeAll();
		JPanel transitionPanel = new JPanel();
		slidePanel.add(transitionPanel, BorderLayout.CENTER);
		transitionPanel.setBorder(BorderFactory.createEmptyBorder());
		transitionPanel.setBackground(SliderColor.dark_gray);
		revalidate();
		
		setManualButtonIcons();
		
		transition.PlayTransition(transitionPanel, Thumbnail.cloneImage(slideThumb.getImageRaw()), Thumbnail.cloneImage(getSlide(getNextSlideIndex(dir)).getImageRaw()));
	}
	
	/**
	 * starts the timer to start the transition to the next slide
	 * @param dir left or right to move the slide
	 * 
	 * @author Timothy Couch
	 */
	private synchronized void scheduleStartTransition(SlideDir dir) {
		//run after slide duration if automatic, run immediately if manual
		int timerLength = !timeline.timelineSettings.isManual ? slideTimes[currentSlideIndex].showSlideDuration : 0;
		System.out.println("Scheduling start of transition with timer " + timerLength);
		
		cancelTimer();
		slideTimer = new Timer();
		slideTimer.schedule(
				new TimerTask() {

					//start transition to next slide
					@Override
					public void run() {
						System.out.println("Starting transition to " + (dir == SlideDir.RIGHT ? "next" : "previous") + " slide! Index: " + currentSlideIndex);
						
						//if there's another slide in that direction, go
						if (getNextSlideIndex(dir) != currentSlideIndex)
						{
							startTransition(dir);
							
							scheduleStartSlide(dir);
						}
						else
						{
							System.out.println("StartTransition: No more slides available in that direction!");
							
							//"pause" the slideshow at the end so that you can start it over from the beginning by pressing play
							if (!timeline.timelineSettings.isManual && !timeline.timelineSettings.isLoopingSlides && !isPaused)
								togglePauseSlideshow();
						}
					}
				},
				timerLength
				);
	}
	
	/**
	 * starts the timer to finish the current transition and start a new slide timer
	 * The timer waits and loops until the transition is finished
	 * @param dir left or right to move the slide
	 * 
	 * @author Timothy Couch
	 */
	private synchronized void scheduleStartSlide(SlideDir dir) {
		cancelTimer();
		slideTimer = new Timer();
		slideTimer.schedule(
				new TimerTask() {

					//finalize transition and start a new slide timer
					@Override
					public void run() 
					{
						System.out.println("Finishing transition to " + (dir == SlideDir.RIGHT ? "next" : "previous") + " slide! Index: " + currentSlideIndex);
						
						//if the transition playing has finished
						if (!getTransition(currentTransitionIndex).isRunning())
						{
							slideTimer.cancel();
							//if the slideshow will advance (otherwise, it doesn't loop and is at the end)
							if (getNextSlideIndex(dir) != currentSlideIndex)
							{
								System.out.println("Advancing Slide from " + currentSlideIndex);
								advanceSlide(dir);
								
								//start the next slide if auto and not paused
								if (!timeline.timelineSettings.isManual && !isPaused)
									scheduleStartTransition(dir);
							}
							else System.out.println("StartSlide: No more slides available in that direction!");
						}
						else System.out.println("Transition not finished! Running timer again");
					}
				},
				slideTimes[currentTransitionIndex].transitionDuration,
				checkTransitionFinishedInterval
				);
	}
	
	/**
	 * calculates times and starts the timers for the automatic slideshow
	 * 
	 * @author Timothy Couch
	 */
	private synchronized void startSlideshow() {
		slideTimes = new SlideShowTime[timeline.transitionsList.getSize()];
		for (int i = 0; i < slideTimes.length; i++)
			slideTimes[i] = new SlideShowTime(timeline, i);
		
		//start at the proper values
		currentSlideIndex = 0;
		currentTransitionIndex = -1;
		showCurrentSlide();
		
		//begin running the auto slideshow
		if (!timeline.timelineSettings.isManual && timeline.thumbnailsList.getSize() > 0)
			scheduleStartTransition(autoDir);
	}
	
	/**
	 * ends the slideshow, waits on the transition to stop (not to finish), returns to menu
	 * 
	 * @author Timothy Couch
	 */
	private synchronized void stopSlideshow()
	{
		cancelTimer();
		
		if (isInitialized() && currentTransitionIndex >= 0)
		{
			getTransition(currentTransitionIndex).stopTransition();
			currentTransitionIndex = -1;
		}
	}
	
	/**
	 * cancels the timer for the slideshow
	 * @return whether the timer existed before
	 * 
	 * @author Timothy Couch
	 */
	private synchronized boolean cancelTimer() {
		if (slideTimer != null)
		{
			slideTimer.cancel();
			slideTimer = null;
			return true;
		}
		
		return false;
	}
	
	/**
	 * Whether the program is currently transitioning
	 * @return true if there's a transition running
	 * 
	 * @author Timothy Couch
	 */
	private synchronized boolean isTransitioning() {
		return currentTransitionIndex >= 0;
	}
	

	
    /**
     * Replaces all rgb with gray while preserving alpha
     * 
     * @param imIcon - the ImageIcon to gray out
	 * 
	 * @author Fernando Palacios
	 * @author Timothy Couch
     */
    public static ImageIcon ImageGray(ImageIcon imIcon) {
    	Image img = imIcon.getImage();
        BufferedImage imgBuff = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        imgBuff.getGraphics().drawImage(img, 0, 0, null);

        for (int i = 0; i < imgBuff.getWidth(); i++) {
            for (int j = 0; j < imgBuff.getHeight(); j++) {                    
                Color pixColor = new Color(imgBuff.getRGB(i, j), true);
                
                if (pixColor.getAlpha() > 0) {
                	pixColor = new Color(grayColor.getRed(), grayColor.getGreen(), grayColor.getBlue(), pixColor.getAlpha());
                	imgBuff.setRGB(i, j, pixColor.getRGB());
                }
            }
        } 
        return new ImageIcon(imgBuff);
    }
	
	/**
	 * removes the timer when the scene is destroyed
	 * 
	 * @author Timothy Couch
	 */
	@Override
	public void destroy()
	{
		super.destroy();
		stopSlideshow();
	}
}
