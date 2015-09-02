package spaceinvaders.core;

public abstract class Enemy extends Actor {
    private Direction movingDirection;

    public Enemy(Integer positionX, Integer positionY, Direction movingDirection) {
        super(positionX, positionY);
        this.setMovingDirection(movingDirection);
    }

    public Direction getMovingDirection() {
        return movingDirection;
    }

    public void setMovingDirection(Direction movingDirection) {
        this.movingDirection = movingDirection;
    }

    public abstract Integer getPoints();
}
