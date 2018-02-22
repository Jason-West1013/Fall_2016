import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class PuzzleSolve {
	
	// Instance Variables
	String _puzzle;
	
	// Constructor
	public PuzzleSolve(String puzzle) {
		_puzzle = puzzle;
	}
	
	/***************************************
	 * Removes all non-character letters from 
	 * the string. Then using the removeDupLett
	 * method, returns the number of unique 
	 * letters. 
	 * @param The puzzle string. 
	 ***************************************/
	public int numberUnique(String s) {
		String temp = s.replaceAll("[^a-zA-Z]", "");
		return removeDupLett(temp).length();	
	}
	
	/***************************************
	 * Removes all non-character letters from 
	 * the string. Then using the removeDubLett
	 * method, returns a string holding only 
	 * the unique characters. 
	 * @param The puzzle string. 
	 ***************************************/
	public String onlyUnique(String s) {
		String temp = s.replaceAll("[^a-zA-Z]","");
		return removeDupLett(temp);
	}
	
	/***************************************
	 * The puzzle method created using the 
	 * pseudocode from the text book. Uses 
	 * ArrayLists for the sets, calls the 
	 * solvePuzzle method on each permeation
	 *  to check for a solution. 
	 *  @param The number of figures to permeate. 
	 *  @param Empty list for holding figures. 
	 *  @param Original list. 
	 ***************************************/
	public void puzzle(int k, ArrayList<Integer> S, ArrayList<Integer> U) {
		for(int x = 0; x < U.size(); x++) {
			S.add(U.get(x));
			U.remove(x);
			if(k == 1) {
				solvePuzzle(S);
			} else
				puzzle(k-1,S,U);
			U.add(S.get(0));
			S.remove(0);
		}
	}
	
	/***************************************
	 * This method checks for a solution to 
	 * the puzzle. Each permeation of the 
	 * numbers replace all the unique letters
	 * in the puzzle string. Then a solution 
	 * is tested. 
	 * @param The List of the numbers to be tested. 
	 ***************************************/
	public void solvePuzzle(ArrayList S) {
		String numbers = S.toString();
		numbers = numbers.replaceAll("[^0-9]",""); // Needed because the ArrayList's toString has a lot of filler characters.
		String uniLetters = onlyUnique(_puzzle);
		String puzzle = _puzzle;
		for(int x = 0; x < S.size(); x++) { // Replaces a number with a unique number in the original puzzle string. 
			String let = uniLetters.substring(x,x + 1);
			String num = numbers.substring(x,x + 1);
			puzzle = puzzle.replaceAll(let,num);
		}
			
		// Splits the puzzle string and converts each of its parts to numbers. 
		String[] puzzParts = puzzle.split("[+=]");
		int[] parts = new int[puzzParts.length];

		for(int x = 0; x < puzzParts.length; x++) {
			puzzParts[x] = puzzParts[x].replaceAll("\\s","");
			parts[x] = Integer.parseInt(puzzParts[x]);
		}
		
		// Performs the arithmetic on the numbers. 
		int topHalf = parts[0] + parts[1] + parts[2];
		if(topHalf == 31486) {
			int rere = 0;
		}
		
		// Checks for a solution. 
		if(topHalf == parts[3]) {
			System.out.println("The solution is...");
			System.out.println(parts[0] + " + " + parts[1] + " + " + parts[2] + " = " + parts[3]);
		}	
	}
	
	/***************************************
	 * Removes any duplicate letters and 
	 * returns the string. Referenced from 
	 * code found on StackOverflow. 
	 * @param The String to be tested. 
	 ***************************************/
	public String removeDupLett(String s) {
		char[] array = s.toCharArray();
		Set<Character> newString = new LinkedHashSet<Character>();
		for (char c : array) {
		    newString.add(c);
		}

		StringBuilder string = new StringBuilder();
		for (Character character : newString) {
		    string.append(character);
		}
		return string.toString();
	}
} 
