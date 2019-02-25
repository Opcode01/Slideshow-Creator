package creator;

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

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;

import core.*;

public class SettingsPane extends Scene
{
	/** Audio file */
	private File audioFile;
	
	/** Create settings panel */
	private JPanel settingsPanel;
	
	/** Create confirmation panel */
	private JPanel confirmationPanel;
	
	/** Create type label */
	private JLabel typeLabel;
	
	/** Create audio label */
	private JLabel audioLabel;
	
	/** Create transitions length label */
	private JLabel lengthLabel;
	
	/** Create slideshow duration label */
	private JLabel durationLabel;
	
	/** Create audio text field */
	private JTextField audioText;
	
	/** Create length dropdown */
	private JComboBox lengthDropDown;
	
	/** Create duration dropdown */
	private JComboBox durationDropDown;
	
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
	
	/** Audio custom button image */
	private ImageIcon selectAudio;
	
	/** Highlighted audio custom button image */
	private ImageIcon highlightedSelectAudio;
	
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
	
	/** Highlighted manual custom button image */
	private ImageIcon highlightedAudio;
	
	/** Create custom light gray color */
	private Color light_gray = new Color(30, 30, 30);
	
	/** Create custom aqua color */
	private Color aqua = new Color(132, 200, 202);
	
	/** Create custom white color */
	private Color white = new Color(255, 255, 255);
	
	/** Create common font for appliation usage */
	private Font commonFont = new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 15);
	
	public SettingsPane()
	{
		// Create GridBagLayout object and constraints
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		// Set image locations
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
		
		// Set frame configurations
		this.setLayout(gridBag);
		this.setBackground(light_gray);
		
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
		durationLabel = new JLabel("Slideshow Duration");
		durationLabel.setFont(commonFont);
		durationLabel.setForeground(white);
		
		// Create text field for audio
		audioText = new JTextField(13);
		audioText.setBackground(light_gray);
		audioText.setForeground(white);
        audioText.setEditable(false);
		
		// Set settings panel configurations
		settingsPanel = new JPanel();
		settingsPanel.setLayout(gridBag);
		settingsPanel.setBackground(light_gray);
		settingsPanel.setBorder(BorderFactory.createCompoundBorder(
	            BorderFactory.createTitledBorder(null, "SETTINGS", TitledBorder.CENTER , TitledBorder.DEFAULT_POSITION, commonFont, Color.WHITE),
	            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		
		// Set constraints and add type label
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(5, 0, 5, 5);
		settingsPanel.add(typeLabel, c);
		
		// Set constraints and add audio label
		c.gridx = 0;
		c.gridy = 1;
		settingsPanel.add(audioLabel, c);
		
		// Set constraints and add length label
		c.gridx = 0;
		c.gridy = 2;
		settingsPanel.add(lengthLabel, c);
		
		// Set constraints and add duration label
		c.gridx = 0;
		c.gridy = 3;
		settingsPanel.add(durationLabel, c);
		
		// Set constraints and add audio text display
		c.gridx = 1;
		c.gridy = 1;
		settingsPanel.add(audioText, c);
		
		// Set constraints and add auto button
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(5, 0, 5, 0);
		settingsPanel.add(autoButton, c);
		
		// Set constraints and add manual button
		c.gridx = 2;
		c.gridy = 0;
		c.anchor = GridBagConstraints.WEST;
		settingsPanel.add(manualButton, c);
		
		// Set constraints and add manual button
		c.gridx = 2;
		c.gridy = 1;
		c.insets = new Insets(5, 0, 5, 5);
		settingsPanel.add(audioButton, c);
		
		// Set options panel configurations
		confirmationPanel = new JPanel();
		confirmationPanel.setLayout(gridBag);
		confirmationPanel.setBackground(light_gray);
		
		// Set constraints and add back button
		c.gridx = 0;
		c.gridy = 0;
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
		this.add(settingsPanel);
		
		// Set constraints and add confirmation panel
		c.gridy = 1;
		c.weighty = 0;
		c.insets = new Insets(10,0,10,0);
		this.add(confirmationPanel, c);
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
