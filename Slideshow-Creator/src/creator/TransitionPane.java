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
	private JPanel transitonPanel;
	
	/** Create confirmation panel */
	private JPanel confirmationPanel;
	
	/** Create transition label */
	private JLabel transitionLabel;
	
	/** Create type label */
	private JLabel typeLabel;

	/** Create transitions length label */
	private JLabel lengthLabel;
	
	/** Create length dropdown */
	private JComboBox<String> lengthDropDown;
	
	/** Create auto button */
	private JToggleButton crossButton;
	
	/** Create manual button */
	private JToggleButton leftButton;
	
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
	
	/** Transition custom header image */
	private ImageIcon transitionHeader;
	
	/** Auto custom button image */
	private ImageIcon save;
	
	/** Highlighted auto custom button image */
	private ImageIcon highlightedSave;
	
	/** Manual custom button image */
	private ImageIcon cancel;
	
	/** Highlighted manual custom button image */
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
	
	/** Create custom light gray color */
	private Color light_gray = new Color(31, 31, 31);
	
	/** Create custom white color */
	private Color white = new Color(255, 255, 255);
	
	/** Create common font for application usage */
	private Font commonFont = new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 12);

	//The transition this pane is associated with
	private Transition transition;
	
	// add the folowing variables after connecting backend: Timeline t, int index, 
	TransitionPane(JFrame parent, String title, Coord2 position, Dimension size){
		
		//Call the parent constructor
		super(parent, title, position, size);
		
		//transition = t.transitionsList.getTransition(index);
		
		
		// Create GridBagLayout object and constraints
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		// Set image locations
		transitionHeader = new ImageIcon(getClass().getResource("Images/transHeader.png"));
		save = new ImageIcon(getClass().getResource("Images/saveButton.png"));
		cancel = new ImageIcon(getClass().getResource("Images/cancelButton.png"));
		highlightedSave = new ImageIcon(getClass().getResource("Images/highlightedSaveButton.png"));
		highlightedCancel = new ImageIcon(getClass().getResource("Images/highlightedCancelButton.png"));
		cross = new ImageIcon("src/core/TransitionImages/resizedCrossFade.png");
		right = new ImageIcon("src/core/TransitionImages/resizedWipeRight.png");
		left = new ImageIcon("src/core/TransitionImages/resizedWipeLeft.png");
		up = new ImageIcon("src/core/TransitionImages/resizedWipeUp.png");
		down = new ImageIcon("src/core/TransitionImages/resizedWipeDown.png");
		
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
		    	ClosePane();
		    }
		});
		
		// Create cross button
		crossButton = new JToggleButton(cross);
		crossButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		crossButton.setBorder(BorderFactory.createEmptyBorder());
		crossButton.setContentAreaFilled(false);
		crossButton.setFocusable(false);
		crossButton.setRolloverIcon(new ImageIcon(ImageHover(cross.getImage())));
		crossButton.setSelectedIcon(new ImageIcon(ImageHover(cross.getImage())));
		crossButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
		    }
		});
		
		// Create right button
		rightButton = new JToggleButton(right);
		rightButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rightButton.setBorder(BorderFactory.createEmptyBorder());
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusable(false);
		rightButton.setRolloverIcon(new ImageIcon(ImageHover(right.getImage())));
		rightButton.setSelectedIcon(new ImageIcon(ImageHover(right.getImage())));
		rightButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
		    }
		});
		
		// Create left button
		leftButton = new JToggleButton(left);
		leftButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		leftButton.setBorder(BorderFactory.createEmptyBorder());
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusable(false);
		leftButton.setRolloverIcon(new ImageIcon(ImageHover(left.getImage())));
		leftButton.setSelectedIcon(new ImageIcon(ImageHover(left.getImage())));
		leftButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
		    }
		});
		
		// Create up button
		upButton = new JToggleButton(up);
		upButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		upButton.setBorder(BorderFactory.createEmptyBorder());
		upButton.setContentAreaFilled(false);
		upButton.setFocusable(false);
		upButton.setRolloverIcon(new ImageIcon(ImageHover(up.getImage())));
		upButton.setSelectedIcon(new ImageIcon(ImageHover(up.getImage())));
		upButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
		    }
		});
		
		// Create down button
		downButton = new JToggleButton(down);
		downButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		downButton.setBorder(BorderFactory.createEmptyBorder());
		downButton.setContentAreaFilled(false);
		downButton.setFocusable(false);
		downButton.setRolloverIcon(new ImageIcon(ImageHover(down.getImage())));
		downButton.setSelectedIcon(new ImageIcon(ImageHover(down.getImage())));
		downButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
		    }
		});
		
		// Add transition type buttons to type group
		ButtonGroup typeGroup = new ButtonGroup();
		typeGroup.add(crossButton);
		typeGroup.add(leftButton);
		typeGroup.add(rightButton);
		typeGroup.add(upButton);
		typeGroup.add(downButton);
		
		// Configure type label
		transitionLabel = new JLabel(transitionHeader);
		
		// Configure type label
		typeLabel = new JLabel("Type");
		typeLabel.setFont(commonFont);
		typeLabel.setForeground(white);
		
		// Configure length label
		lengthLabel = new JLabel("Length");
		lengthLabel.setFont(commonFont);
		lengthLabel.setForeground(white);
        
        // Create length drop down
        String[] lengths = { "Slow", "Medium", "Fast" };
        lengthDropDown = new JComboBox<String>(lengths);
        
		// Set settings panel configurations
		transitonPanel = new JPanel();
		transitonPanel.setLayout(gridBag);
		transitonPanel.setBackground(light_gray);
		
		// Set constraints and add header label
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 6;
		c.insets = new Insets(0, 0, 10, 0);
		transitonPanel.add(transitionLabel, c);
		
		// Set constraints and add type label
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.WEST;
		c.gridwidth = 1;
		c.insets = new Insets(30, 0, 10, 15);
		transitonPanel.add(typeLabel, c);
		
		// Set constraints and add audio label
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(10, 0, 30, 15);
		transitonPanel.add(lengthLabel, c);
		
		// Set constraints and add auto button
		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(30, 12, 10, 0);
		transitonPanel.add(crossButton, c);
		
		// Set constraints and add manual button
		c.gridx = 2;
		c.gridy = 2;
		c.insets = new Insets(30, 7, 10, 9);
		transitonPanel.add(leftButton, c);
		
		c.gridx = 3;
		c.gridy = 2;
		transitonPanel.add(rightButton, c);
		
		c.gridx = 4;
		c.gridy = 2;
		transitonPanel.add(upButton, c);
		
		c.gridx = 5;
		c.gridy = 2;
		transitonPanel.add(downButton, c);
		
		// Set constraints and add length drop down
		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(10, 12, 30, 0);
		transitonPanel.add(lengthDropDown, c);
		
		// Set constraints and separator
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 6;
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(0, 0, 0, 0);
		transitonPanel.add(new JSeparator(), c);
		
		// Set constraints and add separator
		c.gridx = 0;
		c.gridy = 4;
		transitonPanel.add(new JSeparator(), c);
		
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
		transitionGui.add(transitonPanel);
		
		// Set constraints and add confirmation panel
		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 0;
		c.insets = new Insets(20,0,0,0);
		transitionGui.add(confirmationPanel, c);
		
		// Set settings into floating pane
		getContentPane().add(transitionGui, BorderLayout.CENTER);
	}
	
	/** TODO: Use these methods on the action listeners when doing frontend implementation
	 * 
	 * 	transition.setTransitionLength(timeInSeconds);
	 *  transition.setTransitionType(TransitionType);
	 */
	
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
                    c = new Color(228, 199, 193);
                    buffered.setRGB(i, j, c.getRGB());
                }
            }
        }
        return buffered;
    }
    
	public void UpdateTransitionSettings() {
		
		if(crossButton.isSelected()) {
			transition.setTransitionType(TransitionType.CROSS_DISSOLVE);
		}else if(leftButton.isSelected()) {
			transition.setTransitionType(TransitionType.WIPE_LEFT);
		}else if(rightButton.isSelected()) {
			transition.setTransitionType(TransitionType.WIPE_RIGHT);
		}else if(upButton.isSelected()) {
			transition.setTransitionType(TransitionType.WIPE_UP);
		}else if(downButton.isSelected()) {
			transition.setTransitionType(TransitionType.WIPE_DOWN);
		}
		
		String transitionText = (String)lengthDropDown.getSelectedItem();
		if(transitionText == "Slow") {
			transition.setTransitionLength(5);
		}else if(transitionText == "Medium") {
			transition.setTransitionLength(3);
		}else if(transitionText == "Fast") {
			transition.setTransitionLength(1);
		}else {
			System.out.println("Transition time is invalid!");
		}
	}

}
