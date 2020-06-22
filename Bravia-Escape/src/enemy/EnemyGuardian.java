package enemy;

import java.awt.Toolkit;

import map.IMapProperties;

public class EnemyGuardian extends Enemy{ 
	
	public EnemyGuardian(IMapProperties map, int iPos, int jPos) {
		super(map, iPos, jPos);
		this.enemyType = 0;
		this.image = Toolkit.getDefaultToolkit().getImage("resources\\graphics\\enemy.gif");
	}
	
	/*** Retorna o movimento feito pelo inimigo em char, retorna 'S' caso ele nao se mova ***/
	public char getMoveDirection() {
		if(discovered) return this.getBestDirection();
		if(lit) return this.getBestDirection();
		return 'S';
	}
	
}
