import com.cycling74.max.*;
import com.cycling74.max.Atom.*;
import java.io.*;
import java.util.*;

//javac -classpath "C:\Program Files\Cycling '74\Max 7\resources\packages\max-mxj\java-classes\lib\max.jar" DataCollect.java

public class DataCollect extends MaxObject {
	private double hand_dist;
	private double foot_dist;
	private Atom[] r_hand;
	private Atom[] l_hand;
	private Atom[] r_foot;
	private Atom[] l_foot;
	private Atom[] torso;
	private Atom[] waist;
	private Atom[] hip;
	private Atom[] center;
	private Atom[] bod;
	private Queue<Double> queue;
	private StringBuffer cenbuffer;
	private StringBuffer ybuffer;

	private double calculateAverage(Queue <Double> q) {
		Double sum = 0.0;
  if(!q.isEmpty()) {
  	for (Double d : q) {
  		sum += d;
      }
    return sum / q.size();
  }
  return sum;
	}

	private double scale (Double a, Double b, Double min, Double max, Double x) {
/*		     (b-a)(x - min)
f(x) = --------------  + a
          max - min*/
          return (b - a) * (x - min)/(max - min) + a;
	}

	public DataCollect() {
		declareInlets(new int[]{DataTypes.LIST, DataTypes.LIST, 
			DataTypes.LIST, DataTypes.LIST, DataTypes.LIST, 
			DataTypes.LIST, DataTypes.LIST, DataTypes.LIST});
		declareOutlets(new int[]{DataTypes.FLOAT, DataTypes.FLOAT, DataTypes.FLOAT});
		r_hand = new Atom[3];
		l_hand = new Atom[3];
		r_foot = new Atom[3];
		l_foot = new Atom[3];
		torso = new Atom[3];
		waist = new Atom[3];
		hip = new Atom[3];
		center = new Atom[3];
		bod = new Atom[3];
		queue = new LinkedList<Double>();
		cenbuffer = new StringBuffer();
		ybuffer = new StringBuffer();
	}

	public void bang(){
		try(PrintWriter out = new PrintWriter(new BufferedWriter
			(new FileWriter("C:\\Users\\pcpet_000\\Documents\\GitHub\\IWProject\\TestTextCOM.txt", true)))){
			out.println (cenbuffer.toString());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		cenbuffer.delete(0, cenbuffer.length());

		try(PrintWriter out = new PrintWriter(new BufferedWriter
			(new FileWriter("C:\\Users\\pcpet_000\\Documents\\GitHub\\IWProject\\TestText.txt", true)))){
			out.println (ybuffer.toString());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		ybuffer.delete(0, ybuffer.length());
	}

/*	public void inlet (float f) {
		try(PrintWriter out = new PrintWriter(new BufferedWriter
			(new FileWriter("C:\\Users\\pcpet_000\\Documents\\GitHub\\IWProject\\TestText.txt", true)))){
			out.println (Float.toString(f));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
*/
	public void list(Atom[] a) {
		double cenx;
		double ceny;
		double cenz;
		double avgLast10;
		Atom x;
		Atom y;
		Atom z;

		if (getInlet() == 0) {l_hand = a;}
		else if (getInlet() == 1) {r_hand = a;}
		else if (getInlet() == 2) {l_foot = a;}
		else if (getInlet() == 3) {r_foot = a;}
		else if (getInlet() == 4) {torso = a;}
		else if (getInlet() == 5) {waist = a;}
		else if (getInlet() == 6) {hip = a;}
		else if (getInlet() == 7) {bod = a;}

		foot_dist = Math.sqrt(Math.pow((r_foot[0].toDouble() - l_foot[0].toDouble()),2) + 
			Math.pow((r_foot[1].toDouble() - l_foot[1].toDouble()),2) + 
			Math.pow((r_foot[2].toDouble() - l_foot[2].toDouble()),2));
		hand_dist = Math.sqrt(Math.pow((r_hand[0].toDouble() - l_hand[0].toDouble()),2) + 
			Math.pow((r_hand[1].toDouble() - l_hand[1].toDouble()),2) + 
			Math.pow((r_hand[2].toDouble() - l_hand[2].toDouble()),2));

		cenx = (5.335 * r_hand[0].toDouble() + 
			5.335 * l_hand[0].toDouble() + 17.555 * r_foot[0].toDouble() + 
			17.555 * l_foot[0].toDouble() + 54.15 * torso[0].toDouble() + 
			12.65 * waist[0].toDouble() + 14.81 * hip[0].toDouble()) / 
		(5.335 + 2 * 17.555 + 54.15 + 2 * 12.65 + 14.81);
		ceny = (5.335 * r_hand[1].toDouble() + 
			5.335 * l_hand[1].toDouble() + 17.555 * r_foot[1].toDouble() + 
			17.555 * l_foot[1].toDouble() + 54.15 * torso[1].toDouble() + 
			12.65 * waist[1].toDouble() + 14.81 * hip[1].toDouble()) / 
		(5.335 + 2 * 17.555 + 54.15 + 2 * 12.65 + 14.81);

		ceny = scale(-0.5, 0.5, -0.9, 0.9, ceny);

		cenz = (5.335 * r_hand[2].toDouble() + 
			5.335 * l_hand[2].toDouble() + 17.555 * r_foot[2].toDouble() + 
			17.555 * l_foot[2].toDouble() + 54.15 * torso[2].toDouble() + 
			12.65 * waist[2].toDouble() + 14.81 * hip[2].toDouble()) / 
		(5.335 + 2 * 17.555 + 54.15 + 2 * 12.65 + 14.81);

		if (queue.size() == 10) {
			avgLast10 = calculateAverage(queue);
			if (Math.abs(ceny - avgLast10) > 1)
			{
				queue.add(ceny);
				ceny = avgLast10;
				queue.remove();
				post ("got here");
			}
		}
		else
			queue.add(ceny);

		outlet(0, ceny);
		outlet(1, hand_dist);
		outlet(2, foot_dist);

		cenbuffer.append(ceny);
		cenbuffer.append(System.getProperty("line.separator"));
		ybuffer.append(bod[1].toDouble());
		ybuffer.append(System.getProperty("line.separator"));

	}
}
/*
		try(PrintWriter out = new PrintWriter(new BufferedWriter
			(new FileWriter("C:\\Users\\pcpet_000\\Documents\\GitHub\\IWProject\\TestText.txt", true)))){
			out.println (Float.toString(f));
		}

    catch (IOException e) {
			e.printStackTrace();
		}	

*/	/*public void anything(String s, Atom[] args) {
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
