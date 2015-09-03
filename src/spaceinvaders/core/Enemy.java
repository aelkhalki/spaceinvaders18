package spaceinvaders.core;

public abstract class Enemy extends Actor {
    protected static final Integer MOVING_SPEED = 5;

    private Direction movingDirection;

    public Enemy(Integer positionX, Integer positionY, Direction movingDirection) {
        super(positionX, positionY);
        this.setMovingDirection(movingDirection);
    }

    public Enemy(Integer positionX, Integer positionY) {
        this(positionX, positionY, Direction.EAST);
    }

    public Direction getMovingDirection() {
        return movingDirection;
    }

    public void setMovingDirection(Direction movingDirection) {
        this.movingDirection = movingDirection;
    }

    public void updatePosition() {
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
