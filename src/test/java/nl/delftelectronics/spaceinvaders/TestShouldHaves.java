//CHECKSTYLE.OFF: MagicNumber
package nl.delftelectronics.spaceinvaders;

import junit.framework.Assert;
import junit.framework.TestCase;
import nl.delftelectronics.spaceinvaders.core.Engine;
import nl.delftelectronics.spaceinvaders.core.entities.Entity;
import nl.delftelectronics.spaceinvaders.core.entities.LabelEntity;
import nl.delftelectronics.spaceinvaders.core.entities.LargeEnemy;
import nl.delftelectronics.spaceinvaders.core.entities.MediumEnemy;
import nl.delftelectronics.spaceinvaders.core.entities.SmallEnemy;
import nl.delftelectronics.spaceinvaders.core.scenes.PlayScene;

/**
 * @author Max
 *
 */
public class TestShouldHaves extends TestCase {

	/**
	 * Tests the Game Over label when losing the game
	 */
	public void testEnding() {
		PlayScene s = new PlayScene(null);
		Engine.getInstance().setScene(s);
		s.update();
		s.lose();
		s.update();

		boolean found = false;
		for (Entity e : s.getEntities()) {
			if (e instanceof LabelEntity) {
				if (((LabelEntity) e).getText().equalsIgnoreCase("BACK TO MAIN MENU")) {
					found = true;
				}
			}
		}
		Assert.assertTrue(found);
	}
	
	/**
	 * Tests wether the score label exists
	 */
	public void testScore() {
		PlayScene s = new PlayScene(null);
		Engine.getInstance().setScene(s);
		s.update();
		
		boolean found = false;
		for (Entity e : s.getEntities()) {
			if (e instanceof LabelEntity) {
				if (((LabelEntity) e).getText().contains("Score")) {
					found = true;
				}
			}
		}
		Assert.assertTrue(found);
	}
	
	/**
	 * Tests if all enemy types are present in the default game
	 */
	public void testEnemyTypes() {
		PlayScene s = new PlayScene(null);
		Engine.getInstance().setScene(s);
		s.update();
		
		boolean foundSmall = false;
		boolean foundMedium = false;
		boolean foundLarge = false;
		for (Entity e : s.getEntities()) {
			if (e instanceof SmallEnemy) {
				foundSmall = true;
			}
			if (e instanceof MediumEnemy) {
				foundMedium = true;
			}
			if (e instanceof LargeEnemy) {
				foundLarge = true;
			}
		}
		Assert.assertTrue(foundSmall);
		Assert.assertTrue(foundMedium);
		Assert.assertTrue(foundLarge);
	}
	
	/**
	 * Tests if killing an enemy gives the right amount of points
	 */
	public void testEnemyScore() {
		PlayScene s = new PlayScene(null);
		Engine.getInstance().setScene(s);
		s.update();
		
		for (Entity e : s.getEntities()) {
			if (e instanceof SmallEnemy) {
				int prevScore = s.getPoints();
				((SmallEnemy) e).kill();
				Assert.assertEquals(prevScore + 40, s.getPoints());
			}
			if (e instanceof MediumEnemy) {
				int prevScore = s.getPoints();
				((MediumEnemy) e).kill();
				Assert.assertEquals(prevScore + 20, s.getPoints());
			}
			if (e instanceof LargeEnemy) {
				int prevScore = s.getPoints();
				((LargeEnemy) e).kill();
				Assert.assertEquals(prevScore + 10, s.getPoints());
			}
		}
	}
}
