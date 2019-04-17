/**
 * Trans_WipeRight.java
 * Wipes from left to right from one image to another
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

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.SliderColor;
import core.Thumbnail;
import pkgImageTransitions.ColemanTransition;

public class Trans_WipeRight extends ColemanTransition
{

	/** How many time to fade between the images per second. Updates on the fly based on machine performance */
	protected static int fps = 50;
	
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
	//	        Sections of B are drawn from left to right
	//---------------------------------------------------------
	public void DrawImageTransition(JPanel imgPanel, BufferedImage ImageA, BufferedImage ImageB, double time)
	{
		Graphics gPan = imgPanel.getGraphics();
		
		// Dimension holders
		int imgWidth, imgHeight;

		int numIterations = (int) (fps * time);
		int timeMillis = (int) (time * 1000);
		int timeInc = timeMillis / numIterations; // Milliseconds to pause each step
		
		imgWidth = imgPanel.getWidth();
		imgHeight = imgPanel.getHeight();
		
		//create an image A the size of the container
		BufferedImage contImageA = new BufferedImage(imgPanel.getWidth(), imgPanel.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Thumbnail.drawImageFillImage(ImageA, contImageA, SliderColor.dark_gray);
		Graphics gA = contImageA.getGraphics();
		
		//create an image B the size of the container with solid background
		BufferedImage contImageB = new BufferedImage(imgPanel.getWidth(), imgPanel.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Thumbnail.drawImageFillImage(ImageB, contImageB, SliderColor.dark_gray);

		// Draw image A
		int avgElapsedTime = 0;//how much time each fade step takes on average
		for(int i=0; i<numIterations; i++)
		{
			if (isAborting())
				break;
			
			long startTime = System.currentTimeMillis();
			
			int startDraw = (int) ((float) imgWidth / numIterations * i);
			int endDraw = (int) ((float) imgWidth / numIterations * (i + 1));
			
			// Draw part of B into A
			gA.drawImage(contImageB, startDraw, 0, endDraw, imgHeight, startDraw, 0, endDraw, imgHeight, null); // Draw portion of ImageB into ImageA
			
			//Draw A onto the panel
			imgPanel.removeAll();
			imgPanel.add(new JLabel(new ImageIcon(contImageA)));
			imgPanel.revalidate();
			
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
			
			int prevFps = fps;

			//set fps to how many frames of the average elapsed time will fit into one second
			if (avgElapsedTime != 0)
				fps = Math.min(Math.max(Math.round(1000 / avgElapsedTime), 5), 60);//limit framerate to between 5 and 60 fps
			else fps = 60;//so fast that it didn't even take a full millisecond on average
			
			//System.out.println("timeInc: " + timeInc + " avgElapsedTime: " + avgElapsedTime + "\nprevFps: " + prevFps + " fps: " + fps);
		}
	}

}
