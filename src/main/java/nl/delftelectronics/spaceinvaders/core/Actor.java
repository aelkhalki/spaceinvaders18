package nl.delftelectronics.spaceinvaders.core;

/**
 * An actor is a non-playable drawable character.
 */
public abstract class Actor extends SpriteEntity {
    protected static final Integer MOVING_SPEED = 5;

    private Integer westBoundary;
    private Integer eastBoundary;

    /**
     * Create an Actor with a given position and size.
     *
     * @param positionX    x-position of the Actor.
     * @param positionY    y-position of the Actor.
     * @param width        width of the Actor.
     * @param height       height of the Actor.
     * @param spriteName   filename of the sprite.
     * @param westBoundary westernmost boundary of the playing field.
     * @param eastBoundary easternmost boundary of the playing field.
     */
    public Actor(Integer positionX, Integer positionY, Integer width, Integer height,
                 String spriteName, Integer westBoundary, Integer eastBoundary) {
        super(positionX, positionY, width, height, spriteName);
        this.westBoundary = westBoundary;
        this.eastBoundary = eastBoundary;
    }

    /**
     * Move the Actor to the left, based on MOVING_SPEED.
     */
    public void moveLeft() {
        if (getPositionX() - MOVING_SPEED >= westBoundary) {
            setPositionX(getPositionX() - MOVING_SPEED);
        }
    }

    /**
     * Move the Actor to the right, based on MOVING_SPEED.
     */
    public void moveRight() {
        if (getPositionX() + getWidth() + MOVING_SPEED <= eastBoundary) {
            setPositionX(getPositionX() + MOVING_SPEED);
        }
    }
}
