//CHECKSTYLE.OFF: MagicNumber
package nl.delftelectronics.spaceinvaders.core.entities;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import nl.delftelectronics.spaceinvaders.core.Collidable;
import nl.delftelectronics.spaceinvaders.core.scenes.GameScene;
import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Test cases for the Ship class.
 */
public class ShipTest extends TestCase {

    /**
     * Test if the ship('s position) is initialized correctly.
     */
    public void testShipExist() {
        final double dimension = 10;
        Ship ship = new Ship(new Rectangle2D.Double(10, 10, 10, 10), 10, 10);
        GameScene scene = mock(GameScene.class);
        when(scene.getCollisions(any(Collidable.class))).thenReturn(
                new ArrayList<Entity>());
        ship.initialize(scene);

        Assert.assertEquals(dimension, ship.getPositionX());
        Assert.assertEquals(dimension, ship.getPositionY());
    }

    /**
     * Test if the ship registers hitting bullets.
     */
    public void testShipHit() {
        Ship ship = new Ship(new Rectangle2D.Double(10, 10, 10, 10), 10, 10);
        GameScene scene = mock(GameScene.class);
        when(scene.getCollisions(any(Collidable.class))).thenReturn(
                new ArrayList<Entity>());
        ship.initialize(scene);
        Assert.assertEquals(3, ship.getLives());
        ship.hit();
        Assert.assertEquals(2, ship.getLives());
    }

    /**
     * Tests if the ship can create bullets correctly.
     */
    public void testPlayerShootBullet() {
        Ship ship = new Ship(new Rectangle2D.Double(10, 10, 10, 10), 10, 10);
        GameScene scene = mock(GameScene.class);
        ship.initialize(scene);
        ship.playerShootBullet(false);
        verify(scene).addEntity(any(Bullet.class));
    }

    /**
     * Tests if the ship can create bombs correctly.
     */
    public void testPlayerShootBomb() {
        Ship ship = new Ship(new Rectangle2D.Double(10, 10, 10, 10), 10, 10);
        ship.setBombs(1);
        GameScene scene = mock(GameScene.class);
        ship.initialize(scene);
        ship.playerShootBomb(false);
        verify(scene).addEntity(any(Bomb.class));
    }
}
