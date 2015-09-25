package nl.delftelectronics.spaceinvaders.core.entities;

import junit.framework.Assert;
import junit.framework.TestCase;

public class SmallEnemyTest extends TestCase {

	SmallEnemy enemy = new SmallEnemy(10, 10, 10, 10, 10, 10, 0, null);
	int speed = enemy.MOVE_DOWN_SPEED;

	public void testSmallEnemy() {

		SmallEnemy smallenemy = new SmallEnemy(10, 10, 10, 10, 10, 10, 0, null);

		Assert.assertEquals(false, smallenemy.isKilled);
		Assert.assertEquals(10.0, smallenemy.getPositionX());
		Assert.assertEquals(10.0, smallenemy.getPositionY());

	}

	public void testSmallEnemyMovement() {

		SmallEnemy smallenemy = new SmallEnemy(10, speed * 2, 10, 10, 10, 10,0, null);
		double x = smallenemy.getPositionY();
		
		smallenemy.moveDown();
		Assert.assertEquals(x + smallenemy.MOVE_DOWN_SPEED,smallenemy.getPositionY());

	}

	public void testSmallEnemyKill() {

		SmallEnemy smallenemy = new SmallEnemy(10, 10, 10, 10, 10, 10, 0, null);
		
		Assert.assertEquals(false, smallenemy.isKilled);
		smallenemy.kill();
		Assert.assertEquals(true, smallenemy.isKilled);

	}

	public void testSmallEnemyReachedBottom() {

		SmallEnemy smallenemy = new SmallEnemy(0, 40, 10, 10, 10, 10, 180, null);

		smallenemy.moveDown();
		Assert.assertEquals(false, smallenemy.reachedBottom());
		smallenemy.moveDown();
		Assert.assertEquals(true, smallenemy.reachedBottom());
	}

}
