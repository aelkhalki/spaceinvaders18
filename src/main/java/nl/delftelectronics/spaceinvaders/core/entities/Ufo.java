package nl.delftelectronics.spaceinvaders.core.entities;

import java.util.Random;

import org.joda.time.Interval;

import nl.delftelectronics.spaceinvaders.core.Direction;

/**
 * An Ufo is an enemy that does not shoot any bullets, and is worth a random number of points.
 */
public class Ufo extends Enemy {
    private static final int MINIMUM_POINTS = 100;
    private static final int MAXIMUM_POINTS = 1000;
    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;
    private static final String FILENAME = "/ufo.png";
    private static final double MOVING_SPEED = 0.21;
    private Random random;
    /**
     * Create an Enemy with the initial position, the size, the boundaries of the playing field
     * and the initial direction of the Enemy.
     *
     * @param positionX       initial x-position of the Enemy.
     * @param positionY       initial y-position of the Enemy.
     * @param westBoundary    westernmost boundary of the playing field.
     * @param eastBoundary    easternmost boundary of the playing field.
     * @param block			  the block of enemies this belongs to.
     */
    public Ufo(double positionX, double positionY, int westBoundary, int eastBoundary,
               EnemyBlock block) {
        super(positionX, positionY, WIDTH, HEIGHT, westBoundary, eastBoundary, Integer.MAX_VALUE,
                block, FILENAME);
        this.random = new Random();
    }
    
    @Override
    /**
     * Destroys the Entity and does not decrement the enemy count
     */
    public void kill() {
    	if (isKilled) {
    		return;
    	}
    	
    	isKilled = true;
    	destroy();
    	
    	if (playScene != null) {
    		playScene.addPoints(getPoints());
    	}
    }

    @Override
    public Integer getPoints() {
        return MINIMUM_POINTS + random.nextInt(MAXIMUM_POINTS - MINIMUM_POINTS);
    }

    @Override
    public void update(Interval delta) {
    	double movement = MOVING_SPEED * delta.toDurationMillis();
        if (block.getDirection() == Direction.EAST) {
            setPositionX(getPositionX() + movement);
        } else {
            setPositionX(getPositionX() - movement);
        }
    }
}
