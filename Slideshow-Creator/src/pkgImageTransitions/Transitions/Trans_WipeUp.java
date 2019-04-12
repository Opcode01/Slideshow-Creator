/**
 * Trans_WipeUp.java
 * Wipes up from bottom from one image to another
 * Original Author: R Coleman
 * Modified by Timothy Couch
 * 
 * Slideshow Creator
 * Timothy Couch, Joseph Hoang, Fernando Palacios, Austin Vickers
 * CS 499 Senior Design with Dr. Rick Coleman
 * 4/11/19
 */

package pkgImageTransitions.Transitions;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import core.SliderColor;
import core.Thumbnail;
import pkgImageTransitions.ColemanTransition;

public class Trans_WipeUp extends ColemanTransition
{
	
	/** How many time to fade between the images. Updates on the fly based on machine performance */
	protected static int numIterations = 50;

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
	//	        Sections of B are drawn from bottom to top
	//---------------------------------------------------------
	public void DrawImageTransition(JPanel imgPanel, BufferedImage ImageA, BufferedImage ImageB, double time)
	{
		Graphics gPan = imgPanel.getGraphics();
		
		// Dimension holders
		int bY1, bY2;		// Dimensions for imageA
		int imgWidth, imgHeight;
		int incY;					// Y increment each time
		
		int timeSecs = (int) (time * 1000);
		int timeInc = timeSecs / numIterations; // Milliseconds to pause each step
		
		//create an image A the size of the container
		BufferedImage contImageA = new BufferedImage(imgPanel.getWidth(), imgPanel.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Thumbnail.drawImageFillImage(ImageA, contImageA, SliderColor.dark_gray);
		Graphics gA = contImageA.getGraphics();
		
		//create an image B the size of the container with solid background
		BufferedImage contImageB = new BufferedImage(imgPanel.getWidth(), imgPanel.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Thumbnail.drawImageFillImage(ImageB, contImageB, SliderColor.dark_gray);
		
		imgWidth = imgPanel.getWidth();
		imgHeight = imgPanel.getHeight();
		incY = imgHeight / numIterations;		// Do 1/20 each time to start
		
		// Initialize the dimensions for section of ImageB to draw into ImageA
		bY1 = imgHeight - incY;
		bY2 = imgHeight;

		// Draw image A
		int avgElapsedTime = 0;//how much time each fade step takes on average
		for(int i=0; i<numIterations; i++)
		{
			if (isAborting())
				break;
			
			long startTime = System.currentTimeMillis();
			
			// Draw part of B into A
			gA.drawImage(contImageB, 0, bY1, imgWidth, bY2, 0, bY1, imgWidth, bY2, null); // Draw portion of ImageB into ImageA
			gPan.drawImage(contImageA, 0, 0, imgPanel);
			bY2 = bY1;
			bY1 -= incY;  // Take a bigger section next time
			// Pause a bit
			try 
			{
				int elapsedTime = (int) (System.currentTimeMillis() - startTime);
				avgElapsedTime += elapsedTime;
			    Thread.sleep(Math.max(timeInc - elapsedTime, 0));
			} 
			catch(InterruptedException ex) 
			{
			    Thread.currentThread().interrupt();
			} 
		}
		
		if (!isAborting())
		{
			//adjust number of iterations to get a proper transition for next time
			avgElapsedTime /= numIterations;
			
			int prevNumIterations = numIterations;
			
			numIterations = Math.min(Math.max(Math.round(timeSecs / avgElapsedTime), 5), 60);//limit framerate to between 5 and 60 fps
			
			System.out.println("timeInc: " + timeInc + " avgElapsedTime: " + avgElapsedTime + "\nprevNumIterations: " + prevNumIterations + " numIterations: " + numIterations);
		}
	}
	

}
