import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

public class Main {

	public static void main(String[] args) {
		//The variables used.
		int numChecks = 10000;//Contains the number of checks to the recursive methods. 
		double[] xData = new double[numChecks];//Array used to draw the graphs. 
		double[] yData = new double[numChecks];
		double[] yData2 = new double[numChecks];
		double[] yData3 = new double[numChecks];
		double[] yData4 = new double[numChecks];
		Recursion recurve = new Recursion();//Instance created for the 4 recursive methods.
		
		/*System.out.println(recurve.recursion(876)); //returns the count of the recursion. 
		System.out.println("\n" + recurve.count);     //Used to count the number of odd numbers. 
		System.out.println("\n" + recurve.oddCount);*/
		
		//The loop that tests each recursive method per numChecks. 
		//Stores each method in the Data arrays so it can be drawn on a graph.  
		for(int n = 1; n <= numChecks; n++) {
			int g = 0;
			xData[n - 1] = n;
			yData[n - 1] = recurve.recursion(n);	
			yData2[n - 1] = recurve.recursion2(n);
			yData3[n - 1] = recurve.recursion3N(n);
			yData4[n - 1] = recurve.recursion4(n);
			//System.out.print(n + " ");
			//System.out.print(recurve.recursion(n) + " ");
			//System.out.print(recurve.recursion2(n) + " ");
			//System.out.print(recurve.recursion3N(n) + " ");
			//System.out.print(recurve.recursion4(n) + " ");
			//System.out.println();
			
		}
		
		//Creation of all the charts. Used an external Java library XChart. 
		XYChart chart = new XYChartBuilder().height(400).width(600).title("Recursion").xAxisTitle("n").yAxisTitle("g(t)").build();
		XYChart chart2 = new XYChartBuilder().height(400).width(600).title("Recursion").xAxisTitle("n").yAxisTitle("g(t)").build();
		XYChart chart3 = new XYChartBuilder().height(400).width(600).title("Recursion").xAxisTitle("n").yAxisTitle("g(t)").build();
		
		//Adding each line of data to the graph.
		chart.addSeries("Original Function", xData, yData);
		chart.addSeries("Og Function / 2", xData, yData2);
		
		chart2.addSeries("3n+1", xData, yData3);
		chart2.addSeries("3n+1/6", xData, yData4);
		
		chart3.addSeries("Og Function / 2", xData, yData2);
		chart3.addSeries("3n+1/6", xData, yData4);
		
		//Draw each graph. 
		new SwingWrapper(chart).displayChart();
		new SwingWrapper(chart2).displayChart();
		new SwingWrapper(chart3).displayChart();
	}

}
