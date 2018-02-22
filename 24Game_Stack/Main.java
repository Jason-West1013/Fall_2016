
public class Main {

	public static void main(String[] args) {
		final int handSize = 4; //Amount of cards in a hand.
		NewLinkedList list; //List needed for permeating the cards. 
		Rearrange re; //Rearrange variable.
		Find24 game = new Find24(handSize); //Instance of the Find24 class, contains postfix testing, deck creation, and operator permeating.
		
		String[] allHands = game.makeDeck(); //Creates all the hands available in a deck. 
		
		for(int a = 0; a < allHands.length; a++) { //Main loop that tests all the possible hands of four cards. 
			re = new Rearrange(handSize);
			String[] num = re.strToArrStr(allHands[a]);
			list = new NewLinkedList();
			
			for(String s : num)
				list.addTail(s);
			
			re.reArr(handSize,new NewLinkedList(),list); //Permeates all hand variations.
			num = re.getVar();
			
			for(int t = 0; t < num.length; t++) { //Permeates operators and checks for 24. 
				String[] n = re.strToArrStr(num[t]);
				game.setHand(n);
				if(game.checkOps(n))
					break;
				}
			}
	}	
}
	