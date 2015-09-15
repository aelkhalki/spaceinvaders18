package nl.delftelectronics.spaceinvaders.core.entities;

import org.joda.time.Interval;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import static org.mockito.Matchers.any;
import junit.framework.Assert;
import junit.framework.TestCase;
import nl.delftelectronics.spaceinvaders.core.Collidable;
import nl.delftelectronics.spaceinvaders.core.entities.Bullet;
import nl.delftelectronics.spaceinvaders.core.entities.Entity;
import nl.delftelectronics.spaceinvaders.core.scenes.GameScene;

/**
 * Test the Bullet class.
 */
public class BulletTest extends TestCase {
    /**
     * Test method for
     * {@link nl.delftelectronics.spaceinvaders.core.entities.Bullet#update(org.joda.time.Interval)}.
     */
    public void testUpdate() {
        final int dimension = 10;
        final int maximumInterval = 1000;

        GameScene scene = mock(GameScene.class);
        when(scene.getCollisions(any(Collidable.class))).thenReturn(new ArrayList<Entity>());
        
        Bullet bullet = new Bullet(dimension, dimension, dimension, dimension, true);
        bullet.initialize(scene);
        Assert.assertEquals((long) dimension, (long) bullet.getPositionY());
        bullet.update(new Interval(0, maximumInterval));
        Assert.assertEquals((long) dimension - Bullet.MOVING_SPEED, (long) bullet.getPositionY());

        Bullet down = new Bullet(dimension, dimension, dimension, dimension, false);
        down.initialize(scene);
        Assert.assertEquals((long) dimension, (long) down.getPositionY());
        down.update(new Interval(0, maximumInterval));
        Assert.assertEquals((long) dimension + Bullet.MOVING_SPEED, (long) down.getPositionY());
    }
}
