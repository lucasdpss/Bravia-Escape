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
		image = Toolkit.getDefaultToolkit().getImage("assets\\graphics\\tiles\\floor_tile.png");
	}
}
