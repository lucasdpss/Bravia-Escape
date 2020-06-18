package cells;

import java.awt.Toolkit;

public class Exit extends Cell {
	public Exit(int iPos, int jPos){
		this.iPos = iPos;
		this.jPos = jPos;
		lit = false;
		permanentlyLit = false;
		walkable = true;
		cellType = "Ex";
		image = Toolkit.getDefaultToolkit().getImage("resources\\graphics\\tiles\\exit_tile.png");
	}
}
