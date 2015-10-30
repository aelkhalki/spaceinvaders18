package nl.delftelectronics.spaceinvaders.core.entities;

import org.joda.time.Interval;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import static org.mockito.Matchers.any;
import junit.framework.Assert;
import junit.framework.TestCase;
import nl.delftelectronics.spaceinvaders.core.Collidable;
import nl.delftelectronics.spaceinvaders.core.scenes.GameScene;

/**
 * Test the Bullet class.
 */
public class BulletTest extends TestCase {
    /**
     * Test method for
     *{@link nl.delftelectronics.spaceinvaders.core.entities.Bullet#update(org.joda.time.Interval)}.
     */
    public void testUpdate() {
        final double dimension = 10;

        GameScene scene = mock(GameScene.class);
        when(scene.getCollisions(any(Collidable.class))).thenReturn(new ArrayList<Entity>());
        
        // Moving speed is per milisecond
        Bullet bullet = new Bullet(new Rectangle2D.Double(dimension, dimension,
        		dimension, dimension), true);
        bullet.initialize(scene);
        Assert.assertEquals(dimension, bullet.getPositionY());
        bullet.update(new Interval(0, 1));
        Assert.assertEquals(dimension - Bullet.MOVING_SPEED, bullet.getPositionY());

        Bullet down = new Bullet(new Rectangle2D.Double(dimension, dimension,
        		dimension, dimension), false);
        down.initialize(scene);
        Assert.assertEquals(dimension, down.getPositionY());
        down.update(new Interval(0, 1));
        Assert.assertEquals(dimension + Bullet.MOVING_SPEED, down.getPositionY());
    }

    public void testHitShip() {
        final double dimension = 10;

        GameScene scene = mock(GameScene.class);
        ArrayList<Entity> collisions = new ArrayList<Entity>();
        Ship hitShip = mock(Ship.class);
        collisions.add(hitShip);
        when(scene.getCollisions(any(Collidable.class))).thenReturn(collisions);

        Bullet bullet = new Bullet(new Rectangle2D.Double(dimension, dimension, dimension,
                dimension), false);
        bullet.initialize(scene);
        bullet.update(new Interval(0, 1));
        verify(hitShip, times(1)).hit();
    }
}
