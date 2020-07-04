package cells;

import java.awt.Toolkit;
import java.io.File;

import bravia.Bravia;
import main.ILevelController;

public class Exit extends Cell {
	private ILevelController levelController;
	
	public Exit(int iPos, int jPos, ILevelController levelController){
		this.levelController = levelController;
		this.iPos = iPos;
		this.jPos = jPos;
		lit = false;
		permanentlyLit = false;
		walkableBravia = true;
		walkableEnemy = false;
		image = Toolkit.getDefaultToolkit().getImage("assets"+File.separatorChar+"graphics"+File.separatorChar+"tiles"+File.separatorChar+"exit_tile.png");
	}
	
	public void activate(Bravia bravia) {
		levelController.nextWindow();
	}
}
