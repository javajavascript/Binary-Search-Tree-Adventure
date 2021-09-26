package project5;

import java.util.*; 

/**
 * This class is a Binary Search Tree that represents a mountain
 * This class uses nodes to store data and references to left and right nodes.
 * 
 * The foundation of this class is credited to "BST" by @author Joanna Klukowska
 * The foundation of this class is also credited to "BST" by @author Taiwo Oso
 * First "BST" source: https://edstem.org/us/courses/3906/workspaces/pzAluDoU2oXKd9WWMfEVm6FRlDIAAw3i
 * Second "BST" source: https://edstem.org/us/courses/3906/workspaces/pqwtzlJUB55bDsGwDIC0UBiskj4L9xgI
 * Changes were made to the add method to include int level 
 *
 * @author Dan Lu
 *
 */

public class BSTMountain
{
    /** 
     * Internal node class that stores the data in each node and 
     * references to left and right nodes  
     */ 
    private class BSTNode implements Comparable < BSTNode > 
    {
        private RestStop data;
        private int level;
        private BSTNode left;
        private BSTNode right;
        
        /**
         * Creates a node in the BST with the specified data and level
         */
        public BSTNode(RestStop data, int level) 
        {
            this.data = data;
            this.level = level;
        }

        /**
         * Allows comparison of BST nodes by comparing the data of two nodes
         */
        public int compareTo ( BSTNode other ) 
        {
            if (BSTMountain.this.comparator == null )
            {
                return this.data.compareTo ( other.data );
            }
            else 
            {
                return comparator.compare(this.data, other.data); 
            }
        }
    }
    
    private BSTNode root;   //reference to the root node of the tree 
    private Comparator<RestStop> comparator;   //comparator object to overwrite the natural ordering of the elements
    private boolean added ; //helper variable used by the add method 
    private int size;	//keeps track of the BST size
    private int maxLevel;	//keeps track of the level of the deepest nodes in the BST

    /**
     * Constructs a new, empty tree, sorted according to the natural ordering of its elements.
     */
    public BSTMountain() 
    {
        root = null; 
        comparator = null; 
        size = 0;
    }

    /**
     * Constructs a new, empty tree, sorted according to the specified comparator.
     */
    public BSTMountain(Comparator<RestStop> comparator)
    {
        this.comparator = comparator;
    }

    /**
     * Wrapper method for recAdd(T data, BSTNode node, int level) 
     * Adds the specified element to this tree if it is not already present. 
     * If this tree already contains the element, the call leaves the 
     * tree unchanged and returns false.
     * Uses compareTo from the RestStop class
     * @param data element to be added to this tree 
     * @return true if this tree did not already contain the specified element 
     * @throws NullPointerException if the specified element is null  
     */
    public boolean add(RestStop data)
    {
        if (root == null) 
        {
            this.root = new BSTNode(data, 0);
            return true;
        }
        else 
        {
            added = recAdd(data, root, 1);//if not root, its 1 
        }
        return added; 
    }

    /**
     * Recursive add method.
     * @param data element to be added to this tree 
     * @param node node at which the recursive call is made 
     * @param level keeps track of the level of the node
     * @returns true if the node was added, false otherwise
   	 */
    public boolean recAdd(RestStop data, BSTNode node, int level) 
    {
        if (data.compareTo(node.data) < 0) 
        {
            if (level > maxLevel)
            {
            	maxLevel = level;
            }
            if (node.left == null) 
            {
                node.left = new BSTNode(data, level);
                size++;
                return true;
            }
            return recAdd(data, node.left, level+1);         
        } 
        else if (data.compareTo(node.data) > 0) 
        {
            if (level > maxLevel)
            {
            	maxLevel = level;
            }
            if (node.right == null) 
            {
                node.right = new BSTNode(data, level);
                size++;
                return true;
            }
            return recAdd(data, node.right, level+1);
        } 
        else 
        {
            return false;
        }
    }

    /**
     * Returns the number of elements in this tree.
     * @return the number of elements in this tree
     */
    public int size() 
    {
    	return size; 
    }

    /**
     * Wrapper method for toStringTree(StringBuffer sb, BSTNode node, int level) 
     * @returns a string version of the BST
     */
    public String toStringTree( ) 
    {
        StringBuffer sb = new StringBuffer(); 
        toStringTree(sb, root, 0);
        return sb.toString();
    }

    /**
     * Converts the data of the nodes into strings and formats them
     * Uses preorder traversal to display the tree
     * Does not work if the data.toString returns more than one line 
     * @param sb converts node.data into a string and and formats them
     * @param node is the node of the BST 
     * @param level is the level of the node
     */
    private void toStringTree(StringBuffer sb, BSTNode node, int level) 
    {
        //display the node 
        if (level > 0) 
        {
            for (int i = 0; i < level-1; i++) 
            {
                sb.append("   ");
            }
            sb.append("|--");
        }
        if (node == null) 
        {
            sb.append("->\n"); 
            return;
        }
        else 
        {
            sb.append(node.data + "\n"); 
        }
        //display the left and right subtrees 
        toStringTree(sb, node.left, level+1); 
        toStringTree(sb, node.right, level+1); 
    }
    
    /**
     * Wrapper method for travel(BSTNode node, Hiker hiker, String solution) 
     * This method is necessary to call travel(BSTNode node, Hiker hiker, String solution) 
     * from the main method in BSTMountainAdventure
     * @param hiker is the hiker that is traveling through the BST Mountain
     */
    public void travel(Hiker hiker)
    {
    	travel(root, hiker, "");
    }
    
    private static ArrayList<String> solutions = new ArrayList<String>();
    
    /**
     * Allows the hiker to travel through the BST Mountain
     * Converts the data of the nodes into strings
     * Checks each word in the strings and adds supplies to the hikers accordingly
     * @param node is the node of the BST 
     * @param hiker is the hiker that is traveling through the BST Mountain
     * @param solution is a string that represents the nodes that lead the hiker to an exit
     * The solution is in the format (nodeName1, nodeName2, nodeName3, etc)
     */
    public void travel(BSTNode node, Hiker hiker, String solution) 
    {
    	String nodeString = (node.data).toString();
    	//remove brackets since the contents of node.data is formatted as an arraylist 
    	nodeString = nodeString.substring(1,nodeString.length()-1);
    	String[] words = nodeString.split(", ");
    	for (int i = 0; i < words.length; i++)
    	{
        	if (words[i].equals("food"))
        	{
        		hiker.addFood();
        	}
        	if  (words[i].equals("raft"))
        	{
        		hiker.addRaft();
        	}
        	if  (words[i].equals("axe"))
        	{
        		hiker.addAxe();
        	}
        	if  (words[i].equals("fallen tree"))
        	{
        		if (hiker.getAxe() > 0)
        		{
            		hiker.removeAxe();
        		}
        		else
        		{
        			return;//stuck at a tree
        		}
        	}
        	if  (words[i].equals("river"))
        	{
        		if (hiker.getRaft() > 0)
        		{
            		hiker.removeRaft();
        		}
        		else
        		{
        			return;//stuck at a river
        		}
        	}
    	}
    	
    	/**
    	 * This check must go before the food == 0 check.
    	 * This is because when we removed food at the end of the last iteration, 
    	 * it means that we have moved to the next node, so food == 0 checks
    	 * if we have enough food at the next node. But leaf nodes don't have a next node, 
    	 * so they would get caught incorrectly by the food == 0 check.
    	 */
    	if(node.left == null && node.right == null) 
    	{
    		if (node.level == maxLevel)
    		{
    			solution = solution + words[0];
    			solutions.add(solution);
    		}
    	}

    	int food = hiker.getFood();
    	int rafts = hiker.getRaft();
    	int axes = hiker.getAxe();
    	
    	/**
    	 * This food == 0 check must go before we remove food.
    	 * That is because we are checking how much food we have at our current node (upon arrival)
    	 * But, remove food implies that we have moved to the next node.
    	 * 
    	 * If, instead, remove food came before the food == 0 check, that would mean that we 
    	 * move to the next node, check the food, then add food if it's available at the rest stop.
    	 * 
    	 * But that's not the correct order of events. The correct order is move to the next node,
    	 * add food if it's available at the rest stop, and then check the food.
    	 * That is what the order of this code does.
    	 */
    	if (hiker.getFood() == 0)
    	{
    		return;//stuck because no food
    	}
    	
    	hiker.removeFood();

    	String leftSolution = solution;
    	String rightSolution = solution;
    	
    	if(node.left != null)
    	{
    		travel(node.left, hiker, leftSolution + words[0] +  " ");
    	}

    	//need to reset the amount of supplies so that left travel doesn't impact right travel
    	//food-1 is necessary or else food will only be consumed when traveling on left subtrees
    	hiker.setFood(food-1);
    	hiker.setRaft(rafts);
    	hiker.setAxe(axes);
    	
    	if(node.right != null) 
    	{
    		travel(node.right, hiker, rightSolution + words[0] +  " ");
    	}
    }
    
	/**
	 * It is possible to directly print out the solutions on line 278, but this method
	 * allows users to print from the main() method class, BSTMountainAdventure 
	 * The reason this is done is because classes that represent entities like BSTMountain and Hiker
	 * should not be performing I/O because they cannot be reused in other settings
	 * Therefore it's the job of the class with the main() method to do I/O
	 * Returns the solutions as strings
	 * @return the solutions as strings
	 */
	public static String getSolution()
	{
		String solution = "";
		for (int i = 0; i < solutions.size(); i++)
		{
			if (i == solutions.size()-1)
			{
				solution += solutions.get(i);
			}
			else
			{
				solution += solutions.get(i) + "\n";
			}
		}
		return solution;
	}
}

