package nl.delftelectronics.spaceinvaders.core;

/**
 * An Enemy is an object that moves on itself and tries to shoot bullets at the player. The Enemy
 * starts moving east. The enemy will continue moving horizontally until it has reached a
 * boundary of the playing field, after which it will move down and start moving the other
 * direction horizontally.
 */
public abstract class Enemy extends Actor implements AutomaticMovable {
    protected static final Integer MOVING_SPEED = 3;
    protected static final Integer MOVE_DOWN_SPEED = 20;
    private static final Integer MARGIN_FROM_BOTTOM = 100;

    private Direction movingDirection;
    private Integer westBoundary;
    private Integer eastBoundary;
    private Integer southBoundary;

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
     * @param movingDirection initial direction of the Enemy.
     * @param spriteName      filename of the sprite.
     */
    public Enemy(Integer positionX, Integer positionY, Integer width, Integer height,
                 Integer westBoundary, Integer eastBoundary, Integer southBoundary,
                 Direction movingDirection, String spriteName) {
        super(positionX, positionY, width, height, spriteName, westBoundary, eastBoundary);
        this.setMovingDirection(movingDirection);
        this.westBoundary = westBoundary;
        this.eastBoundary = eastBoundary;
        this.southBoundary = southBoundary;
    }

    /**
     * Create an Enemy with the initial position, the size, the boundaries of the playing field.
     * The initial direction is east.
     *
     * @param positionX     initial x-position of the Enemy.
     * @param positionY     initial y-position of the Enemy.
     * @param width         width of the Enemy.
     * @param height        height of the Enemy.
     * @param westBoundary  westernmost boundary of the playing field.
     * @param eastBoundary  easternmost boundary of the playing field.
     * @param southBoundary southernmost boundary of the playing field.
     * @param spriteName    filename of the sprite.
     */
    public Enemy(Integer positionX, Integer positionY, Integer width, Integer height,
                 Integer westBoundary, Integer eastBoundary, Integer southBoundary,
                 String spriteName) {
        this(positionX, positionY, width, height, westBoundary, eastBoundary, southBoundary,
                Direction.EAST, spriteName);
    }

    /**
     * Return the current Direction.
     *
     * @return the current Direction.
     */
    public Direction getMovingDirection() {
        return movingDirection;
    }

    /**
     * Change the Direction of this Enemy.
     *
     * @param movingDirection the new Direction of the Enemy.
     */
    public void setMovingDirection(Direction movingDirection) {
        this.movingDirection = movingDirection;
    }

    /**
     * Update the position of the Bullet, based on the Direction and the MOVING_SPEED.
     *
     * @throws BoundaryReachedException    thrown iff the Enemy has reached the west or east
     *                                     boundary.
     * @throws EnemyReachedBottomException thrown iff the Enemy has approached the south boundary
     *                                     of the playing field.
     */
    public void updatePosition() throws BoundaryReachedException, EnemyReachedBottomException {
        if (movingDirection == Direction.EAST) {
            setPositionX(getPositionX() + MOVING_SPEED);
        } else if (movingDirection == Direction.WEST) {
            setPositionX(getPositionX() - MOVING_SPEED);
        }
        if (reachedBoundary()) {
            throw new BoundaryReachedException();
        } else if (reachedBottom()) {
            throw new EnemyReachedBottomException();
        }
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
        if (movingDirection == Direction.EAST) {
            movingDirection = Direction.WEST;
        } else if (movingDirection == Direction.WEST) {
            movingDirection = Direction.EAST;
        }
    }

    /**
     * Return the number of points the Enemy is worth for shooting down.
     *
     * @return the number of points the Enemy is worth for shooting down.
     */
    public abstract Integer getPoints();
}
