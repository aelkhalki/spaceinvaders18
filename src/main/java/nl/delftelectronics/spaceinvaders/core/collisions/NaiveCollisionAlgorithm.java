package nl.delftelectronics.spaceinvaders.core.collisions;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import nl.delftelectronics.spaceinvaders.core.Collidable;
import nl.delftelectronics.spaceinvaders.core.entities.Entity;

/**
 * Calculates collisions by checking each entity against the given collidee
 * @author Max
 *
 */
public class NaiveCollisionAlgorithm implements CollisionStrategy {
	private List<Entity> entities;
	
	/**
	 * Stores the entity list
	 * @param entities
	 * 				The entities to check against
	 */
	public void update(List<Entity> entities) {
		this.entities = entities;
	}

	/**
	 * Calculates collisions for the given collidee
	 * @param collidee
	 * 		The Collidable to check against
	 * @return
	 * 		The list of colliding entities
	 */
	public List<Entity> getCollisions(Collidable collidee) {
		ArrayList<Entity> result = new ArrayList<Entity>();

		Rectangle referenceRectangle = new Rectangle(
				(int) collidee.getPositionX(),
				(int) collidee.getPositionY(),
				(int) collidee.getWidth(),
				(int) collidee.getHeight());

		for (Entity candidate : entities) {
			if (candidate == collidee) {
				continue;
			}
			if (candidate instanceof Collidable) {
				Collidable c = (Collidable) candidate;
				Rectangle testRectangle = new Rectangle(
						(int) c.getPositionX(), (int) c.getPositionY(),
						(int) c.getWidth(), (int) c.getHeight());

				if (referenceRectangle.intersects(testRectangle)) {
					result.add(candidate);
				}
			}
		}

		return result;
	}

}
