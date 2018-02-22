
public class Main {

	public static void main(String[] args) {
		int numTests = 100000; // Number of tests desired.
		int minNumStudents = 5; // Minimum number of students for test. 
		int maxNumStudents = 100;  //Maximum number of students for test. 
		
		System.out.println("\t The Birthday Paradox\nNumber of Students   Percentage of Match\n" + 
		"------------------   -------------------");
		
		// Tests students for matching birthdays, cycles in increments of 5. 
		for(int x = minNumStudents; x <= maxNumStudents; x += 5) {
			BirthParCal n = new BirthParCal(x,numTests);
			n.testBirth();
			ProbCal p = new ProbCal(n.getNumSuccess(), n.getNumTests());
			//System.out.println(n.getNumSuccess() + " matches out of " + n.getNumTests() + " tests."); // Uncomment to show the number of matches. 
			p.probCheck();
			System.out.println("\t" + n.getNumStudents() + "\t\t    " + p.getProbPercent() + "%");
		}
	}
}
