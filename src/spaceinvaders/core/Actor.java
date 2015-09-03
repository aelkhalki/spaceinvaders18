package spaceinvaders.core;

public abstract class Actor extends Entity {
    protected static final Integer MOVING_SPEED = 10;

    public Actor(Integer positionX, Integer positionY) {
        super(positionX, positionY);
    }

    public void moveLeft() {
        setPositionX(getPositionX() - MOVING_SPEED);
    }

    public void moveRight() {
        setPositionX(getPositionX() + MOVING_SPEED);
    }
}
