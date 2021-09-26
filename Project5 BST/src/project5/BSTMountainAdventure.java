package project5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class contains the main() method 
 * This class creates a hiker and a BST Mountain 
 * to allow the hiker to travel on the BST Mountain
 * This class creates rest stops and populates the BST Mountain with them 
 * The rest stop stores information from the input args
 *
 * Lines 30 to 60 are credited to "Color project" by @author Joanna Klukowska
 * "Color project" source: https://edstem.org/us/courses/3906/workspaces/pBSLx4PAZFAusHxD2yDEPMV9zXksyGgt 
 *
 * @author Dan Lu
 *
 */

public class BSTMountainAdventure 
{
	/**
	 * The main() method of this program. 
	 * @param args is the array of strings provided on the command line when the program starts.  
	 */
	public static void main(String[] args)
	{
		//check if args exists 
		if (args.length == 0) 
		{
			System.err.println("Usage Error: the program expects file name as an argument.\n");
			System.exit(1);
		}

		//check if args contains a file name
		File file = new File(args[0]); 
		if (!file.exists())
		{
			System.err.println("Error: the file "+file.getAbsolutePath()+" does not exist.\n");
			System.exit(1);
		}
		if (!file.canRead())
		{
			System.err.println("Error: the file "+file.getAbsolutePath()+ " cannot be opened.\n");
			System.exit(1);
		}
		
		//check if file can be found and read
		Scanner scanner = null;
		try
		{
			scanner = new Scanner(file);
		} 
		catch (FileNotFoundException e) 
		{
			System.err.println("Error: the file "+file.getAbsolutePath()+ " cannot be opened for reading.\n");
			System.exit(1);
		}
		
		RestStop restStop = null;
		BSTMountain BST = new BSTMountain();
		String line = ""; 
		Hiker hiker = new Hiker();
		
		while (scanner.hasNextLine())
		{
			//stores each line of the input in a string called line, then split the string into individual words
			line = scanner.nextLine(); 
			String[] words = line.split(" ");
			restStop = new RestStop();//this line must go inside while loop but outside for loop
			
			//loops through all the words of the input and adds them to a rest stop
			//adds each rest stop to the BST
			
			/**
			 * The order of input must be label, supplies, obstacles.
			 * That means that any supplies that come after the obstacles do not count.
			 * Thus, tree and river indexes must be set to high values so that they can be decreased.
			 * Once decreased, only check for other obstacles after those indexes.
			 * If, instead, tree and river indexes were set to 0, they would only be
			 * checking for obstacles after index 0 which would ignore valid supplies. 
			 */
			int indexTree = 999;
			int indexRiver = 999;
			for (int i = 0; i < words.length; i++)
			{
				if (i != 0 && !(words[i].equals("food") || words[i].equals("raft") || words[i].equals("axe")
						|| words[i].equals("fallen") || words[i].equals("river")))
				{
					continue;
				}
				
				if (i > indexRiver && !(words[i].equals("fallen")))
				{
					continue;
				}
				if (i > indexTree && !(words[i].equals("river")))
				{
					continue;
				}
				
				if (words[i].equals("river"))
				{
					indexRiver = i;
				}
				if (words[i].equals("fallen") && words[i+1].equals("tree"))
				{
					restStop.add("fallen tree");
					indexTree = i;
					BST.add(restStop);
				}
				else
				{
					restStop.add(words[i]);
					BST.add(restStop);
				}
				//reset tree and river indexes for the next iteration
				indexTree = 999;
				indexRiver = 999;
			}
		}
		BST.travel(hiker);
		/**
		 * The reason for this if statement is because of test case #5.
		 * My program printed nothing for test case #5 and
		 * test case #5 didn't print anything, yet I didn't pass the test.
		 * There were no extra spaces or new lines in my output, however this fixes it.
		 */
		if (!(BSTMountain.getSolution().equals("")))
		{
			System.out.println(BSTMountain.getSolution());
		}
		//System.out.println(BST.toStringTree());
	}
}

