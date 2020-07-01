package enemy;

import java.awt.Toolkit;

import map.IMapProperties;

public class EnemyGuardian extends Enemy{ 
	private boolean discovered;
	
	public EnemyGuardian(IMapProperties map, int iPos, int jPos) {
		super(map, iPos, jPos);
		this.image = Toolkit.getDefaultToolkit().getImage("assets\\graphics\\enemy.gif");
		this.lightRange = 0;
		this.discovered = false;
	}
	
	/*** Retorna o movimento feito pelo inimigo em char, retorna 'S' caso ele nao se mova ***/
	public char getMoveDirection() {
		if(lit) {
			discovered = true;
			lightRange = 1;
		}
		if(discovered) return this.getBestDirection();
		return 'S';
	}
	
}
