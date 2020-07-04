package map;

import cells.Cell;
import main.Window;

public interface ICellFactory {
	public Cell getCell(String objectID, Window window, Map mapGenerated, int iPos, int jPos);
}
