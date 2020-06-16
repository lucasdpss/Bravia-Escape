package bravia;

import items.Item;
import map.IMapProperties;

public class Bravia implements IBravia {
	private int iPos, jPos;
	private int invisibleCounter;
	private int strengthCounter;
	private int torchRange;
	Item[] inventory;
	boolean[] keyInventory;
	IMapProperties map;
	
	public Bravia(IMapProperties map, int iPos, int jPos) {
		this.map = map;
		this.iPos = iPos;
		this.jPos = jPos;
		invisibleCounter = 0;
		strengthCounter = 0;
		torchRange = 2;
		inventory = new Item[2];
		keyInventory = new boolean[5];
	}
	
	//Falta implementar:
	public void move(char direction) {
		
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
	
	public boolean isInvisible() {
		return (invisibleCounter > 0);
	}
	
	public int getIPos() {
		return iPos;
	}
	
	public int getJPos() {
		return jPos;
	}
}
