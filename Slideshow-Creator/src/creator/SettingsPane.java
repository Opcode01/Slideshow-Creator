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
import java.text.NumberFormat;

import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.NumberFormatter;

import core.*;

public class SettingsPane extends FloatingPane
{
	
	/** Create settings panel */
	private JPanel settingsPanel;
	
	/** Create confirmation panel */
	private JPanel confirmationPanel;
	
	/** Create settings label */
	private JLabel settingsLabel;
	
	/** Create type label */
	private JLabel typeLabel;
	
	/** Create audio loop label */
	private JLabel audioLoopLabel;
	
	/** Create slideshow loop label */
	private JLabel slideshowLoopLabel;
	
	/** Create slideshow duration label */
	private JLabel durationLabel;
	
	/** Create duration text field */
	private JTextField durationText;
	
	/** Create duration dropdown */
	private JCheckBox audioLoopCheck;
	
	/** Create duration dropdown */
	private JCheckBox slideshowLoopCheck;
	
	/** Create auto button */
	private JToggleButton autoButton;
	
	/** Create manual button */
	private JToggleButton manualButton;
	
	/** Create ok button */
	private JButton saveButton;
	
	/** Create cancel button */
	private JButton cancelButton;
	
	/** Create button group for manual and auto */
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
	
	/** Checkbox custom button image */
	private ImageIcon checkbox;
	
	/** Selected checkbox custom button image */
	private ImageIcon selectedCheckbox;
	
	/** Highlighted checkbox custom button image */
	private ImageIcon highlightedCheckbox;
	
	/** Highlighted selected checkbox custom button image */
	private ImageIcon highlightedSelectedCheckbox;
	
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
		settingsHeader = new ImageIcon(getClass().getResource("/creator/Images/settingsHeader.png"));
		save = new ImageIcon(getClass().getResource("/creator/Images/saveButton.png"));
		cancel = new ImageIcon(getClass().getResource("/creator/Images/cancelButton.png"));
		auto = new ImageIcon(getClass().getResource("/creator/Images/autoButton.png"));
		manual = new ImageIcon(getClass().getResource("/creator/Images/manualButton.png"));
		highlightedSave = new ImageIcon(getClass().getResource("/creator/Images/highlightedSaveButton.png"));
		highlightedCancel = new ImageIcon(getClass().getResource("/creator/Images/highlightedCancelButton.png"));
		highlightedAuto = new ImageIcon(getClass().getResource("/creator/Images/highlightedAutoButton.png"));
		highlightedManual = new ImageIcon(getClass().getResource("/creator/Images/highlightedManualButton.png"));
		checkbox = new ImageIcon(getClass().getResource("/creator/Images/checkbox.png"));
		selectedCheckbox = new ImageIcon(getClass().getResource("/creator/Images/selectedCheckbox.png"));
		highlightedCheckbox = new ImageIcon(getClass().getResource("/creator/Images/highlightedCheckbox.png"));
		highlightedSelectedCheckbox = new ImageIcon(getClass().getResource("/creator/Images/highlightedSelectedCheckbox.png"));
		
		//Construct original panel container and configurations
		JPanel settingsGui = new JPanel();
		settingsGui.setLayout(gridBag);
		settingsGui.setBackground(SliderColor.medium_gray);
		
		// Create save button
		saveButton = new JButton(save);
		saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		saveButton.setBorder(BorderFactory.createEmptyBorder());
		saveButton.setContentAreaFilled(false);
		saveButton.setFocusable(false);
		saveButton.setRolloverIcon(highlightedSave);
		saveButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	UpdateProjectSettings();
		    	
		    	Timeline t = SceneHandler.singleton.getTimeline();
		    	Settings s = t.timelineSettings;
		    	float currentPlayTime = t.audioPlayer.getPlayingLength();
		    	
		    	int slideShowDuration = s.slideDuration * t.thumbnailsList.getSize();
		    	System.out.println("LENGTH OF AUDIO LIST IS " + t.audioPlayer.getPlayingLength() + "SD IS " + slideShowDuration);
		    	if (slideShowDuration < t.audioPlayer.getPlayingLength()) {
		    		
		    		// Open warning pane in the center of our workspace
		    		JFrame parent = SceneHandler.singleton.getMainFrame();
		    		Coord2 point = new Coord2(
		    			parent.getX() + parent.getSize().width/2,
		    			parent.getY() + parent.getSize().height/2
		    		);
		    		WarningPane p = new WarningPane(parent, "Warning - Duration shorter than audio", point, new Dimension(400, 190), "The slide duration is less than the currently supported audio", "Audio tracks were removed or clipped to the appropriate size.");
		    		parent.setEnabled(false);
		    		
		    		for(int i = (t.audioPlayer.getSize() - 1); i >= 0; i--) {
		    			currentPlayTime -= t.audioPlayer.getAudio(i).getAudioLength();
				    	System.out.println("NUMBER" + i + "LENGTH OF AUDIO LIST IS " + t.audioPlayer.getPlayingLength() + "SD IS " + slideShowDuration);
				    	System.out.println("NUMBER" + i + "LENGTH OF AUDIO LIST CURRENTTIME IS " + currentPlayTime + "SD IS " + slideShowDuration);

		    			if(currentPlayTime < slideShowDuration) {
		    		    	ArrangeScene scene = (ArrangeScene)SceneHandler.singleton.GetCurrentScene();
		    		    	scene.SetupTimelinePanel(true);
		    		    	scene.repaint();
		    		    	scene.revalidate();
		    		    	ClosePane();
		    		    	return;
		    			}
		    			t.audioPlayer.removeAudioAtIndex(i);
			    		System.out.println("REMOVED AUDIO TRACK " + i);
		    		}
		    	}
		    	currentPlayTime = 0;
		    	ArrangeScene scene = (ArrangeScene)SceneHandler.singleton.GetCurrentScene();
		    	scene.SetupTimelinePanel(true);
		    	scene.repaint();
		    	scene.revalidate();
		    	ClosePane();
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
		    	ClosePane();
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
		
		// Create manual button
		manualButton = new JToggleButton(manual);
		manualButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		manualButton.setBorder(BorderFactory.createEmptyBorder());
		manualButton.setContentAreaFilled(false);
		manualButton.setFocusable(false);
		manualButton.setRolloverIcon(highlightedManual);
		manualButton.setSelectedIcon(highlightedManual);
		
		// Add auto and manual buttons to type group
		ButtonGroup typeGroup = new ButtonGroup();
		typeGroup.add(autoButton);
		typeGroup.add(manualButton);
		
		// Configure type label
		settingsLabel = new JLabel(settingsHeader);
		
		// Configure type label
		typeLabel = new JLabel("Type");
		typeLabel.setFont(commonFont);
		typeLabel.setForeground(SliderColor.white);
		
		// Configure duration label
		durationLabel = new JLabel("Slide Duration");
		durationLabel.setFont(commonFont);
		durationLabel.setForeground(SliderColor.white);
		
		// Configure audio loop label
		audioLoopLabel = new JLabel("Loop Audio");
		audioLoopLabel.setFont(commonFont);
		audioLoopLabel.setForeground(SliderColor.white);
		
		// Configure audio loop label
		slideshowLoopLabel = new JLabel("Loop Slideshow");
		slideshowLoopLabel.setFont(commonFont);
		slideshowLoopLabel.setForeground(SliderColor.white);
        
        // Create text field for duration
		durationText = new JTextField(13);
		durationText.setBackground(SliderColor.medium_gray);
		durationText.setBorder(BorderFactory.createLineBorder(SliderColor.white, 1));
		durationText.setForeground(SliderColor.white);
		durationText.setPreferredSize(new Dimension(13, 23));
        
        // Create audio loop check box
        audioLoopCheck = new JCheckBox(checkbox);
        audioLoopCheck.setSelectedIcon(selectedCheckbox);
        audioLoopCheck.setRolloverIcon(highlightedCheckbox);
        audioLoopCheck.setPressedIcon(highlightedCheckbox);
        audioLoopCheck.setRolloverSelectedIcon(highlightedSelectedCheckbox);
        audioLoopCheck.setBackground(SliderColor.medium_gray);
        
        // Create slideshow loop checkbox
        slideshowLoopCheck = new JCheckBox(checkbox);
        slideshowLoopCheck.setSelectedIcon(selectedCheckbox);
        slideshowLoopCheck.setRolloverIcon(highlightedCheckbox);
        slideshowLoopCheck.setPressedIcon(highlightedCheckbox);
        slideshowLoopCheck.setRolloverSelectedIcon(highlightedSelectedCheckbox);
        slideshowLoopCheck.setBackground(SliderColor.medium_gray);
        
		// Set settings panel configurations
		settingsPanel = new JPanel();
		settingsPanel.setLayout(gridBag);
		settingsPanel.setBackground(SliderColor.medium_gray);
		
		// Update Settings items with current values
		LoadSettingsFromTimeline();
		
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
		
		// Set constraints and add length label
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(10, 0, 30, 15);
		settingsPanel.add(durationLabel, c);
		
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
		
		// Set constraints and add duration text
		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(10, 7, 30, 0);
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
		confirmationPanel.setBackground(SliderColor.medium_gray);
		
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
	
	public void UpdateProjectSettings() {
		Timeline t = SceneHandler.singleton.getTimeline();
		
		boolean loopSlides = slideshowLoopCheck.isSelected();
		boolean loopAudio =  audioLoopCheck.isSelected();
		boolean isManual = false;			//Default is false - overridden below
		if(manualButton.isSelected()) {
			isManual = true;
		}
		
		int slideTime = 30;
		try {
			slideTime = Integer.parseInt(durationText.getText());
		}catch(NumberFormatException e) {
			System.out.println("Slide duration not a valid integer - using default value of 1.");
		}
		
		Settings s = new Settings(loopSlides, loopAudio, isManual, slideTime);
		t.UpdateProjectSettings(s);
	}
	
	private void LoadSettingsFromTimeline() {
		Timeline t = SceneHandler.singleton.getTimeline();
		Settings s = t.timelineSettings;
		
		//Defaults are all off and auto selected - we need to set these accordingly
		if(s.isLoopingSlides) {
			slideshowLoopCheck.setSelected(true);
		}
		if(s.isLoopingAudio) {
			audioLoopCheck.setSelected(true);
		}
		if(s.isManual) {
			autoButton.setSelected(false);
			manualButton.setSelected(true);
		}
		durationText.setText(Integer.toString(s.slideDuration));	
	}
}
