//CHECKSTYLE.OFF: MagicNumber
package nl.delftelectronics.spaceinvaders.core.entities;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import org.joda.time.Interval;

import nl.delftelectronics.spaceinvaders.core.Collidable;
import nl.delftelectronics.spaceinvaders.core.scenes.GameScene;
import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Test cases for the Bomb class.
 */
public class BombTest extends TestCase {

    /**
     * Test if the Bomb is created and moves correctly.
     */
	public void testBomb() {
		final double dimension = 10;

		GameScene scene = mock(GameScene.class);
		when(scene.getCollisions(any(Collidable.class))).thenReturn(
				new ArrayList<Entity>());

		// Speed is in millisecond
		Bomb bomb = new Bomb(new Rectangle2D.Double(dimension, dimension, dimension, dimension));
		bomb.initialize(scene);
		bomb.update(new Interval(0, 1));
		Assert.assertEquals(dimension - Bomb.MOVING_SPEED, bomb.getPositionY());

	}

	public void testHitBarricade() {
		final double dimension = 10;

		GameScene scene = mock(GameScene.class);
		ArrayList<Entity> collisions = new ArrayList<Entity>();
		Barricade barricade = mock(Barricade.class);
		collisions.add(barricade);
		when(scene.getCollisions(any(Collidable.class))).thenReturn(collisions);

		// Speed is in millisecond
		Bomb bomb = new Bomb(new Rectangle2D.Double(dimension, dimension, dimension, dimension));
		bomb.initialize(scene);
		bomb.update(new Interval(0, 1));
		verify(barricade, times(1)).hit();
	}

	public void testHitShip() {
		final double dimension = 10;

		GameScene scene = mock(GameScene.class);
		ArrayList<Entity> collisions = new ArrayList<Entity>();
		Enemy hitEnemy = mock(Enemy.class);
		collisions.add(hitEnemy);
		when(scene.getCollisions(any(Collidable.class))).thenReturn(collisions);

		// Speed is in millisecond
		Bomb bomb = new Bomb(new Rectangle2D.Double(dimension, dimension, dimension, dimension));
		bomb.initialize(scene);
		bomb.update(new Interval(0, 1));
		verify(hitEnemy, times(1)).kill();
	}
}