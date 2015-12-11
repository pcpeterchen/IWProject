import com.cycling74.max.*;
import com.cycling74.max.Atom.*;
import java.io.*;
import java.awt.*;
import org.jfree.*;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.*;

//javac -classpath "C:\Program Files\Cycling '74\Max 7\resources\packages\max-mxj\java-classes\lib\max.jar;C:\Users\pcpet_000\Desktop\Downloads5\jfreechart-1.0.19\jfreechart-1.0.19\lib\jfreechart-1.0.19.jar;C:\Users\pcpet_000\Desktop\Downloads5\jfreechart-1.0.19\jfreechart-1.0.19\lib\jcommon-1.0.23.jar" DataCollect.java

public class DataCollect extends MaxObject {

	private float priv1;
	private float priv2;
	private double distance;
	private Atom[] l1;
	private Atom[] l2;
	private DefaultCategoryDataset datasetY;

	public DataCollect() {
		datasetY = new DefaultCategoryDataset();
		declareInlets(new int[]{DataTypes.FLOAT, DataTypes.FLOAT, DataTypes.LIST, DataTypes.LIST});
		l1 = new Atom[3];
		l2 = new Atom[3];
	}
	public void bang() { 
		post("hello bang!");

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

		outletBang(0);
	}

	public void loadbang() {
		post("welcome to the patch!");
	}
	
	public void inlet(int i) {
		post("hello integer " + i*10 + "!");
		outlet(0, i*10);
	}
	
	public void inlet(float x1) {

		/*try(PrintWriter out = new PrintWriter(new BufferedWriter
			(new FileWriter("C:\\Users\\pcpet_000\\Documents\\GitHub\\IWProject\\TestText.txt", true)))){
			out.println (Float.toString(f));
		}

    catch (IOException e) {
			e.printStackTrace();
		}	*/
		if (getInlet() == 0) {
			priv1 = x1;
			//post("hello float 0 " + priv1);
		}
		else 
			priv2 = x1;
			//post("hello float 1 " + priv2);
		distance = Math.sqrt(Math.pow(priv2, 2) + Math.pow(priv1, 2));
		post ("distance " + String.valueOf(distance));
		outlet(0, distance);
	}
	
	public void list(Atom[] a) {
		post("got here");
		if (getInlet() == 0) {
			l1 = a;
/*			post("got here 1");
			post("hello l1 " + " " + Atom.toOneString(l1) + "!"); 
*/		}
		else 
			l2 = a;
/*			post("got here 2");
			post("hello l2 " + " " + Atom.toOneString(l2) + "!");*/	
		double distance = Math.sqrt(Math.pow((l1[0].toFloat() - l2[0].toFloat()),2) + Math.pow((l1[1].toFloat() - l2[1].toFloat()),2) + Math.pow((l1[2].toFloat() - l2[2].toFloat()),2));
		post(String.valueOf(distance));
		outlet(0, distance);
	}
	
	/*public void anything(String s, Atom[] args) {
		l1 = args;
		post("hello anything " + s + " " + Atom.toOneString(l1) + "!");
		//outlet(0, s, args);
	}*/
}