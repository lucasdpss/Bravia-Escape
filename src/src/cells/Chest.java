package cells;

import java.awt.Toolkit;

public class Chest extends Cell {
	public Chest(int iPos, int jPos){
		this.iPos = iPos;
		this.jPos = jPos;
		lit = false;
		permanentlyLit = false;
		walkableBravia = true;
		walkableEnemy = true;
		image = Toolkit.getDefaultToolkit().getImage("assets\\graphics\\tiles\\chest-closed.png");
	}
}
