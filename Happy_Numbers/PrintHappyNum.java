
/************************************************ 
*Extends FindHappyNum which in turn extends 
*happyNum. Overrides the happyInterval method 
*to print out every happy number within specified 
*interval. 
************************************************/
public class PrintHappyNum extends FindHappyNum {
	
	private int _min;
	private int _max;

	/************************************************ 
	 *Constructor that passes the minimum and maximum 
	 *numbers specified to super. Then sets its own 
	 *fields with the super's.
	 *@param The minimum number of the interval. 
	 *@param The maximum number of the interval. 
	 ************************************************/
	public PrintHappyNum(int min, int max) {
		super(min, max);
		_min = getMin();
		_max = getMax();
	}
	
	/************************************************ 
	 *Finds and prints all the happy numbers within 
	 *a specified interval. 
	 ************************************************/
	public void happyIntervals() {
		for(int x = _min; x <= _max; x++) {
			if(checkHappy(Integer.toString(x))) {
				System.out.println(getHappyNum());
			} 
		}	
	}
}
