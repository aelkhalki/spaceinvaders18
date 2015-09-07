package nl.delftelectronics.spaceinvaders.core;

public abstract class Entity {
    private Integer positionX;
    private Integer positionY;

    public Entity(Integer positionX, Integer positionY) {
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

}
