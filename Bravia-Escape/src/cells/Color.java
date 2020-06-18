package cells;

public enum Color {
	BLUE(0),
	GREEN(1),
	RED(2),
	YELLOW(3);
	
	private static Color[] list = values();
	public final int index;
	
	Color(int color){
		index = color;
	}
	
	public static Color getColor(int i) {
		return list[i];
	}
	
	public static int getColorAmount() {
		return list.length;
	}
}
