
/************************************************
 *Receives the number of students and the number
 *of tests that are to be conducted. Then performs 
 *the necessary calculations for the birthday paradox. 
 ************************************************/
public class BirthParCal {
	
	private BirthCal _birthday;
	private int _numStudents;
	private int _numTests;
	private int _numSuccess = 0;
	private String[] _array;
	
/************************************************
 *Constructor setting the number of students and 
 *tests. As well as, creating a new string array. 
 *@param The number of students to be tested.  
 *@param The number of tests to be conducted. 
 ************************************************/
	public BirthParCal(int numStudents, int numTests) {
		_numStudents = numStudents;
		_numTests = numTests;
		_array = new String[_numStudents];
	}
	
/************************************************
 *Uses several nested for loops. The first moves 
 *through the number of tests. The second creates 
 *a random birthday for each student. The third 
 *goes through the group of students looking for 
 *a match, once it finds one it stops looking. 
 ************************************************/
	public void testBirth() {
		for(int x = 0; x < _numTests; x++) {
			boolean testBreak = false;
			for(int y = 0; y < _numStudents; y++) {
				_birthday = new BirthCal();
				_birthday.createBirthday();
				_array[y] = _birthday.getBirthday();
				//System.out.println(_array[y]); //Prints out each birthday for testing purposes.
				
				
				for(int z = 0; z < y; z++) {
					if(_array[y].equals(_array[z])) {
						//System.out.println("These two birthdays match! " + _array[z] + " and " + _array[y]);
						_numSuccess++;
						testBreak = true;
					}
				}
				if(testBreak == true)
					break;
			}
			//System.out.println(); //Easier readability for testing purposes. 
		}
	}
	
/************************************************
 *Returns the number of successful matches.
 ************************************************/
	public int getNumSuccess() {
		return _numSuccess;
	}
	
/************************************************
 *Returns the total number of tests performed. 
 ************************************************/	
	public int getNumTests() {
		return _numTests;
	}
	
	public int getNumStudents() {
		return _numStudents;
	}
}
