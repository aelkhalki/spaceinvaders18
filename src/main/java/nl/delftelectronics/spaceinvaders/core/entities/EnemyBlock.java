/**
 * 
 */
package nl.delftelectronics.spaceinvaders.core.entities;

import java.util.Random;

import org.joda.time.Interval;

import nl.delftelectronics.spaceinvaders.core.Direction;

/**
 * @author Max
 *
 */
public class EnemyBlock extends Entity implements PreUpdatable {
	protected Direction movementDirection = Direction.EAST;
	private boolean shouldFlip = false;
	private boolean shouldDrop = false;
	public Random random = new Random();
	
	/**
	 * Requests the current block movement direction
	 * @return the current movement direction
	 */
	public Direction getDirection() {
		return movementDirection;
	}
	
	/**
	 * Determine whether the whole block should drop this update
	 * @return whether the whole block should drop
	 */
	public boolean getShouldDrop() {
		return shouldDrop;
	}
	
	/**
	 * Requests the block to flip movement direction
	 */
	public void flip() {
		shouldFlip = true;
	}
	
	/**
	 * Flips the direction of the enemy block if requested
	 * @param delta  The amount of time between frames
	 */
	public void preUpdate(Interval delta) {
		shouldDrop = false;
		if (shouldFlip) {
			shouldFlip = false;
			shouldDrop = true;
			if (movementDirection == Direction.EAST) {
				movementDirection = Direction.WEST;
			} else {
				movementDirection = Direction.EAST;
			}
		}
	}
}
