package creator;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import core.*;

public class ArrangeScene extends Scene{
	
	/** Create custom color */
	private JPanel optionsPanel;
	
	/** Create custom color */
	private JPanel imagePanel;
	
	/** Create custom color */
	private JPanel timelinePanel;
	
	/** Create Settings Pane */
	private SettingsPane settingsPane;
	
	/** Back button */
	private JButton backButton;
	
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
	
	/** Create custom dark_gray color */
	private Color dark_gray = new Color(30, 30, 30);
	
	private Color image_gray = new Color(30, 30, 30);
	
	private GridBagLayout gridBag = new GridBagLayout();
	private FlowLayout flow = new FlowLayout(FlowLayout.LEADING);
	private GridBagConstraints imagePanelConstraints;
	
	/**
	 * ArrangeScene() - sets up arrange with GUI stuff
	 *
	 * @author Fernando Palacios
	 */
	public ArrangeScene () {
		// Create GridBagLayout object and constraints
		
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
		backButton.setToolTipText("Back");
		backButton.setBorder(BorderFactory.createEmptyBorder());
		backButton.setContentAreaFilled(false);
		backButton.setFocusable(false);
		backButton.setRolloverIcon(highlightedBack);
		backButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	GoToSelectScene();
		    }
		});
		
		// Create settings button
		settingsButton = new JButton(settings);
		settingsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		settingsButton.setToolTipText("Project Settings");
		settingsButton.setBorder(BorderFactory.createEmptyBorder());
		settingsButton.setContentAreaFilled(false);
		settingsButton.setFocusable(false);
		settingsButton.setRolloverIcon(highlightedSettings);
		settingsButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	//Open settings pane in the center of our workspace
		    	JFrame parent = SceneHandler.singleton.getMainFrame();
		    	Coord2 point = new Coord2(
		    			parent.getX() + parent.getSize().width/2,
		    			parent.getY() + parent.getSize().height/2
		    			);
		    	settingsPane = new SettingsPane(parent, "Project Settings", point, new Dimension(400, 470));
		    	parent.setEnabled(false);
		    }
		});
		
		// Create remove current button
		removeCurrentButton = new JButton(removeCurrent);
		removeCurrentButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		removeCurrentButton.setToolTipText("Remove Selected Image");
		removeCurrentButton.setBorder(BorderFactory.createEmptyBorder());
		removeCurrentButton.setContentAreaFilled(false);
		removeCurrentButton.setFocusable(false);
		removeCurrentButton.setRolloverIcon(highlightedRemoveCurrent);
		removeCurrentButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	//TODO: Remove the currently selected thumbnail and transition from timeline
		    }
		});
		
		// Set options panel configurations
		optionsPanel = new JPanel();
		optionsPanel.setLayout(gridBag);
		optionsPanel.setBackground(light_gray);
		
		// Set constraints and add back button
		c.anchor = GridBagConstraints.NORTHWEST;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0;
		optionsPanel.add(backButton, c);
		
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
		imagePanel.setBackground(dark_gray);
		
		// Set timeline panel configurations
		timelinePanel = new JPanel();
		timelinePanel.setLayout(gridBag);
		timelinePanel.setBackground(light_gray);
		
		// Set constraints and add options panels
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0;
		c.weighty = 1.0;
		c.gridx = 0;
		c.gridy = 0;
		this.add(optionsPanel, c);
		
		///////////////////////
		//Add example image - this is approximately what you should do to set up the display image! :)
		Thumbnail testThumb = new Thumbnail("src/creator/TransitionImages/crossFade.png");
		JLabel testLabel = new JLabel() {
			  @Override
			  public void paintComponent(Graphics g) {
				  testThumb.drawFill(g, this);
				  }
			  };
		c.weightx = 0.01;
		c.anchor = GridBagConstraints.NORTH;
		imagePanel.add(testLabel, c);
		///////////////////////
		
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
		
		
		/*
		JPanel base = new JPanel();
		base.setLayout(flow);
		base.setBackground(light_gray);
		JScrollPane scrollPane = new JScrollPane(base);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 1;
		c.weighty = .7;
		c.gridx = 0;
		c.gridy = 0;
		timelinePanel.add(scrollPane, c);
		
		for(Thumbnail picture : SceneHandler.singleton.getTimeline().thumbnailsList.getThumbnails())
		{
			JLabel thumbnail = new JLabel(new ImageIcon(picture.getImageThumb()));
			JLabel transition = new JLabel();
			base.add(thumbnail);
			
		}
		
		this.repaint();	
		*/
		this.revalidate();
		
		
	}
	
	
	/**
     * ShowImages() - creates thumbnail icons to display in scrollpane
     * 
     * @author Fernando Palacios
	 * @author Timothy Couch
     */
	private void ShowImages(JPanel panel, ThumbnailsList list)
	{
		int i = 0; //counter for buttons
		int gridxCounter = 0; //layout counter x
		int gridyCounter = 0; // layout counter y
		JButton[] buttons = new JButton[list.getSize()];
		GridBagConstraints c = new GridBagConstraints();

		for(i = 0; i < buttons.length; i++) {
			
			if (gridxCounter > 2) {
				gridxCounter = 0;
				gridyCounter++;
			}
			
			//set constraints
			c.gridx = gridxCounter;
			c.gridy = gridyCounter;
			c.fill = GridBagConstraints.BOTH;
			c.insets = new Insets(40, 40, 40, 40);
			
			gridxCounter++;
			
			Thumbnail buttonThumb = list.getThumbnail(i);
			buttons[i] = new JButton(new ImageIcon(buttonThumb.getImageThumb()));
			buttons[i].setPreferredSize(new Dimension(320, 200));
			//buttons[i].setRolloverIcon(new ImageIcon(buttonThumb.getImageThumb()));
			//buttons[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			//buttons[i].setBorder(BorderFactory.createEmptyBorder());
			buttons[i].setFocusable(false);
			
			/*
			buttons[i].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					//add button to or remove button from timeline
					Timeline timeline = SceneHandler.singleton.getTimeline();

					int slideIndex = timeline.thumbnailsList.indexOf(buttonThumb);
					//if thumbnail is in the timeline, remove it
					if (slideIndex >= 0)
					{
						timeline.removeSlide(slideIndex);
						//TODO: remove button highlight @Fernando
					}
					else//thumbnail not on timeline, add it
					{
						timeline.addSlide(buttonThumb);
						//TODO: highlight button @Fernando
					}
				}
				});
				*/
			panel.add(buttons[i], c);
		}
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