
package core;

import java.util.ArrayList;

public class AudioPlayer implements ThreadOnCompleteListener
{	
	/** List of Audio objects */
    private ArrayList<Audio> audioList; 
    
    /** Index of the currently playing audio clip */
    private int currentIndex = 0;
    
    /** Should be set to true if the audio list should loop, or false otherwise*/
    public boolean shouldLoop = false;
    
    public AudioPlayer() {
    	audioList = new ArrayList<Audio>();
    }

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
    	try {
    		Audio result = audioList.get(audioIndex);
    		return result;
    		
    	}catch(IndexOutOfBoundsException e) {
    		System.out.println("An audio clip does not exist at that index!");
    		return null;
		}
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
        audio.addListener(this);
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
        audioList.add(index, audio);
        audio.addListener(this);
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
    	try
    	{
	    	if(audioList.size() != 0)
	    	{
	    		audioList.remove(audio);
	    	}
    	} catch (ArrayIndexOutOfBoundsException e) 
    	{
    		System.out.println("No audio tracks to remove");
    	}
    }
    
    /**
     * removeAudioAtIndex will remove an Audio object from the list at the given index
     * 
     * @param index
     * @author austinvickers
     */
    
    public void removeAudioAtIndex(int index) {
    	Audio a = audioList.get(index);
    	removeAudio(a);
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
    
    //This method should be called when previewing an individual clip in the Creator
    /** Plays an audio clip at a specific index */
    public void playAudioClipAtIndex(int index) {

    	if(getAudio(index) != null) {
        	getAudio(index).Play();	
    	}
    	
    }

    /* This method should be called by the Viewer - if you spam this method,
    *  you will notice that it can lead to multiple clips playing at once on certain edge 
    *  cases. This would be a problem if this was attached to a button, but since it
    *  will only be called by the viewer, lets just make sure its called automatically
    */
    /** Plays all the audio clips in the list sequentially
     * @author austinvickers
     */
    public void playAudioClipsSequentially() {
    	//First reset the counter
    	currentIndex = 0;
    	
    	//Play the first clip
    	playAudioClipAtIndex(currentIndex);
    	
    	//NotifyOfThreadComplete will get notified when its time to play the next clip
    }

    /** This method is called whenever an audio thread finishes 
     *  @author austinvickers
     */
	@Override
	public void notifyOfThreadComplete(NotifyingThread thread) {
		
		System.out.println("Audio player was notified");
		
		if(currentIndex + 1 < audioList.size()) {
			currentIndex++;
			playAudioClipAtIndex(currentIndex);
		}
		else {
			if(shouldLoop) {
				currentIndex = 0;
				playAudioClipAtIndex(currentIndex);
			}
		}
		
		//Otherwise, stop playing clips
	}
	
}

