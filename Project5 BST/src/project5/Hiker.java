package project5;

/**
 * Hiker class stores the hiker's food, rafts, and axes as ints
 *
 * @author Dan Lu
 *
 */

public class Hiker 
{
	private int food;
	private int raft;
	private int axe;
	
	/**
     * Creates a hiker with no food, rafts, or axes
     */
	public Hiker()
	{
		food = 0;
		raft = 0;
		axe = 0;
	}
	
	/**
     * Adds one food to the hiker
     */
	public void addFood()
	{
		food++;
	}
	
	/**
     * Adds one raft to the hiker
     */
	public void addRaft()
	{
		raft++;
	}
	
	/**
     * Adds one axe to the hiker
     */
	public void addAxe()
	{
		axe++;
	}
	
	/**
     * Removes one food from the hiker if they have food
     */
	public void removeFood()
	{
		if (food > 0)
		{
			food--;
		}
	}
	
	/**
     * Removes one raft from the hiker if they have at least one raft
     */
	public void removeRaft()
	{
		if (raft > 0)
		{
			raft--;
		}
	}
	
	/**
     * Removes one axe from the hiker if they have at least one axe
     */
	public void removeAxe()
	{
		if (axe > 0)
		{
			axe--;
		}
	}

	/**
	 * Returns the amount of food the hiker has
     * @returns the amount of food the hiker has
     */
	public int getFood() 
	{
		return food;
	}

	/**
	 * Returns the amount of rafts the hiker has
     * @returns the amount of rafts the hiker has
     */
	public int getRaft() 
	{
		return raft;
	}

	/**
	 * Returns the amount of axes the hiker has
     * @returns the amount of axes the hiker has
     */
	public int getAxe() 
	{
		return axe;
	}

	/**
	 * Sets the food of the hiker to int food
     */
	public void setFood(int food) 
	{
		this.food = food;
	}

	/**
	 * Sets the rafts of the hiker to int raft
     */
	public void setRaft(int raft) 
	{
		this.raft = raft;
	}

	/**
	 * Sets the axes of the hiker to int axe
     */
	public void setAxe(int axe) 
	{
		this.axe = axe;
	}
	
}
