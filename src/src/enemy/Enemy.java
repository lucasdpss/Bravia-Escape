package enemy;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import map.IMapProperties;

public abstract class Enemy implements IMovement, IEnemyProperties, Cloneable{
	protected int iPos, jPos;
	protected IMapProperties map;
	protected boolean lit;
	protected Image image;

	public Enemy(IMapProperties map, int iPos, int jPos) {
		this.map = map;
		this.iPos = iPos;
		this.jPos = jPos;
		this.lit = false;
	}
	
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	/*** Retorna melhor decisao de caminho, retorna 'S' caso nao tenha movimentos possiveis ***/
	protected char getBestDirection() { 
		ArrayList<Direction> list = new ArrayList<Direction>(4);

		if(iPos - 1 >= 0 && map.getCell(iPos-1,jPos).isWalkableEnemy() && map.getEnemy(iPos - 1, jPos) == null) {
			int upDistance = minDistanceToBravia(iPos-1, jPos);
			System.out.print("upDistance: ");  //DEBUG
			System.out.println(upDistance);
			if(upDistance >= 0) {
				Direction up = new Direction(upDistance, 'U');
				list.add(up);
			}
		}
		if(iPos + 1 < map.getMapHeight() && map.getCell(iPos+1,jPos).isWalkableEnemy() && map.getEnemy(iPos + 1, jPos) == null) {
			int downDistance = minDistanceToBravia(iPos+1, jPos);
			System.out.print("downDistance: "); //DEBUG
			System.out.println(downDistance);
			if(downDistance >= 0) {
				Direction down = new Direction(downDistance, 'D');
				list.add(down);
			}
		}
		if(jPos - 1 >= 0 && map.getCell(iPos,jPos-1).isWalkableEnemy() && map.getEnemy(iPos, jPos - 1) == null) {
			int leftDistance = minDistanceToBravia(iPos, jPos-1);
			System.out.print("leftDistance: "); //DEBUG
			System.out.println(leftDistance);
			if(leftDistance >= 0) {
				Direction left = new Direction(leftDistance, 'L');
				list.add(left);
			}
		}
		if(jPos + 1 < map.getMapWidth() && map.getCell(iPos, jPos + 1).isWalkableEnemy() && map.getEnemy(iPos, jPos + 1) == null) {
			int rightDistance = minDistanceToBravia(iPos, jPos+1);
			System.out.print("rightDistance: "); //DEBUG
			System.out.println(rightDistance);
			if(rightDistance >= 0) {
				Direction right = new Direction(rightDistance, 'R');
				list.add(right);
			}
		}

		if(list.isEmpty()) {
			return 'S';
		}else {
			Collections.sort(list);
			return list.get(0).direction;
		}
	}

	/*** Retorna a minima distancia da celula Source para Bravia, -1 caso seja impossivel ***/
	private int minDistanceToBravia(int iSource, int jSource) {  
		int iDest = map.getIBravia();
		int jDest = map.getJBravia();
		int height = map.getMapHeight();
		int width = map.getMapWidth();

		boolean visited[][] = new boolean[height][width];

		for(int i=0;i < height; i++) {
			for(int j=0; j < width; j++) {
				if(map.getCell(i, j).isWalkableEnemy() && map.getEnemy(i, j) == null) {
					visited[i][j] = false;
				}else {
					visited[i][j] = true;
				}
			}
		}
		visited[iSource][jSource] = true;

		QElement source = new QElement(iSource,jSource,0);
		Queue<QElement> q = new LinkedList<QElement>();

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
			if(p.getIPos() + 1 < height && visited[p.getIPos()+1][p.getJPos()] == false) {
				q.add(new QElement(p.getIPos()+1,p.getJPos(),p.getDistance()+1));
				visited[p.getIPos() + 1][p.getJPos()] = true;
			}
			//moving left
			if(p.getJPos() - 1 >= 0 && visited[p.getIPos()][p.getJPos()-1] == false) {
				q.add(new QElement(p.getIPos(),p.getJPos()-1,p.getDistance()+1));
				visited[p.getIPos()][p.getJPos()-1] = true;
			}
			//moving right
			if(p.getJPos() + 1 < width && visited[p.getIPos()][p.getJPos()+1] == false) {
				q.add(new QElement(p.getIPos(),p.getJPos()+1,p.getDistance()+1));
				visited[p.getIPos()][p.getJPos()+1] = true;
			}
		}
		return -1;
	}

	public boolean isLit() {
		return lit;
	}

	public void setLit(boolean lit){
		this.lit = lit;
	}

	public void setIPos(int iPos){
		this.iPos = iPos;
	}

	public void setJPos(int jPos){
		this.jPos = jPos;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getIPos(){
		return iPos;
	}

	public int getJPos(){
		return jPos;
	}

	public Image getImage() {
		return image;
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

	private class Direction implements Comparable<Direction>{
		public int distance;
		public char direction;

		public Direction(int dist, char dir) {
			distance = dist;
			direction = dir;
		}

		public int compareTo(Direction d) {
			return this.distance - d.distance;
		}
	}

}
