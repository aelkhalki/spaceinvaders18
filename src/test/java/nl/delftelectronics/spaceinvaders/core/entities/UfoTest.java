package nl.delftelectronics.spaceinvaders.core.entities;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import nl.delftelectronics.spaceinvaders.core.Collidable;
import nl.delftelectronics.spaceinvaders.core.scenes.GameScene;
import junit.framework.Assert;
import junit.framework.TestCase;

public class UfoTest extends TestCase {

	public void testUfo(){
		final double dimension = 10;

        GameScene scene = mock(GameScene.class);
        when(scene.getCollisions(any(Collidable.class))).thenReturn(new ArrayList<Entity>());
        
        Ufo ufo = new Ufo(dimension,dimension,0,0,null);
        ufo.initialize(scene);
        
        Assert.assertEquals(dimension, ufo.getPositionX());
        Assert.assertEquals(dimension, ufo.getPositionY());   
	}
	
	public void testUfoLives(){
		final double dimension = 10;

        GameScene scene = mock(GameScene.class);
        when(scene.getCollisions(any(Collidable.class))).thenReturn(new ArrayList<Entity>());
        
        Ufo ufo = new Ufo(dimension,dimension,0,0,null);
        ufo.initialize(scene);
        
        Assert.assertEquals(false, ufo.isKilled);
        ufo.destroy();
        Assert.assertEquals(false, ufo.isKilled);
	}
}
