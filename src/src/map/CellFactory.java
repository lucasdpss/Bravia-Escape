package map;

import cells.*;
import main.Window;

public class CellFactory {
	public static Cell getCell(String idObject, Window window, Map mapGenerated, int iPos, int jPos) {
		char first = idObject.charAt(0);
		switch (first) {
		case '-':  //piso
			return new Floor(iPos,jPos);
		case 'W':  //Wall
			return new Wall(iPos,jPos);
		case 'G':  //Gate
			return new Gate(iPos,jPos,Color.getColor(idObject.charAt(1) - '0'));
		case 'B':  //Bonfire
			return new Bonfire(iPos,jPos, mapGenerated);
		case 'K':  //Key
			return new Key(iPos,jPos,Color.getColor(idObject.charAt(1) - '0'));
		case 'E':  //Exit
			return new Exit(iPos, jPos, window);
		default:
			return new Floor(iPos,jPos);
		}
	}
}
