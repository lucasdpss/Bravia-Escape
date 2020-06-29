package bravia;

import java.awt.Image;
import java.awt.Toolkit;

import cells.Cell;
import cells.Color;
import map.IMapProperties;

public class Bravia implements IBravia {
	private int iPos, jPos;
	private int torchRange;
	private boolean[] keyInventory;
	private IMapProperties map;
	private Image image;
	
	public Bravia(IMapProperties map, int iPos, int jPos) {
		this.map = map;
		this.iPos = iPos;
		this.jPos = jPos;
		this.image = Toolkit.getDefaultToolkit().getImage("assets\\graphics\\bravia_front.gif");
		torchRange = 2;
		keyInventory = new boolean[Color.values().length];
	}
	
	public void move(char direction) {
		Cell[][] mapCells = map.getMapCell();
		
		switch (direction) {
		case 'U':
			if(iPos - 1 >= 0) {
				mapCells[iPos-1][jPos].activate(this);
				if(mapCells[iPos-1][jPos].isWalkableBravia())
					iPos--;
			}
			break;
			
		case 'R':
			if(jPos + 1 < map.getMapWidth()) {
				mapCells[iPos][jPos+1].activate(this);
				if(mapCells[iPos][jPos+1].isWalkableBravia())
					jPos++;
			}
			break;
			
		case 'D':
			if(iPos + 1 < map.getMapHeight()) {
				mapCells[iPos+1][jPos].activate(this);
				if(mapCells[iPos+1][jPos].isWalkableBravia())
					iPos++;
			}
			break;
			
		case 'L':
			if(jPos - 1 >= 0) {
				mapCells[iPos][jPos-1].activate(this);
				if(mapCells[iPos][jPos-1].isWalkableBravia())
					jPos--;
			}
			break;

		default:
			break;
		}
	}
	
	
	public void addKey(Color color) {
		keyInventory[color.index] = true;
	}
	
	public boolean hasKey(Color color) {
		return keyInventory[color.index];
	}
	
	public void setKeyInventory(boolean[] keyInventory) {
		this.keyInventory = keyInventory;
	}
	
	public void setIPos(int iPos) {
		this.iPos = iPos;
	}
	
	public void setJPos(int jPos) {
		this.jPos = jPos;
	}
	
	public int getIPos() {
		return iPos;
	}
	
	public int getJPos() {
		return jPos;
	}
	
	public int getRange() {
		return torchRange;
	}
	
	public Image getImage() {
		return image;
	}
}
