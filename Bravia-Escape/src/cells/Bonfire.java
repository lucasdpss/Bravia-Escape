package cells;

import java.awt.Toolkit;

import bravia.Bravia;

public class Bonfire extends Cell {
	private boolean isLighted;
	private int fireRange;
	
	public Bonfire(int iPos, int jPos){
		this.iPos = iPos;
		this.jPos = jPos;
		lit = false;
		permanentlyLit = false;
		walkable = true;
		cellType = "Bf";
		isLighted = false;
		fireRange = 2;
		image = Toolkit.getDefaultToolkit().getImage("resources\\graphics\\tiles\\bonfire_unlit_tile.png");
	}
	
	                          //colocar para salvar o checkpoint
	public void activate(Bravia bravia) { 
		if(isLighted) walkable = false;
		permanentlyLit = true;
		image = Toolkit.getDefaultToolkit().getImage("resources\\graphics\\tiles\\bonfire_lit_tile.gif");
		bravia.getMap().illuminatePermanently(fireRange, iPos, jPos);
		isLighted = true;
	}
	
	public int getFireRange() {
		return fireRange;
	}
}
