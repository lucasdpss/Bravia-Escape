package map;

import enemy.*;
import cells.Cell;

public interface IMapProducer {
	Cell[][] getMapCells();
	Enemy[][] getMapEnemies();
	int getMapHeight();
	int getMapWidth();
	int getIEntrance();
	int getJEntrance();
}
