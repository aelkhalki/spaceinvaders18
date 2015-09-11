package nl.delftelectronics.spaceinvaders.core;

import java.util.List;

import org.joda.time.Interval;

/**
 * Represents a bullet fired by the player or an enemy
 * @author Max
 *
 */
public class Bullet extends SpriteEntity implements AutomaticMovable, Collidable {
    public static final Integer MOVING_SPEED = 15;
    private static final String FILENAME = "/bullet.png";
    public static final Integer WIDTH = 3;
    public static final Integer HEIGHT = 10;
    private Direction direction;
    private boolean isPlayerOwned;

    /**
     * Creates a new bullet at a specified location
     * @param positionX The x position to place the bullet
     * @param positionY The y position to place the bullet
     * @param width The width of the bullet
     * @param height The height of the bullet
     * @param direction The firing direction (must be up or down)
     */
    public Bullet(Integer positionX, Integer positionY,
    		Integer width, Integer height, boolean isPlayerOwned) {
        super(positionX, positionY, width, height, FILENAME);

        this.isPlayerOwned = isPlayerOwned;
        this.direction = isPlayerOwned ? Direction.NORTH : Direction.SOUTH;
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
    	
    	List<Entity> collisions = scene.getCollisions(this);
    	
    	if (isPlayerOwned) {
    		for (Entity e : collisions) {
    			if (e instanceof Enemy) {
    				destroy();
    				Enemy enemy = (Enemy) e;
    				enemy.kill();
    			}
    		}
    	} else {
    		for (Entity e : collisions) {
    			if (e instanceof Ship) {
    				destroy();
    				Ship s = (Ship) e;
    				s.hit();
    			}
    		}
    	}
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
