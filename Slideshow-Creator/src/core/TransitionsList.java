/**
 * TransitionsList.java
 * Holds a list of transitions as well functions to add, remove, and swap
 * 
 * Slideshow Creator
 * Timothy Couch, Joseph Hoang, Fernando Palacios, Austin Vickers
 * CS 499 Senior Design with Dr. Rick Coleman
 * 2/17/19
 */

package core;

 import java.util.ArrayList;
 import java.lang.String;

public class TransitionsList
{
    /**
     * List of transition objects
     */
    private ArrayList<Transition> transitions; 
    
    /**
     * Instantiate ArrayList instance of Transitions 
     */
    public TransitionsList()
    {
        transitions = new ArrayList<Transition>();
    }
    

    /**
     * @return size of arraylist of transition objects
     * 
     * @author Joe Hoang
     */
    public int getSize()
    {
        return transitions.size();
    }

    /**
     * @return arraylist of transition objects 
     * 
     * @author Joe Hoang
     */
    public ArrayList<Transition> getTransitions()
    {
        return transitions;
    }


    /**
     * getTransition will return the transition object at the specified index 
     * 
     * @param transitionIndex specified index to get Transition from
     * 
     * @return Transition object from transitions at given index
     * 
     * @author Joe Hoang
     */
    public Transition getTransition(int transitionIndex)
    {
    	
        return transitions.get(transitionIndex);
    }

    /**
     * addTransition will append given Transition object to transitions arrayList
     * 
     * @param transition transition object to be added to end of transitions list
     * 
     * @author Joe Hoang
     */
    public void addTransition(Transition transition)
    {
        transitions.add(transition);
    }

    /**
     * addTransition will append given Transition object to transitions arrayList
     * Overloaded to add transition at certain index
     * 
     * @param transition transition object to be added to transitions list at specified index
     * @param index to add transition to 
     * 
     * @author Joe Hoang
     */
    public void addTransition(Transition transition, int index)
    {
        transitions.add(index, transition);
    }

    
    /**
     * removeTransition will remove given transition object from transitions arrayList
     * 
     * @param transition Transition object to be removed from transitions list
     * 
     * @author Joe Hoang
     */
    public void removeTransition(Transition transition)
    {
    	try
    	{
	    	if(transitions.size() != 0)
	    	{	
	    		transitions.remove(transition);
	    	}
    	} catch (ArrayIndexOutOfBoundsException e)
    	{
    		System.out.println("Cannot remove from empty list");
    	}
    }

    /**
     * swapTransitions will swap value of two objects in ArrayList
     * 
     * @param transition1 first transition object index to be swapped 
     * 
     * @param transition2 second transition object index to be swapped
     * 
     * @author Joe Hoang
     */
    public void swapTransitions(int indexTransition1, int indexTransition2)
    {
        
        Transition tempTransition = transitions.get(indexTransition1);
        transitions.set(indexTransition1, transitions.get(indexTransition2));
        transitions.set(indexTransition2, tempTransition);
    }

    /**
     * swapForward - function will swap transition object in transitions with the object found at the next index
     * 
     * @param transition object to be moved forward by one index
     * 
     * @return index of new location of transition
     * 
     * @author Joe Hoang
     */
    public int swapForward(Transition transition)
    {
        int indexTransition1 = transitions.indexOf(transition);
        int indexTransition2 = indexTransition1 + 1;
        if(indexTransition2 != transitions.size())
        {
            swapTransitions(indexTransition1, indexTransition2);
            return indexTransition2;
        }
        else
        {
            return -1; 
        }
    }

     /**
     * swapBackground - function will swap transition object in transitions with the object found at the previous index
     * 
     * @param transition object to be moved backward by one index
     * 
     * @return index of new location of transition
     * 
     * @author Joe Hoang
     */
    public int swapBackward(Transition transition)
    {
        int indexTransition1 = transitions.indexOf(transition);
        int indexTransition2 = indexTransition1 - 1;
        if(indexTransition2 != -1)
        {
            swapTransitions(indexTransition1, indexTransition2);
            return indexTransition2;
        }
        else
        {
            return -1; 
        }
    }
    
    /**
     * gets index of supplied thumbnail or -1 if not found
     * @param thumbnail thumbnail to search for
     * 
     * @return index of supplied thumbnail or -1 if not found
     */
    public int indexOf(Transition transition)
    {
        return transitions.indexOf(transition);
    }

}
