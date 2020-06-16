package cells;

public class Gate extends Cell {
	private int color;
	
	public Gate(int color){
		lit = false;
		permanentlyLit = false;
		walkable = false;
		this.color = color;
		cellType = "G" + color;
	}
	
	public void activate() {
		walkable = true;
	}
}
