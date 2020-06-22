package enemy;

import java.awt.Toolkit;
import java.util.Random;

import map.IMapProperties;

public class EnemyHunter extends Enemy{   
	
	public EnemyHunter(IMapProperties map, int iPos, int jPos) {
		super(map, iPos, jPos);
		this.enemyType = 1;
		this.image = Toolkit.getDefaultToolkit().getImage("resources\\graphics\\enemy.gif");
	}
	
	/*** Retorna o movimento feito pelo inimigo, em char ***/
	public char getMoveDirection() {
		if(this.lit == true) {
			return this.getBestDirection();
		}else {
			Random rand = new Random();
			switch (rand.nextInt(4)) {
			case 0:
				if(iPos - 1 >= 0 && map.getCell(iPos-1,jPos).isWalkableEnemy()) {
					return 'U';
				}
			case 1:
				if(iPos + 1 < map.getMapHeight() && map.getCell(iPos+1,jPos).isWalkableEnemy()) {
					return 'D';
				}
			case 2:
				if(jPos - 1 >= 0 && map.getCell(iPos,jPos-1).isWalkableEnemy()) {
					return 'L';
				}
			case 3:
				if(jPos + 1 < map.getMapWidth() && map.getCell(iPos, jPos + 1).isWalkableEnemy()) {
					return 'R';
				}
			default:
				return 'S';
			}
		}
	}
	
}
