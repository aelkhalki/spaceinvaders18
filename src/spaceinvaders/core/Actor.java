package spaceinvaders.core;

public abstract class Actor {
    private static final Integer moveSpeed = 2;
    private Integer positionX;
    private Integer positionY;
    
    public Actor(Integer positionX, Integer positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public Integer getPositionX() {
        return positionX;
    }

    public Integer getPositionY() {
        return positionY;
    }

    public void moveLeft() {
        positionX -= moveSpeed;
    }
    
    public void moveRight() {
        positionX += moveSpeed;
    }
}
