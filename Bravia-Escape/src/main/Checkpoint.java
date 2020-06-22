package main;

import enemy.*;
import bravia.IPocket;
import cells.Cell;
import cells.Color;

public class Checkpoint {
	private static int startIPos, startJPos;
	private static Cell[][] mapCell;
	private static Enemy[][] mapEnemy;
	private static boolean[] keyInventory;
	
	public static void setStartPos(int iPos, int jPos) {
		startIPos = iPos;
		startJPos = jPos;
	}
	
	public static int getStartIPos() {
		return startIPos;
	}
	
	public static int getStartJPos() {
		return startJPos;
	}
	
	public static void setMapCell(Cell[][] cellGrid) {
		mapCell = new Cell[cellGrid.length][cellGrid[0].length];
		for(int i=0 ; i<cellGrid.length ; i++)
			System.arraycopy(cellGrid[i], 0, mapCell[i], 0, cellGrid[i].length);
	}
	
	public static Cell[][] getMapCell(){
		return mapCell;
	}
	
	public static void setMapEnemy(Enemy[][] enemyGrid) {
		mapEnemy = new Enemy[enemyGrid.length][enemyGrid[0].length];
		for(int i=0 ; i<enemyGrid.length ; i++)
			System.arraycopy(enemyGrid[i], 0, mapEnemy[i], 0, enemyGrid[i].length);
	}
	
	public static Enemy[][] getMapEnemy(){
		return mapEnemy;
	}
	
	public static void setKeyInventory(IPocket pocket) {
		keyInventory = new boolean[Color.values().length];
		for(Color color : Color.values()) {
			if(pocket.hasKey(color)) 
				keyInventory[color.getIndex()] = true;
		}
	}
	
	public static boolean[] getKeyInventory() {
		return keyInventory;
	}
}
