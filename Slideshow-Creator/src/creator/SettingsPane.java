package creator;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.*;

public class SettingsPane extends FloatingPane {

	
	public SettingsPane(JFrame parent, String title, Coord2 position, Dimension size) {
		//Call the parent constructor
		super(parent, title, position, size);
		
		JPanel putYourSettingsGuiHere = new JPanel();
		JLabel label = new JLabel("Hello World");
		putYourSettingsGuiHere.add(label);
		
		/*You could literally put an entire scene here since scenes are just JPanels
		JPanel putYourSettingsGuiHere = new SelectScene();
		Don't do this though its super buggy
		*/
		
		getContentPane().add(putYourSettingsGuiHere, BorderLayout.CENTER);
		
	}
}
