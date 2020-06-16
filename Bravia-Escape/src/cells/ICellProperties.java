package cells;

public interface ICellProperties {
	boolean isWalkable();
	boolean isPermanentlyLit();
	boolean isLit();
	void setWalkable(boolean walkable);
	void setPermanentlyLit(boolean permanentlyLit);
	void setLit(boolean lit);
	String getCellType();
	void activate();
}
