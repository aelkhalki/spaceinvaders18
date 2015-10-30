//CHECKSTYLE.OFF: MagicNumber
package nl.delftelectronics.spaceinvaders.core.entities;

import junit.framework.Assert;
import junit.framework.TestCase;
import nl.delftelectronics.spaceinvaders.core.Collidable;
import nl.delftelectronics.spaceinvaders.core.Engine;
import nl.delftelectronics.spaceinvaders.core.GameInformation;
import nl.delftelectronics.spaceinvaders.core.PlayingKeys;
import nl.delftelectronics.spaceinvaders.core.scenes.GameScene;
import org.joda.time.Interval;
import org.junit.Test;

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

    public void testFireBulletByKey() {
        Ship ship = new Ship(new Rectangle2D.Double(10, 10, 10, 10),
                10, 10, mock(GameInformation.class));
        GameScene gameScene = mock(GameScene.class);
        Engine.getInstance().keyDown("W");
        PlayingKeys playingKeys = mock(PlayingKeys.class);
        when(playingKeys.getFireBulletKey()).thenReturn("W");
        when(playingKeys.getFireBombKey()).thenReturn("SHIFT");
        when(playingKeys.getMoveLeftKey()).thenReturn("A");
        when(playingKeys.getMoveRightKey()).thenReturn("D");
        ship.initialize(gameScene);
        ship.setPlayingKeys(playingKeys);

        try {
            ship.update(new Interval(0, 1));
        } catch (IllegalStateException e) {}
        verify(gameScene).addEntity(any(Bullet.class));
    }

    public void testFireBombByKey() {
        GameInformation gameInformation = mock(GameInformation.class);
        when(gameInformation.getBombs()).thenReturn(10);
        Ship ship = new Ship(new Rectangle2D.Double(10, 10, 10, 10),
                10, 10, gameInformation);
        GameScene gameScene = mock(GameScene.class);
        Engine.getInstance().keyDown("SHIFT");
        PlayingKeys playingKeys = mock(PlayingKeys.class);
        when(playingKeys.getFireBulletKey()).thenReturn("W");
        when(playingKeys.getFireBombKey()).thenReturn("SHIFT");
        when(playingKeys.getMoveLeftKey()).thenReturn("A");
        when(playingKeys.getMoveRightKey()).thenReturn("D");
        ship.initialize(gameScene);
        ship.setPlayingKeys(playingKeys);

        try {
            ship.update(new Interval(0, 1));
        } catch (IllegalStateException e) {}
        verify(gameScene).addEntity(any(Bomb.class));
    }
}
