package cells;

import java.awt.Toolkit;
import java.io.File;

public class Wall extends Cell {
	public Wall(int iPos, int jPos){
		this.iPos = iPos;
		this.jPos = jPos;
		lit = false;
		permanentlyLit = false;
		walkableEnemy = false;
		walkableBravia = false;
		image = Toolkit.getDefaultToolkit().getImage("assets"+File.separatorChar+"graphics"+File.separatorChar+"tiles"+File.separatorChar+"wall_tile.png");
	}
}
