package map;

import enemy.*;

import java.util.ArrayList;

import bravia.Bravia;
import cells.Cell;

public interface IMapProperties {
	int getMapHeight();
	int getMapWidth();
	void setMapHeight(int mapHeight);
	void setMapWidth(int mapWidth);
	Cell getCell(int i, int j);
	Enemy getEnemy(int i, int j);
	Cell[][] getMapCell();
	Enemy[][] getMapEnemy();
	void setMapCell(Cell[][] mapCell);
	void setMapEnemy(Enemy[][] mapEnemy);
	Bravia getBravia();
	void setBravia(Bravia bravia);
	int getIBravia();
	int getJBravia();
	ArrayList<Enemy> listEnemies();
}
