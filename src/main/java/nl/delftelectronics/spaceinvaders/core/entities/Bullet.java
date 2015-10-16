package nl.delftelectronics.spaceinvaders.core.entities;

import java.awt.geom.Rectangle2D;

import org.joda.time.Interval;

import nl.delftelectronics.spaceinvaders.core.Collidable;
import nl.delftelectronics.spaceinvaders.core.Direction;
import nl.delftelectronics.spaceinvaders.core.DummyCollidable;

/**
 * Represents a bullet fired by the player or an enemy
 *
 * @author Max
 */
public class Bullet extends Projectile {
	public static final double MOVING_SPEED = 0.36;
	private static final int IMPACT_RADIUS = 1;
	private static final String FILENAME = "/bullet.png";
	public static final int WIDTH = 3;
	public static final int HEIGHT = 10;

	/**
	 * Creates a new bullet at a specified location
	 *
	 * @param position
	 * 			  position of the sprite
	 * @param isPlayerOwned
	 *            Whether this is an enemy bullet or a player owned one
	 */
	public Bullet(Rectangle2D position, boolean isPlayerOwned) {
		super(position, FILENAME, isPlayerOwned);
	}

	/**
	 * Legacy update function
	 * @param delta
	 * 			The amount of time since the last update
	 */
	public void updatePosition(Interval delta) {
		if (direction == Direction.NORTH) {
			setPositionY(getPositionY() - MOVING_SPEED * delta.toDurationMillis());
		} else if (direction == Direction.SOUTH) {
			setPositionY(getPositionY() + MOVING_SPEED * delta.toDurationMillis());
		}
	}

	/**
	 * Return the bounding box of the impact area.
	 *
	 * @return the bounding box of the impact area.
	 */
	public Collidable impactArea() {
		return new DummyCollidable(getPositionX() - IMPACT_RADIUS / 2,
				getPositionY() - IMPACT_RADIUS / 2,
				IMPACT_RADIUS * 2, IMPACT_RADIUS * 2);
	}
}
