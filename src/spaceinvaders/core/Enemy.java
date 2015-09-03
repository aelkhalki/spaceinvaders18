package spaceinvaders.core;

public abstract class Enemy extends Actor implements AutomaticMovable {
    protected static final Integer MOVING_SPEED = 5;

    private Direction movingDirection;
    private Integer westBoundary;
    private Integer eastBoundary;

    public Enemy(Integer positionX, Integer positionY, Integer westBoundary, Integer eastBoundary,
            Direction movingDirection) {
        super(positionX, positionY);
        this.setMovingDirection(movingDirection);
        this.westBoundary = westBoundary;
        this.eastBoundary = eastBoundary;
    }

    public Enemy(Integer positionX, Integer positionY, Integer westBoundary, Integer eastBoundary) {
        this(positionX, positionY, westBoundary, eastBoundary, Direction.EAST);
    }

    public Direction getMovingDirection() {
        return movingDirection;
    }

    public void setMovingDirection(Direction movingDirection) {
        this.movingDirection = movingDirection;
    }

    public void updatePosition() {
        if (getPositionX() <= westBoundary || getPositionX() >= eastBoundary) {
            moveDown();
        }
        if (movingDirection == Direction.EAST) {
            setPositionX(getPositionX() + MOVING_SPEED);
        } else if (movingDirection == Direction.WEST) {
            setPositionX(getPositionX() - MOVING_SPEED);
        }
    }

    public void moveDown() {
        setPositionY(getPositionY() + MOVING_SPEED);
        if (movingDirection == Direction.EAST) {
            movingDirection = Direction.WEST;
        } else if (movingDirection == Direction.WEST) {
            movingDirection = Direction.EAST;
        }
    }

    public abstract Integer getPoints();
}
