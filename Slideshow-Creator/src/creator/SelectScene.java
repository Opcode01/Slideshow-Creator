/**
 * SelectScene.java
 * Scene in which users choose which pictures to use
 * 
 * Slideshow Creator
 * Timothy Couch, Joseph Hoang, Fernando Palacios, Austin Vickers
 * CS 499 Senior Design with Dr. Rick Coleman
 * 2/17/19
 */
package creator;

import java.awt.Color;
import java.awt.Cursor;
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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;

import core.*;

public class SelectScene extends Scene
{
	private static final FileNameExtensionFilter imageFileFilter = new FileNameExtensionFilter("Image File", "jpg", "jpeg", "png", "gif", "bmp");

	/** Create custom color */
	private JPanel optionsPanel;
	
	/** Create custom color */
	private JPanel imagePanel;

	/** main image selection panel constraints */
	private GridBagConstraints imagePanelConstraints;

	/** container for image panel */
	private JPanel imagePanelContainer;
	
	/** Create Settings Pane */
	private JScrollPane imageScroller;
	
	/** Back button */
	private JButton backButton;
	
	/** Arrange button */
	private JButton arrangeButton;
	
	/** Select all button */
	private JButton selectAllButton;
	
	/** List of all thumbnail thumbButtons to be shown on the image panel */
	private JButton[] thumbButtons;
	
	/** Deselect all button */
	private JButton deselectAllButton;
	
    /** Label that shows the directory */
    private JLabel directoryLabel;
	
	/** Back custom button image */
	private ImageIcon back;
	
	/** Arrange custom button image */
	private ImageIcon arrange;
	
	/** Select all custom button image */
	private ImageIcon selectAll;
	
	/** Deselect all custom button image */
	private ImageIcon deselectAll;
	
	/** Highlighted back custom button image */
	private ImageIcon highlightedBack;
	
	/** Highlighted arrange custom button image */
	private ImageIcon highlightedArrange;
	
	/** Highlighted select all custom button image */
	private ImageIcon highlightedSelectAll;
	
	/** Highlighted deselect all custom button image */
	private ImageIcon highlightedDeselectAll;
	
	/** Create custom light gray color */
	private Color light_gray = new Color(41, 41, 41);
	
	/** Create custom dark_gray color */
	private Color dark_gray = new Color(0, 0, 0);
	
	/** Create ThumbnailsList object to reference */
	private ThumbnailsList allThumbs;
	
	/** Create custom aqua color */
	private Color aqua = new Color(132, 200, 202);

	/**
	 * SelectScene() - sets up selection scene with GUI stuff
	 *
	 * @author Fernando Palacios
     * @author austinvickers
     * @author Timothy Couch
     */
    public SelectScene()
    {
    	allThumbs = new ThumbnailsList();
    	
		// Create GridBagLayout object and constraints
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		// Set image locations
		back = new ImageIcon(getClass().getResource("Images/backButton.png"));
		arrange = new ImageIcon(getClass().getResource("Images/arrangeButton.png"));
		selectAll = new ImageIcon(getClass().getResource("Images/selectAllButton.png"));
		deselectAll = new ImageIcon(getClass().getResource("Images/deselectAllButton.png"));
		highlightedBack = new ImageIcon(getClass().getResource("Images/highlightedBackButton.png"));
		highlightedArrange = new ImageIcon(getClass().getResource("Images/highlightedArrangeButton.png"));
		highlightedSelectAll = new ImageIcon(getClass().getResource("Images/highlightedSelectAllButton.png"));
		highlightedDeselectAll = new ImageIcon(getClass().getResource("Images/highlightedDeselectAllButton.png"));
		
		// Set frame configurations
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
		    	GoToDirectoryScene();
		    }
		});
		
		// Create select all button
		arrangeButton = new JButton(arrange);
		arrangeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		arrangeButton.setToolTipText("Arrange");
		arrangeButton.setBorder(BorderFactory.createEmptyBorder());
		arrangeButton.setContentAreaFilled(false);
		arrangeButton.setFocusable(false);
		arrangeButton.setRolloverIcon(highlightedArrange);
		arrangeButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	GoToArrangeScene();
		    }
		});
		
		// Create select all button
		selectAllButton = new JButton(selectAll);
		selectAllButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		selectAllButton.setToolTipText("Select All");
		selectAllButton.setBorder(BorderFactory.createEmptyBorder());
		selectAllButton.setContentAreaFilled(false);
		selectAllButton.setFocusable(false);
		selectAllButton.setRolloverIcon(highlightedSelectAll);
		selectAllButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
				SelectAll(allThumbs);
		    }
		});
		
		// Create deselect all button
		deselectAllButton = new JButton(deselectAll);
		deselectAllButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		deselectAllButton.setToolTipText("Deselect All");
		deselectAllButton.setBorder(BorderFactory.createEmptyBorder());
		deselectAllButton.setContentAreaFilled(false);
		deselectAllButton.setFocusable(false);
		deselectAllButton.setRolloverIcon(highlightedDeselectAll);
		deselectAllButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	DeselectAll(allThumbs);
		    }
		});
		
		// Set options panel configurations
		optionsPanel = new JPanel();
		optionsPanel.setLayout(gridBag);
		optionsPanel.setBackground(light_gray);
		
		// Set constraints and add back button
		c.anchor = GridBagConstraints.NORTHWEST;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0;
		optionsPanel.add(backButton, c);
		
		// Set constraints and add arrange button
		c.gridx = 0;
		c.gridy = 1;
		optionsPanel.add(arrangeButton, c);
		
		// Set constraints and add select all button
		c.gridx = 0;
		c.gridy = 2;
		optionsPanel.add(selectAllButton, c);
		
		// Set constraints and add deselect all button
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 3;
		optionsPanel.add(deselectAllButton, c);
		
		// Create outerpanel that houses the image panel for layout and whitespace
		imagePanelContainer = new JPanel();
		imagePanelContainer.setLayout(gridBag);
		imagePanelContainer.setBackground(dark_gray);
		
		// Set up image panel constraints
		c.insets = new Insets(44, 44, 44, 44);
		imagePanelConstraints = (GridBagConstraints) c.clone();
		
		// Set up blank image panel
		setupImagePanel(false);
		imagePanelContainer.add(imagePanel, c);
		
		// Set scroll pane configurations
		imageScroller = new JScrollPane(imagePanelContainer, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		imageScroller.getVerticalScrollBar().setBackground(light_gray);
		imageScroller.setBorder(BorderFactory.createEmptyBorder());
		imageScroller.getVerticalScrollBar().setUnitIncrement(20);

		
		// Set constraints and add options panel
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(0, 0, 0, 0);
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
		this.add(imageScroller, c);
		this.revalidate();

        directoryLabel = new JLabel("Select Scene! Directory: ");

	}
	
	/**
	 * populates the image panel with images from allThumbs
	 * @param revalidate whether to revalidate the scene once finished
	 * 
	 * @author Fernando Palacios
	 * @author Timothy Couch
	 */
	public void setupImagePanel(boolean revalidate)
	{		
		// Create image panel with new images
		imagePanel = new JPanel();
		imagePanel.setLayout(new GridBagLayout());
		imagePanel.setBackground(dark_gray);
		ShowImages(imagePanel, allThumbs);
		
		// add to outer panel that houses the image panel for layout and whitespace
		imagePanelContainer.removeAll();
		imagePanelContainer.add(imagePanel, imagePanelConstraints);

		if (revalidate)
			this.revalidate();
	}
    
	/**
	 * GoToDirectoryScene() - changes scene to directory
	 *
	 * @author Fernando Palacios
     */
	public void GoToDirectoryScene()
	{
		//restart program when returning to directory to clear the scene
		SceneHandler.singleton.restartProgram();
		SceneHandler.singleton.SwitchToScene(SceneType.DIRECTORY);
	}
	
	/**
	 * GoToArrangeScene()() - changes scene to arrange
	 *
	 * @author Fernando Palacios
     */
	public void GoToArrangeScene()
	{
		System.out.println("Thumbnails in Timeline:");
		for (Thumbnail t : SceneHandler.singleton.getTimeline().thumbnailsList.getThumbnails())
			System.out.println(t.getImagePath());
		SceneHandler.singleton.SwitchToScene(SceneType.ARRANGE);
	}
	
    /**
     * ShowImages() - creates thumbnail icons to display in scrollpane
     * 
     * @author Fernando Palacios
	 * @author Timothy Couch
     */
	private void ShowImages(JPanel panel, ThumbnailsList list)
	{
		int i = 0; //counter for buttons
		int gridxCounter = 0; //layout counter x
		int gridyCounter = 0; // layout counter y
		thumbButtons = new JButton[list.getSize()];
		GridBagConstraints c = new GridBagConstraints();

		for(i = 0; i < thumbButtons.length; i++) {
			
			if (gridxCounter > 2) {
				gridxCounter = 0;
				gridyCounter++;
			}
			
			//set constraints
			c.gridx = gridxCounter;
			c.gridy = gridyCounter;
			c.fill = GridBagConstraints.BOTH;
			c.insets = new Insets(40, 40, 40, 40);
			
			gridxCounter++;
			
			Thumbnail buttonThumb = list.getThumbnail(i);
			
			thumbButtons[i] = new JButton(new ImageIcon(buttonThumb.getImageThumb()));
			JButton keeper = thumbButtons [i];
			thumbButtons[i].setPreferredSize(new Dimension(320, 200));
			thumbButtons[i].setRolloverEnabled(true);
			thumbButtons[i].setRolloverIcon(new ImageIcon(ImageHover(buttonThumb.getImageThumb())));
			thumbButtons[i].setPressedIcon(new ImageIcon(ImageHover(buttonThumb.getImageThumb())));
			thumbButtons[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			thumbButtons[i].setBorder(BorderFactory.createEmptyBorder());
			thumbButtons[i].setFocusable(false);
			thumbButtons[i].setContentAreaFilled(false);
			thumbButtons[i].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					//add button to or remove button from timeline
					Timeline timeline = SceneHandler.singleton.getTimeline();

					int slideIndex = timeline.thumbnailsList.indexOf(buttonThumb);
					//if thumbnail is in the timeline, remove it
					if (slideIndex >= 0)
					{
						timeline.removeSlide(slideIndex);
						keeper.setBorder(BorderFactory.createEmptyBorder());
						keeper.setIcon(new ImageIcon(buttonThumb.getImageThumb()));
					}
					else//thumbnail not on timeline, add it
					{
						timeline.addSlide(buttonThumb);
						keeper.setBorder(new LineBorder(aqua, 3));
						keeper.setIcon(new ImageIcon(ImageHover(buttonThumb.getImageThumb())));
					}
				}
				});
			panel.add(thumbButtons[i], c);
		}
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

        directoryLabel.setText(directoryLabel.getText() + SceneHandler.singleton.getDirectory());
        
		//set up thumbnail list
		allThumbs = new ThumbnailsList();
		addImagesInDirectory(new File(SceneHandler.singleton.getDirectory()));
		setupImagePanel(true);
    }
    
    /**
     * Adds all images that are in the supplied directory to allThumbs recursively (searches subfolders)
     * @param currDir the directory to add images from and below
	 * 
	 * @author Timothy Couch
     */
    private void addImagesInDirectory(File currDir) {
    	//Scraping directory credit to RoflCopterException at https://stackoverflow.com/questions/5694385/getting-the-filenames-of-all-files-in-a-folder
    	File[] files = currDir.listFiles();

    	for (File f : files) {
			//if it's a file
    		if (f.isFile()) {
				//if image, add it to the list of thumbnails
				if (imageFileFilter.accept(f))
				{
					allThumbs.addThumbnail(new Thumbnail(f.getAbsolutePath()));
				}
			}
			//if it's a directory, check in it
			else if (f.isDirectory())
				addImagesInDirectory(f);
    	}
	}
    
    /**
     * SelectAll() - selects all images so they are in timeline
     * @param list - the thumbnail list that holds all the image paths
	 * 
	 * @author Fernando Palacios
     */
    private void SelectAll(ThumbnailsList list) {
    	
    	Timeline timeline = SceneHandler.singleton.getTimeline();
    	
    	for(int i = 0; i < thumbButtons.length; i++) {
    		
    		Thumbnail buttonThumb = list.getThumbnail(i);

			int slideIndex = timeline.thumbnailsList.indexOf(buttonThumb);
			
			if (slideIndex < 0)
			{
				timeline.addSlide(buttonThumb);
				thumbButtons[i].setBorder(new LineBorder(aqua, 3));
				thumbButtons[i].setIcon(new ImageIcon(ImageHover(buttonThumb.getImageThumb())));
			}
		}
    }
    
    /**
     * DeselectAll() - deselects all images so they are not in timeline
     * @param list - the thumbnail list that holds all the image paths
	 * 
	 * @author Fernando Palacios
     */
    private void DeselectAll(ThumbnailsList list) {
    	
    	Timeline timeline = SceneHandler.singleton.getTimeline();
    	
    	for(int i = 0; i < thumbButtons.length; i++) {
    		
    		Thumbnail buttonThumb = list.getThumbnail(i);

			int slideIndex = timeline.thumbnailsList.indexOf(buttonThumb);
			
			if (slideIndex >= 0)
			{
				timeline.removeSlide(slideIndex);
				thumbButtons[i].setBorder(BorderFactory.createEmptyBorder());
				thumbButtons[i].setIcon(new ImageIcon(buttonThumb.getImageThumb()));
			}
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
}
