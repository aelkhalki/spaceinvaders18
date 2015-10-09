//CHECKSTYLE.OFF: MagicNumber
package nl.delftelectronics.spaceinvaders.core.entities;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Test cases for the LargeEnemy class.
 */
public class LargeEnemyTest extends TestCase {

    LargeEnemy enemy = new LargeEnemy(10, 10, 10, 10, 10, 10, 0, null);
    int speed = enemy.MOVE_DOWN_SPEED;

    /**
     * Test if the enemy's initial position is correct.
     */
    public void testlargeEnemy() {

        LargeEnemy largeenemy = new LargeEnemy(10, 10, 10, 10, 10, 10, 0, null);

        Assert.assertEquals(false, largeenemy.isKilled);

        Assert.assertEquals(10.0, largeenemy.getPositionX());
        Assert.assertEquals(10.0, largeenemy.getPositionY());

    }

    /**
     * Test if the enemy moves correctly.
     */
    public void testlargeEnemyMovement() {

        LargeEnemy largeenemy = new LargeEnemy(10, speed * 2, 10, 10, 10, 10, 0, null);

        double x = largeenemy.getPositionY();

        largeenemy.moveDown();

        Assert.assertEquals(x + largeenemy.MOVE_DOWN_SPEED, largeenemy.getPositionY());

    }

    /**
     * Test if the enemy dies correctly.
     */
    public void testlargeEnemyKill() {

        LargeEnemy largeenemy = new LargeEnemy(10, 10, 10, 10, 10, 10, 0, null);
        Assert.assertEquals(false, largeenemy.isKilled);
        largeenemy.kill();
        Assert.assertEquals(true, largeenemy.isKilled);

    }

    /**
     * Test if the enemy knows when it has reached the bottom.
     */
    public void testlargeEnemyReachedBottom() {

        LargeEnemy largeenemy = new LargeEnemy(0, 40, 10, 10, 10, 10, 180, null);

        largeenemy.moveDown();
        Assert.assertEquals(false, largeenemy.reachedBottom());
        largeenemy.moveDown();
        Assert.assertEquals(true, largeenemy.reachedBottom());

    }


}
