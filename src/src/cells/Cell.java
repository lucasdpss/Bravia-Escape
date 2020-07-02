package cells;

import java.awt.Image;

import bravia.Bravia;

public abstract class Cell implements ICellProperties, Cloneable {
	protected boolean lit;
	protected boolean permanentlyLit;
	protected boolean walkableBravia;
	protected boolean walkableEnemy;
	protected Image image;
	protected int iPos, jPos;
	
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	public boolean isWalkableBravia() {
		return walkableBravia;
	}
	
	public boolean isWalkableEnemy() {
		return walkableEnemy;
	}
	
	public boolean isPermanentlyLit() {
		return permanentlyLit;
	}
	
	public boolean isLit() {
		return lit;
	}
	
	public void setWalkableBravia(boolean walkableBravia) {
		this.walkableBravia = walkableBravia;
	}
	
	public void setWalkableEnemy(boolean walkableEnemy) {
		this.walkableEnemy = walkableEnemy;
	}
	
	public void setPermanentlyLit(boolean permanentlyLit) {
		this.permanentlyLit = permanentlyLit;
	}
	
	public void setLit(boolean lit) {
		this.lit = lit;
	}
	
	public Image getImage() {
		return image;
	}

	public void activate(Bravia bravia) {
		
	}
}
