package cells;

import java.awt.Image;

import bravia.Bravia;

public interface ICellProperties {
	boolean isWalkable();
	boolean isPermanentlyLit();
	boolean isLit();
	void setWalkable(boolean walkable);
	void setPermanentlyLit(boolean permanentlyLit);
	void setLit(boolean lit);
	String getCellType();
	public Image getImage();
	void activate(Bravia bravia);
	int getIPos();
	int getJPos();
}
