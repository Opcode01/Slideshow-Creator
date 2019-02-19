package creator;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.*;

public class ArrangeScene extends Scene{
	
	/** Create custom color */
	private JPanel optionsPanel;
	
	/** Create custom color */
	private JPanel imagePanel;
	
	/** Create custom color */
	private JPanel timelinePanel;
	
	/** Back button */
	private JButton backButton;
	
	/** Arrange button */
	private JButton directoryButton;
	
	/** Select all button */
	private JButton settingsButton;
	
	/** Deselect all button */
	private JButton removeCurrentButton;
	
	/** Back custom button image */
	private ImageIcon back;
	
	/** Directory custom button image */
	private ImageIcon directory;
	
	/** Settings custom button image */
	private ImageIcon settings;
	
	/** Remove current custom button image */
	private ImageIcon removeCurrent;
	
	/** Highlighted back custom button image */
	private ImageIcon highlightedBack;
	
	/** Highlighted arrange custom button image */
	private ImageIcon highlightedDirectory;
	
	/** Highlighted select all custom button image */
	private ImageIcon highlightedSettings;
	
	/** Highlighted deselect all custom button image */
	private ImageIcon highlightedRemoveCurrent;
	
	/** Create custom light gray color */
	private Color light_gray = new Color(60, 60, 60);
	
	/** Create custom aqua color */
	private Color aqua = new Color(132, 200, 202);
	
	/**
	 * ArrangeScene() - sets up arrange with GUI stuff
	 *
	 * @author Fernando Palacios
	 */
	public ArrangeScene () {
		// Create GridBagLayout object and constraints
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		// Set panel configurations
		this.setLayout(gridBag);
		
		back = new ImageIcon(getClass().getResource("Images/backButton.png"));
		directory = new ImageIcon(getClass().getResource("Images/directoryButton.png"));
		settings = new ImageIcon(getClass().getResource("Images/settingsButton.png"));
		removeCurrent = new ImageIcon(getClass().getResource("Images/removeCurrentButton.png"));
		highlightedBack = new ImageIcon(getClass().getResource("Images/highlightedBackButton.png"));
		highlightedDirectory = new ImageIcon(getClass().getResource("Images/highlightedDirectoryButton.png"));
		highlightedSettings = new ImageIcon(getClass().getResource("Images/highlightedSettingsButton.png"));
		highlightedRemoveCurrent = new ImageIcon(getClass().getResource("Images/highlightedRemoveCurrentButton.png"));
		
		// Create back button
		backButton = new JButton(back);
		backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		backButton.setBorder(BorderFactory.createEmptyBorder());
		backButton.setContentAreaFilled(false);
		backButton.setFocusable(false);
		backButton.setRolloverIcon(highlightedBack);
		backButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	GoToSelectScene();
		    }
		});
		
		// Create select all button
		directoryButton = new JButton(directory);
		directoryButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		directoryButton.setBorder(BorderFactory.createEmptyBorder());
		directoryButton.setContentAreaFilled(false);
		directoryButton.setFocusable(false);
		directoryButton.setRolloverIcon(highlightedDirectory);
		directoryButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
		    }
		});
		
		// Create select all button
		settingsButton = new JButton(settings);
		settingsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		settingsButton.setBorder(BorderFactory.createEmptyBorder());
		settingsButton.setContentAreaFilled(false);
		settingsButton.setFocusable(false);
		settingsButton.setRolloverIcon(highlightedSettings);
		settingsButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
		    }
		});
		
		// Create deselect all button
		removeCurrentButton = new JButton(removeCurrent);
		removeCurrentButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		removeCurrentButton.setBorder(BorderFactory.createEmptyBorder());
		removeCurrentButton.setContentAreaFilled(false);
		removeCurrentButton.setFocusable(false);
		removeCurrentButton.setRolloverIcon(highlightedRemoveCurrent);
		removeCurrentButton.addActionListener(new ActionListener() {
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
		
		// Set constraints and add directory button
		c.gridx = 0;
		c.gridy = 1;
		optionsPanel.add(directoryButton, c);
		
		// Set constraints and add settings button
		c.gridx = 0;
		c.gridy = 2;
		optionsPanel.add(settingsButton, c);
		
		// Set constraints and add remove current button
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 3;
		optionsPanel.add(removeCurrentButton, c);
		
		// Set image panel configurations
		imagePanel = new JPanel();
		imagePanel.setLayout(gridBag);
		imagePanel.setBackground(aqua);
		
		// Set timeline panel configurations
		timelinePanel = new JPanel();
		timelinePanel.setLayout(gridBag);
		timelinePanel.setBackground(light_gray);
		
		// Set constraints and add options panels
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.01;
		c.weighty = 1.0;
		c.gridx = 0;
		c.gridy = 0;
		this.add(optionsPanel, c);
		
		// Set constraints and add image panel
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 1;
		c.gridy = 0;
		this.add(imagePanel, c);
		
		// Set constraints and add timeline panel
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 2;
		c.weighty = 0.7;
		c.gridx = 0;
		c.gridy = 1;
		this.add(timelinePanel, c);
		this.revalidate();
	}
	
	/**
	 * GoToSelectScene() - changes scene to selection
	 *
	 * @author Fernando Palacios
     */
	public void GoToSelectScene()
	{
		SceneHandler.singleton.SwitchToScene(SceneType.SELECTION);
	}

}
