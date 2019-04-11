package pkgImageTransitions.Transitions;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

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
		
		int numIterations = 15; //how many times to redraw
		int timeInc = (int)(time * 1000) / numIterations; // Milliseconds to pause each step
		
		//create an image A the size of the container
		BufferedImage contImageA = new BufferedImage(imgPanel.getWidth(), imgPanel.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Thumbnail.drawImageFillImage(ImageA, contImageA, SliderColor.dark_gray);
		
		//create an image B the size of the container with solid background
		BufferedImage contImageB = new BufferedImage(imgPanel.getWidth(), imgPanel.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Thumbnail.drawImageFillImage(ImageB, contImageB, SliderColor.dark_gray);
		
		//holds the original alpha values of contImageB
		float[] contImageBAlphas = new float[contImageB.getWidth() * contImageB.getHeight()];
		for (int i = 0; i < contImageB.getWidth() * contImageB.getHeight(); i++) {
			Color pixColor = new Color(contImageB.getRGB(i % contImageB.getWidth(), (int) (i / contImageB.getWidth())), true);
        	contImageBAlphas[i] = pixColor.getAlpha();
        }

		//draw transparent B onto A over and over again and repaint A
		float alphaInc = 1.0f / numIterations;//amount to increase B's alpha each step
		float bAlpha = alphaInc;//compounding amount of alpha for B
		for(int i=0; i < numIterations; i++)
		{
			if (isAborting())
				break;
			
			//make image b more transparent
			for (int h = 0; h < contImageB.getWidth(); h++) {
	            for (int j = 0; j < contImageB.getHeight(); j++) {                    
	                Color pixColor = new Color(contImageB.getRGB(h, j), true);
	                
	                if (pixColor.getAlpha() > 0) {
	                	pixColor = new Color(pixColor.getRed(), pixColor.getGreen(), pixColor.getBlue(), (int) (contImageBAlphas[h + j * contImageB.getWidth()] * bAlpha));
	                	contImageB.setRGB(h, j, pixColor.getRGB());
	                }
	            }
	        }
			bAlpha += alphaInc;//increment image B's alpha over time
			
			//draw A onto the screen
			gPan.drawImage(contImageA, 0, 0, imgPanel);
			
			//draw the transparent image B onto the screen darker every loop
			gPan.drawImage(contImageB, 0, 0, null);
			
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
