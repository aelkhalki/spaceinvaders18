/**
 * 
 */
package nl.delftelectronics.spaceinvaders.core.entities;

import org.joda.time.Interval;

/**
 * Indicates that the Entity wants to be notified before the normal updates
 * 
 * @author Max
 *
 */
public interface PreUpdatable {
	/**
	 * Called before other entities run update
	 * 
	 * @param delta
	 *            The amount of time elapsed since the last update
	 */
	void preUpdate(Interval delta);
}
