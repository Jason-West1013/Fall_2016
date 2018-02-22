import java.text.DecimalFormat;

/************************************************
 * Receives the total number of matches and the 
 * total number of tests and calculates the 
 * probability. 
 ************************************************/
public class ProbCal {
	private double _numMatch;
	private double _totalTest;
	private double _probPercent;
	private DecimalFormat d;
	
	/************************************************
	 * Constructor that sets the number of matches and
	 * tests, as well as, creates a new instance of the
	 * decimal format object (setting the format to 
	 * two decimal places).
	 * @param The total number of successful matches. 
	 * @param The total number of tests performed. 
	 ************************************************/
	public ProbCal(int numMatch, int totalTest) {
		_numMatch = numMatch;
		_totalTest = totalTest;
		d = new DecimalFormat("##.00");
	}
	
	/************************************************
	 * Performs the calculation of probability and 
	 * sets it as a percentage. 
	 ************************************************/
	public void probCheck() {
		_probPercent = (_numMatch / _totalTest) * 100;
	}
	
	/************************************************
	 * Returns the probability percentage. 
	 ************************************************/
	public String getProbPercent() {
		return d.format(_probPercent);
	}
}
