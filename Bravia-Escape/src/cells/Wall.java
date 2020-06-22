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
		cellType = "Wa";
		image = Toolkit.getDefaultToolkit().getImage("resources\\graphics\\tiles\\wall_tile.png");
	}
}
