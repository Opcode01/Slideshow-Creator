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

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Thumbnail
{
	private static Coord2 thumbSize = new Coord2(290, 170);
	private static Coord2 timelineSize = new Coord2(260, 140);
	
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
    private Image imageTimeline;
    
    public Image getImageTimeline() {
    	return imageTimeline;
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
     * IMPORTANT: THIS METHOD IS ONLY FOR EXTERNAL FILES OF ABSOLUTE PATHS, NOT FOR PROJECT RESOURCES
     * Example: new Thumbnail(file.getAbsolutePath())
     * 
     * @author Timothy Couch
     */
    public Thumbnail(String imagePath)
    {
        this.imagePath = imagePath;
        imageRaw = loadImage(imagePath);
        imageThumb = resizeImageThumb(imageRaw);
        imageTimeline = resizeImageTimeline(imageRaw);
    }

    /**
     * Thumbnail - creates an image from given path
     * @param imagePath string file path to image (can be from current directory or full directory)
     * 
     * @author Timothy Couch
     * 
     * IMPORTANT: THIS METHOD IS ONLY FOR PROJECT RESOURCES IN THE res FOLDER, NOT EXTERNAL FILES
     * Example: new Thumbnail(getClass().getResource("/core/TransitionImages/crossFade.png")
     */
    public Thumbnail(URL imagePath)
    {
        this.imagePath = imagePath.getPath();
        imageRaw = loadImage(imagePath);
        imageThumb = resizeImageThumb(imageRaw);
    }

    /**
     * loadImage - loads up an image from the absolute path specified.
     * @param imagePath the absolute path from which to load the image
     * @return image if successfully loaded image, null otherwise
     * 
     * @author Timothy Couch
     * 
     * IMPORTANT: THIS METHOD IS ONLY FOR EXTERNAL FILES OF ABSOLUTE PATHS, NOT FOR PROJECT RESOURCES
     * Example: loadImage(file.getAbsolutePath())
     */
    public static Image loadImage(String imagePath)
    {
        File imFile = new File(imagePath);
        Image image = null;

        //load the image. Hopefully doesn't actually throw exceptions
        try
        {
            //load up the image if it exists - this hopefully prevents exceptions
            if (imFile != null && imFile.exists())
                image = ImageIO.read(imFile);
            else System.out.println("Image not loaded! " + imagePath + " does not exist");
        }
        catch (IOException e)
        {
            System.out.println("Exception: String Image not loaded! " + imagePath + "\n" + e);
        }

        return image;
    }

    /**
     * loadImage - loads up an image from the resource path
     * @param imagePath the resource path from which to load the image
     * @return image if successfully loaded image, null otherwise
     * 
     * @author Timothy Couch
     * 
     * IMPORTANT: THIS METHOD IS ONLY FOR PROJECT RESOURCES IN THE res FOLDER, NOT EXTERNAL FILES
     * Example: loadImage(getClass().getResource("/core/TransitionImages/crossFade.png")
     */
    public static Image loadImage(URL imagePath)
    {
        Image image = new ImageIcon(imagePath).getImage();

        if (image == null)
            System.out.println("URL Image not loaded! " + imagePath);

        return image;
    }
    
    /**
     * resizeImageTimeline - resizes the raw image to the proper size for timeline
     * @return resized image
     */
    private Image resizeImageTimeline(Image image)
    {
        int[] imageDims = getLetterBoxCoords(image.getWidth(null), image.getHeight(null), timelineSize.x, timelineSize.y);
        return image.getScaledInstance(imageDims[2], imageDims[3], Image.SCALE_DEFAULT);
    }
    
    /**
     * resizeImageThumb - resizes the raw image to the proper size for thumbnails
     * @return resized image
     * 
     * @author Timothy Couch
     */
    private Image resizeImageThumb(Image image)
    {
        int[] imageDims = getLetterBoxCoords(image.getWidth(null), image.getHeight(null), thumbSize.x, thumbSize.y);
        return image.getScaledInstance(imageDims[2], imageDims[3], Image.SCALE_DEFAULT);
    }
    
    /**
     * resizes image to fit the size of the specified container in letterbox style
     * @param container container to fit into
     * @param image image to resize
     * @return resized image in size of container
     */
    public static Image resizeImageContainer(Container container, Image image) {
        int[] imageDims = getLetterBoxCoords(image.getWidth(null), image.getHeight(null), container.getWidth(), container.getHeight());
        return toBufferedImage(image.getScaledInstance(imageDims[2], imageDims[3], Image.SCALE_DEFAULT));
    }
    
    /**
     * Converts a given Image into a BufferedImage
     *
     * @param img The Image to be converted
     * @return The converted BufferedImage
     * 
     * @author Sri Harsha Chilakapati https://stackoverflow.com/questions/13605248/java-converting-image-to-bufferedimage
     * All credits to Sri Harsha Chilakapati and editors
     */
    public static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

    
    /**
     * drawFill - draws the thumbnail image into the specified graphics in the container
     * @param g graphics to draw into (JLabel paintComponent method argument)
     * @param container the container in which to draw this image
     * @return the result of g.drawImage (false if still in process of drawing, true otherwise)
     * 
     * @author Timothy Couch
     */
    public boolean drawFill(Graphics g, Container container)
    {
	  Image displayImage = imageRaw;
	  
	  //calculate position and size to draw image with proper aspect ratio
	  int[] drawCoords = getLetterBoxCoords(displayImage, container);
	  
	  //draw image
	  return g.drawImage(displayImage, drawCoords[0], drawCoords[1], drawCoords[2], drawCoords[3], container);
    }

    
    /**
     * drawImageFill - draws the specified image into the specified graphics in the container
     * @param displayImage image to draw
     * @param g graphics to draw into (JLabel paintComponent method argument)
     * @param container the container in which to draw the image
     * @return the result of g.drawImage (false if still in process of drawing, true otherwise)
     * 
     * @author Timothy Couch
     */
    public static boolean drawImageFill(Image displayImage, Graphics g, Container container)
    {	  
	  //calculate position and size to draw image with proper aspect ratio
	  int[] drawCoords = getLetterBoxCoords(displayImage, container);
	  
	  //draw image
	  return g.drawImage(displayImage, drawCoords[0], drawCoords[1], drawCoords[2], drawCoords[3], new Color(0, 0, 0, 0), container);
    }
    
    /**
     * clone aka duplicate the given image
     * @param image the image to clone
     * @return new image
     * 
     * @author Timothy Couch
     * Most credits to Levster at https://stackoverflow.com/questions/8864275/how-to-clone-image
     */
    public static Image cloneImage(Image image) {
		BufferedImage imageCopy = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics g = imageCopy.createGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();
		
		return imageCopy;
    }
    
    /**
     * getLetterBoxCoords - returns the x, y, width, and height of the proper display position for the image in the container at a letterbox aspect ratio
     * @param image the image to draw
     * @param container the container to fit the image in
     * @return int[4]: {x, y, width, height} of new image dimensions
     * 
     * @author Timothy Couch
     */
    private static int[] getLetterBoxCoords(Image image, Container container)
    {    	
    	//get raw dimensions
		Dimension containerDim = container.getSize();
		double containerWidth = containerDim.getWidth();
		double containerHeight = containerDim.getHeight();
		int imageWidth = image.getWidth(null);
		int imageHeight = image.getHeight(null);
    	
    	return getLetterBoxCoords(imageWidth, imageHeight, containerWidth, containerHeight);
    }

    /**
     * getLetterBoxCoords - returns x, y, width, and height of the proper display position for the supplied "image" fitting into supplied "container"
     * @param imageWidth width of image to calculate
     * @param imageHeight height of image to calculate
     * @param containerWidth width of container to calculate
     * @param containerHeight height of container to calculate
     * @return int[4]: {x, y, width, height} of new image dimensions
     * 
     * @author Timothy Couch
     */
    private static int[] getLetterBoxCoords(int imageWidth, int imageHeight, double containerWidth, double containerHeight) {
        int[] dimensions = new int[4];

        // determine if borders need to be vertical or horizontal
        double imageAspect = (double) imageWidth / imageHeight;
        double containerAspect = containerWidth / containerHeight;

        // borders on top and bottom
        if (imageAspect > containerAspect) {
            // get multiplier to make image smaller
            double imageScale = containerWidth / imageWidth;

            // y is down some to make image in middle
            dimensions[1] = (int) Math.round(((containerHeight - imageHeight * imageScale) / 2));

            // make image smaller
            dimensions[2] = (int) Math.round(containerWidth);
            dimensions[3] = (int) Math.round(imageHeight * imageScale);
        } else// borders on left and right
        {
            // get multiplier to make image smaller
            double imageScale = containerHeight / imageHeight;

            // x is down some to make image in middle
            dimensions[0] = (int) Math.round(((containerWidth - imageWidth * imageScale) / 2));

            // make image smaller
            dimensions[2] = (int) Math.round(imageWidth * imageScale);
            dimensions[3] = (int) Math.round(containerHeight);
        }

        return dimensions;
    }

    /**
     * Checks if this thumbnail has the same image path as comparing thumbnail
     * @param o object to compare
     * 
     * @author Timothy Couch
     */
    @Override
    public boolean equals(Object o)
    {
        //thanks to GeeksforGeeks for general form https://www.geeksforgeeks.org/overriding-equals-method-in-java/
        if (o == this)
            return true;
        if (!(o instanceof Thumbnail))
            return false;
        
        Thumbnail t = (Thumbnail) o;
        return imagePath.equals(t.getImagePath());
    }
}
