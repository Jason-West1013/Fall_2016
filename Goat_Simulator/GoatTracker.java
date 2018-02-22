
/******************************************
 * The goat tracker class that keeps tracks
 * of the total amount of goats created of 
 * each color, dead of each color, and the 
 * current number of goats in play of each 
 * color.  
 ******************************************/
public class GoatTracker {

	// Instance variables
	private int _numBlackGoats = 0;
	private int _numGreyGoats = 0;
	private int _numWhiteGoats = 0;
	private int _numBlackDead = 0;
	private int _numWhiteDead = 0;
	private int _numGreyDead = 0;
	private int _allTimeWhite = 0;
	private int _allTimeGrey = 0;
	private int _allTimeBlack = 0;
	
	// Accessor Methods. 
	public int getWhiteGoats() {return _numWhiteGoats;}
	public int getGreyGoats() {return _numGreyGoats;}
	public int getBlackGoats() {return _numBlackGoats;}
	public int getNumDead() {return _numWhiteDead + _numBlackDead + _numGreyDead;}
	public int getAllTimeWhite() {return _allTimeWhite;}
	public int getAllTimeBlack() {return _allTimeBlack;}
	public int getAllTimeGrey() {return _allTimeGrey;}
	
	/******************************************
	 * Adds a goat to the dead count and removes 
	 * a goat from the goats in play.
	 * @param The color of the goat. 
	 ******************************************/
	public void deadGoat(String color) {
		if(color == "white") {
			_numWhiteDead++;
			_numWhiteGoats--;
		}
		if(color == "grey") {
			_numGreyDead++;
			_numGreyGoats--;
		}
		if(color == "black") {
			_numBlackDead++;
			_numBlackGoats--;
		}
	}
	
	/******************************************
	 * Adds a goat to the all time of the specific
	 * color and the goats in play. 
	 * @param The color of the goat. 
	 ******************************************/
	public void addGoat(String color) {
		if(color == "white") {
			_numWhiteGoats++;
			_allTimeWhite++;
		}
		if(color == "grey") {
			_numGreyGoats++;
			_allTimeGrey++;
		}
		if(color == "black") {
			_numBlackGoats++;
			_allTimeBlack++;
		}
	}
	
	
}
