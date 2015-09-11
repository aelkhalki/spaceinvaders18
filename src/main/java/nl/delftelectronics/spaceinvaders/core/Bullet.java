package nl.delftelectronics.spaceinvaders.core;

import java.awt.Rectangle;

/**
 * A Bullet is a projectile that can be shot by on object. A Bullet has a position and a direction.
 */
public class Bullet extends Entity implements AutomaticMovable, Projectile {
    private static final Integer MOVING_SPEED = 15;
    private static final Integer IMPACT_RADIUS = 1;
    private static final String FILENAME = "/bullet.png";
    public static final Integer WIDTH = 3;
    public static final Integer HEIGHT = 10;
    private Direction direction;

    /**
     * Create a Bullet with an initial position, size and direction.
     *
     * @param positionX initial x-position.
     * @param positionY initial y-position.
     * @param width     width of the Bullet.
     * @param height    height of the Bullet.
     * @param direction direction of the Bullet.
     */
    public Bullet(Integer positionX, Integer positionY, Integer width, Integer height,
                  Direction direction) {
        super(positionX, positionY, width, height);
        this.direction = direction;
    }

    /**
     * Return the direction of the Bullet.
     *
     * @return the direction of the Bullet.
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Update the position of the Bullet, based on the Direction and the MOVING_SPEED.
     */
    public void updatePosition() {
        if (direction == Direction.NORTH) {
            setPositionY(getPositionY() - MOVING_SPEED);
        } else if (direction == Direction.SOUTH) {
            setPositionY(getPositionY() + MOVING_SPEED);
        }
    }

    /**
     * Return the bounding box of the impact area.
     *
     * @return the bounding box of the impact area.
     */
    public Rectangle impactArea() {
        return new Rectangle(getPositionX() - IMPACT_RADIUS / 2, getPositionY() - IMPACT_RADIUS / 2,
                IMPACT_RADIUS * 2,
                IMPACT_RADIUS * 2);
    }

    /**
     * Return the filename of the bullet sprite.
     *
     * @return the filename of the bullet sprite.
     */
    public String getSpriteFilename() {
        return FILENAME;
    }
}
