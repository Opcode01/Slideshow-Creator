package core;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;

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
        
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
        		setVisible(false);
        		parent.setEnabled(true);
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
		dispose();
	}
}
