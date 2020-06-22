package bravia;

import java.awt.Image;
import java.awt.Toolkit;

import cells.Cell;
import cells.Color;
import items.Item;
import map.Map;

public class Bravia implements IBravia {
	private int iPos, jPos;
	private int invisibleCounter;
	private int strengthCounter;
	private int torchRange;
	Item[] inventory;
	boolean[] keyInventory;
	private Map map;
	private Image image;
	
	public Bravia(Map map, int iPos, int jPos) {
		this.map = map;
		this.iPos = iPos;
		this.jPos = jPos;
		this.image = Toolkit.getDefaultToolkit().getImage("resources\\graphics\\bravia_front.gif");
		invisibleCounter = 0;
		strengthCounter = 0;
		torchRange = 2;
		inventory = new Item[2];
		keyInventory = new boolean[Color.values().length];
	}
	
	//Falta implementar o que acontece quando ha monstro
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
	
	//Falta implementar:
	public void useItem(int slot) {
		
	}
	
	//Falta implementar:
	public void addItem(Item item) {
		
	}
	
	//Falta implementar:
	public Item getItem(int slot) {
		return null;
	}
	
	public void addKey(Color color) {
		keyInventory[color.index] = true;
	}
	
	public boolean hasKey(Color color) {
		return keyInventory[color.index];
	}
	
	public int totalItems() {
		if(inventory == null) return 0;
		int total = 0;
		for(int i=0; i < 4;i++) {
			if(inventory[i] != null) total++;
		}
		return total;
	}
	
	public int totalKeys() {
		if(keyInventory == null) return 0;
		int total = 0;
		for(int i=0; i < Color.values().length;i++) {
			if(keyInventory[i]) total++;
		}
		return total;
	}
	
	public boolean isInvisible() {
		return (invisibleCounter > 0);
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
	public Map getMap() {
		return map;
	}
}
