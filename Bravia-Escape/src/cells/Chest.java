package cells;

import java.awt.Toolkit;

import bravia.Bravia;

public class Chest extends Cell {
	public Chest(int iPos, int jPos){
		this.iPos = iPos;
		this.jPos = jPos;
		lit = false;
		permanentlyLit = false;
		walkableBravia = true;
		walkableEnemy = true;
		cellType = "Ch";
		image = Toolkit.getDefaultToolkit().getImage("resources\\graphics\\tiles\\chest-closed.png");
	}
	
	public void activate(Bravia bravia) {
		
	}
}
