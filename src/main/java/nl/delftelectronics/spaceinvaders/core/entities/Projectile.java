package nl.delftelectronics.spaceinvaders.core.entities;

import java.awt.Rectangle;
import java.util.List;

import org.joda.time.Interval;

import nl.delftelectronics.spaceinvaders.core.Collidable;
import nl.delftelectronics.spaceinvaders.core.Direction;

/**
 * A Projectile is an object that can be thrown or fired.
 */
public abstract class Projectile extends SpriteEntity implements Collidable {
	protected boolean isPlayerOwned = false;
	protected Direction direction;
	
    public Projectile(double positionX, double positionY, double width, double height, String spriteName, boolean isPlayerOwned) {
		super(positionX, positionY, width, height, spriteName);
		
		this.isPlayerOwned = isPlayerOwned;
        this.direction = isPlayerOwned ? Direction.NORTH : Direction.SOUTH;
	}
    
    /**
     * Updates the bullet position
     */
    @Override
    public void update(Interval delta) {
    	updatePosition(delta);
    	
    	List<Entity> doesTouch = scene.getCollisions(this);
    	if(doesTouch.size() == 0) {
    		return;
    	}
    	
    	List<Entity> collisions = scene.getCollisions(impactArea());
    	
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
     * Return the direction of the Bullet.
     *
     * @return the direction of the Bullet.
     */
    public Direction getDirection() {
        return direction;
    }
    
    abstract void updatePosition(Interval delta);

	abstract Collidable impactArea();
}
