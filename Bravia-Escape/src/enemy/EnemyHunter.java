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
				return 'U';
			case 1:
				return 'L';
			case 2:
				return 'D';
			case 3:
				return 'R';
			default:
				return 'E';
			}
		}
	}
	
}
