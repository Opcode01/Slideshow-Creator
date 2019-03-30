
package core;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

//=============================================================================
/** Class: AudioPlayer
*  Purpose: This class implements the audio file player object which runs
*  	in a separate thread.  Parts of this code were adapted from the project
*   JavaSound found on-line.
*   
*  Author: Dr. Rick Coleman
*  Date: January 2016
*/
//=============================================================================

public class AudioPlayer implements Runnable, LineListener
{	
	/** Name of the file to play */
	private String m_CurrentName;
	
	/** File to open and play */
	private File m_AudioFile = null;

	/** Current sound object - copied from JavaSound.Juke */
	private Object m_CurrentSound;

	/** The system sequencer used to play the files. */
	// This was adapted from Juke.java in JavaSound demo
    private Sequencer sequencer;

	/** Thread used by the audio file player */
	private Thread m_PlayerThread;

	// Some variables from Juke.java.  Not sure if they are really needed but
	//   we define them for now just to adapt the code.
    boolean midiEOM = false;
    boolean audioEOM = false;
    boolean bump = false;

    /** Flag if we are paused (for clips only???) */
    boolean m_bPaused = false;	

	//---------------------------------------------------
	// Default constructor
	//---------------------------------------------------
	public AudioPlayer(File audioFile)
	{
		m_AudioFile = audioFile;
		// Get the sequencer for this system and open it
        try 
        {
            sequencer = MidiSystem.getSequencer();

        }
        catch (Exception ex) 
        { 
        	ex.printStackTrace(); 
        	return; 
        }
        
        // Create the thread for this player
		m_PlayerThread = new Thread(this);
		m_PlayerThread.start();
		
	}
	
	//---------------------------------------------------
	// Run function required by the Runnable interface
	//---------------------------------------------------
    public void run()
    {
    	if(!loadAudioFile())
    		return;		// Oops! Couldn't load the file so terminate
    	playAudioFile();	// If all OK play the file
	}
    
	//---------------------------------------------------
	// Load the audio file
	//---------------------------------------------------
    private boolean loadAudioFile()
    {
    	System.out.println("In loadAudioFile.");
       if (m_AudioFile instanceof File) 
       {
        	m_CurrentName = m_AudioFile.getName();
            try 
            {
            	m_CurrentSound = AudioSystem.getAudioInputStream(m_AudioFile);
            } 
            catch(Exception e1) 
            {
            	try 
                { 
                  	System.out.println("loadAudioFile is creating a BufferedInputStream.");
                    FileInputStream is = new FileInputStream(m_AudioFile);
                    m_CurrentSound = new BufferedInputStream(is, 1024);
                } 
                catch (Exception e3) 
                { 
                	e3.printStackTrace(); 
                    m_CurrentSound = null;
                    return false;
                }
         
            }
       }

        // user pressed stop or changed tabs while loading
        if (sequencer == null) 
        {
        	m_CurrentSound = null;
            return false;
        } 

        if (m_CurrentSound instanceof AudioInputStream) 
        {
        	System.out.println("In if (m_CurrentSound instanceof AudioInputStream).");
          try 
           {
                AudioInputStream stream = (AudioInputStream) m_CurrentSound;
                AudioFormat format = stream.getFormat();

                /**
                 * we can't yet open the device for ALAW/ULAW playback,
                 * convert ALAW/ULAW to PCM
                 */
                if ((format.getEncoding() == AudioFormat.Encoding.ULAW) ||
                    (format.getEncoding() == AudioFormat.Encoding.ALAW)) 
                {
                    AudioFormat tmp = new AudioFormat(
                                              AudioFormat.Encoding.PCM_SIGNED, 
                                              format.getSampleRate(),
                                              format.getSampleSizeInBits() * 2,
                                              format.getChannels(),
                                              format.getFrameSize() * 2,
                                              format.getFrameRate(),
                                              true);
                    stream = AudioSystem.getAudioInputStream(tmp, stream);
                    format = tmp;
                }
                DataLine.Info info = new DataLine.Info(
                                          Clip.class, 
                                          stream.getFormat(), 
                                          ((int) stream.getFrameLength() *
                                              format.getFrameSize()));

                Clip clip = (Clip) AudioSystem.getLine(info);
                clip.addLineListener(this);
                clip.open(stream);
                m_CurrentSound = clip;
            } 
           catch (Exception ex) 
           { 
        	   ex.printStackTrace(); 
        	   m_CurrentSound = null;
        	   return false;
           }
        } 
        // Note:  Tried .aif, .au, .mid, and .wav files and none of them are loaded as a Sequence or BufferedInputStream
        else if (m_CurrentSound instanceof Sequence || m_CurrentSound instanceof BufferedInputStream) 
        {
        	System.out.println("In if (m_CurrentSound instanceof Sequence or BufferedInputStream).");
           try 
            {
                sequencer.open();
                if (m_CurrentSound instanceof Sequence) 
                {
                    sequencer.setSequence((Sequence) m_CurrentSound);
                }
                else 
                {
                    sequencer.setSequence((BufferedInputStream) m_CurrentSound);
                }

            } 
            catch (InvalidMidiDataException imde) 
            { 
            	System.out.println("Unsupported audio file.");
            	m_CurrentSound = null;
            	return false;
            } 
            catch (Exception ex) 
            { 
            	ex.printStackTrace(); 
            	m_CurrentSound = null;
            	return false;
            }
        }

        return true;  // Assume if we get here everything is OK so start playing
    }
    
	//----------------------------------------------------------------------
	/** Play the file. */
	//----------------------------------------------------------------------
	public void playAudioFile()
	{
        midiEOM = audioEOM = bump = false;
        if (m_CurrentSound instanceof Sequence || 
        		m_CurrentSound instanceof BufferedInputStream && m_PlayerThread != null) 
        {
        	System.out.println("playAudioFile starting the sequencer.");
            sequencer.start();
            while (!midiEOM && m_PlayerThread != null && !bump) 
            {
                try 
                { 
                	m_PlayerThread.sleep(99); 
                } 
                catch (Exception e) 
                {
                	break;
                }
            }
        	System.out.println("playAudioFile stopping the sequencer.");
            sequencer.stop();
            sequencer.close();
        } 
        else if (m_CurrentSound instanceof Clip && m_PlayerThread != null) 
        {
        	System.out.println("playAudioFile is creating a clip.");
            Clip clip = (Clip) m_CurrentSound;
            clip.start();
            try 
            { 
            	m_PlayerThread.sleep(99); 
            } 
            catch (Exception e) 
            { }
            while ((m_bPaused || clip.isActive()) && m_PlayerThread != null && !bump) 
            {
                try 
                { 
                	m_PlayerThread.sleep(99); 
                } 
                catch (Exception e) 
                {
                	break;
                }
            }
        	System.out.println("playAudioFile is stopping a clip.");
            clip.stop();
            clip.close();
        }
        m_CurrentSound = null;

	}
	
	//----------------------------------------------------------------
	/** Pause playing the audio file.  Called by the pause button. */
	//----------------------------------------------------------------
	public void pausePlaying()
	{
		m_bPaused = true;
        if (m_CurrentSound instanceof Clip) 
        {
            ((Clip) m_CurrentSound).stop();
        } 
        else if (m_CurrentSound instanceof Sequence || m_CurrentSound instanceof BufferedInputStream) 
        {
            sequencer.stop();
        }
	}
	
	//----------------------------------------------------------------
	/** Resume playing the audio file.  Called by the play button. */
	//----------------------------------------------------------------
	public void resumePlaying()
	{		
		m_bPaused = false;
        if (m_CurrentSound instanceof Clip) 
        {
            ((Clip) m_CurrentSound).start();
        } 
        else if (m_CurrentSound instanceof Sequence || m_CurrentSound instanceof BufferedInputStream) 
        {
            sequencer.start();
        }
	}
	
	//----------------------------------------------------------------
	/** Stop playing the audio file.  Called by the stop button. */
	//----------------------------------------------------------------
	public void stopPlaying()
	{
        if (m_CurrentSound instanceof Sequence || 
        		m_CurrentSound instanceof BufferedInputStream && m_PlayerThread != null) 
        {
            sequencer.stop();
            sequencer.close();
        } 
        else if (m_CurrentSound instanceof Clip && m_PlayerThread != null) 
        {
            ((Clip) m_CurrentSound).stop();
            ((Clip) m_CurrentSound).close();
        }
        m_CurrentSound = null;
    }
	
	//----------------------------------------------------------------
	/** Define the function required for a linelistener interface */
	// It appears that this function is only needed in the case of
	//   playing a clip.  But it is here just to get thing running
	//----------------------------------------------------------------
    public void update(LineEvent event) 
    {
    	System.out.println("update called.");
        if (event.getType() == LineEvent.Type.STOP && !m_bPaused) 
        { 
            audioEOM = true;
        }
    }

}
