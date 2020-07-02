package cells;

import java.awt.Toolkit;
import java.io.File;

import bravia.Bravia;

public class Key extends Cell {
	private Color color;
	private boolean withKey; //determina se a chave ainda está lá ou já foi apanhada
	
	public Key(int iPos, int jPos,Color color){
		this.iPos = iPos;
		this.jPos = jPos;
		lit = false;
		permanentlyLit = false;
		walkableBravia = true;
		walkableEnemy = true;
		this.color = color;
		withKey = true;
		
		switch(color) {
		case BLUE:
			image = Toolkit.getDefaultToolkit().getImage("assets"+File.separatorChar+"graphics"+File.separatorChar+"tiles"+File.separatorChar+"key_blue_tile.png");
			break;
		case GREEN:
			image = Toolkit.getDefaultToolkit().getImage("assets"+File.separatorChar+"graphics"+File.separatorChar+"tiles"+File.separatorChar+"key_green_tile.png");
			break;
		case RED:
			image = Toolkit.getDefaultToolkit().getImage("assets"+File.separatorChar+"graphics"+File.separatorChar+"tiles"+File.separatorChar+"key_red_tile.png");
			break;
		case YELLOW:
			image = Toolkit.getDefaultToolkit().getImage("assets"+File.separatorChar+"graphics"+File.separatorChar+"tiles"+File.separatorChar+"key_yellow_tile.png");
			break;
		}
	}
	
	public void activate(Bravia pocket) {
		if(withKey) {
			pocket.addKey(color);
			image = Toolkit.getDefaultToolkit().getImage("assets"+File.separatorChar+"graphics"+File.separatorChar+"tiles"+File.separatorChar+"floor_tile.png");
			withKey = false;
		}
	}
}
