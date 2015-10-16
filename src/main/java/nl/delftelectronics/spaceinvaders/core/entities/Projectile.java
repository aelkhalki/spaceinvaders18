package nl.delftelectronics.spaceinvaders.core.entities;

import java.awt.geom.Rectangle2D;
import java.util.List;
import org.joda.time.Interval;

import nl.delftelectronics.spaceinvaders.core.Collidable;
import nl.delftelectronics.spaceinvaders.core.Direction;
import nl.delftelectronics.spaceinvaders.core.Logger;

/**
 * A Projectile is an object that can be thrown or fired.
 */
public abstract class Projectile extends SpriteEntity implements Collidable {
	protected boolean isPlayerOwned = false;
	protected Direction direction;

	/**
	 * Creates a new projectile
	 * 
	 * @param position
	 * 			  position of the sprite
	 * @param spriteName
	 *            The sprite name
	 * @param isPlayerOwned
	 *            Whether the projectile is player owned
	 */
	public Projectile(Rectangle2D position, String spriteName,
			boolean isPlayerOwned) {
		super(position, spriteName);

		this.isPlayerOwned = isPlayerOwned;
		if (isPlayerOwned) {
			this.direction = Direction.NORTH;
		} else {
			this.direction = Direction.SOUTH;
		}
	}

	/**
	 * Updates the bullet position
	 * 
	 * @param delta
	 *            The amount of time since the last update
	 */
	@Override
	public void update(Interval delta) {
		updatePosition(delta);

		List<Entity> doesTouch = scene.getCollisions(this);
		if (doesTouch.size() == 0) {
			return;
		}

		List<Entity> collisions = scene.getCollisions(impactArea());

		for (Entity e : collisions) {
			if (e instanceof Enemy && isPlayerOwned) {
				destroy();
				Enemy enemy = (Enemy) e;
				enemy.kill();

				Logger.info("%s is hit by a %s at (%f, %f)", e.getClass().getSimpleName(),
						this.getClass().getSimpleName(), getPositionX(), getPositionY());
			} else if (e instanceof Ship && !isPlayerOwned) {
				destroy();
				Ship s = (Ship) e;
				s.hit();

				Logger.info("%s is hit by a %s at (%f, %f)", s.getClass().getSimpleName(),
						this.getClass().getSimpleName(), getPositionX(), getPositionY());
			} else if (e instanceof Barricade) {
				destroy();
				Barricade barricade = (Barricade) e;
				barricade.hit();

				Logger.info("%s is hit by a %s at (%f, %f)", barricade.getClass().getSimpleName(),
						this.getClass().getSimpleName(), getPositionX(), getPositionY());
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

	/**
	 * Updates the position
	 * 
	 * @param delta
	 *            The amount of time since the last update
	 */
	abstract void updatePosition(Interval delta);

	/**
	 * Calculates the impact area of the projectile
	 * 
	 * @return A collidable representing the collision area
	 */
	abstract Collidable impactArea();
}
