package map;

import enemy.Enemy;

public interface IEnemyFactory {
	public Enemy getEnemy(String objectID, Map mapGenerated, int iPos, int jPos);
}
