package nl.delftelectronics.spaceinvaders;

import nl.delftelectronics.spaceinvaders.core.Direction;
import nl.delftelectronics.spaceinvaders.core.SmallEnemy;
import junit.framework.Assert;
import junit.framework.TestCase;


/**
 * Testing the movement of the smallEnemy, moves down, left and right
 * @author sabribarac
 *
 */
public class PointTest extends TestCase {
	
	SmallEnemy vijand;
	
	public void init(){
		//Enemy(Integer positionX, Integer positionY, Integer width, Integer height, Integer westBoundary,
	    //       Integer eastBoundary, Integer southBoundary, Direction movingDirection)
		vijand = new SmallEnemy(50,25,4,4,0,100,0,Direction.EAST);
		
	}
	
	public void testStaticMovement(){
		init();
		Assert.assertEquals((Integer)50, vijand.getPositionX());
		Assert.assertEquals(Direction.EAST, vijand.getMovingDirection());
		Assert.assertEquals((Integer)25, vijand.getPositionY());
		
	}

	public void testMovementLeft(){
		init();
		Assert.assertEquals((Integer)25, vijand.getPositionY());
		vijand.moveLeft();
		Assert.assertEquals((Integer)45, vijand.getPositionX());
		Assert.assertNotSame((Integer)50, vijand.getPositionX());
	}
	
	public void testMovementRight(){
		init();
		Assert.assertEquals((Integer)25, vijand.getPositionY());
		vijand.moveRight();
		Assert.assertEquals((Integer)55, vijand.getPositionX());
		Assert.assertNotSame((Integer)50, vijand.getPositionX());
	}
	
	public void testMovementDown(){
		init();
		Assert.assertEquals((Integer)25, vijand.getPositionY());
		vijand.moveDown();
		Assert.assertEquals((Integer)45, vijand.getPositionY());
	}
	

}
