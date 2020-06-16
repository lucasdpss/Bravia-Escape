package enemy;

import map.IMapProperties;

public class Enemy implements IMovement {
	private int iPos, jPos;
	private IMapProperties map;
	
	public Enemy(IMapProperties map, int iPos, int jPos) {
		this.map = map;
		this.iPos = iPos;
		this.jPos = jPos;
	}
	
	//Falta implementar:
	public char getMoveDirection() {
		return 'U';
	}

}
