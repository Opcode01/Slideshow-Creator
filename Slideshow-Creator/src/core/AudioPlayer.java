/**
 * AudioPlayer.java
 * Holds and plays an audio track
 * 
 * Slideshow Creator
 * Timothy Couch, Joseph Hoang, Fernando Palacios, Austin Vickers
 * CS 499 Senior Design with Dr. Rick Coleman
 * 2/26/19
 */

package core;

import java.util.ArrayList;

public class AudioPlayer 
{
	 /**
     * List of Audio objects
     */
    private ArrayList<Audio> audioList; 
    
    /**
     * Instantiate ArrayList instance of Audio 
     */
    public AudioPlayer()
    {
        audioList = new ArrayList<Audio>();
    };
    

    /**
     * @return size of arraylist of Audio objects
     * 
     * @author Joe Hoang
     */
    public int getSize()
    {
        return audioList.size();
    }

    /**
     * @return arraylist of Audio objects 
     * 
     * @author Joe Hoang
     */
    public ArrayList<Audio> getAudioList()
    {
        return audioList;
    }


    /**
     * getAudio will return the Audio object at the specified index 
     * 
     * @param AudioIndex specified index to get Audio from
     * 
     * @return Audio object from Audio at given index
     * 
     * @author Joe Hoang
     */
    public Audio getAudio(int audioIndex)
    {
        return audioList.get(audioIndex);
    }
    

    /**
     * addAudio will append given Audio object to Audio arrayList
     * 
     * @param Audio Audio object to be added to end of Audio list
     * 
     * @author Joe Hoang
     */
    public void addAudio(Audio audio)
    {
        audioList.add(audio);
    }
    
    /**
     * addAudio will append given Audio object to Audio arrayList
     * overloaded to allow adding at specified index
     * 
     * @param Audio Audio object to be added to end of Audio list
     * @param index specific to be added to
     * @author Joe Hoang
     */
    public void addAudio(Audio audio, int index)
    {
        audioList.add(index, audio);;
    }

    /**
     * removeAudio will remove given Audio object from Audio arrayList
     * 
     * @param Audio Audio object to be removed from Audio list
     * 
     * @author Joe Hoang
     */
    public void removeAudio(Audio audio)
    {
        audioList.remove(audio);
    }

    /**
     * swapAudio will swap value of two objects in ArrayList
     * 
     * @param Audio1 first Audio object index to be swapped 
     * 
     * @param Audio2 second Audio object index to be swapped
     * 
     * @author Joe Hoang
     */
    public void swapAudio(int indexAudio1, int indexAudio2)
    {
        
        Audio tempAudio = audioList.get(indexAudio1);
        audioList.set(indexAudio1, audioList.get(indexAudio2));
        audioList.set(indexAudio2, tempAudio);
    }

    /**
     * swapForward - function will swap Audio object in Audio with the object found at the next index
     * 
     * @param Audio object to be moved forward by one index
     * 
     * @return index of new location of Audio
     * 
     * @author Joe Hoang
     */
    public int swapForward(Audio audio)
    {
        int indexAudio1 = audioList.indexOf(audio);
        int indexAudio2 = indexAudio1 + 1;
        if(indexAudio2 != audioList.size())
        {
            swapAudio(indexAudio1, indexAudio2);
            return indexAudio2;
        }
        else
        {
            return -1; 
        }
    }

     /**
     * swapBackground - function will swap Audio object in Audio with the object found at the previous index
     * 
     * @param Audio object to be moved backward by one index
     * 
     * @return index of new location of Audio
     * 
     * @author Joe Hoang
     */
    public int swapBackward(Audio audio)
    {
        int indexAudio1 = audioList.indexOf(audio);
        int indexAudio2 = indexAudio1 - 1;
        if(indexAudio2 != -1)
        {
            swapAudio(indexAudio1, indexAudio2);
            return indexAudio2;
        }
        else
        {
            return -1; 
        }
    }

    /**
     * gets index of supplied Audio or -1 if not found
     * @param Audio Audio to search for
     * 
     * @return index of supplied Audio or -1 if not found
     */
    public int indexOf(Audio audio)
    {
        return audioList.indexOf(audio);
    }

}
