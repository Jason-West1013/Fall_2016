import java.util.Iterator;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

public class Main {
	
	public static void main(String[] args) {
		GoatTracker gt = new GoatTracker(); // Track how many goats of each color are in play.
		Troll whiteBridge = new Troll("white");
		Troll blackBridge = new Troll("black");
		Troll greyBridge = new Troll("grey");
		Goat goat = new Goat();
		Goat[] leaveGrey = new Goat[2];
		Goat[] leaveWhite = new Goat[2];
		Goat[] leaveBlack = new Goat[2];
		
		// Time spend in game. 
		final int TIMEUNITS = 10000;
		
		//Chart variables
		int[] xData = new int[TIMEUNITS];
		int[] wGData = new int[TIMEUNITS];
		int[] bGData = new int[TIMEUNITS];
		int[] gGData = new int[TIMEUNITS];
		int[] wTData = new int[TIMEUNITS];
		int[] bTData = new int[TIMEUNITS];
		int[] gTData = new int[TIMEUNITS];
		
		// Starting Points
		greyBridge.startBridge(new Goat("grey",gt,0),gt);
		blackBridge.startBridge(new Goat("black",gt,0),gt);
		whiteBridge.startBridge(new Goat("white",gt,0),gt);
		
		for(int x = 1; x <= TIMEUNITS; x++) { // time counter
			
			// The goats that are leaving each bridge.
			if(!greyBridge.getBridge().isEmpty()) {
				leaveGrey[0] = (Goat)greyBridge.tollsPaid();
				if(whiteBridge.getColor() == leaveGrey[0].getColor())
					leaveGrey[1] = new Goat(leaveGrey[0].getColor(),gt,x);
			}
			if(!whiteBridge.getBridge().isEmpty()) {
				leaveWhite[0] = (Goat)whiteBridge.tollsPaid();
				if(blackBridge.getColor() == leaveWhite[0].getColor())
					leaveWhite[1] = new Goat(leaveWhite[0].getColor(),gt,x);
			} 
			if(!blackBridge.getBridge().isEmpty()) {
				leaveBlack[0] = (Goat)blackBridge.tollsPaid();
				if(greyBridge.getColor() == leaveBlack[0].getColor())
					leaveBlack[1] = new Goat(leaveBlack[0].getColor(),gt,x);
			}
			
			//Clean up
			whiteBridge.cleanUp(gt); 
			blackBridge.cleanUp(gt);
			greyBridge.cleanUp(gt);	
			
			// The goats that are entering each bridge.
			whiteBridge.enterBridge(leaveGrey[0],gt);
			leaveGrey[0] = null;
			if(leaveGrey[1] != null) {
				whiteBridge.startBridge(leaveGrey[1],gt);
				leaveGrey[1] = null;
			}
			blackBridge.enterBridge(leaveWhite[0],gt);
			leaveWhite[0] = null;
			if(leaveWhite[1] != null) {
				blackBridge.startBridge(leaveWhite[1],gt);
				leaveWhite[1] = null;
			}
			greyBridge.enterBridge(leaveBlack[0],gt);
			leaveBlack[0] = null;
			if(leaveBlack[1] != null) {
				greyBridge.startBridge(leaveBlack[1],gt);
				leaveBlack[1] = null;
				
			// Data for plotting. 
			xData[x] = x;
			wGData[x] = gt.getWhiteGoats();
			bGData[x] = gt.getBlackGoats();
			gGData[x] = gt.getGreyGoats();
			
			wTData[x] = whiteBridge.getTotalCoins();
			bTData[x] = blackBridge.getTotalCoins();
			gTData[x] = greyBridge.getTotalCoins();
			}
		}
		XYChart goatChart = new XYChartBuilder().height(400).width(600).title("Goats Over Time").xAxisTitle("Time").yAxisTitle("Number of Goats").build();
		XYChart trollChart = new XYChartBuilder().height(400).width(600).title("Coins Over Time").xAxisTitle("Time").yAxisTitle("Number of Coins").build();
		
		goatChart.addSeries("White Goats",xData,wGData);
		goatChart.addSeries("Black Goats",xData,bGData);
		goatChart.addSeries("Grey Goats",xData, gGData);
		
		trollChart.addSeries("Troll at White Bridge",xData,wTData);
		trollChart.addSeries("Troll at Black Bridge",xData,bTData);
		trollChart.addSeries("Troll at Grey Bridge",xData,gTData);
		
		new SwingWrapper(goatChart).displayChart();
		new SwingWrapper(trollChart).displayChart();
	}
}
