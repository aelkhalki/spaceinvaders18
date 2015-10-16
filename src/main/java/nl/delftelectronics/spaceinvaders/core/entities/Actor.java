package nl.delftelectronics.spaceinvaders.core.entities;

import java.awt.geom.Rectangle2D;

import org.joda.time.Interval;

/**
 * An actor is a non-playable drawable character.
 */
public abstract class Actor extends SpriteEntity {
	protected static final double MOVING_SPEED = 0.120;

	private double westBoundary;
	private double eastBoundary;

	/**
	 * Create an Actor with a given position and size.
	 *
	 * @param position
	 * 			  position of the sprite
	 * @param spriteName
	 *            filename of the sprite.
	 * @param westBoundary
	 *            westernmost boundary of the playing field.
	 * @param eastBoundary
	 *            easternmost boundary of the playing field.
	 */
	public Actor(Rectangle2D position, String spriteName,
			double westBoundary, double eastBoundary) {
		super(position, spriteName);
		this.westBoundary = westBoundary;
		this.eastBoundary = eastBoundary;
	}

	/**
	 * Move the Actor to the left, based on MOVING_SPEED.
	 * 
	 * @param delta
	 *            The amount of time the Actor has been moving
	 */
	public void moveLeft(Interval delta) {
		double movement = MOVING_SPEED * delta.toDurationMillis();
		if (getPositionX() - movement >= westBoundary) {
			setPositionX(getPositionX() - movement);
		}
	}

	/**
	 * Move the Actor to the right, based on MOVING_SPEED.
	 * 
	 * @param delta
	 *            The amount of time the Actor has been moving
	 */
	public void moveRight(Interval delta) {
		double movement = MOVING_SPEED * delta.toDurationMillis();
		if (getPositionX() + getWidth() + movement <= eastBoundary) {
			setPositionX(getPositionX() + movement);
		}
	}
}
