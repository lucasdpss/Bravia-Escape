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
		
		String imagePath = "assets"+File.separatorChar+"graphics"+File.separatorChar+"tiles"+File.separatorChar+"key_" + color.name().toLowerCase() + "_tile.png";
		image = Toolkit.getDefaultToolkit().getImage(imagePath);
	}
	
	public void activate(Bravia pocket) {
		if(withKey) {
			pocket.addKey(color);
			image = Toolkit.getDefaultToolkit().getImage("assets"+File.separatorChar+"graphics"+File.separatorChar+"tiles"+File.separatorChar+"floor_tile.png");
			withKey = false;
		}
	}
}
