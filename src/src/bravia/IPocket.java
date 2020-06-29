package bravia;

import cells.Color;

public interface IPocket {
	  void addKey(Color color);
	  boolean hasKey(Color color);
	  void setKeyInventory(boolean[] keyInventory);
}