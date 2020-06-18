package cells;

import java.awt.Toolkit;

public class Chest extends Cell {
	public Chest(){
		lit = false;
		permanentlyLit = false;
		walkable = true;
		cellType = "Ch";
		image = Toolkit.getDefaultToolkit().getImage("resources\\graphics\\tiles\\chest_closed.png");
	}
}
