package enemy;

import java.awt.Toolkit;
import java.io.File;

import map.IMapProperties;

public class EnemyGuardian extends Enemy{ 
	private boolean discovered;
	
	public EnemyGuardian(IMapProperties map, int iPos, int jPos) {
		super(map, iPos, jPos);
		this.image = Toolkit.getDefaultToolkit().getImage("assets"+File.separatorChar+"graphics"+File.separatorChar+"enemy.gif");
		this.lightRange = 0;
		this.discovered = false;
	}
	
	/*** Retorna o movimento feito pelo inimigo em char, retorna 'S' caso ele nao se mova ***/
	public char getMoveDirection() {
		int distBravia = this.minDistanceToBravia(this.iPos, this.jPos);
		if(distBravia > 4 || distBravia == -1) {
			discovered = false;
			lightRange = 0;
			lit = false;
		}
		if(lit) {
			discovered = true;
			lightRange = 1;
		}
		if(discovered) return this.getBestDirection();
		return 'S';
	}
	
}
