package nl.delftelectronics.spaceinvaders.core.entities;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.joda.time.Interval;

import nl.delftelectronics.spaceinvaders.core.Collidable;
import nl.delftelectronics.spaceinvaders.core.scenes.GameScene;
import junit.framework.Assert;
import junit.framework.TestCase;

public class BombTest extends TestCase {
	
	public void testBomb(){
		final double dimension = 10;

        GameScene scene = mock(GameScene.class);
        when(scene.getCollisions(any(Collidable.class))).thenReturn(new ArrayList<Entity>());
        
        //Speed is in millisecond
        Bomb bomb = new Bomb(dimension, dimension, dimension, dimension);
        bomb.initialize(scene);
        bomb.update(new Interval(0, 1));
        Assert.assertEquals(dimension - Bomb.MOVING_SPEED, bomb.getPositionY());
		
	}

}