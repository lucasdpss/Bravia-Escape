package cells;

public class Bonfire extends Cell {
	public Bonfire(){
		lit = false;
		permanentlyLit = false;
		walkable = true;
		cellType = "Bf";
	}
	
	public void activate() {
		permanentlyLit = true;
		walkable = false;
	}
}
