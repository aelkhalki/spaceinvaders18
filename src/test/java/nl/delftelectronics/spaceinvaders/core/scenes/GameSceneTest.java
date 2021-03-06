//CHECKSTYLE.OFF: MagicNumber
package nl.delftelectronics.spaceinvaders.core.scenes;

import junit.framework.TestCase;
import nl.delftelectronics.spaceinvaders.core.Collidable;
import nl.delftelectronics.spaceinvaders.core.collisions.NaiveCollisionAlgorithm;
import nl.delftelectronics.spaceinvaders.core.collisions.SortedCollisionAlgorithm;
import nl.delftelectronics.spaceinvaders.core.entities.DrawableEntity;
import nl.delftelectronics.spaceinvaders.core.entities.Entity;
import junit.framework.Assert;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.awt.geom.Rectangle2D;

import org.joda.time.Interval;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

/**
 * Tests the GameScene class
 * @author Max
 *
 */
public class GameSceneTest extends TestCase {

	/**
	 * Test method for {@link GameScene#update()}.
	 */
	public void testUpdate() {
		GameScene scene = new GameScene(mock(Scene.class));
		Entity e = mock(Entity.class);
		scene.addEntity(e);
		scene.update();
		verify(e).update(any(Interval.class));
	}

	/**
	 * Test method for
	 * {@link GameScene#draw(javafx.scene.canvas.GraphicsContext)}.
	 */
	public void testDraw() {
		GameScene scene = new GameScene(mock(Scene.class));
		Entity e1 = mock(Entity.class);
		DrawableEntity e2 = mock(DrawableEntity.class);
		scene.addEntity(e1);
		scene.addEntity(e2);
		GraphicsContext c = null; // Unable to mock final class GraphicsContext
		scene.draw(c);
		verify(e2).draw(any(Interval.class), eq(c));
	}

	/**
	 * Test method for {@link GameScene#entityDestroyed(Entity)}.
	 */
	public void testEntityDestroyed() {
		GameScene scene = new GameScene(mock(Scene.class));
		DestroyEntity e = new DestroyEntity();
		scene.addEntity(e);
		
		Assert.assertEquals(0, e.updateCount);
		
		scene.update();
		Assert.assertEquals(1, e.updateCount);
		// Ought to be removed by now
		
		scene.update();
		Assert.assertEquals(1, e.updateCount);
	}
	
	/**
	 * Test method for {@link GameScene#addEntity(Entity)}.
	 */
	public void testAddEntity() {
		GameScene scene = new GameScene(mock(Scene.class));
		Entity e = mock(Entity.class);
		scene.addEntity(e);
		verify(e).initialize(scene);
	}
	
	/**
	 * Test method for getCollisions using naive collision detection.
	 */
	public void testNaiveCollisions() {
		GameScene scene = new GameScene(mock(Scene.class));
		scene.setCollisionAlgorithm(new NaiveCollisionAlgorithm());
		assertCollisions(scene);
	}
	
	/**
	 * Test method for getCollisions using sorted collision detection.
	 */
	public void testSortedCollisions() {
		GameScene scene = new GameScene(mock(Scene.class));
		scene.setCollisionAlgorithm(new SortedCollisionAlgorithm());
		assertCollisions(scene);
	}
	
	/**
	 * Asserts collisions are working correctly for a given collision algorithm
	 * @param scene The scene to use for checking collisions
	 */
	private void assertCollisions(GameScene scene) {
		CollisionEntity first = new CollisionEntity(0, 0, 10, 10);
		scene.addEntity(first);
		scene.update();
		
		Assert.assertEquals(0, scene.getCollisions(first).size());
		
		CollisionEntity second = new CollisionEntity(100, 100, 10, 10);
		scene.addEntity(second);
		scene.update();
		Assert.assertEquals(0, scene.getCollisions(first).size());
		
		CollisionEntity third = new CollisionEntity(5, 5, 10, 50);
		scene.addEntity(third);
		scene.update();
		Assert.assertEquals(1, scene.getCollisions(first).size());
		Assert.assertEquals(third, scene.getCollisions(first).get(0));
		
		Entity fourth = new Entity();
		scene.addEntity(fourth);
		scene.update();
		Assert.assertEquals(1, scene.getCollisions(first).size());
		Assert.assertEquals(third, scene.getCollisions(first).get(0));
	}
	
	/**
	 * Class used to test entities that remove themselves
	 * @author Max
	 *
	 */
	static class DestroyEntity extends Entity {
		public int updateCount = 0;

		@Override
		public void update(Interval delta) {
			updateCount++;
			destroy();
		}
	}
	
	/**
	 * Class used to test entities that collide
	 * @author Max
	 *
	 */
	static class CollisionEntity extends DrawableEntity implements Collidable {
		/**
		 * Creates a new Drawable collision entity for testing
		 * @param positionX The left offset of the collision box
		 * @param positionY The top offset of the collision box
		 * @param width     The width of the collision box
		 * @param height    The height of the collision box
		 */
		public CollisionEntity(double positionX, double positionY,
				double width, double height) {
			super(new Rectangle2D.Double(positionX, positionY, width, height));
		}
	}
}
