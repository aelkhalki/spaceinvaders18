//CHECKSTYLE.OFF: MagicNumber
package nl.delftelectronics.spaceinvaders.core.entities;

import junit.framework.Assert;
import junit.framework.TestCase;
import nl.delftelectronics.spaceinvaders.core.Collidable;
import nl.delftelectronics.spaceinvaders.core.GameInformation;
import nl.delftelectronics.spaceinvaders.core.scenes.GameScene;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Test cases for the Ship class.
 */
public class ShipTest extends TestCase {

    /**
     * Test if the ship('s position) is initialized correctly.
     */
    public void testShipExist() {
        final double dimension = 10;
        GameInformation gameInformation = new GameInformation(0, 3, 0, 1,
                new ArrayList<Rectangle2D>());
        Ship ship = new Ship(new Rectangle2D.Double(10, 10, 10, 10),
        		10, 10, gameInformation);
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
        GameInformation gameInformation = new GameInformation(0, 3, 0, 1,
                new ArrayList<Rectangle2D>());
        Ship ship = new Ship(new Rectangle2D.Double(10, 10, 10, 10),
        		10, 10, gameInformation);
        GameScene scene = mock(GameScene.class);
        when(scene.getCollisions(any(Collidable.class))).thenReturn(
                new ArrayList<Entity>());
        ship.initialize(scene);
        Assert.assertEquals(3, gameInformation.getLives());
        ship.hit();
        Assert.assertEquals(2, gameInformation.getLives());
    }

    /**
     * Tests if the ship can create bullets correctly.
     */
    public void testPlayerShootBullet() {
        GameInformation gameInformation = new GameInformation(0, 3, 0, 1,
                new ArrayList<Rectangle2D>());
        Ship ship = new Ship(new Rectangle2D.Double(10, 10, 10, 10),
        		10, 10, gameInformation);
        GameScene scene = mock(GameScene.class);
        ship.initialize(scene);
        ship.playerShootBullet(false);
        verify(scene).addEntity(any(Bullet.class));
    }

    /**
     * Tests if the ship can create bombs correctly.
     */
    public void testPlayerShootBomb() {

        GameInformation gameInformation = new GameInformation(0, 3, 1, 1,
                new ArrayList<Rectangle2D>());
        Ship ship = new Ship(new Rectangle2D.Double(10, 10, 10, 10),
        		10, 10, gameInformation);
        GameScene scene = mock(GameScene.class);
        ship.initialize(scene);
        ship.playerShootBomb(false);
        verify(scene).addEntity(any(Bomb.class));
    }
}
