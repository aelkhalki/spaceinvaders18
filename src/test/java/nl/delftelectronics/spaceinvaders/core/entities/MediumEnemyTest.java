package nl.delftelectronics.spaceinvaders.core.entities;

import junit.framework.Assert;
import junit.framework.TestCase;

public class MediumEnemyTest extends TestCase {

	MediumEnemy enemy = new MediumEnemy(10, 10, 10, 10, 10, 10, 0, null);
	int speed = enemy.MOVE_DOWN_SPEED;

	public void testmediumEnemy() {

		MediumEnemy mediumenemy = new MediumEnemy(10, 10, 10, 10, 10, 10, 0,null);

		Assert.assertEquals(false, mediumenemy.isKilled);
		Assert.assertEquals(10.0, mediumenemy.getPositionX());
		Assert.assertEquals(10.0, mediumenemy.getPositionY());

	}

	public void testmediumEnemyMovement() {

		MediumEnemy mediumenemy = new MediumEnemy(10, speed * 2, 10, 10, 10,10, 0, null);
		double x = mediumenemy.getPositionY();

		mediumenemy.moveDown();
		Assert.assertEquals(x + mediumenemy.MOVE_DOWN_SPEED,mediumenemy.getPositionY());

	}

	public void testmediumEnemyKill() {

		MediumEnemy mediumenemy = new MediumEnemy(10, 10, 10, 10, 10, 10, 0,null);
		
		Assert.assertEquals(false, mediumenemy.isKilled);
		mediumenemy.kill();
		Assert.assertEquals(true, mediumenemy.isKilled);

	}

	public void testmediumEnemyReachedBottom() {

		MediumEnemy mediumenemy = new MediumEnemy(0, 40, 10, 10, 10, 10, 180,null);

		mediumenemy.moveDown();
		Assert.assertEquals(false, mediumenemy.reachedBottom());
		mediumenemy.moveDown();
		Assert.assertEquals(true, mediumenemy.reachedBottom());
	}

}
