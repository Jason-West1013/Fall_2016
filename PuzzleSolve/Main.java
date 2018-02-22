import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		String puzzle = "FORTY + TEN + TEN = SIXTY"; // The puzzle
		ArrayList<Integer> U = new ArrayList<Integer>();
		PuzzleSolve ps = new PuzzleSolve(puzzle);
		
		for(int x = 0; x < 10; x++) // The numbers 0-9 added to the list. 
			U.add(x);
		
		ps.puzzle(ps.numberUnique(puzzle),new ArrayList<Integer>(),U); 
	}
}
