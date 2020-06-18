package enemy;

import java.awt.Image;

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
	
	//Falta implementar:
	public char getMoveDirection() {
		return 'U';
	}
	
	public Image getImage() {
		return image;
	}

}
