/**
 * DirectoryExplorer.java
 * Scene in which user chooses directory or file to load
 * 
 * Slideshow Creator
 * Timothy Couch, Joseph Hoang, Fernando Palacios, Austin Vickers
 * CS 499 Senior Design with Dr. Rick Coleman
 * 2/10/19
 */
package creator;

import core.*;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class DirectoryExplorer extends Scene {
	
	/** Directory path */
	private File directoryPath;
	
	/** Bg panel */
	private JPanel bgPanel;
	
	/** Bg label */
	private JLabel bgLabel;
	
	/** Header label */
	private JLabel headerLabel;
	
	/** Select directory button */
	private JButton selectDirectoryButton;
	
	/** Select existing slideshow creator file button */
	private JButton selectExistingButton;
	
	/** Select directory custom button image */
	private ImageIcon selectDirectory;
	
	/** Highlighted select directory custom button image */
	private ImageIcon highlightedSelectDirectory;
	
	/** Select existing file custom button image */
	private ImageIcon selectExisting;
	
	/** bg image */
	private ImageIcon bg;
	
	/** bg image */
	private ImageIcon header;
	
	/** Highlighted select existing file custom button image */
	private ImageIcon highlightedSelectExisting;
	
	/** Create custom color */
	private Color dark_gray = new Color(30, 30, 30);

	/**
	 * DirectoryExplorer() - sets up directory explorer with GUI stuff
	 * 
	 * @author Fernando Palacios
	 * @author austinvickers
	 */
	public DirectoryExplorer()
	{
		
		// Create GridBagLayout object and constraints
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		// Create images
		selectDirectory = new ImageIcon(getClass().getResource("Images/selectDirectoryButton.png"));
		highlightedSelectDirectory = new ImageIcon(getClass().getResource("Images/highlightedSelectDirectoryButton.png"));
		selectExisting = new ImageIcon(getClass().getResource("Images/selectExistingButton.png"));
		highlightedSelectExisting = new ImageIcon(getClass().getResource("Images/highlightedSelectExistingButton.png"));
		bg = new ImageIcon(getClass().getResource("Images/bg.jpg"));
		header = new ImageIcon(getClass().getResource("Images/header.png"));
		
		// Change look and feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// Set frame configurations
		this.setLayout(new BorderLayout());
		
		// Create select directory button
		selectDirectoryButton = new JButton(selectDirectory);
		selectDirectoryButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		selectDirectoryButton.setBorder(BorderFactory.createEmptyBorder());
		selectDirectoryButton.setContentAreaFilled(false);
		selectDirectoryButton.setFocusable(false);
		selectDirectoryButton.setRolloverIcon(highlightedSelectDirectory);
		selectDirectoryButton.setPressedIcon(highlightedSelectDirectory);
		selectDirectoryButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	SelectDirectory();
		    }
		});
		
		// Create existing directory button
		selectExistingButton = new JButton(selectExisting);
		selectExistingButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		selectExistingButton.setBorder(BorderFactory.createEmptyBorder());
		selectExistingButton.setContentAreaFilled(false);
		selectExistingButton.setFocusable(false);
		selectExistingButton.setRolloverIcon(highlightedSelectExisting);
		selectExistingButton.setPressedIcon(highlightedSelectExisting);
		selectExistingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectFile();
			}
		});
		
		// Create and add label
		bgLabel = new JLabel(bg);
		this.add(bgLabel);
		
		// Create header label
		headerLabel = new JLabel(header);
		
		// Set panel configurations
		bgPanel = new JPanel();
		bgPanel.setLayout(gridBag);
		bgPanel.setBackground(dark_gray);
		
		// Set constraints and add directory button
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(20, 80, 5, 80);
		bgPanel.add(headerLabel, c);
		
		// Set constraints and add directory button
		c.gridx = 0;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridy = 1;
		c.insets = new Insets(0, 80, 7, 80);
		bgPanel.add(selectDirectoryButton, c);
		
		// Set constraints and add existing button
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(7, 80, 100, 80);
		bgPanel.add(selectExistingButton, c);
		
		
		// Configure label and add bg panel
		bgLabel.setLayout(gridBag);
		bgLabel.add(bgPanel);
		
	}
	
	/**
	 * SelectDirectory() - brings up dialogue box to select directory
	 * 
	 * @author Fernando Palacios
	 */
	public void SelectDirectory() {
		
    	JFileChooser chooser = new JFileChooser();
    	chooser.setCurrentDirectory(new java.io.File(".")); // start at application current directory
    	chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    	int returnVal = chooser.showDialog(DirectoryExplorer.this, "Choose Directory");
    	if(returnVal == JFileChooser.APPROVE_OPTION) {
    	    directoryPath = chooser.getSelectedFile();
    		GoToSelectScene(directoryPath.getAbsolutePath());
    	}
	}
	
	/**
	 * SelectFile - brings up dialogue box to select slideshow file
	 * 
	 * @author Timothy Couch
	 */
	public void SelectFile() {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));//start at this directory
		chooser.setFileFilter(new FileNameExtensionFilter("Slideshow File", "sl"));
		int returnVal = chooser.showDialog(DirectoryExplorer.this, "Choose Slideshow File");
    	if(returnVal == JFileChooser.APPROVE_OPTION) {
    	    File slFile = chooser.getSelectedFile();
    		GoToSelectScene(slFile);
    	}
	}
	
	/**
	 * GoToSelectScene() - sends to select scene with specified path
	 * @param dir is directory
	 * 
	 * @author Timothy Couch
	 */
	public void GoToSelectScene(String dir)
	{
		SceneHandler.singleton.setDirectory(dir);
		SceneHandler.singleton.SwitchToScene(SceneType.SELECTION);
	}
	
	/**
	 * GoToSelectScene - sends to select scene with specified file
	 * @param slFile slideshow file to open
	 * 
	 * @author Timothy Couch
	 */
	public void GoToSelectScene(File slFile)
	{
		SceneHandler.singleton.setDirectory(slFile);
	    SceneHandler.singleton.getTimeline().timelineSettings.PrintAll();
		SceneHandler.singleton.SwitchToScene(SceneType.SELECTION);
	}
	
}
