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
		
		//setUndecorated(true);
		
		//Set the location (need to offset for size of window)
		setLocation(position.x-size.width/2, position.y-size.height/2);
        getContentPane();
        
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
        		setVisible(false);
        		parent.setEnabled(true);
        		parent.setVisible(true);
        		dispose();
		    }
		});
		
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);	
	}
	
	public void ClosePane() {
		setVisible(false);
		parentFrame.setEnabled(true);
		parentFrame.setVisible(true);
		dispose();
	}
}
