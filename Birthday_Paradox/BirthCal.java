import java.util.Random;

/************************************************
 * Performs the calculations necessary to create
 * a random birthday. 
 ************************************************/
public class BirthCal {
	private int _month;
	private int _day;
	private String _birthday;
	private Random _rand = new Random();
	
/************************************************
 * Randomly creates a month and depending on which
 * one randomly creates a day within the guidelines
 * of the chosen month. Also transforms the birthday
 * from an integer to a string to concatenate 
 * the date. 
 ************************************************/
	public void createBirthday() {
		_month = _rand.nextInt(12) + 1;
		switch (_month) {
			case 1: _day = _rand.nextInt(31) + 1;
				break;
			case 2: _day = _rand.nextInt(29) + 1;
				break;
			case 3: _day = _rand.nextInt(31) + 1;
				break;
			case 4: _day = _rand.nextInt(30) + 1;
				break;
			case 5: _day = _rand.nextInt(31) + 1;
				break;
			case 6: _day = _rand.nextInt(30) + 1;
				break;
			case 7: _day = _rand.nextInt(31) + 1;
				break;
			case 8: _day = _rand.nextInt(31) + 1;
				break;
			case 9: _day = _rand.nextInt(30) + 1;
				break;
			case 10: _day = _rand.nextInt(31) + 1;
				break;
			case 11: _day = _rand.nextInt(30) + 1;
				break;
			case 12: _day = _rand.nextInt(31) + 1;
				break;
		}
		
		//Creates an output more that is easier to read.
		//Necessary for testing purposes. 
			_birthday = Integer.toString(_month) + "/" + Integer.toString(_day);
	}
	
/************************************************
 * Getter method that returns the concatenated
 * as a String. 
 ************************************************/
	public String getBirthday() {
		return _birthday;
	}
	
}
