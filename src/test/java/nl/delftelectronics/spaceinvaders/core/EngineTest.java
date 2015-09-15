package nl.delftelectronics.spaceinvaders.core;

import junit.framework.TestCase;
import nl.delftelectronics.spaceinvaders.core.scenes.GameScene;
import java.awt.Point;

import static org.mockito.Mockito.mock;

/**
 * Test the Engine class.
 */
public class EngineTest extends TestCase {
    
	public void testClick() {
		GameScene gameScene = mock(GameScene.class);
    	Engine engine = new Engine(gameScene);
    	
    	Collidable all = new DummyCollidable(0, 0, 1000, 1000);
    	
    	assertFalse(engine.wasClicked(all));
    	
    	engine.addClick(new Point(100, 100));
    	assertTrue(engine.wasClicked(all));
    	engine.update();
    	assertFalse(engine.wasClicked(all));
    }
}
