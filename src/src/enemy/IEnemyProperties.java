package enemy;

import java.awt.Image;

public interface IEnemyProperties {
	boolean isLit();
	void setLit(boolean lit);
	void setIPos(int iPos);
	void setJPos(int jPos);
	void setImage(Image image);
	int getIPos();
	int getJPos();
	Image getImage();
}
