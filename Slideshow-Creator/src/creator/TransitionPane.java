/**
 * TransitionPane.java
 * Floating window that allows users to edit transitions
 * 
 * Slideshow Creator
 * Timothy Couch, Joseph Hoang, Fernando Palacios, Austin Vickers
 * CS 499 Senior Design with Dr. Rick Coleman
 * 4/11/19
 */

package creator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.Border;

import core.*;

public class TransitionPane extends FloatingPane {
	
	/** Create settings panel */
	private JPanel transitionPanel;
	
	/** Create confirmation panel */
	private JPanel confirmationPanel;
	
	/** Create transition label */
	private JLabel transitionLabel;
	
	/** Create type label */
	private JLabel typeLabel;

	/** Create transitions length label */
	private JLabel lengthLabel;
	
	/** Create transitions change all label */
	private JLabel changeAllLabel;
	
	/** Create duration dropdown */
	private JCheckBox changeAllCheck;
	
	/** Create cross button */
	private JToggleButton crossButton;
	
	/** Create left button */
	private JToggleButton leftButton;
	
	/** Create right button */
	private JToggleButton slowButton;
	
	/** Create up button */
	private JToggleButton mediumButton;
	
	/** Create down button */
	private JToggleButton fastButton;
	
	/** Create auto button */
	private JToggleButton rightButton;
	
	/** Create manual button */
	private JToggleButton upButton;
	
	/** Create auto button */
	private JToggleButton downButton;
	
	/** Create ok button */
	private JButton saveButton;
	
	/** Create cancel button */
	private JButton cancelButton;
	
	/** Create button group for manual and auto */
	private ButtonGroup typeGroup;
	
	/** Create button group for manual and auto */
	private ButtonGroup lengthGroup;
	
	/** Transition custom header image */
	private ImageIcon transitionHeader;
	
	/** Save custom button image */
	private ImageIcon save;
	
	/** Highlighted save custom button image */
	private ImageIcon highlightedSave;
	
	/** Cancel custom button image */
	private ImageIcon cancel;
	
	/** Highlighted cancel custom button image */
	private ImageIcon highlightedCancel;
	
	/** Cross dissolve custom button image */
	private ImageIcon cross;
	
	/** Up transition custom button image */
	private ImageIcon up;
	
	/** Down transition custom button image */
	private ImageIcon down;
	
	/** Left transition custom button image */
	private ImageIcon left;
	
	/** Right transition custom button image */
	private ImageIcon right;
	
	/** Slow custom button image */
	private ImageIcon slow;
	
	/** Highlighted slow custom button image */
	private ImageIcon highlightedSlow;
	
	/** Medium custom button image */
	private ImageIcon medium;
	
	/** Highlighted medium custom button image */
	private ImageIcon highlightedMedium;
	
	/** Fast custom button image */
	private ImageIcon fast;
	
	/** Highlighted fast custom button image */
	private ImageIcon highlightedFast;
	
	/** Checkbox custom button image */
	private ImageIcon checkbox;
	
	/** Selected checkbox custom button image */
	private ImageIcon selectedCheckbox;
	
	/** Highlighted checkbox custom button image */
	private ImageIcon highlightedCheckbox;
	
	/** Highlighted selected checkbox custom button image */
	private ImageIcon highlightedSelectedCheckbox;
	
	/** Create custom light gray color */
	private Color light_gray = new Color(31, 31, 31);
	
	/** Create custom white color */
	private Color white = new Color(255, 255, 255);
	
	/** Create common font for application usage */
	private Font commonFont = new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 12);

	//The transition this pane is associated with
	private Transition transition;
	
	// add the following variables after connecting backend: Timeline t, int index, 
	TransitionPane(JFrame parent, String title, Coord2 position, Dimension size, int index){
		
		//Call the parent constructor
		super(parent, title, position, size);
		
		Timeline t = SceneHandler.singleton.getTimeline();
		transition = t.transitionsList.getTransition(index);
		
		// Create GridBagLayout object and constraints
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		// Set image locations
		transitionHeader = new ImageIcon(getClass().getResource("/creator/Images/transHeader.png"));
		save = new ImageIcon(getClass().getResource("/creator/Images/saveButton.png"));
		cancel = new ImageIcon(getClass().getResource("/creator/Images/cancelButton.png"));
		highlightedSave = new ImageIcon(getClass().getResource("/creator/Images/highlightedSaveButton.png"));
		highlightedCancel = new ImageIcon(getClass().getResource("/creator/Images/highlightedCancelButton.png"));
		cross = new ImageIcon(getClass().getResource("/core/TransitionImages/resizedCrossFade.png"));
		right = new ImageIcon(getClass().getResource("/core/TransitionImages/resizedWipeRight.png"));
		left = new ImageIcon(getClass().getResource("/core/TransitionImages/resizedWipeLeft.png"));
		up = new ImageIcon(getClass().getResource("/core/TransitionImages/resizedWipeUp.png"));
		down = new ImageIcon(getClass().getResource("/core/TransitionImages/resizedWipeDown.png"));
		slow = new ImageIcon(getClass().getResource("/creator/Images/slowButton.png"));
		medium = new ImageIcon(getClass().getResource("/creator/Images/mediumButton.png"));
		fast = new ImageIcon(getClass().getResource("/creator/Images/fastButton.png"));
		highlightedSlow = new ImageIcon(getClass().getResource("/creator/Images/highlightedSlowButton.png"));
		highlightedMedium = new ImageIcon(getClass().getResource("/creator/Images/highlightedMediumButton.png"));
		highlightedFast = new ImageIcon(getClass().getResource("/creator/Images/highlightedFastButton.png"));
		checkbox = new ImageIcon(getClass().getResource("/creator/Images/checkbox.png"));
		selectedCheckbox = new ImageIcon(getClass().getResource("/creator/Images/selectedCheckbox.png"));
		highlightedCheckbox = new ImageIcon(getClass().getResource("/creator/Images/highlightedCheckbox.png"));
		highlightedSelectedCheckbox = new ImageIcon(getClass().getResource("/creator/Images/highlightedSelectedCheckbox.png"));
		
		//Construct original panel container and configurations
		JPanel transitionGui = new JPanel();
		transitionGui.setLayout(gridBag);
		transitionGui.setBackground(light_gray);
		
		// Create save button
		saveButton = new JButton(save);
		saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		saveButton.setBorder(BorderFactory.createEmptyBorder());
		saveButton.setContentAreaFilled(false);
		saveButton.setFocusable(false);
		saveButton.setRolloverIcon(highlightedSave);
		saveButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	UpdateTransitionSettings();
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
		    	ArrangeScene scene = (ArrangeScene)SceneHandler.singleton.GetCurrentScene();
		    	scene.SetupTimelinePanel(true);
		    	ClosePane();
		    }
		});
		
		// Create cross button
		crossButton = new JToggleButton(cross);
		crossButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		crossButton.setBorder(BorderFactory.createEmptyBorder());
		crossButton.setContentAreaFilled(false);
		crossButton.setToolTipText("Cross Dissolve");
		crossButton.setFocusable(false);
		crossButton.setRolloverIcon(new ImageIcon(ImageHover(cross.getImage())));
		crossButton.setSelectedIcon(new ImageIcon(ImageHover(cross.getImage())));
		
		// Create right button
		rightButton = new JToggleButton(right);
		rightButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rightButton.setBorder(BorderFactory.createEmptyBorder());
		rightButton.setContentAreaFilled(false);
		rightButton.setToolTipText("Wipe Right");
		rightButton.setFocusable(false);
		rightButton.setRolloverIcon(new ImageIcon(ImageHover(right.getImage())));
		rightButton.setSelectedIcon(new ImageIcon(ImageHover(right.getImage())));
		
		// Create left button
		leftButton = new JToggleButton(left);
		leftButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		leftButton.setBorder(BorderFactory.createEmptyBorder());
		leftButton.setContentAreaFilled(false);
		leftButton.setToolTipText("Wipe Left");
		leftButton.setFocusable(false);
		leftButton.setRolloverIcon(new ImageIcon(ImageHover(left.getImage())));
		leftButton.setSelectedIcon(new ImageIcon(ImageHover(left.getImage())));
		
		// Create up button
		upButton = new JToggleButton(up);
		upButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		upButton.setBorder(BorderFactory.createEmptyBorder());
		upButton.setContentAreaFilled(false);
		upButton.setToolTipText("Wipe Up");
		upButton.setFocusable(false);
		upButton.setRolloverIcon(new ImageIcon(ImageHover(up.getImage())));
		upButton.setSelectedIcon(new ImageIcon(ImageHover(up.getImage())));
		
		// Create down button
		downButton = new JToggleButton(down);
		downButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		downButton.setBorder(BorderFactory.createEmptyBorder());
		downButton.setContentAreaFilled(false);
		downButton.setToolTipText("Wipe Down");
		downButton.setFocusable(false);
		downButton.setRolloverIcon(new ImageIcon(ImageHover(down.getImage())));
		downButton.setSelectedIcon(new ImageIcon(ImageHover(down.getImage())));
		
		// Create slow button
		slowButton = new JToggleButton(slow);
		slowButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		slowButton.setBorder(BorderFactory.createEmptyBorder());
		slowButton.setContentAreaFilled(false);
		slowButton.setFocusable(false);
		slowButton.setRolloverIcon(highlightedSlow);
		slowButton.setSelectedIcon(highlightedSlow);
		
		// Create medium button
		mediumButton = new JToggleButton(medium);
		mediumButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mediumButton.setBorder(BorderFactory.createEmptyBorder());
		mediumButton.setContentAreaFilled(false);
		mediumButton.setFocusable(false);
		mediumButton.setRolloverIcon(highlightedMedium);
		mediumButton.setSelectedIcon(highlightedMedium);
		
		// Create fast button
		fastButton = new JToggleButton(fast);
		fastButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		fastButton.setBorder(BorderFactory.createEmptyBorder());
		fastButton.setContentAreaFilled(false);
		fastButton.setFocusable(false);
		fastButton.setRolloverIcon(highlightedFast);
		fastButton.setSelectedIcon(highlightedFast);
		
		// Add transition type buttons to type group
		typeGroup = new ButtonGroup();
		typeGroup.add(crossButton);
		typeGroup.add(leftButton);
		typeGroup.add(rightButton);
		typeGroup.add(upButton);
		typeGroup.add(downButton);
		
		// Add transition length buttons to type group
		lengthGroup = new ButtonGroup();
		lengthGroup.add(slowButton);
		lengthGroup.add(mediumButton);
		lengthGroup.add(fastButton);
		
		// Configure type label
		transitionLabel = new JLabel(transitionHeader);
		
		// Configure type label
		typeLabel = new JLabel("Type");
		typeLabel.setFont(commonFont);
		typeLabel.setForeground(white);
		
		// Configure length label
		lengthLabel = new JLabel("Speed");
		lengthLabel.setFont(commonFont);
		lengthLabel.setForeground(white);
		
		// Configure change all label
		changeAllLabel = new JLabel("Apply to All");
		changeAllLabel.setFont(commonFont);
		changeAllLabel.setForeground(white);
		
        // Create slideshow loop checkbox
        changeAllCheck = new JCheckBox(checkbox);
        changeAllCheck.setSelectedIcon(selectedCheckbox);
        changeAllCheck.setRolloverIcon(highlightedCheckbox);
        changeAllCheck.setPressedIcon(highlightedCheckbox);
        changeAllCheck.setRolloverSelectedIcon(highlightedSelectedCheckbox);
        changeAllCheck.setBackground(light_gray);
        
		// Set settings panel configurations
		transitionPanel = new JPanel();
		transitionPanel.setLayout(gridBag);
		transitionPanel.setBackground(light_gray);
		
		// Set constraints and add header label
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 6;
		c.insets = new Insets(0, 0, 10, 0);
		transitionPanel.add(transitionLabel, c);
		
		// Set constraints and add type label
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.WEST;
		c.gridwidth = 1;
		c.insets = new Insets(35, 0, 15, 15);
		transitionPanel.add(typeLabel, c);
		
		// Set constraints and add audio label
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(20, 0, 30, 15);
		transitionPanel.add(lengthLabel, c);
		
		// Set constraints and add duration label
		c.gridx = 0;
		c.gridy = 5;
		c.insets = new Insets(20, 0, 5, 0);
		transitionPanel.add(changeAllLabel, c);
		
		// Set constraints and add auto button
		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(30, 12, 5, 0);
		transitionPanel.add(crossButton, c);
		
		// Set constraints and add manual button
		c.gridx = 2;
		c.gridy = 2;
		transitionPanel.add(leftButton, c);
		
		c.gridx = 3;
		c.gridy = 2;
		transitionPanel.add(rightButton, c);
		
		c.gridx = 4;
		c.gridy = 2;
		transitionPanel.add(upButton, c);
		
		c.gridx = 5;
		c.gridy = 2;
		transitionPanel.add(downButton, c);
		
		// Set constraints and add length drop down
		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(5, 12, 15, 0);
		transitionPanel.add(slowButton, c);
		
		// Set constraints and add length drop down
		c.gridx = 2;
		c.gridy = 3;
		transitionPanel.add(mediumButton, c);
		
		// Set constraints and add length drop down
		c.gridx = 3;
		c.gridy = 3;
		transitionPanel.add(fastButton, c);
		
		// Set constraints and add apply all changes checkbox
		c.gridx = 5;
		c.gridy = 5;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(20, 0, 5, 0);
		transitionPanel.add(changeAllCheck, c);
		
		// Set constraints and separator
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 6;
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(0, 0, 0, 0);
		transitionPanel.add(new JSeparator(), c);
		
		// Set constraints and add separator
		c.gridx = 0;
		c.gridy = 4;
		transitionPanel.add(new JSeparator(), c);
		
		// Set options panel configurations
		confirmationPanel = new JPanel();
		confirmationPanel.setLayout(gridBag);
		confirmationPanel.setBackground(light_gray);
		
		// Set constraints and add back button
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.insets = new Insets(0, 10, 10, 10);
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
		transitionGui.add(transitionPanel);
		
		// Set constraints and add confirmation panel
		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 0;
		c.insets = new Insets(20,0,10,0);
		transitionGui.add(confirmationPanel, c);
		
		// Set settings into floating pane
		getContentPane().add(transitionGui, BorderLayout.CENTER);
		
		// Load Transition settings in from transition
		LoadTransitionSettings();
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
                    c = new Color(217, 202, 192);
                    buffered.setRGB(i, j, c.getRGB());
                }
            }
        }
        return buffered;
    }
    
    /**
     * UpdateTransitionSettings() - Updates the transition type AND/OR length when user saves changes
	 * 
	 * @author Fernando Palacios
     */
	public void UpdateTransitionSettings() {
		
		double speed = transition.getTransitionLength();
		TransitionType type = transition.getTransitionType();
		Timeline t = SceneHandler.singleton.getTimeline();
		
		if(crossButton.isSelected()) {
			type = TransitionType.CROSS_DISSOLVE;
		}else if(leftButton.isSelected()) {
			type = TransitionType.WIPE_LEFT;
		}else if(rightButton.isSelected()) {
			type = TransitionType.WIPE_RIGHT;
		}else if(upButton.isSelected()) {
			type = TransitionType.WIPE_UP;
		}else if(downButton.isSelected()) {
			type = TransitionType.WIPE_DOWN;
		}
		
		if(slowButton.isSelected()) {
			speed = 5;
		}else if(mediumButton.isSelected()) {
			speed = 3;
		}else if(fastButton.isSelected()) {
			speed = 1;
		}
		
		if(changeAllCheck.isSelected())
		{
			t.UpdateTransitionSettings(speed, type);
		} else {
			transition.setTransitionType(type);
			transition.setTransitionLength(speed);
		}
		
		// Tell arrange scene to revalidate its buttons
		ArrangeScene scene = (ArrangeScene)SceneHandler.singleton.GetCurrentScene();
		scene.SetupTimelinePanel(true);
	}
	
    /**
     * LoadTransitionSettings() - Loads in the transition type AND length from the selected transition in timeline
	 * 
	 * @author Fernando Palacios
     */
	public void LoadTransitionSettings() {
		
		if(transition.getTransitionType() == TransitionType.CROSS_DISSOLVE) {
			crossButton.setSelected(true);
		}else if(transition.getTransitionType() == TransitionType.WIPE_RIGHT) {
			rightButton.setSelected(true);
		}else if(transition.getTransitionType() == TransitionType.WIPE_LEFT) {
			leftButton.setSelected(true);
		}else if(transition.getTransitionType() == TransitionType.WIPE_UP) {
			upButton.setSelected(true);
		}else if(transition.getTransitionType() == TransitionType.WIPE_DOWN) {
			downButton.setSelected(true);
		}
		
		if(transition.getTransitionLength() == 5) {
			slowButton.setSelected(true);
		}else if(transition.getTransitionLength() == 3) {
			mediumButton.setSelected(true);
		}else if(transition.getTransitionLength() == 1) {
			fastButton.setSelected(true);
		}
	}

}
