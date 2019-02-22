/**
 * Thumbnail.java
 * Holds and resizes as needed an image for slideshow
 * 
 * Slideshow Creator
 * Timothy Couch, Joseph Hoang, Fernando Palacios, Austin Vickers
 * CS 499 Senior Design with Dr. Rick Coleman
 * 2/13/19
 */

package core;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Thumbnail
{
	private static Coord2 thumbSize = new Coord2(100, 75);
	
    /**
     * Publicly read-only path to image
     */
    private String imagePath;

    public String getImagePath() {
        return imagePath;
    }

    /**
     * Publicly read-only image itself
     */
    private Image imageRaw;

    public Image getImageRaw() {
        return imageRaw;
    }
    
    /**
     * Publicly read-only image resized to thumbnail size
     */
    private Image imageThumb;
    
    public Image getImageThumb() {
    	return imageThumb;
    }

    /**
     * Thumbnail - creates an image from given path
     * @param imagePath string file path to image (can be from current directory or full directory)
     * 
     * @author Timothy Couch
     */
    public Thumbnail(String imagePath)
    {
        this.imagePath = imagePath;
        imageRaw = loadImage(imagePath);
        imageThumb = resizeImageThumb(imageRaw);
    }

    /**
     * loadImage - loads up an image from the path
     * @param imagePath the path from which to load the image
     * 
     * @return image if successfully loaded image, null otherwise
     */
    private Image loadImage(String imagePath)
    {
        File imFile = new File(imagePath);
        Image image = null;

        //load the image. Hopefully doesn't actually throw exceptions
        try
        {
            //load up the image if it exists - this hopefully prevents exceptions
            if (imFile != null && imFile.exists())
                image = ImageIO.read(imFile);
            else System.out.println("Raw image not loaded! " + imagePath + " does not exist");
        }
        catch (IOException e)
        {
            System.out.println("Exception: Raw image not loaded! " + imagePath + "\n" + e);
        }

        return image;
    }
    
    /**
     * resizeImageThumb - resizes the raw image to the proper size for thumbnails
     * @return resized image
     */
    private Image resizeImageThumb(Image image)
    {
    	//x is negative to preserve aspect ratio
    	return image.getScaledInstance(-thumbSize.x, thumbSize.y, Image.SCALE_DEFAULT);
    }
}
