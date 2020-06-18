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
		walkable = false;
		this.color = color;
		cellType = "G" + color;
		open = false;
		
		switch(color) {
		case BLUE:
			image = Toolkit.getDefaultToolkit().getImage("resources\\graphics\\tiles\\gate_blue_tile.png");
			break;
		case GREEN:
			image = Toolkit.getDefaultToolkit().getImage("resources\\graphics\\tiles\\gate_green_tile.png");
			break;
		case RED:
			image = Toolkit.getDefaultToolkit().getImage("resources\\graphics\\tiles\\gate_red_tile.png");
			break;
		case YELLOW:
			image = Toolkit.getDefaultToolkit().getImage("resources\\graphics\\tiles\\gate_yellow_tile.png");
			break;
		}
		
	}
	
	public void activate(Bravia pocket) {
		if(!open) {
			if(pocket.hasKey(color)) {
				open = true;
				walkable = true;
				image = Toolkit.getDefaultToolkit().getImage("resources\\graphics\\tiles\\gate_open_tile.png");
			}
		}
	}
}
