package nl.delftelectronics.spaceinvaders;

import nl.delftelectronics.spaceinvaders.core.Direction;
import nl.delftelectronics.spaceinvaders.core.LargeEnemy;
import nl.delftelectronics.spaceinvaders.core.MediumEnemy;
import nl.delftelectronics.spaceinvaders.core.SmallEnemy;
import junit.framework.Assert;
import junit.framework.TestCase;


/**
 * Testing the movement of the smallEnemy, moves down, left and right
 * @author sabribarac
 *
 */
public class EnemyMovementTest extends TestCase {
	
	SmallEnemy enemySmall;
	MediumEnemy enemyMedium;
	LargeEnemy enemyLarge;
	
	public void init(){
		//Enemy(Integer positionX, Integer positionY, Integer width, Integer height, Integer westBoundary,
	    //       Integer eastBoundary, Integer southBoundary, Direction movingDirection)
		enemySmall = new SmallEnemy(50,25,4,4,0,100,0,Direction.EAST);
		enemyMedium = new MediumEnemy(50,25,4,4,0,100,0,Direction.EAST);
		enemyLarge = new LargeEnemy(50,25,4,4,0,100,0,Direction.EAST);
		
	}
	
	public void testStaticMovement(){
		init();
		Assert.assertEquals((Integer)50, enemySmall.getPositionX());
		Assert.assertEquals(Direction.EAST, enemySmall.getMovingDirection());
		Assert.assertEquals((Integer)25, enemySmall.getPositionY());
		
		Assert.assertEquals((Integer)50, enemyMedium.getPositionX());
		Assert.assertEquals(Direction.EAST, enemyMedium.getMovingDirection());
		Assert.assertEquals((Integer)25, enemyMedium.getPositionY());
		
		Assert.assertEquals((Integer)50, enemyLarge.getPositionX());
		Assert.assertEquals(Direction.EAST, enemyLarge.getMovingDirection());
		Assert.assertEquals((Integer)25, enemyLarge.getPositionY());
	}

	public void testMovementLeft(){
		init();
		Assert.assertEquals((Integer)25, enemySmall.getPositionY());
		enemySmall.moveLeft();
		Assert.assertEquals((Integer)45, enemySmall.getPositionX());
		Assert.assertNotSame((Integer)50, enemySmall.getPositionX());
		
		Assert.assertEquals((Integer)25, enemyMedium.getPositionY());
		enemyMedium.moveLeft();
		Assert.assertEquals((Integer)45, enemyMedium.getPositionX());
		Assert.assertNotSame((Integer)50, enemyMedium.getPositionX());
		
		Assert.assertEquals((Integer)25, enemyLarge.getPositionY());
		enemyLarge.moveLeft();
		Assert.assertEquals((Integer)45, enemyLarge.getPositionX());
		Assert.assertNotSame((Integer)50, enemyLarge.getPositionX());
	}
	
	public void testMovementRight(){
		init();
		Assert.assertEquals((Integer)25, enemySmall.getPositionY());
		enemySmall.moveRight();
		Assert.assertEquals((Integer)55, enemySmall.getPositionX());
		Assert.assertNotSame((Integer)50, enemySmall.getPositionX());
		
		Assert.assertEquals((Integer)25, enemyMedium.getPositionY());
		enemyMedium.moveRight();
		Assert.assertEquals((Integer)55, enemyMedium.getPositionX());
		Assert.assertNotSame((Integer)50, enemyMedium.getPositionX());
		
		Assert.assertEquals((Integer)25, enemyLarge.getPositionY());
		enemyLarge.moveRight();
		Assert.assertEquals((Integer)55, enemyLarge.getPositionX());
		Assert.assertNotSame((Integer)50, enemyLarge.getPositionX());
	}
	
	public void testMovementDown(){
		init();
		Assert.assertEquals((Integer)25, enemySmall.getPositionY());
		enemySmall.moveDown();
		Assert.assertEquals((Integer)45, enemySmall.getPositionY());
		
		Assert.assertEquals((Integer)25, enemyMedium.getPositionY());
		enemyMedium.moveDown();
		Assert.assertEquals((Integer)45, enemyMedium.getPositionY());
		
		Assert.assertEquals((Integer)25, enemyLarge.getPositionY());
		enemyLarge.moveDown();
		Assert.assertEquals((Integer)45, enemyLarge.getPositionY());
	}
	

}
