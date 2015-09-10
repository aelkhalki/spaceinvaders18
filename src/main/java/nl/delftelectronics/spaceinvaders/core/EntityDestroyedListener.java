/**
 * 
 */
package nl.delftelectronics.spaceinvaders.core;

import java.util.EventListener;

/**
 * Handles the intention of an entity to remove itself from the game
 * @author Max
 *
 */
public interface EntityDestroyedListener extends EventListener {
	public void entityDestroyed(Entity entity);
}
