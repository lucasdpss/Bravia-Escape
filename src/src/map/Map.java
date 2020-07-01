package map;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import enemy.*;
import main.Checkpoint;
import bravia.Bravia;
import cells.Cell;

public class Map implements IMap{
	private Cell[][] mapCell;
	private Enemy[][] mapEnemy;
	private int mapHeight, mapWidth;
	private Bravia bravia;
	private ArrayList<Enemy> listEnemy;

	public void illuminate(int range, int iSource, int jSource) {
		if(range == 0) return;
		mapCell[iSource][jSource].setLit(true);
		if(mapEnemy[iSource][jSource] != null) mapEnemy[iSource][jSource].setLit(true);
		
		for(int j = jSource-range; j <= jSource+range; j++) {

			if(j == jSource-range || j == jSource+range) {
				for(int i = iSource-(range-1); i <= iSource+(range-1); i++) {
					if(i >= 0 && i < mapHeight && j >= 0 && j < mapWidth) {
						int distance = distanceToCell(i, j, iSource, jSource);
						if(distance > 0 && distance <= range+2) {
							mapCell[i][j].setLit(true);
							if(mapEnemy[i][j] != null) mapEnemy[i][j].setLit(true);
						}
					}
				}
			}else {
				for(int i = iSource-(range); i <= iSource+(range); i++) {
					if(i >= 0 && i < mapHeight && j >= 0 && j < mapWidth) {
						int distance = distanceToCell(i, j, iSource, jSource);
						if(distance > 0 && distance <= range+2) {
							mapCell[i][j].setLit(true);
							if(mapEnemy[i][j] != null) mapEnemy[i][j].setLit(true);
						}
					}
				}
			}

		}
	}

	public void illuminatePermanently(int range, int iSource, int jSource) {
		if(range == 0) return;
		mapCell[iSource][jSource].setPermanentlyLit(true);
		if(mapEnemy[iSource][jSource] != null) mapEnemy[iSource][jSource].setLit(true);
		
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
				if(mapEnemy[i][j] != null) mapEnemy[i][j].setLit(false);
			}
		}
	}

	/*** Retorna a distancia entre dois pontos do mapa, -1 caso nao eh possivel chegar ***/
	private int distanceToCell(int iSource, int jSource, int iDest, int jDest) {
		/*** preparar ambiente e variaveis para BFS ***/
		boolean visited[][] = new boolean[mapHeight][mapWidth];

		for(int i=0;i < mapHeight; i++) {
			for(int j=0; j < mapWidth; j++) {
				if(mapCell[i][j].isWalkableBravia()) {
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

	/*** Funcao para movimentar os inimgos na matriz de inimigos ***/
	public void moveEnemies() { 
		boolean collision = false;
		listEnemies();
		if(listEnemy.isEmpty()) return;
		for(Enemy enemy : listEnemy) {
			if(moveEnemy(enemy, enemy.getMoveDirection())) {
				collision = true;
				break;
			}
		}
		if(collision)
			try {
				killBravia();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
	}
	
	/*** Lista todos os inimigos no mapa e retorna a lista ***/
	public ArrayList<Enemy> listEnemies() {
		listEnemy = new ArrayList<Enemy>();
		for(int i=0;i < mapHeight; i++) {
			for(int j=0; j < mapWidth; j++) {
				if(mapEnemy[i][j] != null) {
					listEnemy.add(mapEnemy[i][j]);
				}
			}
		}
		return listEnemy;
	}
	
	/*** Funcao para mover um inimigo no mapa, assumindo que a direcao dada eh valida ***/
	//retorna true caso tenha colidido com a bravia, retorna false caso contrario
	private boolean moveEnemy(Enemy enemy, char direction) {
		System.out.println(direction);               //DEBUG
		int newIPos,newJPos;
		
		switch (direction) {
		case 'U':
			newIPos = enemy.getIPos() - 1;
			newJPos = enemy.getJPos();
			mapEnemy[newIPos][newJPos] = mapEnemy[enemy.getIPos()][enemy.getJPos()];
			mapEnemy[enemy.getIPos()][enemy.getJPos()] = null;
			enemy.setIPos(newIPos);
			enemy.setJPos(newJPos);
			System.out.print(newIPos);         //DEBUG	
			System.out.print(" ");
			System.out.println(newJPos);
			break;
		case 'D':
			newIPos = enemy.getIPos() + 1;
			newJPos = enemy.getJPos();
			mapEnemy[newIPos][newJPos] = mapEnemy[enemy.getIPos()][enemy.getJPos()];
			mapEnemy[enemy.getIPos()][enemy.getJPos()] = null;
			enemy.setIPos(newIPos);
			enemy.setJPos(newJPos);
			System.out.print(newIPos);         //DEBUG	
			System.out.print(" ");
			System.out.println(newJPos);
			break;
		case 'L':
			newIPos = enemy.getIPos();
			newJPos = enemy.getJPos() - 1;
			mapEnemy[newIPos][newJPos] = mapEnemy[enemy.getIPos()][enemy.getJPos()];
			mapEnemy[enemy.getIPos()][enemy.getJPos()] = null;
			enemy.setIPos(newIPos);
			enemy.setJPos(newJPos);
			System.out.print(newIPos);         //DEBUG	
			System.out.print(" ");
			System.out.println(newJPos);
			break;
		case 'R':
			newIPos = enemy.getIPos();
			newJPos = enemy.getJPos() + 1;
			mapEnemy[newIPos][newJPos] = mapEnemy[enemy.getIPos()][enemy.getJPos()];
			mapEnemy[enemy.getIPos()][enemy.getJPos()] = null;
			enemy.setIPos(newIPos);
			enemy.setJPos(newJPos);
			System.out.print(newIPos);         //DEBUG	
			System.out.print(" ");
			System.out.println(newJPos);
			break;
		default:
			break;
		}
		
		if(bravia.getIPos() == enemy.getIPos() && bravia.getJPos() == enemy.getJPos()) { 
			return true;
		}
		return false;
	}
	
	public void killBravia() throws CloneNotSupportedException { 
		System.out.println("morreu");
		mapCell = Checkpoint.getMapCell();
		mapEnemy = Checkpoint.getMapEnemy();
		bravia.setIPos(Checkpoint.getStartIPos());
		bravia.setJPos(Checkpoint.getStartJPos());
		bravia.setKeyInventory(Checkpoint.getKeyInventory());
		return;
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
	
	public Enemy getEnemy(int i, int j) {
		return mapEnemy[i][j];
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
