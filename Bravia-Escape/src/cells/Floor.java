package cells;

import java.awt.Toolkit;

public class Floor extends Cell{	
	public Floor(){
		lit = false;
		permanentlyLit = false;
		walkable = true;
		cellType = "--";
		image = Toolkit.getDefaultToolkit().getImage("resources\\graphics\\tiles\\floor_tile.png");
	}
}
