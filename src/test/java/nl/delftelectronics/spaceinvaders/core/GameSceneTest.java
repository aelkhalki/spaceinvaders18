/**
 * 
 */
package nl.delftelectronics.spaceinvaders.core;

import junit.framework.TestCase;
import junit.framework.Assert;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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
	 * Test method for {@link nl.delftelectronics.spaceinvaders.core.GameScene#update()}.
	 */
	public void testUpdate() {
		GameScene scene = new GameScene(mock(Scene.class));
		Entity e = mock(Entity.class);
		scene.addEntity(e);
		scene.update();
		verify(e).update(any(Interval.class));
	}

	/**
	 * Test method for {@link nl.delftelectronics.spaceinvaders.core.GameScene#draw(javafx.scene.canvas.GraphicsContext)}.
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
	 * Test method for {@link nl.delftelectronics.spaceinvaders.core.GameScene#entityDestroyed(nl.delftelectronics.spaceinvaders.core.Entity)}.
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
	 * Test method for {@link nl.delftelectronics.spaceinvaders.core.GameScene#addEntity(nl.delftelectronics.spaceinvaders.core.Entity)}.
	 */
	public void testAddEntity() {
		GameScene scene = new GameScene(mock(Scene.class));
		Entity e = mock(Entity.class);
		scene.addEntity(e);
		verify(e).initialize(scene);
	}
	
	/**
	 * Test method for {@link nl.delftelectronics.spaceinvaders.core.GameScene#getCollisions(nl.delftelectronics.spaceinvaders.core.Collidable)}.
	 */
	public void testCollisions() {
		GameScene scene = new GameScene(mock(Scene.class));
		CollisionEntity first = new CollisionEntity(0, 0, 10, 10);
		scene.addEntity(first);
		
		Assert.assertEquals(0, scene.getCollisions(first).size());
		
		CollisionEntity second = new CollisionEntity(100, 100, 10, 10);
		scene.addEntity(second);
		Assert.assertEquals(0, scene.getCollisions(first).size());
		
		CollisionEntity third = new CollisionEntity(5, 5, 10, 50);
		scene.addEntity(third);
		Assert.assertEquals(1, scene.getCollisions(first).size());
		Assert.assertEquals(third, scene.getCollisions(first).get(0));
		
		Entity fourth = new Entity();
		scene.addEntity(fourth);
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

		public CollisionEntity(Integer positionX, Integer positionY, Integer width, Integer height) {
			super(positionX, positionY, width, height);
		}
	}
}
