package creator;

import core.*;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class DirectoryExplorer extends Scene {
	
	/** Directory path */
	private File directoryPath;
	
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
	
	/** Highlighted select existing file custom button image */
	private ImageIcon highlightedSelectExisting;
	
	/** Create custom color */
	private Color light_gray = new Color(60, 60, 60);

	public DirectoryExplorer() {
		
		// Create images
		selectDirectory = new ImageIcon(getClass().getResource("Images/selectDirectoryButton.png"));
		highlightedSelectDirectory = new ImageIcon(getClass().getResource("Images/highlightedSelectDirectoryButton.png"));
		selectExisting = new ImageIcon(getClass().getResource("Images/selectExistingButton.png"));
		highlightedSelectExisting = new ImageIcon(getClass().getResource("Images/highlightedSelectExistingButton.png"));
		
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
		
		// Create GridBagLayout object and constraints
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		// Set panel configurations
		this.setLayout(gridBag);
		this.setBackground(light_gray);
		
		// Set constraints
		c.gridx = 0;
		c.gridy = 0;
		
		// Create select directory button
		selectDirectoryButton = new JButton(selectDirectory);
		selectDirectoryButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		selectDirectoryButton.setBorder(BorderFactory.createEmptyBorder());
		selectDirectoryButton.setContentAreaFilled(false);
		selectDirectoryButton.setFocusable(false);
		selectDirectoryButton.setRolloverIcon(highlightedSelectDirectory);
		selectDirectoryButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	SelectDirectory();
		    }
		});
		
		this.add(selectDirectoryButton, c);
		
		// Set constraints
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(10,0,0,0);
		
		// Create existing directory button
		selectExistingButton = new JButton(selectExisting);
		selectExistingButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		selectExistingButton.setBorder(BorderFactory.createEmptyBorder());
		selectExistingButton.setContentAreaFilled(false);
		selectExistingButton.setFocusable(false);
		selectExistingButton.setRolloverIcon(highlightedSelectExisting);
		
		this.add(selectExistingButton, c);


	}
	
	public void SelectDirectory() {
		
    	JFileChooser chooser = new JFileChooser();
    	chooser.setCurrentDirectory(new java.io.File(".")); // start at application current directory
    	chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    	int returnVal = chooser.showSaveDialog(DirectoryExplorer.this);
    	if(returnVal == JFileChooser.APPROVE_OPTION) {
    	    directoryPath = chooser.getSelectedFile();
    	    GoToSelectScene();
    	}
    	
	}
	
	public void GoToSelectScene() {
		
	}
	
}
