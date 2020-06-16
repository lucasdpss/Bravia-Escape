package bravia;

import items.Item;

public interface IPocket {
	  void useItem(int slot);
	  void addItem(Item item);
	  Item getItem(int slot);
}