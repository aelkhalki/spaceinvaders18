/**
 *
 */
package nl.delftelectronics.spaceinvaders.core;

import java.util.EventListener;

/**
 * Handles the intention of an entity to remove itself from the game
 *
 * @author Max
 */
public interface EntityDestroyedListener extends EventListener {
    /**
     * Called then an entity is destroyed.
     *
     * @param entity the entity that is destroyed.
     */
    void entityDestroyed(Entity entity);
}
