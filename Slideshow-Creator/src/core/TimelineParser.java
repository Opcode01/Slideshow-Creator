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

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class TimelineParser 
{
	
	/**
	 * Print out settings and information related 
	 * to timeline to a JSON file
	 * 
	 * @param slideshowName path and name to output file 
	 * 
	 * @author Joe Hoang
	 */
	public static void ExportTimeline(String slideshowName)
	{
		JSONObject out = new JSONObject();
		Timeline output = SceneHandler.singleton.getTimeline();
		out.put("isLoopingSlides", output.timelineSettings.isLoopingSlides);
		out.put("isLoopingAudio", output.timelineSettings.isLoopingAudio);
		out.put("isManual", output.timelineSettings.isManual);
		out.put("transitionLength", output.timelineSettings.transitionLength);
		out.put("slideDuration", output.timelineSettings.slideDuration);
		
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
		
		out.put("thumbnails", thumbnails);
		out.put("transitions", transitions);
		
		try
		{
			FileWriter file = new FileWriter(slideshowName + ".sl", false);
			file.write(out.toJSONString());
			file.flush();
			file.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * given path to JSON file, import a timeline with saved settings
	 * @param JSONpath to JSON file
	 * @return instance of timeline
	 * 
	 * @author Joe Hoang 
	 */
	public static Timeline ImportTimeline(String JSONpath) 
	{
		Timeline importedTimeline = new Timeline();
		JSONParser parser = new JSONParser();
		try 
		{
			Object input = parser.parse(new FileReader(JSONpath));
			
			JSONObject in = (JSONObject) input;
			
			JSONArray thumbnails = (JSONArray) in.get("thumbnails");
			for(int i = 0; i < thumbnails.size(); i++)
			{
				JSONObject tbl = (JSONObject) thumbnails.get(i);
				//JSON exports as long with type cast error
				//grab as long then cast to int 
				long index = (Long) tbl.get("index");
				String path = (String) tbl.get("path");
				Thumbnail newThumb = new Thumbnail(path);
				importedTimeline.thumbnailsList.addThumbnail(newThumb,(int)index);
			}
			
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
			
			boolean loopingSlides = (boolean) in.get("isLoopingSlides");
			boolean isManual = (boolean) in.get("isManual");
			boolean isLoopingAudio = (boolean) in.get("isLoopingAudio");
			long slideDuration = (Long) in.get("slideDuration");
			long transitionLength = (Long) in.get("transitionLength");
			Settings importedSettings = new Settings(loopingSlides, 
													isLoopingAudio, 
													isManual, 
													(int)transitionLength, 
													(int)slideDuration);
			
			importedTimeline.timelineSettings = importedSettings;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Timeline Imported:");
		//test values
		for(Thumbnail t : importedTimeline.thumbnailsList.getThumbnails())
		{
			System.out.println(t.getImagePath());
			System.out.println(importedTimeline.transitionsList.getTransition(0).getTransitionLength());
		}
		
		return importedTimeline;
		
	}

}
