package cells;

import java.awt.Toolkit;

public class Wall extends Cell {
	public Wall(int iPos, int jPos){
		this.iPos = iPos;
		this.jPos = jPos;
		lit = false;
		permanentlyLit = false;
		walkableEnemy = false;
		walkableBravia = false;
		image = Toolkit.getDefaultToolkit().getImage("assets\\graphics\\tiles\\wall_tile.png");
	}
}
