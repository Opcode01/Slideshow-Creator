/**
 * FloatingPane.java
 * A simple floating window that pops up over the screen
 * 
 * Slideshow Creator
 * Timothy Couch, Joseph Hoang, Fernando Palacios, Austin Vickers
 * CS 499 Senior Design with Dr. Rick Coleman
 * 4/11/19
 */

package core;

import java.awt.Dimension;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class FloatingPane extends JDialog {
	
	private JFrame parentFrame;
	
	public FloatingPane(JFrame parent, String title, Coord2 position, Dimension size) {
		//Call JDialog constructor
		super(parent, title);
		parentFrame = parent;
		
		//Set the size of the pane
		setPreferredSize(size);
		setResizable(false);
		setUndecorated(true);
		
		//Set the location (need to offset for size of window)
		setLocation(position.x-size.width/2, position.y-size.height/2);
        getContentPane();
		
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);	
	}
	
	public void ClosePane() {
		parentFrame.setEnabled(true);
		parentFrame.setVisible(true);
		setVisible(false);
		dispose();
	}
}
