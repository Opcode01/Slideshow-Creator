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
        return thumbnails.get(thumbnailIndex);
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
     * removeThumbnail will remove given thumbnail object from thumbnails arrayList
     * 
     * @param thumbnail Thumbnail object to be removed from thumbnails list
     * 
     * @author Joe Hoang
     */
    public void removeThumbnail(Thumbnail thumbnail)
    {
        thumbnails.remove(thumbnail);
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
        
        Thumbnail tempThumbnail = thumbnails.get(indexThumbnail1);
        thumbnails.set(indexThumbnail1, thumbnails.get(indexThumbnail2));
        thumbnails.set(indexThumbnail2, tempThumbnail);
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
    }

}
