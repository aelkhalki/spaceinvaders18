/**
 * 
 */
package nl.delftelectronics.spaceinvaders.core.entities;

import org.joda.time.Interval;

/**
 * Indicates that the Entity wants to be notified after the normal updates
 * 
 * @author Max
 *
 */
public interface PostUpdatable {
	/**
	 * Called after other entities run update
	 * 
	 * @param delta
	 *            The amount of time elapsed since the last update
	 */
	void postUpdate(Interval delta);
}
