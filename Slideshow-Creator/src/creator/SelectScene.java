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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

import core.*;

public class SelectScene extends Scene
{
	/** Create custom color */
	private JPanel optionsPanel;
	
	/** Create custom color */
	private JPanel imagePanel;
	
	/** Back button */
	private JButton backButton;
	
	/** Arrange button */
	private JButton arrangeButton;
	
	/** Select all button */
	private JButton selectAllButton;
	
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
	private Color light_gray = new Color(60, 60, 60);
	
	/** Create custom image_gray color */
	private Color image_gray = new Color(30, 30, 30);
	
	private ThumbnailsList allThumbs;

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
		selectAllButton.setBorder(BorderFactory.createEmptyBorder());
		selectAllButton.setContentAreaFilled(false);
		selectAllButton.setFocusable(false);
		selectAllButton.setRolloverIcon(highlightedSelectAll);
		selectAllButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
		    }
		});
		
		// Create deselect all button
		deselectAllButton = new JButton(deselectAll);
		deselectAllButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		deselectAllButton.setBorder(BorderFactory.createEmptyBorder());
		deselectAllButton.setContentAreaFilled(false);
		deselectAllButton.setFocusable(false);
		deselectAllButton.setRolloverIcon(highlightedDeselectAll);
		deselectAllButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
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
		
		// Set image panel configurations
		imagePanel = new JPanel();
		imagePanel.setLayout(gridBag);
		imagePanel.setBackground(image_gray);
		
		// Set constraints and add options panel
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
		this.revalidate();

        directoryLabel = new JLabel("Select Scene! Directory: ");

    }
    
	/**
	 * GoToDirectoryScene() - changes scene to directory
	 *
	 * @author Fernando Palacios
     */
	public void GoToDirectoryScene()
	{
		SceneHandler.singleton.SwitchToScene(SceneType.DIRECTORY);
	}
	
	/**
	 * GoToArrangeScene()() - changes scene to arrange
	 *
	 * @author Fernando Palacios
     */
	public void GoToArrangeScene()
	{
		SceneHandler.singleton.SwitchToScene(SceneType.ARRANGE);
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
        directoryLabel.setText(directoryLabel.getText() + SceneHandler.singleton.getDirectory());
        
        //set up thumbnail list
        addImagesInDirectory(new File(SceneHandler.singleton.getDirectory()));
    }
    
    /**
     * Adds all images that are in the supplied directory to allThumbs
     * @param currDir the directory to add images from and below
	 * 
	 * @author Timothy Couch
     */
    private void addImagesInDirectory(File currDir) {
    	//Scraping directory credit to RoflCopterException at https://stackoverflow.com/questions/5694385/getting-the-filenames-of-all-files-in-a-folder
    	File[] files = currDir.listFiles();

    	for (File f : files) {
    		if (f.isFile()) {
    			System.out.println("File " + f.getName());
    			allThumbs.addThumbnail(new Thumbnail(f.getAbsolutePath()));
			}
    	}
    }
}
