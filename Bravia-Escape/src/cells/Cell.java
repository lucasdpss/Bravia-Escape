package cells;

import java.awt.Image;

public abstract class Cell implements ICellProperties {
	protected boolean lit;
	protected boolean permanentlyLit;
	protected boolean walkable;
	protected String cellType;
	protected Image image;
	
	public boolean isWalkable() {
		return walkable;
	}
	
	public boolean isPermanentlyLit() {
		return permanentlyLit;
	}
	
	public boolean isLit() {
		return lit;
	}
	
	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}
	
	public void setPermanentlyLit(boolean permanentlyLit) {
		this.permanentlyLit = permanentlyLit;
	}
	
	public void setLit(boolean lit) {
		this.lit = lit;
	}
	
	public String getCellType() {
		return cellType;
	}
	
	public Image getImage() {
		return image;
	}
	
	public void activate() {
		
	}
}
