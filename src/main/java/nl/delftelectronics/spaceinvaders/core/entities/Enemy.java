package nl.delftelectronics.spaceinvaders.core.entities;

import org.joda.time.Interval;

import nl.delftelectronics.spaceinvaders.core.Collidable;
import nl.delftelectronics.spaceinvaders.core.Direction;
import nl.delftelectronics.spaceinvaders.core.scenes.GameScene;
import nl.delftelectronics.spaceinvaders.core.scenes.PlayScene;

/**
 * An Enemy is an object that moves on itself and tries to shoot bullets at the player. The Enemy
 * starts moving east. The enemy will continue moving horizontally until it has reached a
 * boundary of the playing field, after which it will move down and start moving the other
 * direction horizontally.
 */
public abstract class Enemy extends Actor implements Collidable {
    protected static final Integer MOVING_SPEED = 3;
    protected static final Integer MOVE_DOWN_SPEED = 20;
    private static final Integer MARGIN_FROM_BOTTOM = 100;

    private Integer westBoundary;
    private Integer eastBoundary;
    private Integer southBoundary;
    protected PlayScene playScene;
    protected EnemyBlock block;
    protected boolean isKilled = false;

    /**
     * Create an Enemy with the initial position, the size, the boundaries of the playing field
     * and the initial direction of the Enemy.
     *
     * @param positionX       initial x-position of the Enemy.
     * @param positionY       initial y-position of the Enemy.
     * @param width           width of the Enemy.
     * @param height          height of the Enemy.
     * @param westBoundary    westernmost boundary of the playing field.
     * @param eastBoundary    easternmost boundary of the playing field.
     * @param southBoundary   southernmost boundary of the playing field.
     * @param block 		  the block of enemies this enemy belongs to.
     * @param spriteName      filename of the sprite.
     */
    public Enemy(Integer positionX, Integer positionY, Integer width, Integer height,
                 Integer westBoundary, Integer eastBoundary, Integer southBoundary,
                 EnemyBlock block, String spriteName) {
        super(positionX, positionY, width, height, spriteName, westBoundary, eastBoundary);
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
     * Update the position of the Bullet, based on the Direction and the MOVING_SPEED.
     */
    @Override
    public void update(Interval delta) {
    	Direction movingDirection = Direction.NORTH;
    	movingDirection = block.getDirection();
    	
    	if (block.getShouldDrop()) {
    		moveDown();
    	}
    	
        if (movingDirection == Direction.EAST) {
            setPositionX(getPositionX() + MOVING_SPEED);
        } else if (movingDirection == Direction.WEST) {
            setPositionX(getPositionX() - MOVING_SPEED);
        }
        if (reachedBoundary()) {
        	block.flip();
        } else if (reachedBottom() && playScene != null) {
            playScene.lose();
        }
        
        if (block.random.nextDouble() < 0.0001) {
        	fire();
        }
    }
    
    /**
     * Shoots a bullet
     */
    public void fire() {
    	Bullet enemyBullet = new Bullet(getPositionX(), getPositionY(), 3, 10,
                false);
        scene.addEntity(enemyBullet);
    }

    /**
     * Return true iff the Enemy has reached the west or east boundary.
     *
     * @return true iff the Enemy has reached the west or east boundary.
     */
    public boolean reachedBoundary() {
        return getPositionX() <= westBoundary || getPositionX() + getWidth() >= eastBoundary;
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
     * Move the Enemy down based on MOVE_DOWN_SPEED and switch its direction (i.e. Direction.EAST
     * becomes Direction.WEST and vice versa).
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
