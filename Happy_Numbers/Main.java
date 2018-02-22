
public class Main {

	public static void main(String[] args) {
		
		HappyNum find, print, seq;
		find = new FindHappyNum(1,10000);
		print = new PrintHappyNum(9001,10000);
		seq = new HappySequential();
		
		//Find the number of happy numbers between 1 and 10000.
		find.happyIntervals();
		System.out.println();

		//Print all the happy numbers between 9001 and 10000.
		print.happyIntervals();
		System.out.println();
		
		//A 25 digit happy number.
		seq.checkHappy("1283940592182304853727912");
		System.out.println();
		
		//A 25 digit unhappy number.
		seq.checkHappy("6273847382892076588932031");

		
	}
}
