package project5;

import java.util.ArrayList;

/**
 * RestStop class stores the name, supplies, and obstacles of a rest stop as strings
 * This class inherits all of its properties from an ArrayList<String>
 * This class implements Comparable<RestStop> to allow comparison of rest stops
 * This class stores the supplies in the order that they were parsed 
 * 
 * @author Dan Lu
 *
 */

public class RestStop extends ArrayList<String> implements Comparable<RestStop>
{
	/**
	 * Allows alphabetical comparison of rest stops by comparing the values 
	 * in the first index of each rest stop, which are the names/labels of the rest stops.
	 */
	public int compareTo(RestStop o) 
	{
		return (this.get(0)).compareTo(o.get(0));
	}

}
