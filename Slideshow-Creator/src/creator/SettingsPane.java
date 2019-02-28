package creator;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;

import core.*;

public class SettingsPane extends FloatingPane
{
	/** Audio file */
	private File audioFile;
	
	/** Create settings panel */
	private JPanel settingsPanel;
	
	/** Create confirmation panel */
	private JPanel confirmationPanel;
	
	/** Create settings label */
	private JLabel settingsLabel;
	
	/** Create type label */
	private JLabel typeLabel;
	
	/** Create audio label */
	private JLabel audioLabel;
	
	/** Create transitions length label */
	private JLabel audioLoopLabel;
	
	/** Create audio label */
	private JLabel slideshowLoopLabel;
	
	/** Create transitions length label */
	private JLabel lengthLabel;
	
	/** Create slideshow duration label */
	private JLabel durationLabel;
	
	/** Create audio text field */
	private JTextField audioText;
	
	/** Create duration text field */
	private JTextField durationText;
	
	/** Create length dropdown */
	private JComboBox lengthDropDown;
	
	/** Create duration dropdown */
	private JCheckBox audioLoopCheck;
	
	/** Create duration dropdown */
	private JCheckBox slideshowLoopCheck;
	
	/** Create auto button */
	private JToggleButton autoButton;
	
	/** Create manual button */
	private JToggleButton manualButton;
	
	/** Create audio button */
	private JButton audioButton;
	
	/** Create ok button */
	private JButton saveButton;
	
	/** Create cancel button */
	private JButton cancelButton;
	
	/** Create button group fr manual and auto */
	private ButtonGroup typeGroup;
	
	/** Settings header custom button image */
	private ImageIcon settingsHeader;
	
	/** Auto custom button image */
	private ImageIcon auto;
	
	/** Highlighted auto custom button image */
	private ImageIcon highlightedAuto;
	
	/** Manual custom button image */
	private ImageIcon manual;
	
	/** Highlighted manual custom button image */
	private ImageIcon highlightedManual;
	
	/** Auto custom button image */
	private ImageIcon save;
	
	/** Highlighted auto custom button image */
	private ImageIcon highlightedSave;
	
	/** Manual custom button image */
	private ImageIcon cancel;
	
	/** Highlighted manual custom button image */
	private ImageIcon highlightedCancel;
	
	/** Audio custom button image */
	private ImageIcon audio;
	
	/** Highlighted audio custom button image */
	private ImageIcon highlightedAudio;
	
	/** Checkbox custom button image */
	private ImageIcon checkbox;
	
	/** Selected checkbox custom button image */
	private ImageIcon selectedCheckbox;
	
	/** Highlighted checkbox custom button image */
	private ImageIcon highlightedCheckbox;
	
	/** Highlighted selected checkbox custom button image */
	private ImageIcon highlightedSelectedCheckbox;
	
	/** Create custom light gray color */
	private Color light_gray = new Color(30, 30, 30);
	
	/** Create custom aqua color */
	private Color aqua = new Color(132, 200, 202);
	
	/** Create custom white color */
	private Color white = new Color(255, 255, 255);
	
	/** Create common font for application usage */
	private Font commonFont = new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 12);
	
	public SettingsPane(JFrame parent, String title, Coord2 position, Dimension size)
	{
		//Call the parent constructor
		super(parent, title, position, size);
		
		// Create GridBagLayout object and constraints
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		// Set image locations
		settingsHeader = new ImageIcon(getClass().getResource("Images/settingsHeader.png"));
		save = new ImageIcon(getClass().getResource("Images/saveButton.png"));
		cancel = new ImageIcon(getClass().getResource("Images/cancelButton.png"));
		auto = new ImageIcon(getClass().getResource("Images/autoButton.png"));
		manual = new ImageIcon(getClass().getResource("Images/manualButton.png"));
		audio = new ImageIcon(getClass().getResource("Images/audioButton.png"));
		highlightedSave = new ImageIcon(getClass().getResource("Images/highlightedSaveButton.png"));
		highlightedCancel = new ImageIcon(getClass().getResource("Images/highlightedCancelButton.png"));
		highlightedAuto = new ImageIcon(getClass().getResource("Images/highlightedAutoButton.png"));
		highlightedManual = new ImageIcon(getClass().getResource("Images/highlightedManualButton.png"));
		highlightedAudio = new ImageIcon(getClass().getResource("Images/highlightedAudioButton.png"));
		checkbox = new ImageIcon(getClass().getResource("Images/checkbox.png"));
		selectedCheckbox = new ImageIcon(getClass().getResource("Images/selectedCheckbox.png"));
		highlightedCheckbox = new ImageIcon(getClass().getResource("Images/highlightedCheckbox.png"));
		highlightedSelectedCheckbox = new ImageIcon(getClass().getResource("Images/highlightedSelectedCheckbox.png"));
		
		//Construct original panel container and configurations
		JPanel settingsGui = new JPanel();
		settingsGui.setLayout(gridBag);
		settingsGui.setBackground(light_gray);
		
		// Create save button
		saveButton = new JButton(save);
		saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		saveButton.setBorder(BorderFactory.createEmptyBorder());
		saveButton.setContentAreaFilled(false);
		saveButton.setFocusable(false);
		saveButton.setRolloverIcon(highlightedSave);
		saveButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
		    }
		});
		
		// Create cancel button
		cancelButton = new JButton(cancel);
		cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cancelButton.setBorder(BorderFactory.createEmptyBorder());
		cancelButton.setContentAreaFilled(false);
		cancelButton.setFocusable(false);
		cancelButton.setRolloverIcon(highlightedCancel);
		cancelButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
		    }
		});
		
		// Create auto button
		autoButton = new JToggleButton(auto, true);
		autoButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		autoButton.setBorder(BorderFactory.createEmptyBorder());
		autoButton.setContentAreaFilled(false);
		autoButton.setFocusable(false);
		autoButton.setRolloverIcon(highlightedAuto);
		autoButton.setSelectedIcon(highlightedAuto);
		autoButton.setEnabled(true);
		autoButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
		    }
		});
		
		// Create manual button
		manualButton = new JToggleButton(manual);
		manualButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		manualButton.setBorder(BorderFactory.createEmptyBorder());
		manualButton.setContentAreaFilled(false);
		manualButton.setFocusable(false);
		manualButton.setRolloverIcon(highlightedManual);
		manualButton.setSelectedIcon(highlightedManual);
		manualButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
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
		    	SelectAudio(audioText);
		    }
		});
		
		// Add auto and manual buttons to type group
		ButtonGroup typeGroup = new ButtonGroup();
		typeGroup.add(autoButton);
		typeGroup.add(manualButton);
		
		// Configure type label
		settingsLabel = new JLabel(settingsHeader);
		
		// Configure type label
		typeLabel = new JLabel("Type");
		typeLabel.setFont(commonFont);
		typeLabel.setForeground(white);
		
		// Configure audio label
		audioLabel = new JLabel("Audio");
		audioLabel.setFont(commonFont);
		audioLabel.setForeground(white);
		
		// Configure length label
		lengthLabel = new JLabel("Transitions Length");
		lengthLabel.setFont(commonFont);
		lengthLabel.setForeground(white);
		
		// Configure duration label
		durationLabel = new JLabel("Slide Duration");
		durationLabel.setFont(commonFont);
		durationLabel.setForeground(white);
		
		// Configure audio loop label
		audioLoopLabel = new JLabel("Loop Audio");
		audioLoopLabel.setFont(commonFont);
		audioLoopLabel.setForeground(white);
		
		// Configure audio loop label
		slideshowLoopLabel = new JLabel("Loop Slideshow");
		slideshowLoopLabel.setFont(commonFont);
		slideshowLoopLabel.setForeground(white);
		
		// Create text field for audio
		audioText = new JTextField(13);
		audioText.setBackground(light_gray);
		audioText.setForeground(white);
        audioText.setEditable(false);
        
		// Create text field for duration
		durationText = new JTextField(13);
		durationText.setBackground(light_gray);
		durationText.setForeground(white);
        
        // Create length drop down
        String[] lengths = { "Slow", "Medium", "Fast" };
        lengthDropDown = new JComboBox(lengths);
        
        // Create audio loop check box
        audioLoopCheck = new JCheckBox(checkbox);
        audioLoopCheck.setSelectedIcon(selectedCheckbox);
        audioLoopCheck.setRolloverIcon(highlightedCheckbox);
        audioLoopCheck.setPressedIcon(highlightedCheckbox);
        audioLoopCheck.setRolloverSelectedIcon(highlightedSelectedCheckbox);
        audioLoopCheck.setBackground(light_gray);
        
        // Create slideshow loop checkbox
        slideshowLoopCheck = new JCheckBox(checkbox);
        slideshowLoopCheck.setSelectedIcon(selectedCheckbox);
        slideshowLoopCheck.setRolloverIcon(highlightedCheckbox);
        slideshowLoopCheck.setPressedIcon(highlightedCheckbox);
        slideshowLoopCheck.setRolloverSelectedIcon(highlightedSelectedCheckbox);
        slideshowLoopCheck.setBackground(light_gray);
        
		// Set settings panel configurations
		settingsPanel = new JPanel();
		settingsPanel.setLayout(gridBag);
		settingsPanel.setBackground(light_gray);
		
		// Set constraints and add header label
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		c.insets = new Insets(0, 0, 10, 0);
		settingsPanel.add(settingsLabel, c);
		
		// Set constraints and add type label
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(30, 0, 10, 15);
		settingsPanel.add(typeLabel, c);
		
		// Set constraints and add audio label
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(10, 0, 10, 15);
		settingsPanel.add(audioLabel, c);
		
		// Set constraints and add length label
		c.gridx = 0;
		c.gridy = 4;
		settingsPanel.add(durationLabel, c);
		
		// Set constraints and add duration label
		c.gridx = 0;
		c.gridy = 5;
		c.insets = new Insets(10, 0, 30, 15);
		settingsPanel.add(lengthLabel, c);
		
		// Set constraints and add duration label
		c.gridx = 0;
		c.gridy = 7;
		c.insets = new Insets(20, 0, 5, 0);
		settingsPanel.add(audioLoopLabel, c);
		
		// Set constraints and add duration label
		c.gridx = 0;
		c.gridy = 8;
		c.insets = new Insets(5, 0, 10, 0);
		settingsPanel.add(slideshowLoopLabel, c);
		
		// Set constraints and add audio text display
		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(10, 7, 10, 0);
		settingsPanel.add(audioText, c);
		
		// Set constraints and add duration text
		c.gridx = 1;
		c.gridy = 4;
		c.insets = new Insets(7, 7, 7, 0);
		settingsPanel.add(durationText, c);
		
		// Set constraints and add auto button
		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(30, 7, 10, 0);
		settingsPanel.add(autoButton, c);
		
		// Set constraints and add manual button
		c.gridx = 2;
		c.gridy = 2;
		c.insets = new Insets(30, 0, 10, 0);
		settingsPanel.add(manualButton, c);
		
		// Set constraints and add audio button
		c.gridx = 2;
		c.gridy = 3;
		c.insets = new Insets(5, 5, 5, 5);
		settingsPanel.add(audioButton, c);
		
		// Set constraints and add length drop down
		c.gridx = 1;
		c.gridy = 5;
		c.insets = new Insets(10, 7, 30, 0);
		settingsPanel.add(lengthDropDown, c);
		
		// Set constraints and add audio loop check
		c.gridx = 1;
		c.gridy = 7;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(20, 0, 5, 0);
		settingsPanel.add(audioLoopCheck, c);
		
		// Set constraints and add slideshow loop checkbox
		c.gridx = 1;
		c.gridy = 8;
		c.insets = new Insets(5, 0, 10, 0);
		settingsPanel.add(slideshowLoopCheck, c);
		
		// Set constraints and separator
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 3;
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(0, 0, 0, 0);
		settingsPanel.add(new JSeparator(), c);
		
		// Set constraints and add separator
		c.gridx = 0;
		c.gridy = 6;
		settingsPanel.add(new JSeparator(), c);
		
		// Set options panel configurations
		confirmationPanel = new JPanel();
		confirmationPanel.setLayout(gridBag);
		confirmationPanel.setBackground(light_gray);
		
		// Set constraints and add back button
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.insets = new Insets(10, 10, 10, 10);
		confirmationPanel.add(saveButton, c);
		
		// Set constraints and add ok button
		c.gridx = 1;
		c.gridy = 0;
		confirmationPanel.add(cancelButton, c);
		
		// Set constraints and add settings panel
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		settingsGui.add(settingsPanel);
		
		// Set constraints and add confirmation panel
		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 0;
		c.insets = new Insets(0,0,10,0);
		settingsGui.add(confirmationPanel, c);
		
		// Set settings into floating pane
		getContentPane().add(settingsGui, BorderLayout.CENTER);
	}
	
	/**
	 * SelectAudio() - brings up dialogue box to select audio
	 * 
	 * @author Fernando Palacios
	 */
	public void SelectAudio(JTextField display) {
		
    	JFileChooser chooser = new JFileChooser();
    	chooser.setCurrentDirectory(new java.io.File(".")); // start at application current directory
    	
        //chooser.addChoosableFileFilter(new AudioFilter());
        //chooser.setAcceptAllFileFilterUsed(false);
    	int returnVal = chooser.showDialog(SettingsPane.this, "Open");
    	if(returnVal == JFileChooser.APPROVE_OPTION) {
    	    audioFile = chooser.getSelectedFile();
        	display.setText(audioFile.getName());
    	}
	}

	
//	class AudioFilter extends FileFilter {
//		 
//	    //Accept all directories and all gif, jpg, tiff, or png files.
//	    public boolean accept(File f) {
//	        if (f.isDirectory()) {
//	            return true;
//	        }
//	 
//	        String extension = Utils.getExtension(f);
//	        if (extension != null) {
//	            if (extension.equals(Utils.tiff) ||
//	                extension.equals(Utils.tif) ||
//	                extension.equals(Utils.gif) ||
//	                extension.equals(Utils.jpeg) ||
//	                extension.equals(Utils.jpg) ||
//	                extension.equals(Utils.png)) {
//	                    return true;
//	            } else {
//	                return false;
//	            }
//	        }
//	 
//	        return false;
//	    }
//
//		@Override
//		public String getDescription() {
//			return "Just Audio Files";
//		}
}
