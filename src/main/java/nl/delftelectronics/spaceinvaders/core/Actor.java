package nl.delftelectronics.spaceinvaders.core;

public abstract class Actor extends Entity {
    protected static final Integer MOVING_SPEED = 5;

    public Actor(Integer positionX, Integer positionY, Integer width, Integer height) {
        super(positionX, positionY, width, height);
    }

    public void moveLeft() {
        setPositionX(getPositionX() - MOVING_SPEED);
    }

    public void moveRight() {
        setPositionX(getPositionX() + MOVING_SPEED);
    }
}
