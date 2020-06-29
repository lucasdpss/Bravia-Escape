package bravia;

import java.awt.Image;

public interface IBraviaProperties {
	  int getIPos();
	  int getJPos();
	  void setJPos(int jPos);
	  void setIPos(int iPos);
	  public int getRange();
	  Image getImage();
}