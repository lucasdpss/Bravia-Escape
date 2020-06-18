package cells;

import java.awt.Toolkit;

import bravia.IPocket;

public class Key extends Cell {
	private Color color;
	private boolean withKey; //determina se a chave ainda está lá ou já foi apanhada
	
	public Key(Color color){
		lit = false;
		permanentlyLit = false;
		walkable = true;
		this.color = color;
		cellType = "K" + color;
		withKey = true;
		
		switch(color) {
		case BLUE:
			image = Toolkit.getDefaultToolkit().getImage("resources\\graphics\\tiles\\key_blue_tile.png");
			break;
		case GREEN:
			image = Toolkit.getDefaultToolkit().getImage("resources\\graphics\\tiles\\key_green_tile.png");
			break;
		case RED:
			image = Toolkit.getDefaultToolkit().getImage("resources\\graphics\\tiles\\key_red_tile.png");
			break;
		case YELLOW:
			image = Toolkit.getDefaultToolkit().getImage("resources\\graphics\\tiles\\key_yellow_tile.png");
			break;
		}
	}
	
	public void activate(IPocket pocket) {
		if(withKey) {
			pocket.addKey(color);
			image = Toolkit.getDefaultToolkit().getImage("resources\\graphics\\tiles\\floor_tile.png");
			withKey = false;
		}
	}
}
