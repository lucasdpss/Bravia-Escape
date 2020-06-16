package cells;

public class Key extends Cell {
	private int color;
	
	public Key(int color){
		lit = false;
		permanentlyLit = false;
		walkable = true;
		this.color = color;
		cellType = "K" + color;
	}
}
