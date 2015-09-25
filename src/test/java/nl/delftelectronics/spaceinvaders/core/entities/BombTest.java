package nl.delftelectronics.spaceinvaders.core.entities;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import nl.delftelectronics.spaceinvaders.core.Collidable;
import nl.delftelectronics.spaceinvaders.core.scenes.GameScene;
import junit.framework.Assert;
import junit.framework.TestCase;

public class BombTest extends TestCase {
	
	public void testUpdate(){
		
		final double dimension = 10;

        GameScene scene = mock(GameScene.class);
        when(scene.getCollisions(any(Collidable.class))).thenReturn(new ArrayList<Entity>());
        
		Bomb bm = new Bomb(dimension,dimension,dimension,dimension);
		bm.initialize(scene);
		Assert.assertEquals(10.0, bm.getPositionX());
		Assert.assertEquals(10.0, bm.getPositionY());
		Assert.assertEquals(dimension - Bomb.MOVING_SPEED, 9.808);
	}
	

}
