//CHECKSTYLE.OFF: MagicNumber
package nl.delftelectronics.spaceinvaders.core.entities;

import java.awt.geom.Rectangle2D;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Test cases for the MediumEnemy class.
 */
public class MediumEnemyTest extends TestCase {

    MediumEnemy enemy = new MediumEnemy(
    		new Rectangle2D.Double(10, 10, 10, 10), 10, 10, 0, null);
    int speed = enemy.MOVE_DOWN_SPEED;

    /**
     * Test if the enemy's initial position is correct.
     */
    public void testmediumEnemy() {

        MediumEnemy mediumenemy = new MediumEnemy(
        		new Rectangle2D.Double(10, 10, 10, 10), 10, 10, 0, null);

        Assert.assertEquals(false, mediumenemy.isKilled);

        Assert.assertEquals(10.0, mediumenemy.getPositionX());
        Assert.assertEquals(10.0, mediumenemy.getPositionY());

    }

    /**
     * Test if the enemy moves correctly.
     */
    public void testmediumEnemyMovement() {

        MediumEnemy mediumenemy = new MediumEnemy(
        		new Rectangle2D.Double(10, speed * 2, 10, 10), 10, 10, 0, null);

        double x = mediumenemy.getPositionY();

        mediumenemy.moveDown();

        Assert.assertEquals(x + mediumenemy.MOVE_DOWN_SPEED, mediumenemy.getPositionY());

    }

    /**
     * Test if the enemy dies correctly.
     */
    public void testmediumEnemyKill() {

        MediumEnemy mediumenemy = new MediumEnemy(
        		new Rectangle2D.Double(10, 10, 10, 10), 10, 10, 0, null);
        Assert.assertEquals(false, mediumenemy.isKilled);
        mediumenemy.kill();
        Assert.assertEquals(true, mediumenemy.isKilled);


    }

    /**
     * Test if the enemy knows when it has reached the bottom.
     */
    public void testmediumEnemyReachedBottom() {

        MediumEnemy mediumenemy = new MediumEnemy(
        		new Rectangle2D.Double(0, 40, 10, 10), 10, 10, 180, null);

        mediumenemy.moveDown();
        Assert.assertEquals(false, mediumenemy.reachedBottom());
        mediumenemy.moveDown();
        Assert.assertEquals(true, mediumenemy.reachedBottom());
    }


}
