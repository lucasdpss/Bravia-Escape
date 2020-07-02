package cells;

import java.awt.Toolkit;
import java.io.File;

import bravia.Bravia;
import main.Window;

public class Exit extends Cell {
	private Window window;
	
	public Exit(int iPos, int jPos, Window window){
		this.window = window;
		this.iPos = iPos;
		this.jPos = jPos;
		lit = false;
		permanentlyLit = false;
		walkableBravia = true;
		walkableEnemy = false;
		image = Toolkit.getDefaultToolkit().getImage("assets"+File.separatorChar+"graphics"+File.separatorChar+"tiles"+File.separatorChar+"exit_tile.png");
	}
	
	public void activate(Bravia bravia) {
		window.nextWindow();
	}
}
