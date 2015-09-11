/**
 * 
 */
package nl.delftelectronics.spaceinvaders.core;

import java.util.Random;

import org.joda.time.Interval;

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
	 * Determine wether the whole block should drop this update
	 * @return wether the whole block should drop
	 */
	public boolean getShouldDrop() {
		return shouldDrop;
	}
	
	/**
	 * Requests the block to flip movement direction
	 */
	public void Flip() {
		shouldFlip = true;
	}
	
	/**
	 * Flips the direction of the enemy block if requested
	 */
	public void preUpdate(Interval delta) {
		shouldDrop = false;
		if (shouldFlip) {
			shouldFlip = false;
			shouldDrop = true;
			movementDirection = movementDirection == Direction.EAST ?
					Direction.WEST : Direction.EAST;
		}
	}
}
