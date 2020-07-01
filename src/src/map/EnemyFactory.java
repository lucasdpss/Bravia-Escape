package map;


import enemy.*;

public class EnemyFactory {
	public static Enemy getEnemy(String idObject, Map mapGenerated, int iPos, int jPos) {
		if(idObject.charAt(0) == 'M') {
			switch (idObject.charAt(1)) {
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
