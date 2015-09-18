package nl.delftelectronics.spaceinvaders.core.entities;

import org.joda.time.Interval;

import nl.delftelectronics.spaceinvaders.core.Collidable;
import nl.delftelectronics.spaceinvaders.core.Direction;
import nl.delftelectronics.spaceinvaders.core.Logger;
import nl.delftelectronics.spaceinvaders.core.scenes.GameScene;
import nl.delftelectronics.spaceinvaders.core.scenes.PlayScene;

/**
 * An Enemy is an object that moves on itself and tries to shoot bullets at the
 * player. The Enemy starts moving east. The enemy will continue moving
 * horizontally until it has reached a boundary of the playing field, after
 * which it will move down and start moving the other direction horizontally.
 */
public abstract class Enemy extends SpriteEntity implements Collidable {
	protected static final double MOVING_SPEED = 0.120;
	protected static final int MOVE_DOWN_SPEED = 20;
	private static final int MARGIN_FROM_BOTTOM = 100;
	private static final double SHOOTING_CHANCE = 0.0001;
	private static final double BULLET_WIDTH = 3;
	private static final double BULLET_HEIGHT = 10;

	private Integer westBoundary;
	private Integer eastBoundary;
	private Integer southBoundary;
	protected PlayScene playScene;
	protected EnemyBlock block;
	protected boolean isKilled = false;

	/**
	 * Create an Enemy with the initial position, the size, the boundaries of
	 * the playing field and the initial direction of the Enemy.
	 *
	 * @param positionX
	 *            initial x-position of the Enemy.
	 * @param positionY
	 *            initial y-position of the Enemy.
	 * @param width
	 *            width of the Enemy.
	 * @param height
	 *            height of the Enemy.
	 * @param westBoundary
	 *            westernmost boundary of the playing field.
	 * @param eastBoundary
	 *            easternmost boundary of the playing field.
	 * @param southBoundary
	 *            southernmost boundary of the playing field.
	 * @param block
	 *            the block of enemies this enemy belongs to.
	 * @param spriteName
	 *            filename of the sprite.
	 */
	public Enemy(double positionX, double positionY, double width,
			double height, int westBoundary, int eastBoundary,
			int southBoundary, EnemyBlock block, String spriteName) {
		super(positionX, positionY, width, height, spriteName);
		this.westBoundary = westBoundary;
		this.eastBoundary = eastBoundary;
		this.southBoundary = southBoundary;
		this.block = block;
	}

	/**
	 * Creates a reference to the current PlayScene if it exists.
	 */
	@Override
	public void initialize(GameScene scene) {
		super.initialize(scene);
		if (scene instanceof PlayScene) {
			playScene = (PlayScene) scene;
		}
	}

	/**
	 * Destroys the Entity and decrements the enemy count
	 */
	public void kill() {
		if (isKilled) {
			return;
		}

		isKilled = true;
		destroy();

		if (playScene != null) {
			playScene.enemyCount--;
			playScene.addPoints(getPoints());

			if (playScene.enemyCount == 0) {
				playScene.win();
			}
		}
	}

	/**
	 * Update the position of the Bullet, based on the Direction and the
	 * MOVING_SPEED.
	 */
	@Override
	public void update(Interval delta) {
		double prevX = getPositionX();
		double prevY = getPositionY();
		
		Direction movingDirection = Direction.NORTH;
		movingDirection = block.getDirection();

		double movement = MOVING_SPEED * delta.toDurationMillis();

		if (block.getShouldDrop()) {
			moveDown();
		}

		if (movingDirection == Direction.EAST) {
			setPositionX(getPositionX() + movement);
		} else if (movingDirection == Direction.WEST) {
			setPositionX(getPositionX() - movement);
		}
		if (reachedBoundary()) {
			block.flip();
		} else if (reachedBottom() && playScene != null) {
			playScene.lose();
		}

		if (block.random.nextDouble() < SHOOTING_CHANCE) {
			fire();
		}
		
		if (prevX != getPositionX() || prevY != getPositionY()) {
			Logger.info("%s moved from (%f, %f) to (%f, %f)", this.getClass().toString(),
					prevX, prevY, getPositionX(), getPositionY());
		}
	}

	/**
	 * Shoots a bullet
	 */
	public void fire() {
		Bullet enemyBullet = new Bullet(getPositionX(), getPositionY(),
				BULLET_WIDTH, BULLET_HEIGHT, false);
		scene.addEntity(enemyBullet);
		Logger.info("%s fired a Bullet at (%f, %f) in the direction South",
				this.getClass().toString(),
				getPositionX(), getPositionY());
	}

	/**
	 * Return true iff the Enemy has reached the west or east boundary.
	 *
	 * @return true iff the Enemy has reached the west or east boundary.
	 */
	public boolean reachedBoundary() {
		return (getPositionX() <= westBoundary && block.getDirection() == Direction.WEST)
				|| (getPositionX() + getWidth() >= eastBoundary
					&& block.getDirection() == Direction.EAST);
	}

	/**
	 * Return true iff the Enemy has approached the south boundary.
	 *
	 * @return true iff the Enemy has approached the south boundary.
	 */
	public boolean reachedBottom() {
		return getPositionY() + getHeight() + MARGIN_FROM_BOTTOM >= southBoundary;
	}

	/**
	 * Move the Enemy down based on MOVE_DOWN_SPEED and switch its direction
	 * (i.e. Direction.EAST becomes Direction.WEST and vice versa).
	 */
	public void moveDown() {
		setPositionY(getPositionY() + MOVE_DOWN_SPEED);
	}

	/**
	 * Return the number of points the Enemy is worth for shooting down.
	 *
	 * @return the number of points the Enemy is worth for shooting down.
	 */
	public abstract Integer getPoints();
}
