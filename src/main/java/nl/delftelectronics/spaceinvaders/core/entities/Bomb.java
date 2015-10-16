package nl.delftelectronics.spaceinvaders.core.entities;

import java.awt.geom.Rectangle2D;

import org.joda.time.Interval;

import nl.delftelectronics.spaceinvaders.core.Collidable;
import nl.delftelectronics.spaceinvaders.core.Direction;
import nl.delftelectronics.spaceinvaders.core.DummyCollidable;

/**
 * A Bomb is a projectile that can be shot by an object. If a bullet hits an
 * Enemy, it also hits adjacent enemies. A Bomb has a position and a direction.
 */
public class Bomb extends Projectile {
	public static final double MOVING_SPEED = 0.192;
	private static final int IMPACT_RADIUS = 150;
	private static final String FILENAME = "/bomb.png";
	public static final int WIDTH = 25;
	public static final int HEIGHT = 40;

	/**
	 * Create a Bomb with an initial position, size and direction.
	 *
	 * @param position
	 * 			  position of the sprite
	 */
	public Bomb(Rectangle2D position) {
		super(position, FILENAME, true);
	}

	/**
	 * Legacy update function
	 * 
	 * @param delta
	 *            The amount of time in this frame
	 */
	public void updatePosition(Interval delta) {
		if (direction == Direction.NORTH) {
			setPositionY(getPositionY() - MOVING_SPEED * (double) delta.toDurationMillis());
		} else if (direction == Direction.SOUTH) {
			setPositionY(getPositionY() + MOVING_SPEED * (double) delta.toDurationMillis());
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
