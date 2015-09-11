package nl.delftelectronics.spaceinvaders.core;

import java.awt.Rectangle;

public abstract class Entity implements Displayable {
    private Integer positionX;
    private Integer positionY;
    private Integer width;
    private Integer height;
    private Rectangle rectangle;

    public Entity(Integer positionX, Integer positionY, Integer width, Integer height) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.width = width;
        this.height = height;
        this.rectangle = new Rectangle(positionX, positionY, width, height);
    }

    public Integer getPositionX() {
        return positionX;
    }

    public void setPositionX(Integer newPosition) {
        positionX = newPosition;
        rectangle.setLocation(positionX, positionY);
    }

    public Integer getPositionY() {
        return positionY;
    }

    public void setPositionY(Integer newPosition) {
        positionY = newPosition;
        rectangle.setLocation(positionX, positionY);
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public boolean intersects(Entity other) {
        return rectangle.intersects(other.rectangle);
    }

    public Rectangle getBoundingBox() {
        return rectangle;
    }
}
