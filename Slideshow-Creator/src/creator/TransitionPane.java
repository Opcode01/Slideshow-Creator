package creator;

import java.awt.Dimension;
import javax.swing.JFrame;
import core.*;

public class TransitionPane extends FloatingPane {

	public TransitionPane(JFrame parent, String title, Coord2 position, Dimension size) {
		//Call the parent constructor
		super(parent, title, position, size);
	}
}
