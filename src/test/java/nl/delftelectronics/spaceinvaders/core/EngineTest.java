package nl.delftelectronics.spaceinvaders.core;

import junit.framework.TestCase;
import nl.delftelectronics.spaceinvaders.core.scenes.GameScene;

import org.joda.time.Interval;
import org.mockito.ArgumentCaptor;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;

/**
 * Test the Engine class.
 */
public class EngineTest extends TestCase {
    
	public void testClick() {
		GameScene gameScene = mock(GameScene.class);
    	Engine engine = new Engine(gameScene, new ArrayList<String>());
    	
    	Collidable all = new DummyCollidable(0, 0, 1000, 1000);
    	
    	assertFalse(engine.wasClicked(all));
    	
    	engine.addClick(new Point(100, 100));
    	assertTrue(engine.wasClicked(all));
    	engine.update();
    	assertFalse(engine.wasClicked(all));
    }
}
