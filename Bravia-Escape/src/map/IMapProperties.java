package map;

import enemy.*;
import cells.Cell;

public interface IMapProperties {
	int getMapHeight();
	int getMapWidth();
	Cell getCell(int i, int j);
	Enemy getEnemy(int i, int j);
	Cell[][] getMapCell();
	int getIBravia();
	int getJBravia();
}
