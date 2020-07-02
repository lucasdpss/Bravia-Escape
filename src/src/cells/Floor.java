package cells;

import java.awt.Toolkit;
import java.io.File;

public class Floor extends Cell{	
	public Floor(int iPos, int jPos){
		this.iPos = iPos;
		this.jPos = jPos;
		lit = false;
		permanentlyLit = false;
		walkableBravia = true;
		walkableEnemy = true;
		image = Toolkit.getDefaultToolkit().getImage("assets"+File.separatorChar+"graphics"+File.separatorChar+"tiles"+File.separatorChar+"floor_tile.png");
	}
}
