/**
 * 
 */
package nl.delftelectronics.spaceinvaders.core;

import org.joda.time.Interval;

/**
 * Indicates that the Entity wants to be notified before the normal updates
 * @author Max
 *
 */
public interface PreUpdatable {
	void preUpdate(Interval delta);
}
