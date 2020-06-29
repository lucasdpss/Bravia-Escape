package cells;

import java.awt.Toolkit;

public class Floor extends Cell{	
	public Floor(int iPos, int jPos){
		this.iPos = iPos;
		this.jPos = jPos;
		lit = false;
		permanentlyLit = false;
		walkableBravia = true;
		walkableEnemy = true;
		cellType = "--";
		image = Toolkit.getDefaultToolkit().getImage("resources\\graphics\\tiles\\floor_tile.png");
	}
}
