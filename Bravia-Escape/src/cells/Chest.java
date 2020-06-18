package cells;

import java.awt.Toolkit;

public class Chest extends Cell {
	public Chest(int iPos, int jPos){
		this.iPos = iPos;
		this.jPos = jPos;
		lit = false;
		permanentlyLit = false;
		walkable = true;
		cellType = "Ch";
		image = Toolkit.getDefaultToolkit().getImage("resources\\graphics\\tiles\\chest-closed.png");
	}
}
