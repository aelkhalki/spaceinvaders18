package spaceinvaders.core;

public abstract class Enemy extends Actor implements AutomaticMovable {
    protected static final Integer MOVING_SPEED = 3;
    protected static final Integer MOVE_DOWN_SPEED = 20;

    private Direction movingDirection;
    private Integer westBoundary;
    private Integer eastBoundary;
    private Integer southBoundary;

    public Enemy(Integer positionX, Integer positionY, Integer westBoundary, Integer eastBoundary,
            Integer southBoundary, Direction movingDirection) {
        super(positionX, positionY);
        this.setMovingDirection(movingDirection);
        this.westBoundary = westBoundary;
        this.eastBoundary = eastBoundary;
        this.southBoundary = southBoundary;
    }

    public Enemy(Integer positionX, Integer positionY, Integer westBoundary, Integer eastBoundary,
            Integer southBoundary) {
        this(positionX, positionY, westBoundary, eastBoundary, southBoundary, Direction.EAST);
    }

    public Direction getMovingDirection() {
        return movingDirection;
    }

    public void setMovingDirection(Direction movingDirection) {
        this.movingDirection = movingDirection;
    }

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

    public boolean reachedBoundary() {
        return getPositionX() <= westBoundary || getPositionX() >= eastBoundary;
    }

    public boolean reachedBottom() {
        return getPositionY() + 200 >= southBoundary;
    }

    public void moveDown() {
        setPositionY(getPositionY() + MOVE_DOWN_SPEED);
        if (movingDirection == Direction.EAST) {
            movingDirection = Direction.WEST;
        } else if (movingDirection == Direction.WEST) {
            movingDirection = Direction.EAST;
        }
    }

    public abstract Integer getPoints();
}
