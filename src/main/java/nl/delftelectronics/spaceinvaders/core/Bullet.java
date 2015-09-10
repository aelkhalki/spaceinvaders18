package nl.delftelectronics.spaceinvaders.core;

import org.joda.time.Interval;

/**
 * Represents a bullet fired by the player or an enemy
 * @author Max
 *
 */
public class Bullet extends SpriteEntity implements AutomaticMovable {
    public static final Integer MOVING_SPEED = 15;
    private static final String FILENAME = "/bullet.png";
    public static final Integer WIDTH = 3;
    public static final Integer HEIGHT = 10;
    private Direction direction;

    /**
     * Creates a new bullet at a specified location
     * @param positionX The x position to place the bullet
     * @param positionY The y position to place the bullet
     * @param width The width of the bullet
     * @param height The height of the bullet
     * @param direction The firing direction (must be up or down)
     */
    public Bullet(Integer positionX, Integer positionY,
    		Integer width, Integer height, Direction direction) {
        super(positionX, positionY, width, height, FILENAME);

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
     * Updates the bullet position
     */
    @Override
    public void update(Interval delta) {
    	updatePosition();
    }

    /**
     * Legacy update function
     */
    public void updatePosition() {
        if (direction == Direction.NORTH) {
            setPositionY(getPositionY() - MOVING_SPEED);
        } else if (direction == Direction.SOUTH) {
            setPositionY(getPositionY() + MOVING_SPEED);
        }
    }
}
