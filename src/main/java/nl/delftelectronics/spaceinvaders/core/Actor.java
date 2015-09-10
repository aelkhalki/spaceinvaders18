package nl.delftelectronics.spaceinvaders.core;

public abstract class Actor extends SpriteEntity {
    protected static final Integer MOVING_SPEED = 5;

    public Actor(Integer positionX, Integer positionY, Integer width, Integer height, String spriteName) {
        super(positionX, positionY, width, height, spriteName);
    }

    public void moveLeft() {
        setPositionX(getPositionX() - MOVING_SPEED);
    }

    public void moveRight() {
        setPositionX(getPositionX() + MOVING_SPEED);
    }
}
