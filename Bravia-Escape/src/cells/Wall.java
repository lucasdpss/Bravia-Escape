package cells;

public class Wall extends Cell {
	private int color;
	
	public Wall(){
		lit = false;
		permanentlyLit = false;
		walkable = false;
		cellType = "Wa";
	}
}
