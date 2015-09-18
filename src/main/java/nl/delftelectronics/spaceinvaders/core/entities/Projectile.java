package nl.delftelectronics.spaceinvaders.core.entities;

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

	/**
	 * Creates a new projectile
	 * 
	 * @param positionX
	 *            The left offset of the projectile
	 * @param positionY
	 *            The top offset of the projectile
	 * @param width
	 *            The width of the collision box of the projectile
	 * @param height
	 *            The height of the collision box of the projectile
	 * @param spriteName
	 *            The sprite name
	 * @param isPlayerOwned
	 *            Whether the projectile is player owned
	 */
	public Projectile(double positionX, double positionY,
			double width, double height, String spriteName,
			boolean isPlayerOwned) {
		super(positionX, positionY, width, height, spriteName);

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
