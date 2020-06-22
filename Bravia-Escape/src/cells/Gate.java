package cells;

import java.awt.Toolkit;

import bravia.Bravia;

public class Gate extends Cell {
	private Color color;
	private boolean open;
	
	public Gate(int iPos, int jPos,Color color){
		this.iPos = iPos;
		this.jPos = jPos;
		lit = false;
		permanentlyLit = false;
		walkableBravia = false;
		walkableEnemy = false;
		this.color = color;
		cellType = "G" + color;
		open = false;
		
		String imagePath = "resources\\graphics\\tiles\\gate_" + color.name().toLowerCase() + "_locked.png";
		image = Toolkit.getDefaultToolkit().getImage(imagePath);
	}
	
	public void activate(Bravia pocket) {
		if(!open) {
			if(pocket.hasKey(color)) {
				open = true;
				walkableBravia = true;
				String imagePath = "resources\\graphics\\tiles\\gate_" + color.name().toLowerCase() + "_unlocked.png";
				image = Toolkit.getDefaultToolkit().getImage(imagePath);
			}
		}
	}
}
