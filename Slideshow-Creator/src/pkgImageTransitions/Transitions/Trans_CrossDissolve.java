package pkgImageTransitions.Transitions;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

import javax.swing.JPanel;

import core.Thumbnail;
import pkgImageTransitions.ColemanTransition;

public class Trans_CrossDissolve extends ColemanTransition
{
	//---------------------------------------------------
	// Default constructor
	//---------------------------------------------------
	public Trans_CrossDissolve()
	{
		
	}
	
	//---------------------------------------------------
	/** Perform the transition from one image to another */
	// Args:  
	//  imgPanel - Panel the images are drawn into
	//	ImageA - Current Image on the screen
	//  ImageB - Image to transition to
	//  time - Number of seconds to take to do this transition
	// Note both off screen BufferedImages have already been
	// scaled to exactly fit in the area of the imgPanel
	// Cross dissolves need to be more gradual, i.e. more
	//   redraws with a smaller increment on the alpha
	//---------------------------------------------------------
	public void DrawImageTransition(JPanel imgPanel, BufferedImage ImageA, BufferedImage ImageB, double time)
	{
		Graphics gPan = imgPanel.getGraphics();
		Graphics2D gA = ImageA.createGraphics();
		
		int numIterations = 15;
		int timeInc;				// Milliseconds to pause each time
		timeInc = (int)(time * 1000) / numIterations;
		// Create a BufferedImage ARGB to hold the image to overlay
		BufferedImage ImageB_ARGB = new BufferedImage(ImageB.getWidth(), ImageB.getHeight(), BufferedImage.TYPE_INT_ARGB);
		// Draw ImageB into ImageB_ARGB
		ImageB_ARGB.getGraphics().drawImage(ImageB, 0, 0, null);
		// Set up the initial fade data
		// Create a rescale filter op 
		float alphaInc = .2f;//1 / numIterations;//0.20f;
		float imageBAlpha = alphaInc;

        // Draw the scaled current image
		Thumbnail.drawImageFill(ImageA, gPan, imgPanel);

		// Draw image A -- appears we need to do this fade longer
		// Each time we redraw ImageB_ARGB over ImageA we add just a bit more
		for(int i=0; i < numIterations; i++)
		{
			if (isAborting())
				break;
			// Draw B over A. Note: Can't do the first draw directly into the screen panel
			//	because that drawImage only works with BufferedImages as the destination.
			
			//set up transparent image
			float[] scales = { 1.0f, 1.0f, 1.0f, imageBAlpha};
			float[] offsets = new float[4];
			RescaleOp rop = new RescaleOp(scales, offsets, null);
			//filter image B to be transparent
			BufferedImage transparentB = rop.filter(ImageB_ARGB, null);
			
			//draw the transparent image over and over again
			Thumbnail.drawImageFill(transparentB, gPan, imgPanel);
			
			//gA.drawImage(ImageB_ARGB, rop, 0, 0); // Draw portion of ImageB into ImageA
			
			
			
			//gPan.drawImage(ImageA, 0,0, imgPanel); // Copy ImageA into panel
			
			//pause for a bit
			try 
			{
			    Thread.sleep((int) (timeInc * .9));
			} 
			catch(InterruptedException ex) 
			{
			    Thread.currentThread().interrupt();
			}
			
			imageBAlpha += alphaInc;
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
