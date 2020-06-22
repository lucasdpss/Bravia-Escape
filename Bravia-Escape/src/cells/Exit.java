package cells;

import java.awt.Toolkit;

import bravia.Bravia;

public class Exit extends Cell {
	public Exit(int iPos, int jPos){
		this.iPos = iPos;
		this.jPos = jPos;
		lit = false;
		permanentlyLit = false;
		walkableBravia = true;
		walkableEnemy = false;
		cellType = "Ex";
		image = Toolkit.getDefaultToolkit().getImage("resources\\graphics\\tiles\\exit_tile.png");
	}
	
	public void activate(Bravia bravia) {
		
	}
}
