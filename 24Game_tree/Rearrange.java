/****************************************rS
 * Just contains a couple methods to permeates 
 * the variations a four card hand can have. 
 ****************************************/
public class Rearrange {

	private int _hand = 0;
	private String[] _total;
	private int _count = 0;
	
	/****************************************
	 * Constructor that sets the hand size passed 
	 * and initializes the array to hold all the 
	 * variations. 
	 * @param The hand size.
	 ****************************************/
	public Rearrange(int hand) {
		_hand = hand;
		_total = new String[24];
	}
	
	/****************************************
	 * A multiple recursion method that permeates
	 * through the hand variations. 
	 * @param The hand size. 
	 * @param An empty list to hold the sequence.
	 * @param The four cards. 
	 ****************************************/
	public void reArr(int k, NewLinkedList<String> s, NewLinkedList<String> u) {
		
		for(int x = 0; x < u.getSize(); x++) {
			s.addHead(u.getHead());
			u.removeHead();
			if(k == 1) {
				for(int y = 0; y < _hand; y++) {
					if(_total[_count] == null) {
						_total[_count] = s.getHead();
						s.addTail(s.removeHead());
					} else {
						_total[_count] += s.getHead();
						s.addTail(s.removeHead());
					}
				}
				_count++;
			} else
				reArr(k-1,s,u);
			u.addTail(s.getHead());
			s.removeHead();
		}
	}
	
	/****************************************
	 * Returns the array containing the hand 
	 * variations. 
	 ****************************************/
	public String[] getVar() {
		return _total;
	}
	
	/****************************************
	 * Receives a string and converts it to 
	 * an array. Only purpose is convenience.
	 * @param A string to be placed into an 
	 * array
	 ****************************************/
	public String[] strToArrStr(String str) {
		String[] array = new String[str.length()];
		for(int x = 0; x < str.length(); x++) {
				array[x] = Character.toString(str.charAt(x));
		}
		return array;
	}
}
