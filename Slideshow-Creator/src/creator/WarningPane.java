package creator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.Coord2;
import core.FloatingPane;

public class WarningPane extends FloatingPane{

	/** Create ok button */
	private JButton okButton;
	
	/** Create text label */
	private JLabel warningText;
	
	/** Create custom light gray color */
	private Color light_gray = new Color(31, 31, 31);
	
	/** Create custom white color */
	private Color white = new Color(255, 255, 255);
	
	/** Create common font for application usage */
	private Font commonFont = new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 12);

	
	public WarningPane(JFrame parent, String title, Coord2 position, Dimension size) {
		
		super(parent, title, position, size);
		
		// Create GridBagLayout object and constraints
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		//Construct original panel container and configurations
		JPanel warningGui = new JPanel();
		warningGui.setLayout(gridBag);
		warningGui.setBackground(light_gray);
		
		warningText = new JLabel("Warning - your audio tracks extend past the length of your slideshow. Remove some audio, add more slides, or change the slide duration in Settings.");
		warningText.setFont(commonFont);
		warningText.setForeground(white);
		
		// Create save button
		okButton = new JButton("OK");
		okButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		//okButton.setBorder(BorderFactory.createEmptyBorder());
		//okButton.setContentAreaFilled(false);
		okButton.setFocusable(false);
		//okButton.setRolloverIcon(highlightedSave);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClosePane();	
			}
		});
		
		c.gridx = 0;
		c.gridy = 0;
		warningGui.add(warningText, c);
		
		c.gridx = 0;
		c.gridy = 2;
		warningGui.add(okButton, c);
		
		// Set settings into floating pane
		getContentPane().add(warningGui, BorderLayout.CENTER);
		
	}

	
}
