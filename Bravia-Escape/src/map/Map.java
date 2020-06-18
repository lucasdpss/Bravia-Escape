package map;

import bravia.Bravia;
import cells.Cell;
import enemy.Enemy;

public class Map implements IMap{
	private Cell[][] mapCell;
	private Enemy[][] mapEnemy;
	private int mapHeight, mapWidth;
	private Bravia bravia;

	public Map() {

	}

	public void illuminate(int range, int iSource, int jSource) {
		for(int j = jSource-range; j <= jSource+range; j++) {
			
			if(j == jSource-range || j == jSource+range) {
				for(int i = iSource-(range-1); i <= iSource+(range-1); i++) {
					if(i >= 0 && i < mapHeight && j >= 0 && j < mapWidth) {
						mapCell[i][j].setLit(true);
					}
				}
			}else {
				for(int i = iSource-(range); i <= iSource+(range); i++) {
					if(i >= 0 && i < mapHeight && j >= 0 && j < mapWidth) {
						mapCell[i][j].setLit(true);
					}
				}
			}
			
		}
	}
	
	public void illuminatePermanently(int range, int iSource, int jSource) {
		for(int j = jSource-range; j <= jSource+range; j++) {
			
			if(j == jSource-range || j == jSource+range) {
				for(int i = iSource-(range-1); i <= iSource+(range-1); i++) {
					if(i >= 0 && i < mapHeight && j >= 0 && j < mapWidth) {
						mapCell[i][j].setPermanentlyLit(true);
					}
				}
			}else {
				for(int i = iSource-(range); i <= iSource+(range); i++) {
					if(i >= 0 && i < mapHeight && j >= 0 && j < mapWidth) {
						mapCell[i][j].setPermanentlyLit(true);
					}
				}
			}
			
		}
	}

	public void clearLights() {
		for(int i=0; i < mapHeight; i++) {
			for(int j=0; j < mapWidth; j++) {
				mapCell[i][j].setLit(false);
			}
		}
	}

	public void moveEnemies() {

	}
	public Enemy getEnemy(int i, int j) {
		return null;
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
	public void setBravia(Bravia bravia) {
		this.bravia = bravia;
	}

	public int getMapHeight() {
		return mapHeight;
	}
	public int getMapWidth() {
		return mapWidth;
	}
	public Cell[][] getMapCells() {
		return mapCell;
	}
	public Cell getCell(int i, int j){
		if(i >= 0 && i <= mapHeight && j >= 0 && j <= mapWidth) {
			return mapCell[i][j];
		}
		return null;
	}
	public Enemy[][] getMapEnemy() {
		return mapEnemy;
	}
	public int getIBravia() {
		return bravia.getIPos();
	}
	public int getJBravia() {
		return bravia.getJPos();
	}
	public Bravia getBravia() {
		return bravia;
	}
}
