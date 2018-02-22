import java.util.Comparator;

/******************************************
 * Compares the goats in the queue. Checks 
 * whether or not they are a high or low 
 * low priority. 
 ******************************************/
public class GoatComparator<GoatPriority> implements Comparator<GoatPriority> {

	/******************************************
	 * Takes two parameters and tests them. It 
	 * returns a 0 if they are equal, a -1 if the 
	 * value being tested is higher, or a 1 if the 
	 * value being tested is lower. 
	 * @param The value being tested. 
	 * @param The next value in the queue. 
	 ******************************************/
	@Override
	public int compare(GoatPriority a,GoatPriority b) {
		if(b == "low" && a == "high") // a is the key being tested. 
			return -1;
		if(b == "high" && a == "low")
			return 1;
		else 
			return 0;
	}

}
