import com.cycling74.max.*;
import com.cycling74.max.Atom.*;
import java.io.*;

//javac -classpath "C:\Program Files\Cycling '74\Max 7\resources\packages\max-mxj\java-classes\lib\max.jar" DataCollect.java

public class DataCollect extends MaxObject {
	
	private double distance;
	private Atom[] r_hand;
	private Atom[] l_hand;

	public DataCollect() {
		declareInlets(new int[]{DataTypes.LIST, DataTypes.LIST, DataTypes.LIST, DataTypes.LIST});
		r_hand = new Atom[3];
		l_hand = new Atom[3];
	}

	public void list(Atom[] a) {
		post("got here");
		if (getInlet() == 0) {
			r_hand = a;
		}
		else 
			l_hand = a;	
		double distance = Math.sqrt(Math.pow((a[0].toFloat() - b[0].toFloat()),2) + Math.pow((a[1].toFloat() - b[1].toFloat()),2) + Math.pow((a[2].toFloat() - b[2].toFloat()),2));
		post(String.valueOf(distance));
		outlet(0, distance);
	}
}

		/*String chartTitle = "Title";
    String categoryAxisLabel = "Y-Pos";
    String valueAxisLabel = "Time";

    JFreeChart chart = ChartFactory.createLineChart(chartTitle,
            categoryAxisLabel, valueAxisLabel, datasetY);

		File imageFile = new File("C:\\Users\\pcpet_000\\Documents\\GitHub\\IWProject\\LineChart.png");
		int width = 640;
		int height = 480;
		 
		try {
		    ChartUtilities.saveChartAsPNG(imageFile, chart, width, height);
		} catch (IOException ex) {
		    System.err.println(ex);
		}*/

		

//		post(Float.toString(datasetY.getValue(0,0)));

	
	/*public void anything(String s, Atom[] args) {
		r_hand = args;
		post("hello anything " + s + " " + Atom.toOneString(r_hand) + "!");
		//outlet(0, s, args);
	}*/
		/*try(PrintWriter out = new PrintWriter(new BufferedWriter
			(new FileWriter("C:\\Users\\pcpet_000\\Documents\\GitHub\\IWProject\\TestText.txt", true)))){
			out.println (Float.toString(f));
		}

    catch (IOException e) {
			e.printStackTrace();
		}	*/