/************************************************ 
 *Extends HappyNum and overrides several methods
 *to add the ability to show the sequences of 
 *checking for each happy number. 
 ************************************************/
public class HappySequential extends HappyNum {

	NewLinkedList<Integer> _list;
	String _happyNum;

	/************************************************ 
	 *Overridden method printing out the arthmetic 
	 *associated with searching for a happy number.
	 *@param Takes the array holding each digit of 
	 *the possible happy number. 
	 ************************************************/
	public int happyArthmetic(int[] array) {
		int num = 0;
		for(int x = 0; x < array.length; x ++) {
			if(array.length == 1 || array.length == x + 1)
				System.out.print(array[x] + "^2");
			else
				System.out.print(array[x] + "^2 + ");
			array[x] = (int) Math.pow(array[x],2);
			num += array[x];
		}
		return num;
	}
	
	/************************************************ 
	 *Overridden method printing out the arthmetic 
	 *associated with searching for a happy number.
	 *Returns true if the number is happy.  
	 *@param The happy number to be tested. 
	 ************************************************/
	public boolean checkHappy(String happyNum) {
		boolean check = true;
		_happyNum = happyNum;
		setHappyNum(happyNum);
		_list = new NewLinkedList<Integer>();
		
		while(check) {
			int newHappy = 0;
			int[] array = stringToArray(happyNum);
			newHappy = happyArthmetic(array);
		
			if(newHappy == 1) {
				System.out.println(" = " + newHappy);
				System.out.println(_happyNum + " is a happy number.");
				check = false;
			} else if(_list.getSize() == 0){
				_list.addHead(newHappy);
				System.out.print(" = " + newHappy);
				System.out.println();
				happyNum = Integer.toString(newHappy);
			} else if(_list.searchList(newHappy)){
				System.out.println(" = " + newHappy);
				System.out.println(_happyNum + " is not a happy number.");
				check = false;
				return check;
			} else {
				_list.addHead(newHappy);
				System.out.print(" = " + newHappy);
				System.out.println();
				happyNum = Integer.toString(newHappy);
			}
		}
		return !check;
	}
}
