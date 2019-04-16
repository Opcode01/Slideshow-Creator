/**
 * FileSelectExplorer.java
 * Scene in which user chooses file to load in viewer
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
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

import core.*;

public class FileSelectExplorer extends Scene {
	
	/** Bg panel */
	private JPanel bgPanel;
	
	/** Bg label */
	private JLabel bgLabel;
	
	/** Header label */
	private JLabel headerLabel;
	
	/** Select existing slideshow creator file button */
	private JButton selectExistingButton;
	
	/** Select existing file custom button image */
	private ImageIcon selectExisting;
	
	/** bg image */
	private ImageIcon bg;
	
	/** bg image */
	private ImageIcon header;
	
	/** Highlighted select existing file custom button image */
	private ImageIcon highlightedSelectExisting;

	/**
	 * DirectoryExplorer() - sets up directory explorer with GUI stuff
	 * 
	 * @author Fernando Palacios
	 * @author austinvickers
	 */
	public FileSelectExplorer() throws Exception
	{
		
		// Create GridBagLayout object and constraints
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		// Create images
		selectExisting = new ImageIcon(getClass().getResource("/viewer/Images/selectSliderFileButton.png"));
		highlightedSelectExisting = new ImageIcon(getClass().getResource("/viewer/Images/highlightedSelectSliderFileButton.png"));
		bg = new ImageIcon(getClass().getResource("/viewer/Images/viewerBg.jpg"));
		header = new ImageIcon(getClass().getResource("/viewer/Images/viewerHeader.png"));
		
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
				try{
				SelectFile();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
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
		bgPanel.setBackground(SliderColor.beige_gray);
		
		// Set constraints and add directory button
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(20, 80, 5, 80);
		bgPanel.add(headerLabel, c);
		
		// Set constraints and add existing button
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.insets = new Insets(7, 80, 100, 80);
		bgPanel.add(selectExistingButton, c);
		
		// Configure label and add bg panel
		bgLabel.setLayout(gridBag);
		bgLabel.add(bgPanel);
		
	}
	
	/**
	 * SelectFile - brings up dialogue box to select slideshow file
	 * 
	 * @author Timothy Couch
	 */
	public void SelectFile() throws Exception
	{
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));//start at this directory
		chooser.setFileFilter(new FileNameExtensionFilter("Slideshow File", "sl"));
		chooser.setAcceptAllFileFilterUsed(false);
		int returnVal = chooser.showDialog(FileSelectExplorer.this, "Choose Slideshow File");
    	if(returnVal == JFileChooser.APPROVE_OPTION) {
    	    File slFile = chooser.getSelectedFile();
    		GoToPlayScene(slFile);
    	}
	}
	
	/**
	 * GoToPlayScene - sends to select scene with specified file
	 * @param slFile slideshow file to open
	 * 
	 * @author Timothy Couch
	 */
	public void GoToPlayScene(File slFile) throws Exception, NullPointerException
	{
		try
		{
			SceneHandler.singleton.setDirectory(slFile);
			if(SceneHandler.singleton.getTimeline() != null)
			{
				SceneHandler.singleton.SwitchToScene(SceneType.PLAY);
			}
			else
			{
				JFrame parent = SceneHandler.singleton.getMainFrame();
		    	Coord2 point = new Coord2(
		    			parent.getX() + parent.getSize().width/2,
		    			parent.getY() + parent.getSize().height/2
		    			);
		    	
				WarningPane p = new WarningPane(
		    			parent,
		    			"Warning - Invalid File Selected",
		    			"Slider file cannot be loaded.",
		    			point, 
		    			new Dimension(400, 190));
			}
		} catch (NullPointerException npe) {
			throw npe;
		} catch (Exception e) {
			throw e;
		}
	}
	
}