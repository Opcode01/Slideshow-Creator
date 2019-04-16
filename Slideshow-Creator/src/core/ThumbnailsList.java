/**
 * ThumbnailsList.java
 * Holds a list of thumbnails as well functions to add, remove, and swap
 * 
 * Slideshow Creator
 * Timothy Couch, Joseph Hoang, Fernando Palacios, Austin Vickers
 * CS 499 Senior Design with Dr. Rick Coleman
 * 2/17/19
 */

 package core;

 import java.util.ArrayList;
 import java.lang.String;

public class ThumbnailsList
{
    /**
     * List of thumbnail objects
     */
    private ArrayList<Thumbnail> thumbnails; 
    
    /**
     * Instantiate ArrayList instance of Thumbnails 
     */
    public ThumbnailsList()
    {
        thumbnails = new ArrayList<Thumbnail>();
    };
    

    /**
     * @return size of arraylist of thumbnail objects
     * 
     * @author Joe Hoang
     */
    public int getSize()
    {
        return thumbnails.size();
    }

    /**
     * @return arraylist of thumbnail objects 
     * 
     * @author Joe Hoang
     */
    public ArrayList<Thumbnail> getThumbnails()
    {
        return thumbnails;
    }


    /**
     * getThumbnail will return the thumbnail object at the specified index 
     * 
     * @param thumbnailIndex specified index to get Thumbnail from
     * 
     * @return Thumbnail object from thumbnails at given index
     * 
     * @author Joe Hoang
     */
    public Thumbnail getThumbnail(int thumbnailIndex)
    {
    	try
    	{
    		return thumbnails.get(thumbnailIndex);
    	} catch (ArrayIndexOutOfBoundsException e) 
    	{
    		System.out.println("Cannot get thumbnail:" + e.getMessage());
    		return null;
    	}
        
    }
    

    /**
     * addThumbnail will append given Thumbnail object to thumbnails arrayList
     * 
     * @param thumbnail thumbnail object to be added to end of thumbnails list
     * 
     * @author Joe Hoang
     */
    public void addThumbnail(Thumbnail thumbnail)
    {
        thumbnails.add(thumbnail);
    }
    
    /**
     * addThumbnail will append given Thumbnail object to thumbnails arrayList
     * overloaded to allow adding at specified index
     * 
     * @param thumbnail thumbnail object to be added thumbnails list at specified index
     * @param index specific to be added to
     * @author Joe Hoang
     */
    public void addThumbnail(Thumbnail thumbnail, int index)
    {
        thumbnails.add(index, thumbnail);;
    }

    /**
     * removeThumbnail will remove given thumbnail object from thumbnails arrayList
     * 
     * @param thumbnail Thumbnail object to be removed from thumbnails list
     * 
     * @author Joe Hoang
     */
    public void removeThumbnail(Thumbnail thumbnail)
    {
    	try
    	{
	    	if(thumbnails.size() != 0)
	    	{
	    		thumbnails.remove(thumbnail);
	    	}
    	} catch (ArrayIndexOutOfBoundsException e) 
    	{
    		System.out.println("Cannot remove from empty list.");
    	}
    }
    
    /**
     * removeThumbnail will remove given thumbnail object from thumbnails arrayList at specified index
     * 
     * @param index at which to remove thumbnail
     * 
     * @author Joe Hoang
     */
    public void removeThumbnail(int index)
    {
    	try
    	{
	    	if(thumbnails.size() != 0)
	    	{
	    		thumbnails.remove(index);
	    	}
    	} catch (ArrayIndexOutOfBoundsException e) 
    	{
    		System.out.println("Cannot remove from empty list.");
    	}
    }

    /**
     * swapThumbnails will swap value of two objects in ArrayList
     * 
     * @param thumbnail1 first thumbnail object index to be swapped 
     * 
     * @param thumbnail2 second thumbnail object index to be swapped
     * 
     * @author Joe Hoang
     */
    public void swapThumbnails(int indexThumbnail1, int indexThumbnail2)
    {
    	try
    	{
    		Thumbnail tempThumbnail = thumbnails.get(indexThumbnail1);
            thumbnails.set(indexThumbnail1, thumbnails.get(indexThumbnail2));
            thumbnails.set(indexThumbnail2, tempThumbnail);
    	} catch (ArrayIndexOutOfBoundsException e) 
    	{
    		System.out.println("Cannot swap thumbails" + e.getMessage());
    	}
        
    }

    /**
     * swapForward - function will swap thumbnail object in thumbnails with the object found at the next index
     * 
     * @param thumbnail object to be moved forward by one index
     * 
     * @return index of new location of thumbnail
     * 
     * @author Joe Hoang
     */
    public int swapForward(Thumbnail thumbnail)
    {
    	try
    	{
    		int indexThumbnail1 = thumbnails.indexOf(thumbnail);
            int indexThumbnail2 = indexThumbnail1 + 1;
            if(indexThumbnail2 != thumbnails.size())
            {
                swapThumbnails(indexThumbnail1, indexThumbnail2);
                return indexThumbnail2;
            }
            else
            {
                return -1; 
            }
    	} catch (ArrayIndexOutOfBoundsException e) 
    	{
    		System.out.println("Cannot swap thumbails at given indices" + e.getMessage());
    		return -1;
    	}
        
    }

     /**
     * swapBackground - function will swap thumbnail object in thumbnails with the object found at the previous index
     * 
     * @param thumbnail object to be moved backward by one index
     * 
     * @return index of new location of thumbnail
     * 
     * @author Joe Hoang
     */
    public int swapBackward(Thumbnail thumbnail)
    {
    	try
    	{
    		int indexThumbnail1 = thumbnails.indexOf(thumbnail);
            int indexThumbnail2 = indexThumbnail1 - 1;
            if(indexThumbnail2 != -1)
            {
                swapThumbnails(indexThumbnail1, indexThumbnail2);
                return indexThumbnail2;
            }
            else
            {
                return -1; 
            }
    	} catch (ArrayIndexOutOfBoundsException e) 
    	{
    		System.out.println("Cannot swap thumbails at given indices" + e.getMessage());
    		return -1;
    	}
        
    }

    /**
     * gets index of supplied thumbnail or -1 if not found
     * @param thumbnail thumbnail to search for
     * 
     * @return index of supplied thumbnail or -1 if not found
     */
    public int indexOf(Thumbnail thumbnail)
    {
        return thumbnails.indexOf(thumbnail);
    }

}
