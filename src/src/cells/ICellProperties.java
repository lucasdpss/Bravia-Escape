package cells;

import java.awt.Image;

import bravia.Bravia;

public interface ICellProperties {
	boolean isWalkableBravia();
	boolean isWalkableEnemy();
	boolean isPermanentlyLit();
	boolean isLit();
	void setWalkableBravia(boolean walkableBravia);
	void setWalkableEnemy(boolean walkableEnemy);
	void setPermanentlyLit(boolean permanentlyLit);
	void setLit(boolean lit);
	public Image getImage();
	void activate(Bravia bravia);
	int getIPos();
	int getJPos();
}
