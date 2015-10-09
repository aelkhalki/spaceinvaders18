package nl.delftelectronics.spaceinvaders.core.collisions;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import nl.delftelectronics.spaceinvaders.core.Collidable;
import nl.delftelectronics.spaceinvaders.core.entities.Entity;

/**
 * Calculates collisions based on sorting the Entities by x position. This helps
 * branch prediction and potentially allows skipping a lot of checks.
 * 
 * @author Max
 *
 */
public class SortedCollisionAlgorithm implements CollisionStrategy {

	List<Collidable> sortedEntities;

	/**
	 * Sorts the enties by x position
	 * 
	 * @param entities
	 *            The entities to process
	 */
	public void update(List<Entity> entities) {
		sortedEntities = new ArrayList<Collidable>();

		for (Entity e : entities) {
			if (e instanceof Collidable) {
				sortedEntities.add((Collidable) e);
			}
		}

		sortedEntities.sort(new EntitySorter());
	}

	/**
	 * Iterates over the Entities and returns the colliding Entities
	 * 
	 * @param collidee
	 *            The collider to check
	 * @return
	 * 			  The list of colliding entities
	 */
	public List<Entity> getCollisions(Collidable collidee) {
		ArrayList<Entity> results = new ArrayList<Entity>();

		Rectangle referenceRectangle = new Rectangle((int) collidee.getPositionX(),
				(int) collidee.getPositionY(),
				(int) collidee.getWidth(), (int) collidee.getHeight());

		for (Collidable c : sortedEntities) {
			if (c == collidee) {
				continue;
			}

			if (c.getPositionX() + c.getWidth() < collidee.getPositionX()) {
				continue;
			}

			if (c.getPositionX() > collidee.getPositionX() + collidee.getWidth()) {
				break;
			}

			Rectangle testRectangle = new Rectangle((int) c.getPositionX(),
					(int) c.getPositionY(), (int) c.getWidth(),
					(int) c.getHeight());

			if (referenceRectangle.intersects(testRectangle)) {
				results.add((Entity) c);
			}
		}

		return results;
	}

	/**
	 * Compares Collidables on x position
	 * 
	 * @author Max
	 *
	 */
	private static class EntitySorter implements Comparator<Collidable> {

		/**
		 * Calculates offset between two collidables
		 */
		public int compare(Collidable o1, Collidable o2) {
			return (int) (o1.getPositionX() - o2.getPositionX());
		}
	}
}
