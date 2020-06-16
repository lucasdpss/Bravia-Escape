package main;

import cells.Cell;
import enemy.Enemy;

public class Checkpoint {
	private static int startIPos, startJPos;
	private static Cell[][] mapCell;
	private static Enemy[][] mapEnemy;
	private static boolean[] keyInventory;
	
	public static void setStartPos(int iPos, int jPos) {
		startIPos = iPos;
		startJPos = jPos;
	}
	
	public static int getStartIPos() {
		return startIPos;
	}
	
	public static int getStartJPos() {
		return startJPos;
	}
}
