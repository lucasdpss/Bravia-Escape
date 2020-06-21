package enemy;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import map.IMapProperties;

public class Enemy implements IMovement {
	private int iPos, jPos;
	private IMapProperties map;
	private boolean lit;
	private Image image;
	
	public Enemy(IMapProperties map, int iPos, int jPos) {
		this.map = map;
		this.iPos = iPos;
		this.jPos = jPos;
		this.lit = false;
		this.image = null;   //falta colocar o caminho da imagem
	}
	
	public boolean isLit() {
		return lit;
	}
	
	public void setLit(boolean lit){
		this.lit = lit;
	}
	
	public char getMoveDirection() {
		int upDistance = minDistanceToBravia(iPos-1, jPos);
		int downDistance = minDistanceToBravia(iPos+1, jPos);
		int leftDistance = minDistanceToBravia(iPos, jPos-1);
		int rightDistance = minDistanceToBravia(iPos, jPos+1);
		
		Direction up = new Direction(upDistance, 'U');
		Direction down = new Direction(downDistance, 'D');
		Direction left = new Direction(leftDistance, 'L');
		Direction right = new Direction(rightDistance, 'R');
		
		ArrayList<Direction> list = new ArrayList<Direction>(4);
		list.add(up);
		list.add(down);
		list.add(left);
		list.add(right);
		
		Collections.sort(list);
		
		return list.get(0).direction;
	}
	
	public Image getImage() {
		return image;
	}
	
	private int minDistanceToBravia(int iSource, int jSource) {
		int iDest = map.getIBravia();
		int jDest = map.getJBravia();
		int height = map.getMapHeight();
		int width = map.getMapWidth();
		
		boolean visited[][] = new boolean[height][width];
		
		for(int i=0;i < height; i++) {
			for(int j=0; j < width; j++) {
				if(map.getCell(i, j).isWalkable()) {
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
