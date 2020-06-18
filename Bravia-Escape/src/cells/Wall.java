package cells;

import java.awt.Toolkit;

public class Wall extends Cell {
	public Wall(){
		lit = false;
		permanentlyLit = false;
		walkable = false;
		cellType = "Wa";
		image = Toolkit.getDefaultToolkit().getImage("resources\\graphics\\tiles\\wall_tile.png");
	}
}
