package cells;

public class Wall extends Cell {
	
	public Wall(){
		lit = false;
		permanentlyLit = false;
		walkable = false;
		cellType = "Wa";
	}
}
