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

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import core.*;

public class ArrangeScene extends Scene{
	
	/** Create custom color */
	private JPanel optionsPanel;
	
	/** Create custom color */
	private JPanel imagePanel;
	
	/** Create custom color */
	private JPanel timelinePanel;
	
	/** container for timeline panel */
	private JPanel timelinePanelContainer;
	
	/** Create Settings Pane */
	private SettingsPane settingsPane;
	
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
	
	/** Back custom button image */
	private ImageIcon back;
	
	/** Directory custom button image */
	private ImageIcon directory;
	
	/** Settings custom button image */
	private ImageIcon settings;
	
	/** Remove current custom button image */
	private ImageIcon removeCurrent;
	
	/** Highlighted back custom button image */
	private ImageIcon highlightedBack;
	
	/** Highlighted arrange custom button image */
	private ImageIcon highlightedDirectory;
	
	/** Highlighted select all custom button image */
	private ImageIcon highlightedSettings;
	
	/** Highlighted deselect all custom button image */
	private ImageIcon highlightedRemoveCurrent;
	
	/** Create ThumbnailsList object to reference */
	private Timeline list;
	
	/** Create custom aqua color */
	private Color aqua = new Color(132, 200, 202);
	
	/** Create custom light gray color */
	private Color light_gray = new Color(60, 60, 60);
	
	/** Create custom dark_gray color */
	private Color dark_gray = new Color(30, 30, 30);
	
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
		
		back = new ImageIcon(getClass().getResource("Images/backButton.png"));
		directory = new ImageIcon(getClass().getResource("Images/directoryButton.png"));
		settings = new ImageIcon(getClass().getResource("Images/settingsButton.png"));
		removeCurrent = new ImageIcon(getClass().getResource("Images/removeCurrentButton.png"));
		highlightedBack = new ImageIcon(getClass().getResource("Images/highlightedBackButton.png"));
		highlightedDirectory = new ImageIcon(getClass().getResource("Images/highlightedDirectoryButton.png"));
		highlightedSettings = new ImageIcon(getClass().getResource("Images/highlightedSettingsButton.png"));
		highlightedRemoveCurrent = new ImageIcon(getClass().getResource("Images/highlightedRemoveCurrentButton.png"));
		
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
		    	//Open settings pane in the center of our workspace
		    	JFrame parent = SceneHandler.singleton.getMainFrame();
		    	Coord2 point = new Coord2(
		    			parent.getX() + parent.getSize().width/2,
		    			parent.getY() + parent.getSize().height/2
		    			);
		    	settingsPane = new SettingsPane(parent, "Project Settings", point, new Dimension(400, 470));
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
		    	//TODO: Remove the currently selected thumbnail and transition from timeline
		    	Timeline timeline = SceneHandler.singleton.getTimeline();
		    	System.out.println(selectedThumbnail.getImagePath() + "remove");
		    	int removeIndex = timeline.thumbnailsList.indexOf(selectedThumbnail);
		    	timeline.removeSlide(removeIndex);
		    	//remove components and repaint 
		    	timelinePanel.removeAll();
		    	ShowImages(timelinePanel);
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
		
		// Create outerpanel that houses the timeline panel for layout and whitespace
		timelinePanelContainer = new JPanel();
		timelinePanelContainer.setLayout(gridBag);
		timelinePanelContainer.setBackground(light_gray);
		
		// Set up timeline panel constraints
		timelinePanelConstraints = (GridBagConstraints) c.clone();
		
		// Set up blank image panel
		setupTimelinePanel(false);
		timelinePanelContainer.add(timelinePanel, c);
		
		// Set scroll pane configurations
		timelineScroller = new JScrollPane(timelinePanelContainer, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		timelineScroller.getVerticalScrollBar().setBackground(light_gray);
		timelineScroller.setBorder(BorderFactory.createEmptyBorder());
		
		///////////////////////
		//example 2 of drawing the image associated with a transition
		JButton transitionButton = new JButton(TransitionType.WIPE_DOWN.getImage());
		transitionButton.setPreferredSize(new Dimension(75, 50));
		c.fill = GridBagConstraints.NONE;
		timelinePanel.add(transitionButton, c);
		
		///////////////////////
		
		///////////////////////
		//Add example image - this is approximately what you should do to set up the display image! :)
		Thumbnail testThumb = new Thumbnail("src/core/TransitionImages/crossFade.png");
		JLabel testLabel = new JLabel() {
			  @Override
			  public void paintComponent(Graphics g) {
				  testThumb.drawFill(g, this);
				  //example 1 of drawing the image associated with a transition
				  //g.drawImage(TransitionType.WIPE_RIGHT.getImage().getImage(), 0, 200, this);
				  }
			  };
		
		c.weightx = 0.01;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.NORTH;
		imagePanel.add(testLabel, c);
		///////////////////////
		
		// Set constraints and add options panels
		c.insets = new Insets(0, 0, 0, 0);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 0;
		this.add(optionsPanel, c);
		
		// Set constraints and add image panel
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 1;
		c.gridy = 0;
		this.add(imagePanel, c);
		
		// Set constraints and add timeline panel
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 2;
		c.weighty = 0.4;
		c.gridx = 0;
		c.gridy = 1;
		this.add(timelineScroller, c);
		
		this.revalidate();
	}
	
    /**
     * initialize() - opens the images and sets up the scene for use
     * 
     * @precondition must run after project directory has been determined
	 * 
	 * @author Timothy Couch
     */
    @Override
    public void initialize()
    {
		super.initialize();

		setupTimelinePanel(true);
    }
	
	public void setupTimelinePanel(boolean revalidate)
	{		
		// Create image panel with new images
		timelinePanel = new JPanel();
		timelinePanel.setLayout(new GridBagLayout());
		timelinePanel.setBackground(light_gray);
		ShowImages(timelinePanel);
		
		// add to outer panel that houses the image panel for layout and whitespace
		timelinePanelContainer.removeAll();
		timelinePanelConstraints.gridheight = 1;
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
     */
	private void ShowImages(JPanel panel)
	{
		int i = 0; //counter for buttons
		int gridxCounter = 0; //layout counter x
		int gridyCounter = 0; // layout counter y
		
		Timeline timeline = SceneHandler.singleton.getTimeline();
		JToggleButton[] thumbButtons = new JToggleButton[timeline.thumbnailsList.getSize()];
		JButton[] transButtons = new JButton[timeline.transitionsList.getSize()];
		GridBagConstraints c = new GridBagConstraints();
		
		for(i = 0; i < thumbButtons.length; i++) {
			
			//set constraints
			c.gridx = gridxCounter++;
			c.gridy = gridyCounter;
			c.fill = GridBagConstraints.BOTH;
			c.insets = new Insets(20, 20, 20, 20);
			
			Thumbnail buttonThumb = timeline.thumbnailsList.getThumbnail(i);
			Transition buttonTrans = timeline.transitionsList.getTransition(i);
			
			transButtons[i] = new JButton(buttonTrans.getTransitionType().getImage());
			transButtons[i].setPreferredSize(new Dimension(320, 200));
			transButtons[i].setRolloverEnabled(true);
			//transButtons[i].setRolloverIcon(new ImageIcon(ImageHover(buttonThumb.getImageThumb())));
			transButtons[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			transButtons[i].setBorder(BorderFactory.createEmptyBorder());
			transButtons[i].setFocusable(false);
			transButtons[i].setContentAreaFilled(false);
			
			thumbButtons[i] = new JToggleButton(new ImageIcon(buttonThumb.getImageThumb()));
			JToggleButton keeper = thumbButtons [i];
			thumbButtons[i].setPreferredSize(new Dimension(320, 200));
			thumbButtons[i].setRolloverEnabled(true);
			thumbButtons[i].setRolloverIcon(new ImageIcon(ImageHover(buttonThumb.getImageThumb())));
			thumbButtons[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			thumbButtons[i].setBorder(BorderFactory.createEmptyBorder());
			thumbButtons[i].setFocusable(false);
			thumbButtons[i].setContentAreaFilled(false);
			thumbButtons[i].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					//add button to or remove button from timeline
					Timeline timeline = SceneHandler.singleton.getTimeline();

					int slideIndex = timeline.thumbnailsList.indexOf(buttonThumb);
					
					selectedThumbnail = buttonThumb;
					
					
					if(keeper.isSelected())
					{
						//clear borders
						for(JToggleButton button : thumbButtons)
						{
							button.setBorder(BorderFactory.createEmptyBorder());
							//button.setIcon(new ImageIcon(buttonThumb.getImageThumb()));
							button.setSelected(false);
						}
						
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
						imagePanel.add(testLabel, c);
						imagePanel.revalidate();
						
						///////////////////////
						//keeper.setIcon(new ImageIcon(ImageHover(buttonThumb.getImageThumb())));
					}
					
					
					
				}
				});
			
			panel.add(thumbButtons[i], c);
			
			// Set constraints
			c.gridx = gridxCounter++;
			c.gridy = gridyCounter;
			c.fill = GridBagConstraints.NONE;
			c.insets = new Insets(0, 0, 0, 0);
			panel.add(transButtons[i], c);
		}
	}
	
    /**
     * ImageHover() - darkens the image so that it adds a hovered effect
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
	}

}