package map;

import cells.Cell;
import enemy.Enemy;

public interface IMapProperties {
	int getMapHeight();
	int getMapWidth();
	Cell getCell(int i, int j);
	Enemy getEnemy(int i, int j);
	Cell[][] getMapCells();
	int getIBravia();
	int getJBravia();
}
