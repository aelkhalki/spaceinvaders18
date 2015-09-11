package nl.delftelectronics.spaceinvaders.core;

import java.awt.Rectangle;

import javafx.scene.canvas.GraphicsContext;
import org.joda.time.Interval;

/**
 * Represents an entity that can draw itself
 */
public class DrawableEntity extends Entity {
    private Integer positionX;
    private Integer positionY;
    private Integer width;
    private Integer height;
    private Rectangle rectangle;

    /**
     * Create a DrawableEntity, using its position and size.
     *
     * @param positionX the x-coordinate of the entity.
     * @param positionY the y-coordinate of the entity.
     * @param width     the width of the entity.
     * @param height    the height of the entity.
     */
    public DrawableEntity(Integer positionX, Integer positionY, Integer width, Integer height) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.width = width;
        this.height = height;
        this.rectangle = new Rectangle(positionX, positionY, width, height);
    }

    /**
     * Called when the entity should draw itself
     *
     * @param timeStep The interval since the last draw
     * @param gc       The context to draw in
     */
    public void draw(Interval timeStep, GraphicsContext gc) {

    }

    /**
     * Return the x-coordinate of the position.
     *
     * @return the x-coordinate of the position.
     */
    public Integer getPositionX() {
        return positionX;
    }

    /**
     * Set the new x-coordinate.
     *
     * @param newPosition the new x-coordinate.
     */
    public void setPositionX(Integer newPosition) {
        positionX = newPosition;
        rectangle.setLocation(positionX, positionY);
    }

    /**
     * Return the y-coordinate of the position.
     *
     * @return the y-coordinate of the position.
     */
    public Integer getPositionY() {
        return positionY;
    }

    /**
     * Set the new y-coordinate.
     *
     * @param newPosition the new y-coordinate.
     */
    public void setPositionY(Integer newPosition) {
        positionY = newPosition;
        rectangle.setLocation(positionX, positionY);
    }

    /**
     * Return the width of the entity.
     *
     * @return the width of the entity.
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * Return the height of the entity.
     *
     * @return the height of the entity.
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * Return the bounding box of the entity.
     *
     * @return the bounding box of the entity.
     */
    public Rectangle getBoundingBox() {
        return rectangle;
    }

    /**
     * Return true of the other entity intersects this entity, otherwise false.
     *
     * @param other entity to perform the intersection test against.
     * @return true of the other entity intersects this entity, otherwise false.
     */
    public boolean intersects(DrawableEntity other) {
        return rectangle.intersects(other.getBoundingBox());
    }
}
