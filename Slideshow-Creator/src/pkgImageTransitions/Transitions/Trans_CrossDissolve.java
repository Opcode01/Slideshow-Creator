package pkgImageTransitions.Transitions;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

import javax.swing.JPanel;

import core.SliderColor;
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
		
		//create an image A the size of the container
		BufferedImage contImageA = new BufferedImage(imgPanel.getWidth(), imgPanel.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Thumbnail.drawImageFillImage(ImageA, contImageA);
		
		//create an image B the size of the container with solid background
		BufferedImage contImageB = new BufferedImage(imgPanel.getWidth(), imgPanel.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Thumbnail.drawImageFillImage(ImageB, contImageB, SliderColor.dark_gray);
		
		//make image b transparent
		float alphaInc = 1.0f / numIterations;
		/*float[] scales = { 1.0f, 1.0f, 1.0f, alphaInc};
		float[] offsets = new float[4];
		RescaleOp rop = new RescaleOp(scales, offsets, null);
		rop.filter(contImageB, contImageB);*/
		for (int i = 0; i < contImageB.getWidth(); i++) {
            for (int j = 0; j < contImageB.getHeight(); j++) {                    
                Color pixColor = new Color(contImageB.getRGB(i, j), true);
                
                if (pixColor.getAlpha() > 0) {
                	pixColor = new Color(pixColor.getRed(), pixColor.getGreen(), pixColor.getBlue(), (int) (pixColor.getAlpha() * alphaInc));
                	contImageB.setRGB(i, j, pixColor.getRGB());
                }
            }
        }

        // Draw the scaled current image
		gPan.drawImage(contImageA, 0, 0, imgPanel);
		//Thumbnail.drawImageFill(contImageA, gPan, imgPanel);
		
		// Draw image A -- appears we need to do this fade longer
		// Each time we redraw ImageB_ARGB over ImageA we add just a bit more
		Graphics2D contImageAGraphics = contImageA.createGraphics();
		for(int i=0; i < numIterations; i++)
		{
			if (isAborting())
				break;
			// Draw B over A. Note: Can't do the first draw directly into the screen panel
			//	because that drawImage only works with BufferedImages as the destination.
			
			//draw the transparent image B onto image A to make it thicker and thicker each loop
			contImageAGraphics.drawImage(contImageB, 0, 0, null);
			System.out.println("Drawing " + i);
			
			//draw A onto the screen
			gPan.drawImage(contImageA, 0, 0, imgPanel);
			
			//pause for a bit
			try 
			{
			    Thread.sleep((int) (timeInc * .9));
			} 
			catch(InterruptedException ex) 
			{
			    Thread.currentThread().interrupt();
			}
		}	
		if (!isAborting())
		{
			// one final draw to the panel to be sure it's all there
			Thumbnail.drawImageFill(ImageB, gPan, imgPanel);
		}
	}
	
}
