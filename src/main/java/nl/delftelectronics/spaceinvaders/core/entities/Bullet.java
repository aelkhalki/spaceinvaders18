package nl.delftelectronics.spaceinvaders.core.entities;

import java.util.List;

import org.joda.time.Interval;

import nl.delftelectronics.spaceinvaders.core.Collidable;
import nl.delftelectronics.spaceinvaders.core.Direction;

import java.awt.Rectangle;

/**
 * Represents a bullet fired by the player or an enemy
 *
 * @author Max
 */
public class Bullet extends SpriteEntity implements Projectile, Collidable {
    public static final Integer MOVING_SPEED = 15;
    private static final Integer IMPACT_RADIUS = 1;
    private static final String FILENAME = "/bullet.png";
    public static final Integer WIDTH = 3;
    public static final Integer HEIGHT = 10;
    private Direction direction;
    private boolean isPlayerOwned;

    /**
     * Creates a new bullet at a specified location
     *
     * @param positionX The x position to place the bullet
     * @param positionY The y position to place the bullet
     * @param width     The width of the bullet
     * @param height    The height of the bullet
     * @param direction The firing direction (must be up or down)
     */
    public Bullet(Integer positionX, Integer positionY,
    		Integer width, Integer height, boolean isPlayerOwned) {
        super(positionX, positionY, width, height, FILENAME);

        this.isPlayerOwned = isPlayerOwned;
        this.direction = isPlayerOwned ? Direction.NORTH : Direction.SOUTH;
    }

    /**
     * Creates a new bullet at a specified location
     *
     * @param positionX The x position to place the bullet
     * @param positionY The y position to place the bullet
     * @param width     The width of the bullet
     * @param height    The height of the bullet
     * @param direction The firing direction (must be up or down)
     * @param filename  The filename of the sprite.
     */
    public Bullet(Integer positionX, Integer positionY,
                  Integer width, Integer height, Direction direction, String filename) {
        super(positionX, positionY, width, height, filename);

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
}
