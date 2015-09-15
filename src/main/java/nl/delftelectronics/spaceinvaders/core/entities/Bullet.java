package nl.delftelectronics.spaceinvaders.core.entities;

import java.util.List;

import org.joda.time.Interval;

import nl.delftelectronics.spaceinvaders.core.Collidable;
import nl.delftelectronics.spaceinvaders.core.Direction;
import nl.delftelectronics.spaceinvaders.core.DummyCollidable;

import java.awt.Rectangle;

/**
 * Represents a bullet fired by the player or an enemy
 *
 * @author Max
 */
public class Bullet extends Projectile {
    public static final Integer MOVING_SPEED = 15 * 24;
    private static final Integer IMPACT_RADIUS = 1;
    private static final String FILENAME = "/bullet.png";
    public static final Integer WIDTH = 3;
    public static final Integer HEIGHT = 10;

    /**
     * Creates a new bullet at a specified location
     *
     * @param positionX The x position to place the bullet
     * @param positionY The y position to place the bullet
     * @param width     The width of the bullet
     * @param height    The height of the bullet
     * @param direction The firing direction (must be up or down)
     */
    public Bullet(double positionX, double positionY,
    		double width, double height, boolean isPlayerOwned) {
        super(positionX, positionY, width, height, FILENAME, isPlayerOwned);
    }

    /**
     * Legacy update function
     */
    public void updatePosition(Interval delta) {
        if (direction == Direction.NORTH) {
            setPositionY(getPositionY() - (int)(MOVING_SPEED * (double)delta.toDurationMillis() / 1000));
        } else if (direction == Direction.SOUTH) {
            setPositionY(getPositionY() + (int)(MOVING_SPEED * (double)delta.toDurationMillis() / 1000));
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
