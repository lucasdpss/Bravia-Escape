package bravia;

import cells.Color;
import items.Item;

public interface IPocket {
	  void useItem(int slot);
	  void addItem(Item item);
	  Item getItem(int slot);
	  void addKey(Color color);
	  boolean hasKey(Color color);
	  int totalKeys();
	  int totalItems();
}