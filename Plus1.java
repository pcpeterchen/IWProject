
import com.cycling74.max.*;

public class Plus1 extends MaxObject {

	private int addend = 0;
	
	public Plus1() {
		declareInlets(new int[]{DataTypes.INT, DataTypes.INT});
		declareOutlets(new int[]{DataTypes.INT});
	}
	
	public void inlet(int i) {
		if (getInlet() == 0) {
			outlet(0, i+addend);
		} else {
			addend = i;
		}
	}
}