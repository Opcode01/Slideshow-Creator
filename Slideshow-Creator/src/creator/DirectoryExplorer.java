package creator;

import core.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.FlowLayout;

public class DirectoryExplorer extends Scene {

	public DirectoryExplorer() {
		
		this.setSize(800, 600);
				
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel label = new JLabel("This is the directory explorer scene!");
		getContentPane().add(label);
		
		JButton testButton = new JButton("Hello World!");
		getContentPane().add(testButton);
	}
	
	public void GoToSelectScene() {
		
	}
	
}
