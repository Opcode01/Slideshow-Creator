/**
 * WarningPane.java
 * Floating window that alerts users of various issues
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
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import core.Coord2;
import core.FloatingPane;
import core.SliderColor;

public class WarningPane extends FloatingPane{

	/** Create ok button */
	private JButton okButton;
	
	/** Create second text label */
	private JLabel warningHeader;
	
	/** Create text label */
	private JTextArea textArea;
	
	/** Highlighted auto custom button image */
	private ImageIcon warning;
	
	/** Ok custom button image */
	private ImageIcon ok;
	
	/** Highlighted ok custom button image */
	private ImageIcon highlightedOk;
	
	/** Create common font for application usage */
	private Font commonFont = new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 12);

	
	public WarningPane(JFrame parent, String title, String text, Coord2 position, Dimension size) {
		
		super(parent, title, position, size);
		
		// Create GridBagLayout object and constraints
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		//Construct original panel container and configurations
		JPanel warningGui = new JPanel();
		warningGui.setLayout(gridBag);
		warningGui.setBackground(SliderColor.beige_gray);
		
		// Set images
		ok = new ImageIcon(getClass().getResource("/creator/Images/okButton.png"));
		highlightedOk = new ImageIcon(getClass().getResource("/creator/Images/highlightedOkButton.png"));
		warning = new ImageIcon(getClass().getResource("/creator/Images/warningHeader.png"));
		
		warningHeader = new JLabel(warning);
		
		
		//I changed this so WarningPane is now extensible to any text
		textArea = new JTextArea();
		textArea.setPreferredSize(new Dimension(350, 50));
        textArea.setText(text);
        textArea.setFont(commonFont);
        textArea.setEditable(false);
        textArea.setForeground(SliderColor.white);
        textArea.setBackground(SliderColor.beige_gray);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
		
		// Create save button
		okButton = new JButton(ok);
		okButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		okButton.setBorder(BorderFactory.createEmptyBorder());
		okButton.setContentAreaFilled(false);
		okButton.setFocusable(false);
		okButton.setRolloverIcon(highlightedOk);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClosePane();	
			}
		});
		
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(15, 0, 20, 0);
		warningGui.add(warningHeader, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(0, 0, 0, 0);
		warningGui.add(textArea, c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(25, 0, 20, 0);
		warningGui.add(okButton, c);
		
		// Set settings into floating pane
		getContentPane().add(warningGui, BorderLayout.CENTER);
		
	}

	
}
