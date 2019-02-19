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
import javax.swing.JPanel;

import core.*;

public class ArrangeScene extends Scene{
	
	/** Create custom color */
	private JPanel optionsPanel;
	
	/** Create custom color */
	private JPanel imagePanel;
	
	/** Select existing slideshow creator file button */
	private JButton backButton;
	
	/** Select existing slideshow creator file button */
	private JButton arrangeButton;
	
	/** Select back custom button image */
	private ImageIcon back;
	
	/** Create custom color */
	private Color light_gray = new Color(60, 60, 60);
	
	/** Create custom color */
	private Color aqua = new Color(132, 200, 202);
	
	public ArrangeScene() {
		
		// Create GridBagLayout object and constraints
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		// Set panel configurations
		this.setLayout(gridBag);
		
		back = new ImageIcon(getClass().getResource("Images/backButton.png"));
		
		// Create back button
		backButton = new JButton(back);
		backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		backButton.setBorder(BorderFactory.createEmptyBorder());
		backButton.setContentAreaFilled(false);
		backButton.setFocusable(false);
		backButton.setRolloverIcon(back);
		backButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
		    }
		});
		
		arrangeButton = new JButton(back);
		arrangeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		arrangeButton.setBorder(BorderFactory.createEmptyBorder());
		arrangeButton.setContentAreaFilled(false);
		arrangeButton.setFocusable(false);
		arrangeButton.setRolloverIcon(back);
		
		// Set options panel configurations
		optionsPanel = new JPanel();
		optionsPanel.setLayout(gridBag);
		optionsPanel.setBackground(Color.DARK_GRAY);
		optionsPanel.add(backButton);
		
		// Set image panel configurations
		imagePanel = new JPanel();
		imagePanel.setLayout(gridBag);
		imagePanel.setBackground(aqua);
		imagePanel.add(arrangeButton);
		
		// Set constraints

		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.01;
		c.weighty = 1.0;
		c.gridx = 0;
		c.gridy = 0;
		
		this.add(optionsPanel, c);
		
		// Set constraints
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 1;
		c.gridy = 0;
		
		this.add(imagePanel, c);
		
	}

}
