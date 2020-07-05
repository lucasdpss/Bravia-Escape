package cells;

import java.awt.Toolkit;
import java.io.File;

import bravia.Bravia;
import main.Checkpoint;
import map.Map;

public class Bonfire extends Cell {
	private boolean hasFire;
	private int fireRange;
	private Map map;
	
	public Bonfire(int iPos, int jPos, Map map){
		this.iPos = iPos;
		this.jPos = jPos;
		this.map = map;
		lit = false;
		permanentlyLit = false;
		walkableBravia = true;
		walkableEnemy = false;
		hasFire = false;
		fireRange = 2;
		image = Toolkit.getDefaultToolkit().getImage("assets"+File.separatorChar+"graphics"+File.separatorChar+"tiles"+File.separatorChar+"bonfire_unlit_tile.png");
	}
	
	                          //colocar para salvar o checkpoint
	public void activate(Bravia bravia) { 
		if(!hasFire) {
			walkableBravia = false;
			permanentlyLit = true;
			image = Toolkit.getDefaultToolkit().getImage("assets"+File.separatorChar+"graphics"+File.separatorChar+"tiles"+File.separatorChar+"bonfire_lit_tile.gif");
			map.illuminatePermanently(fireRange, iPos, jPos);
			
			try {
				Checkpoint.setStartPos(iPos, jPos);
				Checkpoint.setKeyInventory(bravia);
				Checkpoint.setMapCell(map.getMapCell());
				Checkpoint.setMapEnemy(map.getMapEnemy());
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			
			hasFire = true;
		}
	}
	
	public int getFireRange() {
		return fireRange;
	}
}
