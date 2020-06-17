package map;

import cells.Cell;
import enemy.Enemy;

public interface IMapProducer {
	Cell[][] getMapCells();
	Enemy[][] getMapEnemies();
	int getMapHeight();
	int getMapWidth();
	int getIEntrance();
	int getJEntrance();
}
