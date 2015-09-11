package nl.delftelectronics.spaceinvaders.core;

import java.util.Random;

/**
 * An Ufo is an enemy that does not shoot any bullets, and is worth a random number of points.
 */
public class Ufo extends Enemy {
    private static final Integer MINIMUM_POINTS = 100;
    private static final Integer MAXIMUM_POINTS = 1000;
    private static final Integer WIDTH = 100;
    private static final Integer HEIGHT = 100;
    private static final String FILENAME = "/ufo.png";
    private static final Integer MOVING_SPEED = 7;
    private Random random;
    /**
     * Create an Enemy with the initial position, the size, the boundaries of the playing field
     * and the initial direction of the Enemy.
     *
     * @param positionX       initial x-position of the Enemy.
     * @param positionY       initial y-position of the Enemy.
     * @param westBoundary    westernmost boundary of the playing field.
     * @param eastBoundary    easternmost boundary of the playing field.
     * @param movingDirection initial direction of the Enemy.
     */
    public Ufo(Integer positionX, Integer positionY, Integer westBoundary, Integer eastBoundary,
               Direction movingDirection) {
        super(positionX, positionY, WIDTH, HEIGHT, westBoundary, eastBoundary, null,
                movingDirection, FILENAME);
        this.random = new Random();
    }

    @Override
    public Integer getPoints() {
        return MINIMUM_POINTS + random.nextInt(MAXIMUM_POINTS - MINIMUM_POINTS);
    }

    @Override
    public void updatePosition() {
        if (getMovingDirection() == Direction.EAST) {
            setPositionX(getPositionX() + MOVING_SPEED);
        } else {
            setPositionX(getPositionX() - MOVING_SPEED);
        }

    }
}
