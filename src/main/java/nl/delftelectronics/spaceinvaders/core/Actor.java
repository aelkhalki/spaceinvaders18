package nl.delftelectronics.spaceinvaders.core;

/**
 * An actor is a non-playable drawable character.
 */
public abstract class Actor extends Entity {
    protected static final Integer MOVING_SPEED = 5;

    /**
     * Create an Actor with a given position and size.
     *
     * @param positionX x-position of the Actor.
     * @param positionY y-position of the Actor.
     * @param width     width of the Actor.
     * @param height    height of the Actor.
     */
    public Actor(Integer positionX, Integer positionY, Integer width, Integer height) {
        super(positionX, positionY, width, height);
    }

    /**
     * Move the Actor to the left, based on MOVING_SPEED.
     */
    public void moveLeft() {
        setPositionX(getPositionX() - MOVING_SPEED);
    }

    /**
     * Move the Actor to the right, based on MOVING_SPEED.
     */
    public void moveRight() {
        setPositionX(getPositionX() + MOVING_SPEED);
    }
}
