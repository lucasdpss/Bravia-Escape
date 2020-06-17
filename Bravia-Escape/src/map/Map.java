package map;

import cells.Cell;
import enemy.Enemy;

public class Map implements IMap{
	private Cell[][] mapCell;
	private Enemy[][] mapEnemy;
	private int mapHeight, mapWidth;
	private int IBravia, JBravia;
	
	public Map() {
		
	}
	
	
	public void setIBravia(int IBravia) {
		this.IBravia = IBravia;
	}
	public void setJBravia(int JBravia) {
		this.JBravia = JBravia;
	}
	public void setMapHeight(int mapHeight) {
		this.mapHeight = mapHeight;
	}
	public void setMapWidth(int mapWidth) {
		this.mapWidth = mapWidth;
	}
	public void setMapCell(Cell[][] mapCell) {
		this.mapCell = mapCell;
	}
	public void setMapEnemy(Enemy[][] mapEnemy) {
		this.mapEnemy = mapEnemy;
	}
	
	
	public int getMapHeight() {
		return mapHeight;
	}
	public int getMapWidth() {
		return mapWidth;
	}
	public Cell[][] getMapCell() {
		return mapCell;
	}
	public Enemy[][] getMapEnemy() {
		return mapEnemy;
	}
	public int getIBravia() {
		return IBravia;
	}
	public int getJBravia() {
		return JBravia;
	}
}
