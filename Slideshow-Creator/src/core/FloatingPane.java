package core;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;

public class FloatingPane extends JDialog {
	
	public FloatingPane(JFrame parent, String title, Coord2 position, Dimension size) {
		//Call JDialog constructor
		super(parent, title);
		
		//Set the size of the pane
		setPreferredSize(size);
		setResizable(false);
		
		//Set the location (need to offset for size of window)
		setLocation(position.x-size.width/2, position.y-size.height/2);
		
        //Add Close button at the bottom
        JPanel buttonPane = new JPanel();
        JButton button = new JButton("Close");
        buttonPane.add(button);
        // set action listener on the button
        button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setVisible(false);
        		parent.setEnabled(true);
        		dispose();
        	}
        });
        getContentPane().add(buttonPane, BorderLayout.PAGE_END);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
		
	}
}
