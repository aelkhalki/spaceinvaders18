//CHECKSTYLE.OFF: MagicNumber
package nl.delftelectronics.spaceinvaders.core.entities;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Test cases for the SmallEnemy class.
 */
public class SmallEnemyTest extends TestCase {

    SmallEnemy enemy = new SmallEnemy(10, 10, 10, 10, 10, 10, 0, null);
    int speed = enemy.MOVE_DOWN_SPEED;

    /**
     * Test if the enemy's initial position is correct.
     */
    public void testSmallEnemy() {

        SmallEnemy smallenemy = new SmallEnemy(10, 10, 10, 10, 10, 10, 0, null);

        Assert.assertEquals(false, smallenemy.isKilled);

        Assert.assertEquals(10.0, smallenemy.getPositionX());
        Assert.assertEquals(10.0, smallenemy.getPositionY());

    }

    /**
     * Test if the enemy moves correctly.
     */
    public void testSmallEnemyMovement() {

        SmallEnemy smallenemy = new SmallEnemy(10, speed * 2, 10, 10, 10, 10, 0, null);

        double x = smallenemy.getPositionY();

        smallenemy.moveDown();

        Assert.assertEquals(x + smallenemy.MOVE_DOWN_SPEED, smallenemy.getPositionY());

    }

    /**
     * Test if the enemy dies correctly.
     */
    public void testSmallEnemyKill() {

        SmallEnemy smallenemy = new SmallEnemy(10, 10, 10, 10, 10, 10, 0, null);
        Assert.assertEquals(false, smallenemy.isKilled);
        smallenemy.kill();
        Assert.assertEquals(true, smallenemy.isKilled);


    }

    /**
     * Test if the enemy knows when it has reached the bottom.
     */
    public void testSmallEnemyReachedBottom() {

        SmallEnemy smallenemy = new SmallEnemy(0, 40, 10, 10, 10, 10, 180, null);

        smallenemy.moveDown();
        Assert.assertEquals(false, smallenemy.reachedBottom());
        smallenemy.moveDown();
        Assert.assertEquals(true, smallenemy.reachedBottom());
    }


}
