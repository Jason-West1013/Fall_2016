/****************************************
 * Performs most of the game logic. 
 ****************************************/
public class Find24 {
	
	private final int _deck = 52;
	private String _finStr;	
	private String[] _op = {"+", "-", "x", "/"};
	private String[] _hand;
	
	/****************************************
	 * Constructor that initializes the array 
	 * containing cards.  
	 * @param The size of the hand. 
	 ****************************************/
	public Find24(int handSize) {
		_hand = new String[handSize];
		
	}
	
	/****************************************
	 * Sets the hand so it can be used within 
	 * the class. 
	 * @param The array containing the specific 
	 * hand. 
	 ****************************************/
	public void setHand(String[] hand) {
		_hand = hand;
	}
	
	/****************************************
	 * Tests whether or not the specific hand in 
	 * the specific order equals 24. Uses postfix
	 * notation with a stack to check. Prints out 
	 * the result if 24 is found to be the answer. 
	 * @param The postfix notation of the hand to be 
	 * tested. Returns true if it does equal 24.
	 ****************************************/
	public boolean postfixTest(String postfix) {
		LinkedStack<Integer> nums = new LinkedStack<Integer>();
		
		for(int w = 0; w < postfix.length(); w++) {
			char ch = postfix.charAt(w);
			
			if(ch > '0' && ch <= '9')
				nums.push(Character.getNumericValue(ch));
			else if(ch == 'A')
				nums.push(1);
			else if(ch == 'T')
				nums.push(10);
			else if(ch == 'J')
				nums.push(11);
			else if(ch == 'Q')
				nums.push(12);
			else if(ch == 'K')
				nums.push(13);
			else {
				int num1 = nums.pop();
				int num2 = nums.pop();
				switch(ch) {
				case '+':
					nums.push(num2 + num1);
					storeStr(Integer.toString(num2) + " + " + Integer.toString(num1) + " = " + Integer.toString(nums.top()) + " ");
					break;
				case '-':
					nums.push(num2 - num1);
					storeStr(Integer.toString(num2) + " - " + Integer.toString(num1) + " = " + Integer.toString(nums.top()) + " ");					
					break;
				case 'x':
					nums.push(num2 * num1);
					storeStr(Integer.toString(num2) + " x " + Integer.toString(num1) + " = " + Integer.toString(nums.top()) + " ");					
					break;
				case '/':
					if(num1 == 0) {
						postfix = "Error";
						break;
					}
					nums.push(num2 / num1);
					storeStr(Integer.toString(num2) + " / " + Integer.toString(num1) + " = " + Integer.toString(nums.top()) + " ");					
					break;					
				}
			}
		}
		if(nums.isEmpty())
				nums.push(0);
		if(nums.top() == 24) {
			String h = null;
			for(String s : _hand) {
				if(h == null) {
					if(s.equals("T"))
						h = "10";
					else
						h = s;
				} else {
					if(s.equals("T"))
						h += "10";
					else
						h += s;
				}
			}
			System.out.println(h + ": " + printString());
			_finStr = null;
			return true;
		} else 
			_finStr = null;
			return false;

		
	}
	
	/****************************************
	 * Creates the deck of 52 cards, and then 
	 * stores each possible variation in an 
	 * array. Returns the array. 
	 ****************************************/
	public String[] makeDeck() {
		String[] numCards = new String[1820];
		String[] cards = new String[_deck];
		int cardNum = 1;
		int count = 0;
		
		for(int x = 0; x < _deck; x += 4) {
			String card = null;
			if(cardNum > 1 && cardNum <= 9)
				card = Integer.toString(cardNum);
			else if(cardNum == 1)
				card = "A";
			else if(cardNum == 10)
				card = "T";
			else if(cardNum == 11)
				card = "J";
			else if(cardNum == 12)
				card = "Q";
			else if(cardNum == 13)
				card = "K";
			cards[x] = card;
			cards[x+1] = card;
			cards[x+2] = card;
			cards[x+3] = card;
			cardNum++;
		}
		
		for(int x1 = 0; x1 < _deck - 3; x1+=4) 
			for(int x2 = x1; x2 < _deck - 2; x2+=4) 
				for(int x3 = x2; x3 < _deck - 1; x3+=4) 
					for(int x4 = x3; x4 < _deck; x4+=4) {
						if(numCards[count] == null)
							numCards[count] = cards[x1];
						numCards[count] += cards[x2];
						numCards[count] += cards[x3];
						numCards[count] += cards[x4];
						count++;
					}
		return numCards;
	}
	
	/****************************************
	 * Permeates through all the operators with 
	 * the specific hand. calls the postfix method
	 * within the class to test. When the result 
	 * is 24 returns true. 
	 * @param The postfix notation to be tested. 
	 ****************************************/
	public boolean checkOps(String[] n) {
		for(int x = 0; x < _op.length; x++) 
			for(int y = 0; y < _op.length; y++)
				for(int z = 0; z < _op.length; z++) {
					if(this.postfixTest(n[0] + n[1] + _op[z] + n[2] + n[3] + _op[y] + _op[x])) 
						return true;
					if(this.postfixTest(n[0] + n[1] + _op[z] + n[2] + _op[y] + n[3] + _op[x]))
						return true;
					if(this.postfixTest(n[0] + n[1] + n[2] + n[3] + _op[z] + _op[y] + _op[x]))
						return true;
					if(this.postfixTest(n[0] + n[1] + n[2] + _op[z] + n[3] + _op[y] + _op[x]))
						return true;
					if(this.postfixTest(n[0] + n[1] + n[2] + _op[z] + _op[y] + n[3] + _op[x]))
						return true;
				}
		return false;
	}
	
	/****************************************
	 * Stores the output sting to print when 
	 * a solution is correct. 
	 * @param The string to be stored. 
	 ****************************************/
	public void storeStr(String answer) {
		if(_finStr == null)
			_finStr = answer + " ";
		else
			_finStr += answer + " ";
	}
	
	/****************************************
	 * Prints the solution to the screen. 
	 ****************************************/
	public String printString() {
		return _finStr;
	}
}


