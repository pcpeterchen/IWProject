import com.cycling74.max.*;
import java.io.*;


public class DataCollect extends MaxObject {

	public void bang() {
		post("hello bang!");
		outletBang(0);
	}

	public void loadbang() {
		post("welcome to the patch!");
	}
	
	public void inlet(int i) {
		post("hello integer " + i*10 + "!");
		outlet(0, i*10);
	}
	
	public void inlet(float f) {
		try(PrintWriter out = new PrintWriter(new BufferedWriter
			(new FileWriter("C:\\Users\\pcpet_000\\Documents\\GitHub\\IWProject\\TestText.txt", true)))){
			out.println (Float.toString(f));
		}

    catch (IOException e) {
			e.printStackTrace();
		}	
		post("hello float " + f + "!");
		outlet(0, f);
	}
	
	public void list(Atom[] a) {
		post("hello list " + Atom.toOneString(a) + "!");
		outlet(0, a);
	}
	
	public void anything(String s, Atom[] args) {
		post("hello anything " + s + " " + Atom.toOneString(args) + "!");
		outlet(0, s, args);
	}
}