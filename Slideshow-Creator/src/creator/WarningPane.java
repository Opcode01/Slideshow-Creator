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

import core.Coord2;
import core.FloatingPane;
import core.SceneHandler;

public class WarningPane extends FloatingPane{

	/** Create ok button */
	private JButton okButton;
	
	/** Create cancel button */
	private JButton cancelButton;
	
	/** Create second text label */
	private JLabel warningHeader;
	
	/** Create text label */
	private JLabel warningPText;
	
	/** Create second text label */
	private JLabel warningFText;
	
	/** Create second text label */
	private JLabel warningOText;
	
	/** Highlighted auto custom button image */
	private ImageIcon warning;
	
	/** Ok custom button image */
	private ImageIcon ok;
	
	/** Highlighted ok custom button image */
	private ImageIcon highlightedOk;
	
	/** Manual custom button image */
	private ImageIcon cancel;
	
	/** Highlighted manual custom button image */
	private ImageIcon highlightedCancel;
	
	/** Create custom light gray color */
	private Color light_gray = new Color(31, 31, 31);
	
	/** Create custom white color */
	private Color white = new Color(255, 255, 255);
	
	/** Create common font for application usage */
	private Font commonFont = new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 10);

	
	public WarningPane(JFrame parent, String title, Coord2 position, Dimension size, String problemText, String fixText) {
		
		super(parent, title, position, size);
		
		// Create GridBagLayout object and constraints
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		//Construct original panel container and configurations
		JPanel warningGui = new JPanel();
		warningGui.setLayout(gridBag);
		warningGui.setBackground(light_gray);
		
		// Set images
		ok = new ImageIcon(getClass().getResource("/creator/Images/okButton.png"));
		highlightedOk = new ImageIcon(getClass().getResource("/creator/Images/highlightedOkButton.png"));
		warning = new ImageIcon(getClass().getResource("/creator/Images/warningHeader.png"));
		
		warningHeader = new JLabel(warning);
		
		warningPText = new JLabel(problemText);
		warningPText.setFont(commonFont);
		warningPText.setForeground(white);
		
		warningFText = new JLabel(fixText);
		warningFText.setFont(commonFont);
		warningFText.setForeground(white);
		
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
		warningGui.add(warningPText, c);
		
		c.gridx = 0;
		c.gridy = 2;
		warningGui.add(warningFText, c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(25, 0, 20, 0);
		warningGui.add(okButton, c);
		
		// Set settings into floating pane
		getContentPane().add(warningGui, BorderLayout.CENTER);
	}
	
	public WarningPane(JFrame parent, String title, Coord2 position, Dimension size, String problemText, String fixText, String optionText) {
		
		super(parent, title, position, size);
		
		// Create GridBagLayout object and constraints
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		//Construct original panel container and configurations
		JPanel warningGui = new JPanel();
		warningGui.setLayout(gridBag);
		warningGui.setBackground(light_gray);
		
		// Set images
		ok = new ImageIcon(getClass().getResource("/creator/Images/okButton.png"));
		highlightedOk = new ImageIcon(getClass().getResource("/creator/Images/highlightedOkButton.png"));
		cancel = new ImageIcon(getClass().getResource("/creator/Images/cancelButton.png"));
		highlightedCancel = new ImageIcon(getClass().getResource("/creator/Images/highlightedCancelButton.png"));
		warning = new ImageIcon(getClass().getResource("/creator/Images/warningHeader.png"));
		
		warningHeader = new JLabel(warning);
		
		warningPText = new JLabel(problemText);
		warningPText.setFont(commonFont);
		warningPText.setForeground(white);
		
		warningFText = new JLabel(fixText);
		warningFText.setFont(commonFont);
		warningFText.setForeground(white);
		
		warningOText = new JLabel(optionText);
		warningOText.setFont(commonFont);
		warningOText.setForeground(white);
		
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
		
		c.anchor = GridBagConstraints.CENTER;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(15, 0, 20, 0);
		warningGui.add(warningHeader, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(0, 0, 0, 0);
		warningGui.add(warningPText, c);
		
		c.gridx = 0;
		c.gridy = 2;
		warningGui.add(warningFText, c);
		
		c.gridx = 0;
		c.gridy = 3;
		warningGui.add(warningOText, c);
		
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 4;
		c.insets = new Insets(25, 0, 20, 0);
		warningGui.add(okButton, c);
		
		c.gridx = 1;
		c.gridy = 4;
		c.insets = new Insets(25, 0, 20, 0);
		warningGui.add(cancelButton, c);
		
		// Set settings into floating pane
		getContentPane().add(warningGui, BorderLayout.CENTER);
		
	}
}
