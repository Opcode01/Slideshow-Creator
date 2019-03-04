package pkgImageTransitions;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

//==========================================================================================
// abstract class Transition
// Define the parent class that all image transitions will extend.  This way new transitions
//   can be easily added to the application later. This class mostly provides an interface for
//   other transitions to use and some default implementation. This class also contains two 
//   static images into which the previously scaled for screen display images are stored for the
//   current image and the one to be transitioned to.
//
// Author: Dr. Rick Coleman
// Date: January 2016
// Last Modified by Austin Vickers - March 2019
//==========================================================================================
public class ColemanTransition
{	
	//--------------------------------------------------------
	/** Default constructor */
	//--------------------------------------------------------
	public ColemanTransition()
	{
		
	}
	
	//--------------------------------------------------------
	/** Perform the transition from one image to another */
	// All subclasses must override this.  This only does
	//  an instant switch from one to the other
	// Args:  
	//  imgPanel - Panel the images are drawn into
	//	ImageA - Current Image on the screen
	//  ImageB - Image to transition to
	//  time - Number of seconds to take to do this transition
	//---------------------------------------------------------
	public void DrawImageTransition(JPanel imgPanel, BufferedImage ImageA, BufferedImage ImageB, double time)
	{
		// Get all the graphics objects
		Graphics gPan = imgPanel.getGraphics();
		Graphics gA = ImageA.getGraphics();
		
		// Default just copy ImageB into ImageA then copy to screen
	    gA.drawImage(ImageB, 0, 0, null);
	    gPan.drawImage(ImageA, 0, 0, imgPanel);
	}

}
