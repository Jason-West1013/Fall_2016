import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		//Variables
		final int HANDSIZE = 4;
		String hand = null;
		Scanner sc = new Scanner(System.in);
		NewLinkedList  list;
		Rearrange re;
		Find24_Tree game;
		
		//Main game loop, is always true unless 'bye' is typed. 
		while(true) {
			boolean checkHand = true;
			System.out.println("Enter a " + HANDSIZE + " card hand. 2-10, J, Q, K, or A. Enter 'T' for 10. Type 'Bye' to exit.");
			hand = sc.next();
			if(hand.equals("bye") || hand.equals("Bye") || hand.equals("BYE")) { //Chose 3 common ways of saying bye to exit.
				System.out.println("Thanks for playing!");
				System.exit(0); //Exit code.
			}
			if(hand.length() != HANDSIZE) { //Checks the hand size and reruns the loop if not correct.
				System.out.println("This is the wrong size hand, only enter " + HANDSIZE + " cards.");
				continue;
			}
			for(int x = 0; x < hand.length(); x++) { //Checks that the correct form of the cards are entered. 
				char a = hand.charAt(x);
				if(hand.charAt(x) >= '2' && hand.charAt(x) <= '9' || 
						hand.charAt(x) == 't' || hand.charAt(x) == 'T' ||
						hand.charAt(x) == 'j' || hand.charAt(x) == 'J' ||
						hand.charAt(x) == 'q' || hand.charAt(x) == 'Q' || 
						hand.charAt(x) == 'k' || hand.charAt(x) == 'K' ||
						hand.charAt(x) == 'a' || hand.charAt(x) == 'A') {
				} else {
					System.out.println("This is not the correct input try again.\n");		
					checkHand = false;
					break;
				}
			}
			if(checkHand) {
				re = new Rearrange(HANDSIZE);
				list = new NewLinkedList();
				String[] cards = re.strToArrStr(hand);
				for(String s : cards)
					list.addTail(s);
				re.reArr(HANDSIZE,new NewLinkedList(),list); //Finds all permeations of the chosen cards. 
				cards = re.getVar();
				game = new Find24_Tree();
				for(int t = 0; t < cards.length; t++) { //Permeates operators and checks for 24. 
					String[] n = re.strToArrStr(cards[t]);
					game.checkOps(re.strToArrStr(cards[t]));
				}
				if(game.getCount() == 0)
					System.out.println("There are no solutions for this card set.\n");
				System.out.println();
			}
		}
	}
}

