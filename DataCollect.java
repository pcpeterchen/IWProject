import com.cycling74.max.*;
import com.cycling74.max.Atom.*;
import java.io.*;

//javac -classpath "C:\Program Files\Cycling '74\Max 7\resources\packages\max-mxj\java-classes\lib\max.jar" DataCollect.java

public class DataCollect extends MaxObject {
	private double hand_dist;
	private double foot_dist;
	private Atom[] r_hand;
	private Atom[] l_hand;
	private Atom[] r_foot;
	private Atom[] l_foot;

	public DataCollect() {
		declareInlets(new int[]{DataTypes.LIST, DataTypes.LIST, DataTypes.LIST, DataTypes.LIST});
		r_hand = new Atom[3];
		l_hand = new Atom[3];
		r_foot = new Atom[3];
		l_foot = new Atom[3];
	}

	public void bang() { 
		post("hello bang!");
	}

	public void loadbang() {
		post("welcome to the patch!");
	}
	
	public void inlet(int i) {
		post("hello integer " + i*10 + "!");
		outlet(0, i*10);
	}
	
	public void inlet(float x1) {
	}
	
	public void list(Atom[] a) {

		if (getInlet() == 0) {
			l_hand = a;
		}
		else if (getInlet() == 1) {			
			r_hand = a;
		}
		else if (getInlet() == 2) {
			l_foot = a;
		}
		else 
		r_foot = a;

	foot_dist = Math.sqrt(Math.pow((r_foot[0].toFloat() - l_foot[0].toFloat()),2) + 
			Math.pow((r_foot[1].toFloat() - l_foot[1].toFloat()),2) + 
			Math.pow((r_foot[2].toFloat() - l_foot[2].toFloat()),2));
	hand_dist = Math.sqrt(Math.pow((r_hand[0].toFloat() - l_hand[0].toFloat()),2) + 
		Math.pow((r_hand[1].toFloat() - l_hand[1].toFloat()),2) + 
		Math.pow((r_hand[2].toFloat() - l_hand[2].toFloat()),2));

	post("foot_distance" + String.valueOf(foot_dist));
	outlet(0, hand_dist);
	outlet(1, foot_dist);
	}
}
		/*try(PrintWriter out = new PrintWriter(new BufferedWriter
			(new FileWriter("C:\\Users\\pcpet_000\\Documents\\GitHub\\IWProject\\TestText.txt", true)))){
			out.println (Float.toString(f));
		}

    catch (IOException e) {
			e.printStackTrace();
		}	*/

	/*public void anything(String s, Atom[] args) {
		r_hand = args;
		post("hello anything " + s + " " + Atom.toOneString(r_hand) + "!");
		//outlet(0, s, args);
	}*/

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
