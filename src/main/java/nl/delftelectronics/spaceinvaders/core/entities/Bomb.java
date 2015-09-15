package nl.delftelectronics.spaceinvaders.core.entities;

import java.awt.Rectangle;

import org.joda.time.Interval;

import nl.delftelectronics.spaceinvaders.core.Collidable;
import nl.delftelectronics.spaceinvaders.core.Direction;
import nl.delftelectronics.spaceinvaders.core.DummyCollidable;

/**
 * A Bomb is a projectile that can be shot by an object. If a bullet hits an Enemy, it also hits
 * adjacent enemies. A Bomb has a position and a direction.
 */
public class Bomb extends Projectile {
	public static final Integer MOVING_SPEED = 8 * 24;
    private static final Integer IMPACT_RADIUS = 150;
    private static final String FILENAME = "/bomb.png";
    public static final Integer WIDTH = 25;
    public static final Integer HEIGHT = 40;

    /**
     * Create a Bomb with an initial position, size and direction.
     *
     * @param positionX initial x-position.
     * @param positionY initial y-position.
     * @param width     width of the Bomb.
     * @param height    height of the Bomb.
     * @param direction direction of the Bomb.
     */
    public Bomb(double positionX, double positionY, double width, double height,
                Direction direction) {
        super(positionX, positionY, width, height, FILENAME, true);
    }
    
    /**
     * Legacy update function
     */
    public void updatePosition(Interval delta) {
        if (direction == Direction.NORTH) {
            setPositionY(getPositionY() - MOVING_SPEED * (double)delta.toDurationMillis() / 1000);
        } else if (direction == Direction.SOUTH) {
            setPositionY(getPositionY() + MOVING_SPEED * (double)delta.toDurationMillis() / 1000);
        }
    }

    /**
     * Return the bounding box of the impact area.
     *
     * @return the bounding box of the impact area.
     */
    public Collidable impactArea() {
        return new DummyCollidable(getPositionX() - IMPACT_RADIUS / 2, getPositionY() - IMPACT_RADIUS / 2,
                IMPACT_RADIUS * 2,
                IMPACT_RADIUS * 2);
    }
}
