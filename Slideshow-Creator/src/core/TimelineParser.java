/**
 * TimelineParser.java
 * Class that processes slideshow file and returns a timeline
 * 
 * Slideshow Creator
 * Timothy Couch, Joseph Hoang, Fernando Palacios, Austin Vickers
 * CS 499 Senior Design with Dr. Rick Coleman
 * 3/1/19
 */
package core;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import creator.WarningPane;

import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;

public class TimelineParser 
{
	/**saves last file path to save to*/
	private static String lastDirPath; 
	
	/**return the value of lastDirPath*/
	public static String getLastDirPath()
	{
		return lastDirPath;
	}
	
	/**set last dir path*/
	public static void setLastDirPath(String dirPath)
	{
		lastDirPath = dirPath;
	}
	
	/**Has the user chosen a directory already?*/
	private static boolean hasSavedOnce = false;
	
	/**return the value of hasSavedOnce*/
	public static boolean getHasSavedOnce()
	{
		return hasSavedOnce;
	}
	
	/**set has saved once*/
	public static void setHasSavedOnce(boolean saved)
	{
		hasSavedOnce = saved;
	}
	
	private static boolean resetIndexNeeded = false; 
	
	/**
	 * Print out settings and information related 
	 * to timeline to a JSON file
	 * 
	 * @param slideshowName path and name to output file 
	 * 
	 * @author Joe Hoang
	 */
	public static void ExportTimeline(String slideshowPath) throws FileNotFoundException
	{
		try
		{
			JSONObject out = new JSONObject();
			Timeline output = SceneHandler.singleton.getTimeline();
			out.put("isLoopingSlides", output.timelineSettings.isLoopingSlides);
			out.put("isLoopingAudio", output.timelineSettings.isLoopingAudio);
			out.put("isManual", output.timelineSettings.isManual);
			out.put("slideDuration", output.timelineSettings.slideDuration);
			out.put("parentDir", SceneHandler.singleton.getDirectory());
			
			JSONArray thumbnails = new JSONArray();
			ThumbnailsList thumbsList = output.thumbnailsList;
			for(Thumbnail t : thumbsList.getThumbnails())
			{
				JSONObject thumbnail = new JSONObject();
				thumbnail.put("index", thumbsList.indexOf(t));
				thumbnail.put("path", t.getImagePath());
				thumbnails.add(thumbnail);
			}
			
			JSONArray transitions = new JSONArray();
			TransitionsList transList = output.transitionsList;
			for(Transition t : transList.getTransitions())
			{
				JSONObject transition = new JSONObject();
				transition.put("index", transList.indexOf(t));
				transition.put("length", t.getTransitionLength());
				transition.put("type", t.getTransitionType().toString());
				transitions.add(transition);
			}
			
			JSONArray audioList = new JSONArray();
			AudioPlayer audioPlayer = output.audioPlayer;
			for (Audio a : audioPlayer.getAudioList())
			{
				JSONObject audio = new JSONObject();
				audio.put("path", a.getAudioPath());
				audio.put("index", audioPlayer.indexOf(a));
				audioList.add(audio);
				
			}
			
			out.put("thumbnails", thumbnails);
			out.put("transitions", transitions);
			out.put("audio", audioList);
		
			FileWriter file = new FileWriter(slideshowPath + ".sl", false);
			file.write(out.toJSONString());
			file.flush();
			file.close();
		} catch (FileNotFoundException fnfe) {
		// TODO Auto-generated catch block
		//fnfe.printStackTrace();
			throw fnfe;
		} catch (IOException e) {
			//e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Timeline Export Error:" + e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param JSONPath path to slider file to be read in
	 * @return boolean value based on whether the file can be read by parser or not
	 * @throws FileNotFoundException
	 * @throws IOException
	 * 
	 * Validates if file is JSON by attempting to parsing it. If a parse exception is thrown,
	 * the function will return false.
	 * 
	 * @author Joe Hoang
	 */
	public static boolean isJSONValid(String JSONPath) throws FileNotFoundException, IOException 
	{
		JSONParser parser = new JSONParser();
		try
		{
			Object input = parser.parse(new FileReader(JSONPath));
			return true; 
			
		} catch (ParseException pe)
		{
			System.out.println("Not valid JSON");
			return false;
		}
	}
	/**
	 * given path to JSON file, import a timeline with saved settings
	 * @param JSONpath to JSON file
	 * @return instance of timeline
	 * 
	 * @author Joe Hoang 
	 */
	public static Timeline ImportTimeline(String JSONpath) throws Exception, NullPointerException, FileNotFoundException, ParseException, IOException
	{
		Timeline importedTimeline = new Timeline();
		JSONParser parser = new JSONParser();
		try 
		{
			if(isJSONValid(JSONpath))
			{
				Object input = parser.parse(new FileReader(JSONpath));
				
				JSONObject in = (JSONObject) input;
			
				if(new File((String) in.get("parentDir")).isDirectory())
				{
					
					JSONArray transitions = (JSONArray) in.get("transitions");
					//Iterator<JSONObject> transition = transitions.iterator();
					for(int i = 0; i < transitions.size(); i++)
					{
						JSONObject tsn = (JSONObject) transitions.get(i);
						long index = (Long) tsn.get("index");
						double length = (double) tsn.get("length");
						TransitionType type = TransitionType.valueOf(tsn.get("type").toString());
						Transition newTrans = new Transition(type, length);
						importedTimeline.transitionsList.addTransition(newTrans, (int) index);
					}
					
					JSONArray thumbnails = (JSONArray) in.get("thumbnails");
					System.out.println(thumbnails.size());
					
					for(int i = 0; i < thumbnails.size(); i++)
					{
						JSONObject tbl = (JSONObject) thumbnails.get(i);
						//JSON exports as long with type cast error
						//grab as long then cast to int 
						long index = (Long) tbl.get("index");
						String path = (String) tbl.get("path");
						try
						{
							//System.out.println(index);
							//System.out.println(path);
							if(new File(path).exists())
							{
								Thumbnail newThumb = new Thumbnail(path);
								importedTimeline.thumbnailsList.addThumbnail(newThumb,(int)index);
							}
							else
							{
								JFrame parent = SceneHandler.singleton.getMainFrame();
						    	Coord2 point = new Coord2(
						    			parent.getX() + parent.getSize().width/2,
						    			parent.getY() + parent.getSize().height/2
						    			);
						    	
								WarningPane p = new WarningPane(
						    			parent,
						    			"Warning - Image file missing",
						    			"Cannot find image: " + path,
						    			point, 
						    			new Dimension(400, 190));
								importedTimeline.transitionsList.removeTransition((int) index);
								resetIndexNeeded = true;
						}
						}catch (IndexOutOfBoundsException iobe) {
							System.out.println(index);
							Thumbnail newThumb = new Thumbnail(path);
							//shift thumbnail down one index
							importedTimeline.thumbnailsList.addThumbnail(newThumb,(int)index - 1);
							//importedTimeline.transitionsList.removeTransition((int) index);
							//System.out.println(importedTimeline.transitionsList.getTransition((int)index -1).getTransitionType());
							continue;
						}
					}
					
					
					JSONArray audioList = (JSONArray) in.get("audio");
					//Iterator<JSONObject> transition = transitions.iterator();
					for(int i = 0; i < audioList.size(); i++)
					{
						JSONObject audio = (JSONObject) audioList.get(i);
						long index = (Long) audio.get("index");
						String path = (String) audio.get("path");
						File audioFile = new File(path);
						Audio newAudio = new Audio(audioFile);
						importedTimeline.audioPlayer.addAudio(newAudio, (int) index);
					}
					
					boolean loopingSlides = (boolean) in.get("isLoopingSlides");
					boolean isManual = (boolean) in.get("isManual");
					boolean isLoopingAudio = (boolean) in.get("isLoopingAudio");
					long slideDuration = (Long) in.get("slideDuration");
					Settings importedSettings = new Settings(loopingSlides, 
															isLoopingAudio, 
															isManual,
															(int)slideDuration);
					
					importedTimeline.setDirectory((String) in.get("parentDir"));
					
					importedTimeline.timelineSettings = importedSettings;
					
					/*
					if (importedTimeline != null)
					{
						System.out.println("Timeline Imported:");
						//test values
						for(Thumbnail t : importedTimeline.thumbnailsList.getThumbnails())
						{
							System.out.println(t.getImagePath());
							System.out.println(importedTimeline.transitionsList.getTransition(0).getTransitionLength());
						}
					}
					else System.out.println("Error: Timeline not imported!");
					*/
					
					//set last directory path to save
					lastDirPath = JSONpath.replace(".sl", "");
					hasSavedOnce = true;
					
					/*
					if(resetIndexNeeded)
					{
						resetListIndex();
					}
					*/
					
					return importedTimeline;
					
				}//end if 
				else
				{
					System.out.println("Slider file cannot be loaded. Filepaths do not exist.");
					return null;
				}
			} 
			else
			{
				System.out.println("Cannot load slider file.");
				return null;
			}
		} catch (FileNotFoundException fnfe) {
			// TODO Auto-generated catch block
			//fnfe.printStackTrace();
			JFrame parent = SceneHandler.singleton.getMainFrame();
	    	Coord2 point = new Coord2(
	    			parent.getX() + parent.getSize().width/2,
	    			parent.getY() + parent.getSize().height/2
	    			);
	    	
			WarningPane p = new WarningPane(
	    			parent,
	    			"Warning - Invalid File Selected",
	    			"Files cannot be found.",
	    			point, 
	    			new Dimension(400, 190));
			return null;
		} catch (NullPointerException npe) {
			JFrame parent = SceneHandler.singleton.getMainFrame();
	    	Coord2 point = new Coord2(
	    			parent.getX() + parent.getSize().width/2,
	    			parent.getY() + parent.getSize().height/2
	    			);
	    	
			WarningPane p = new WarningPane(
	    			parent,
	    			"Warning - Invalid File Selected",
	    			"File picked is an invalid file. Slideshow file might be corrupted.",
	    			point, 
	    			new Dimension(400, 190));
			return null;
		} catch (IOException ioe) {
			// TODO Auto-generated catch block
			JFrame parent = SceneHandler.singleton.getMainFrame();
	    	Coord2 point = new Coord2(
	    			parent.getX() + parent.getSize().width/2,
	    			parent.getY() + parent.getSize().height/2
	    			);
	    	
			WarningPane p = new WarningPane(
	    			parent,
	    			"Warning - Invalid File Selected",
	    			"IO Exception",
	    			point, 
	    			new Dimension(400, 190));
			//ioe.printStackTrace();
			return null;
		} catch (ParseException pe) {
			// TODO Auto-generated catch block
			//pe.printStackTrace();
			JFrame parent = SceneHandler.singleton.getMainFrame();
	    	Coord2 point = new Coord2(
	    			parent.getX() + parent.getSize().width/2,
	    			parent.getY() + parent.getSize().height/2
	    			);
	    	
			WarningPane p = new WarningPane(
	    			parent,
	    			"Warning - Invalid File Selected",
	    			"File picked is an invalid file. Slideshow file might be corrupted.",
	    			point, 
	    			new Dimension(400, 190));
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Will reset list indices from null input
	 * 
	 * @Joe Hoang
	 
	public static void resetListIndex()
	{
		Timeline fixTimeline = SceneHandler.singleton.getTimeline();
		for(int i=0; i < fixTimeline.thumbnailsList.getSize(); i++)
		{
			
		}
	}
	*/

}
