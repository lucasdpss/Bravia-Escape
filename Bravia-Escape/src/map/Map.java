package map;

import java.util.LinkedList;
import java.util.Queue;

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
		mapCell[iSource][jSource].setLit(true);
		for(int j = jSource-range; j <= jSource+range; j++) {
			
			if(j == jSource-range || j == jSource+range) {
				for(int i = iSource-(range-1); i <= iSource+(range-1); i++) {
					if(i >= 0 && i < mapHeight && j >= 0 && j < mapWidth) {
						int distance = distanceToCell(i, j, iSource, jSource);
						if(distance > 0 && distance <= range+2) mapCell[i][j].setLit(true);
					}
				}
			}else {
				for(int i = iSource-(range); i <= iSource+(range); i++) {
					if(i >= 0 && i < mapHeight && j >= 0 && j < mapWidth) {
						int distance = distanceToCell(i, j, iSource, jSource);
						if(distance > 0 && distance <= range+2) mapCell[i][j].setLit(true);
					}
				}
			}
			
		}
	}
	
	public void illuminatePermanently(int range, int iSource, int jSource) {
		mapCell[iSource][jSource].setPermanentlyLit(true);
		for(int j = jSource-range; j <= jSource+range; j++) {
			if(j == jSource-range || j == jSource+range) {
				for(int i = iSource-(range-1); i <= iSource+(range-1); i++) {
					if(i >= 0 && i < mapHeight && j >= 0 && j < mapWidth) {
						int distance = distanceToCell(i, j, iSource, jSource);
						if(distance > 0 && distance <= range+2) mapCell[i][j].setPermanentlyLit(true);
					}
				}
			}else {
				for(int i = iSource-(range); i <= iSource+(range); i++) {
					if(i >= 0 && i < mapHeight && j >= 0 && j < mapWidth) {
						int distance = distanceToCell(i, j, iSource, jSource);
						if(distance > 0 && distance <= range+2) mapCell[i][j].setPermanentlyLit(true);
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
	
	//retorna a distancia entre dois pontos do mapa, -1 caso nao eh possivel chegar
		public int distanceToCell(int iSource, int jSource, int iDest, int jDest) {
			/*** preparar ambiente e variaveis para BFS ***/
			boolean visited[][] = new boolean[mapHeight][mapWidth];

			for(int i=0;i < mapHeight; i++) {
				for(int j=0; j < mapWidth; j++) {
					if(mapCell[i][j].isWalkable()) {
						visited[i][j] = false;
					}else {
						visited[i][j] = true;
					}
				}
			}
			visited[iDest][jDest] = false; 
			visited[iSource][jSource] = true;

			QElement source = new QElement(iSource,jSource,0);
			Queue<QElement> q = new LinkedList<QElement>();   //Queue eh uma interface, precisa instaciar ela em algo pra usar

			/*** comeca a BFS ***/
			q.add(source);
			while(!q.isEmpty()) {
				QElement p = q.poll(); //pega o primeiro e ja o exclui

				if(p.getIPos() == iDest && p.getJPos() == jDest) { //encontrou o destino
					return p.getDistance();
				}

				//moving up
				if(p.getIPos() - 1 >= 0 && visited[p.getIPos()-1][p.getJPos()] == false) {
					q.add(new QElement(p.getIPos()-1,p.getJPos(),p.getDistance()+1));
					visited[p.getIPos() - 1][p.getJPos()] = true;
				}
				//moving down
				if(p.getIPos() + 1 < mapHeight && visited[p.getIPos()+1][p.getJPos()] == false) {
					q.add(new QElement(p.getIPos()+1,p.getJPos(),p.getDistance()+1));
					visited[p.getIPos() + 1][p.getJPos()] = true;
				}
				//moving left
				if(p.getJPos() - 1 >= 0 && visited[p.getIPos()][p.getJPos()-1] == false) {
					q.add(new QElement(p.getIPos(),p.getJPos()-1,p.getDistance()+1));
					visited[p.getIPos()][p.getJPos()-1] = true;
				}
				//moving right
				if(p.getJPos() + 1 < mapWidth && visited[p.getIPos()][p.getJPos()+1] == false) {
					q.add(new QElement(p.getIPos(),p.getJPos()+1,p.getDistance()+1));
					visited[p.getIPos()][p.getJPos()+1] = true;
				}
			}
			return -1; //terminou a queue e nao encontrou caminho para Dest.
		}

	public void moveEnemies() {          //falta implementar

	}
	
	public Enemy getEnemy(int i, int j) { //falta implementar
		return null;
	}
	
	private class QElement{ //elementos que vao poder ser enfileirados para a BFS
		private int iPos,jPos,distance;

		public QElement(int iPos,int jPos,int distance){
			this.iPos = iPos;
			this.jPos = jPos;
			this.distance = distance;
		}

		public int getIPos() {
			return iPos;
		}
		public int getJPos() {
			return jPos;
		}
		public int getDistance() {
			return distance;
		}
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
	public Cell[][] getMapCell() {
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
