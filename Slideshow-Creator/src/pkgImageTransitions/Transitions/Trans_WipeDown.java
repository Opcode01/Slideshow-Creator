package pkgImageTransitions.Transitions;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import pkgImageTransitions.ColemanTransition;

public class Trans_WipeDown extends ColemanTransition
{
	
	//---------------------------------------------------
	/** Perform the transition from one image to another */
	// Args:  
	//  imgPanel - Panel the images are drawn into
	//	ImageA - Current Image on the screen
	//  ImageB - Image to transition to
	//  time - Number of seconds to take to do this transition
	// Note both off screen BufferedImages have already been
	// scaled to exactly fit in the area of the imgPanel
	// Basic algorithm:
	//   For each iteration
	//      Copy from B iterationIndex * incX of B onto the screen overwriting Image A that is there
	//	        Sections of B are drawn from top to bottom
	//---------------------------------------------------------
	public void DrawImageTransition(JPanel imgPanel, BufferedImage ImageA, BufferedImage ImageB, double time)
	{
		Graphics gPan = imgPanel.getGraphics();
		Graphics gA = ImageA.getGraphics();
		
		// Dimension holders
		int bY1, bY2;		// Dimensions for imageA
		int imgWidth, imgHeight;
		int incY;					// Y increment each time
		int numIterations = 50;		// Number of iterations in the sweep
		int timeInc;				// Milliseconds to pause each time
		timeInc = (int)(time * 1000) / numIterations;
		
		imgWidth = imgPanel.getWidth();
		imgHeight = imgPanel.getHeight();
		incY = imgHeight / numIterations;		// Do 1/20 each time to start
		
		// Initialize the dimensions for section of ImageB to draw into ImageA
		bY1 = 0;
		bY2 = incY;

        // Draw the scaled current image if necessary
		gPan.drawImage(ImageA, 0, 0, imgPanel);

		// Draw image A
		for(int i=0; i<numIterations; i++)
		{
			if (isAborting())
				break;
			// Draw part of B into A
			gA.drawImage(ImageB, 0, bY1, imgWidth, bY2, 0, bY1, imgWidth, bY2, null); // Draw portion of ImageB into ImageA
			gPan.drawImage(ImageA, 0,0, imgPanel); // Copy ImageA into panel
			bY1 = bY2;
			bY2 += incY;  // Take a bigger section next time
			// Pause a bit
			try 
			{
			    Thread.sleep(timeInc);                 
			} 
			catch(InterruptedException ex) 
			{
			    Thread.currentThread().interrupt();
			} 
		}
		if (!isAborting())
		{
			// Move m_NextImage into m_CurrentImage for next time -  May not need this
			ImageA.getGraphics().drawImage(ImageB, 0, 0, imgPanel);
			// And one final draw to the panel to be sure it's all there
			gPan.drawImage(ImageA, 0,0, imgPanel); 
		}
	}

}
