package map;


import enemy.*;

public class EnemyFactory {
	public static Enemy getEnemy(String objectID, Map mapGenerated, int iPos, int jPos) {
		if(objectID.charAt(0) == 'M') {
			switch (objectID.charAt(1)) {
			case '0':
				return new EnemyGuardian(mapGenerated,iPos,jPos);
			case '1':
				return new EnemyHunter(mapGenerated,iPos,jPos);
			default:
				return null;
			}
		}
		return null;
	}
}
