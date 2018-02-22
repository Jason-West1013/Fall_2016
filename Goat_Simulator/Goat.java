import PriorityQueue.HeapAdaptablePriorityQueue;

/******************************************
 * The goat class representing the goats
 * the travel between all the troll bridges. 
 * Each instance is a different goat. 
 ******************************************/
public class Goat {

	//Instance Variables
	private String _color;
	private int _whiteCoins;
	private int _blackCoins;
	private int _greyCoins;
	private boolean _paid = false;
	private final int TOLL = 20; // Should be in troll class, troll should have control.
	private int _timeStamp = 0; // At what time was the goat created.
	
	//Constructors
	/***********************************************
	 * Default constructor for the goat class.
	 ***********************************************/
	public Goat() {}
	
	/***********************************************
	 * Builds a goat with a specific color, timestamp, 
	 * coins, and adds the goat to the GoatTracker class.
	 * @param The color of the goat instance. 
	 * @param Passed the goat tracker instance.
	 * @param Passed the time from the main loop. 
	 ***********************************************/
	public Goat(String color, GoatTracker gt, int time) {
		gt.addGoat(color); // Keeps track of the goats in play
		_timeStamp = time; // Used as the time span of the goat. 
		_color = color;
		_whiteCoins = 100;
		_blackCoins = 100;
		_greyCoins = 100;
	}
	
	
	// Accessor Methods.
	
	/***********************************************
	 * Returns the color of the goat created. 
	 ***********************************************/
	public String getColor() {return _color;}
	
	/***********************************************
	 * Sets whether or not the goat was paid to go 
	 * to the front of the line.  
	 * @param Boolean representing if the goat paid. 
	 ***********************************************/
	public void setPaid(boolean paid) {_paid = paid;}
	
	/***********************************************
	 * Returns whether or not the goat paid the toll. 
	 ***********************************************/
	public boolean didPaid() {return _paid;}
	
	/***********************************************
	 * Returns the color of the coins the goat has to 
	 * pay to get on the bridge. 
	 * @param The color of the bridge. 
	 ***********************************************/
	public int whichCoins(String color) {
		if(color == "white")
			return _whiteCoins;
		else if(color == "black")
			return _blackCoins;
		else 
			return _greyCoins;
	}
	
	/***********************************************
	 * Adds 100 coins of each color when the goat 
	 * crosses all three bridges. 
	 ***********************************************/
	public void gainCoins() {
		_whiteCoins += 100;
		_blackCoins += 100;
		_greyCoins += 100;
	}
	
	/***********************************************
	 * Subtracts 1 of each coin ever time passed. 
	 ***********************************************/
	public void subtractCoins() {
		_whiteCoins -= 1;
		_blackCoins -= 1;
		_greyCoins -= 1;
	}
	
	/***********************************************
	 * Checks is the goat has zero coins of a color
	 * and returns true if dead. 
	 ***********************************************/
	public boolean isDead() {
		if(_whiteCoins <= 0 || _blackCoins <= 0 || _greyCoins <= 0) {
			return true;
		} else
			return false;
	}
	
	/***********************************************
	 * Pays the toll when entering a bridge or getting
	 * high priority. If there isn't enough coins it 
	 * returns the remainder so the troll isn't passed 
	 * a negative number. The goat is then found during
	 * cleanup. 
	 * @param The color of the bridge. 
	 ***********************************************/
	public int payToll(String color) {
		if(color == "white") {
			if(_whiteCoins >= 20)
				return _whiteCoins -= TOLL;
			else {
				int coins = _whiteCoins;
				_whiteCoins = 0;
				return coins;
			}
		} else if(color == "black") {
			if(_blackCoins >= 20)
				return _blackCoins -= TOLL;
			else {
				int coins = _blackCoins;
				_blackCoins = 0;
				return coins;
			}
		} else {
			if(_greyCoins >= 20)
				return _greyCoins -= TOLL;
			else {
				int coins = _greyCoins;
				_greyCoins = 0;
				return coins;
			}
		}
	}
	
	/***********************************************
	 * Loops through the goats in the heap and returns 
	 * how far away the goat is from the front. 
	 * @param The bridge the goat is currently on. 
	 ***********************************************/
	public int position(Troll troll) {
		Object array[] = troll.getBridge().iterator();
		int count = 1;
		for(Object goat : array) {
			if(!((Goat)goat).equals(this))
				count++;
			else
				break;
		}	
		return count;
	}
	
	/***********************************************
	 * The original decision of the goat depending on 
	 * its color. 
	 * @param The bridge the goat is going on. 
	 * @param The goat tracker to keep track of all 
	 * the goat's stats. 
	 ***********************************************/
	public boolean decision(Troll troll, GoatTracker gt) {
		// Does not pay unless they will die in the bridge, or if it's afraid of getting overrun. 
		if(getColor() == "grey") { 
			if(troll.getBridge().size() > whichCoins(troll.getColor()) || gt.getWhiteGoats() > gt.getGreyGoats() + 20 || 
					gt.getBlackGoats() > gt.getGreyGoats() + 20)
				return true;
		}
		// Takes into account whether or not it will survive the bridge,
		// subtracts how many goats will die before it gets off from that number, 
		// and always wants to stay ahead of the white goats. 
		if(getColor() == "black") {  
			if((troll.getBridge().size() - troll.howManyDead(this)) > whichCoins(troll.getColor()) || gt.getBlackGoats() <= gt.getWhiteGoats() || 
					troll.getBridge().size() > whichCoins(getColor()) || gt.getBlackGoats() < 10) {
				return true;
			}
		}
		// Wants to stay ahead of the other two colors of goats, 
		// and if there are no other high priority goats in line it chooses high priority to get off the bridge quickly.
		if(getColor() == "white") {
			if(gt.getWhiteGoats() < gt.getBlackGoats() || gt.getWhiteGoats() < gt.getGreyGoats() || 
					troll.getBridge().size() > whichCoins(getColor()) || troll.howManyHigh() == 0)
				return true;
		}
		return false;
	}
	
	/***********************************************
	 * A carbon copy of the original decision except
	 * the position of the goat is compared instead 
	 * of the entire queue.
	 * @param The bridge the goat is currently on. 
	 * @param The goat tracker instance. 
	 ***********************************************/
	public boolean updateDecision(Troll troll, GoatTracker gt) {
		if(getColor() == "grey") {
			if(position(troll) > whichCoins(troll.getColor()) || gt.getWhiteGoats() > gt.getGreyGoats() + 20 || 
					gt.getBlackGoats() > gt.getGreyGoats() + 20)
				return true;
		}
		if(getColor() == "black") {
			if((position(troll) - troll.howManyDead(this)) > whichCoins(troll.getColor()) || gt.getBlackGoats() <= gt.getWhiteGoats() ||
					position(troll) > whichCoins(getColor()) || gt.getBlackGoats() < 10) 
				return true;
		}
		if(getColor() == "white") {
			if(gt.getWhiteGoats() < gt.getBlackGoats() || gt.getWhiteGoats() < gt.getGreyGoats() || 
					troll.getBridge().size() > whichCoins(getColor()))
				return true;
		}
		return false;
	}
}
