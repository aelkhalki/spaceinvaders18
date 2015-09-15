package nl.delftelectronics.spaceinvaders.core;

import java.awt.Rectangle;

import javafx.scene.canvas.GraphicsContext;
import org.joda.time.Interval;

/**
 * Represents an entity that can draw itself
 */
public class DrawableEntity extends Entity {
    private int positionX;
    private int positionY;
    private int width;
    private int height;
    private Rectangle rectangle;

    /**
     * Create a DrawableEntity, using its position and size.
     *
     * @param positionX the x-coordinate of the entity.
     * @param positionY the y-coordinate of the entity.
     * @param width     the width of the entity.
     * @param height    the height of the entity.
     */
    public DrawableEntity(int positionX, int positionY, int width, int height) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.width = width;
        this.height = height;
        this.rectangle = new Rectangle(positionX, positionY, width, height);
    }
	
	/**
	 * Called after intialize. Used to load graphical resources if neccecary
	 * @param gc The graphical context to load the resources in
	 */
	public void initializeGraphics(GraphicsContext gc) {
		
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
    public int getPositionX() {
        return positionX;
    }

    /**
     * Set the new x-coordinate.
     *
     * @param newPosition the new x-coordinate.
     */
    public void setPositionX(int newPosition) {
        positionX = newPosition;
        rectangle.setLocation(positionX, positionY);
    }

    /**
     * Return the y-coordinate of the position.
     *
     * @return the y-coordinate of the position.
     */
    public int getPositionY() {
        return positionY;
    }

    /**
     * Set the new y-coordinate.
     *
     * @param newPosition the new y-coordinate.
     */
    public void setPositionY(int newPosition) {
        positionY = newPosition;
        rectangle.setLocation(positionX, positionY);
    }

    /**
     * Return the width of the entity.
     *
     * @return the width of the entity.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Return the height of the entity.
     *
     * @return the height of the entity.
     */
    public int getHeight() {
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
