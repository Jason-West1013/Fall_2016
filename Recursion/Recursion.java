/**********************************************
 * A class that tests the two algorithms assigned
 * and two new, more efficient ones. 
 **********************************************/
public class Recursion {
	//private int count = 0;
	//private int oddCount = 0;

/**********************************************
 * The first recursion method assigned. It gets 
 * passed an integer; if even divides by 2 and 
 * recalls the method, if odd subtracts one and 
 * recalls the method. 
 * @param The integer to be counted down to one.  
 **********************************************/
	public int recursion(int n) {
		//System.out.println(n);
		//count++;
		if(n == 1)
			return 1;
		else if((n % 2) == 0)
			return 1 + recursion(n / 2);
		else {
			//oddCount++;
			return 1 + recursion(n - 1);
		}
	}
	
	/**********************************************
	 * The more efficient recursive method compared to 
	 * the first. The only difference is if n is odd 
	 * it subtracts 1 and then divides by 2. This 
	 * essentially saves a method call. 
	 * @param The integer to be counted down to one. 
	 **********************************************/
	public int recursion2(int n) {
		//System.out.println(n); //Outputs each n number that was called.
		if(n == 1)
			return 1;
		else if((n % 2) == 0)
			return 1 + recursion2(n / 2);
		else
			return 1 + recursion2((n - 1) / 2);
	}
	
	/**********************************************
	 * The recursive method that does the same thing as 
	 * the first if n is even, but when it is odd it 
	 * multiplies n by 3 and adds 1. 
	 * @param The integer to be counted down to one. 
	 **********************************************/
	public int recursion3N(int n) {
		//count++;
		//System.out.println(n);
		if(n == 1)
			return 1;
		else if((n % 2) == 0) 
			return 1 + recursion3N(n / 2);
		else {
			//oddCount++;
			return 1 + recursion3N((3 * n) + 1);
		}
	}
	
	/**********************************************
	 * The more efficient recursive algorithm. Essentially
	 * the same except when the number is odd it performs the 
	 * same calculation and then divides the number by 6.
	 * This algorithm is just as efficient as the 
	 * remodeled algorithm above. 
	 * @param The integer to be counted down to one. 
	 **********************************************/
	public int recursion4(int n) {
		if(n == 1)
			return 1;
		else if((n % 2) == 0)
			return 1 + recursion4(n / 2);
		else
			return 1 + recursion4(((3 * n) + 1) / 6);
	}
}
