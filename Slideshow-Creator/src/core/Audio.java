package core;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio 
{
	private String audioPath;
	private String audioName;
	
	/**
	 * return local path to audio file 
	 * @return
	 */
	public String getAudioPath()
	{
		return audioPath;
	}
	
	/**
	 * return name of audio file 
	 * @return
	 */
	public String getAudioName()
	{
		return audioName;
	}
	
	private float audioLength;
	
	/**
	 * Returns audio length in seconds 
	 * @return audioLength float value of audio file in seconds
	 * @author Joe Hoang 
	 */
	public float getAudioLength() 
	{
		return audioLength;
	}
	
	/**
	 *  get audio length from the audio file by checking the audio format and
	 *  retrieving the frame size and rate to divide the audio file length 
	 *  gives Audio length in seconds
	 */
	public void setAudioLength()
	{
		AudioInputStream audioInputStream;
		try {
			File audioFile = new File(audioPath);
			audioInputStream = AudioSystem.getAudioInputStream(audioFile);
			AudioFormat format = audioInputStream.getFormat();
		    long audioFileLength = audioFile.length();
		    int frameSize = format.getFrameSize();
		    float frameRate = format.getFrameRate();
		    audioLength = (audioFileLength / (frameSize * frameRate));
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	public Audio(String audioPath, String audioName)
	{
		this.audioPath = audioPath;
		this.audioName = audioName;
		setAudioLength();
	}
	
}
