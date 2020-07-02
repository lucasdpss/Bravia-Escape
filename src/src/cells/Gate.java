package cells;

import java.awt.Toolkit;
import java.io.File;

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
		open = false;
		
		String imagePath = "assets"+File.separatorChar+"graphics"+File.separatorChar+"tiles"+File.separatorChar+"gate_" + color.name().toLowerCase() + "_locked.png";
		image = Toolkit.getDefaultToolkit().getImage(imagePath);
	}
	
	public void activate(Bravia pocket) {
		if(!open) {
			if(pocket.hasKey(color)) {
				open = true;
				walkableBravia = true;
				String imagePath = "assets"+File.separatorChar+"graphics"+File.separatorChar+"tiles"+File.separatorChar+"gate_" + color.name().toLowerCase() + "_unlocked.png";
				image = Toolkit.getDefaultToolkit().getImage(imagePath);
			}
		}
	}
}
