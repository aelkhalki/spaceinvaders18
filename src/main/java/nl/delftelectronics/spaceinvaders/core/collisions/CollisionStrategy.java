package nl.delftelectronics.spaceinvaders.core.collisions;

import java.util.List;
import nl.delftelectronics.spaceinvaders.core.Collidable;
import nl.delftelectronics.spaceinvaders.core.entities.Entity;

/**
 * Interface for a collision checking algorithm
 * 
 * @author Max
 */
public interface CollisionStrategy {
	/**
	 * Updates the list of entities, precalculations can be done here on the
	 * scene
	 * 
	 * @param entities
	 *            The list of entities in the scene
	 */
	void update(List<Entity> entities);

	/**
	 * Calculates collisions for a given collidable
	 * 
	 * @param collidee
	 *            The collidable to check for collisions
	 * @return The colliding Entities
	 */
	List<Entity> getCollisions(Collidable collidee);
}
