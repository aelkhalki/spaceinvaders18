package spaceinvaders.core;

public abstract class Actor {
    protected static final Integer MOVING_SPEED = 2;
    private Integer positionX;
    private Integer positionY;

    public Actor(Integer positionX, Integer positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public Integer getPositionX() {
        return positionX;
    }

    public void setPositionX(Integer newPosition) {
        positionX = newPosition;
    }

    public Integer getPositionY() {
        return positionY;
    }

    public void setPositionY(Integer newPosition) {
        positionY = newPosition;
    }

    public void moveLeft() {
        positionX -= MOVING_SPEED;
    }

    public void moveRight() {
        positionX += MOVING_SPEED;
    }
}
