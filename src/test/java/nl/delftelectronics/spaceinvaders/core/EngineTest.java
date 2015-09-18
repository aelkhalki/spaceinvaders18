//CHECKSTYLE.OFF: MagicNumber
package nl.delftelectronics.spaceinvaders.core;

import junit.framework.Assert;
import junit.framework.TestCase;
import nl.delftelectronics.spaceinvaders.core.scenes.GameScene;
import java.awt.Point;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Test the Engine class.
 */
public class EngineTest extends TestCase {

	/**
	 * Tests click hitting
	 */
	public void testClick() {
		GameScene gameScene = mock(GameScene.class);
		Engine engine = new Engine(gameScene);

		Collidable all = new DummyCollidable(0, 0, 1000, 1000);
		Collidable none = new DummyCollidable(0, 0, 0, 0);
		
		assertFalse(engine.wasClicked(all));
		assertFalse(engine.wasClicked(none));

		engine.addClick(new Point(100, 100));
		
		assertTrue(engine.wasClicked(all));
		assertFalse(engine.wasClicked(none));
		
		engine.update();
		assertFalse(engine.wasClicked(all));
		assertFalse(engine.wasClicked(none));
	}

	/**
	 * Tests changing the scene
	 */
	public void testSceneChange() {
		GameScene gameScene1 = mock(GameScene.class);
		GameScene gameScene2 = mock(GameScene.class);

		Engine engine = new Engine(gameScene1);
		Assert.assertEquals(gameScene1, engine.getScene());
		engine.update();
		verify(gameScene1).update();

		engine.setScene(gameScene2);
		engine.update();
		verify(gameScene2).update();
	}
	
	/**
	 * Tests pressing keys
	 */
	public void testKeys() {
		GameScene gameScene = mock(GameScene.class);
		Engine engine = new Engine(gameScene);
		
		String key1 = "SPACE";
		String key2 = "X";
		
		Assert.assertFalse(engine.isKeyPressed(key1));
		Assert.assertFalse(engine.isKeyPressed(key2));
		
		engine.keyDown(key1);
		Assert.assertTrue(engine.isKeyPressed(key1));
		Assert.assertFalse(engine.isKeyPressed(key2));
		
		engine.keyDown(key1);
		engine.keyDown(key2);
		Assert.assertTrue(engine.isKeyPressed(key1));
		Assert.assertTrue(engine.isKeyPressed(key2));
		
		engine.keyUp(key1);
		Assert.assertFalse(engine.isKeyPressed(key1));
		Assert.assertTrue(engine.isKeyPressed(key2));
		
		engine.keyUp(key1);
		Assert.assertFalse(engine.isKeyPressed(key1));
		Assert.assertTrue(engine.isKeyPressed(key2));
	}
}
