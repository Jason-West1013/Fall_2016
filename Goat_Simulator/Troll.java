import java.util.Iterator;

import PriorityQueue.HeapAdaptablePriorityQueue;

/******************************************
 * The troll class representing the troll 
 * collecting money and the bridge it 
 * resides at. 
 ******************************************/
public class Troll {

	// Instance Variables
	private String _color;
	private int _totalCoins;
	private GoatComparator _comp = new GoatComparator();
	private HeapAdaptablePriorityQueue _bridge;

	/***********************************************
	 * Constructor that initializes the color, the
	 * coin bank, and the priority queue instance. 
	 * @param The color of the bridge.  
	 ***********************************************/
	public Troll(String color) {
		_color = color;
		_totalCoins = 0;
		_bridge = new HeapAdaptablePriorityQueue(_comp);
	}
	
	// Accessor Methods
	
	/***********************************************
	 * Returns the color of the bridge. 
	 ***********************************************/
	public String getColor() {return _color;}
	
	/***********************************************
	 * Returns the total amount of coins the troll 
	 * has.
	 ***********************************************/
	public int getTotalCoins() {return _totalCoins;}
	
	/***********************************************
	 * Returns the bridge instance. 
	 ***********************************************/
	public HeapAdaptablePriorityQueue getBridge() {return _bridge;}
	
	/***********************************************
	 * Returns how many goats are on the bridge 
	 * with high priority. 
	 ***********************************************/
	public int howManyHigh() {return _bridge.howManyWithKey("high");}
	
	/***********************************************
	 * Returns the number of goats that will die 
	 * before the passes goat leaves the bridge. 
	 * @param The goat to be compared. 
	 ***********************************************/
	public int howManyDead(Goat goat) {
		Object[] array = _bridge.iterator();
		int count = 0;
		
		for(int x = 0; x < array.length; x++) {
			if(((Goat)array[x]).whichCoins(getColor()) < (x + 1))
				count++;
		}
		return count;
	}

	// General behavior methods. 
	
	/***********************************************
	 * Adds a toll payment to the troll's coin bank. 
	 * @param The toll.
	 ***********************************************/
	public void addToll(int coins) {_totalCoins += coins;}	// Adds the tolls collected for each troll.  
	
	/***********************************************
	 * Removes the first goat in the queue from the 
	 * bridge. 
	 ***********************************************/
	public Object tollsPaid() {return _bridge.removeMin().getValue();} // Removes the first goat in line from the bridge.
	
	/***********************************************
	 * The starting point for each goat. This is only 
	 * called when the goat is on the same colored 
	 * bridge so its decision is checked every time 
	 * it is called. 
	 * @param The goat entering the bridge. 
	 ***********************************************/
	public void startBridge(Goat goat, GoatTracker gt) {
		if(goat.decision(this,gt)) {
			addToll(goat.payToll(getColor()));
			goat.setPaid(true);
			_bridge.insert("high",goat);
		} else
			_bridge.insert("low",goat);
	}
	
	// A goat enters a bridge each turn, this tests the various rules. 
	/***********************************************
	 * The goat enters the bridge during its turn. 
	 * @param The goat entering the bridge.
	 * @param The goat tracker instance. 
	 ***********************************************/
	public void enterBridge(Goat goat,GoatTracker gt) {
		if(goat.getColor() == getColor()) { // Checks color
				goat.gainCoins();
			if(goat.decision(this,gt)) { // Tests goat's decision.
				addToll(goat.payToll(getColor())); // if true it pays the toll in advance to get high priority.
				goat.setPaid(true);
				_bridge.insert("high",goat); // Enters bridge with high priority.
			} else {
				goat.setPaid(false);
				_bridge.insert("low",goat); // Else enters bridge with low priority.
			}
		} else { // If the goat is not the same color as the bridge. 
			addToll(goat.payToll(getColor()));
			goat.setPaid(false);
			_bridge.insert("low",goat);
		}
	}
	
	/***********************************************
	 * Cleans up the bridge. Rechecks the decision 
	 * of each goat, checks if any are dead, and 
	 * subtracts 1 coin of each color from every goat. 
	 * @param The goat tracker instance.
	 ***********************************************/
	public void cleanUp(GoatTracker gt) {
		Object[] array = _bridge.iterator(); // Iterator method that stores the goats in the bridge in an array.

		for(int x = 0; x < array.length; x ++) { // Loops through the returned array of goats in the bridge.
			
			((Goat) array[x]).subtractCoins(); // Subtracts one coin of every color from each goat.
			
			if(((Goat) array[x]).isDead()) {  // Removes dead goats.
				_bridge.remove(_bridge.getEntry(array[x]));
				gt.deadGoat(((Goat) array[x]).getColor());
				// If each of the goats decision changed but it did not pay yet. 
			} else if(((Goat) array[x]).updateDecision(this,gt) && !((Goat) array[x]).didPaid()) {
				addToll(((Goat) array[x]).payToll(getColor()));
				((Goat) array[x]).setPaid(true);
				
				if(((Goat) array[x]).isDead()) {  // Checks for dead goats again, because paying for high priority may have killed goat.
					_bridge.remove(_bridge.getEntry(array[x]));
					gt.deadGoat(((Goat) array[x]).getColor());
					continue; // Continues with the loop is the goat is dead. 
				}
				_bridge.replaceKey(_bridge.getEntry(array[x]),"high"); // The goat is given high priority.
			}
		}		
	}
}
