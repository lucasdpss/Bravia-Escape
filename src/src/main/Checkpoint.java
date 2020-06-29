package main;

import bravia.IPocket;
import cells.Cell;
import cells.Color;
import enemy.Enemy;
import map.Map;

public class Checkpoint {
	private static int startIPos, startJPos;
	private static Cell[][] mapCell;
	private static Enemy[][] mapEnemy;
	private static boolean[] keyInventory;
	private static Map map;
	
	public static void setStartPos(int iPos, int jPos) {
		startIPos = iPos;
		startJPos = jPos;
	}
	
	public static void setMap(Map mapGenerated) { //so recebe na construcao do mapa, nao eh usado nas fogueiras
		map = mapGenerated;
	}
	
	public static Map getMap() {
		return map;
	}
	
	public static int getStartIPos() {
		return startIPos;
	}
	
	public static int getStartJPos() {
		return startJPos;
	}
	
	public static void setMapCell(Cell[][] cellGrid) throws CloneNotSupportedException {
		int mapHeight = cellGrid.length;
		int mapWidth = cellGrid[0].length;
		mapCell = new Cell[mapHeight][mapWidth];
		
		for(int i=0; i < mapHeight; i++) {
			for(int j=0; j < mapWidth; j++) {
				mapCell[i][j] = (Cell) cellGrid[i][j].clone();
			}
		}
	}
	
	public static Cell[][] getMapCell() throws CloneNotSupportedException{
		int mapHeight = mapCell.length;
		int mapWidth = mapCell[0].length;
		Cell[][] newMapCell = new Cell[mapHeight][mapWidth];
		
		for(int i=0; i < mapHeight; i++) {
			for(int j=0; j < mapWidth; j++) {
				newMapCell[i][j] = (Cell) mapCell[i][j].clone();
			}
		}
		return newMapCell;
	}
	
	public static void setMapEnemy(Enemy[][] enemyGrid) throws CloneNotSupportedException {
		int mapHeight = enemyGrid.length;
		int mapWidth = enemyGrid[0].length;
		mapEnemy = new Enemy[mapHeight][mapWidth];
		
		for(int i=0; i < mapHeight; i++) {
			for(int j=0; j < mapWidth; j++) {
				Enemy enemy = enemyGrid[i][j];
				if(enemy != null) {
					mapEnemy[i][j] = (Enemy) enemy.clone();
				}
			}
		}
	}
	
	public static Enemy[][] getMapEnemy() throws CloneNotSupportedException{
		int mapHeight = mapEnemy.length;
		int mapWidth = mapEnemy[0].length;
		Enemy[][] newMapEnemy = new Enemy[mapHeight][mapWidth];
		
		for(int i=0; i < mapHeight; i++) {
			for(int j=0; j < mapWidth; j++) {
				Enemy enemy = mapEnemy[i][j];
				if(enemy != null) {
					newMapEnemy[i][j] = (Enemy) enemy.clone();
				}
			}
		}
		
		return newMapEnemy;
	}
	
	public static void setKeyInventory(IPocket pocket) {
		keyInventory = new boolean[Color.values().length];
		for(Color color : Color.values()) {
			if(pocket.hasKey(color)) 
				keyInventory[color.getIndex()] = true;
		}
	}
	
	public static boolean[] getKeyInventory() {
		boolean[] newKeyInventory = new boolean[keyInventory.length];
		for(int i=0; i < keyInventory.length; i++) {
			newKeyInventory[i] = keyInventory[i];
		}
		return newKeyInventory;
	}
}
