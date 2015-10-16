package nl.delftelectronics.spaceinvaders.core.entities;

import java.awt.geom.Rectangle2D;

import javafx.scene.canvas.GraphicsContext;
import org.joda.time.Interval;

/**
 * Represents an entity that can draw itself
 */
public class DrawableEntity extends Entity {
	private Rectangle2D rectangle;

	/**
	 * Create a DrawableEntity, using its position and size.
	 *
	 * @param position
	 * 			  position of the sprite
	 */
	public DrawableEntity(Rectangle2D position) {
		this.rectangle = position;
	}

	/**
	 * Called after intialize. Used to load graphical resources if neccecary
	 * 
	 * @param gc
	 *            The graphical context to load the resources in
	 */
	public void initializeGraphics(GraphicsContext gc) {

	}

	/**
	 * Called when the entity should draw itself
	 *
	 * @param timeStep
	 *            The interval since the last draw
	 * @param gc
	 *            The context to draw in
	 */
	public void draw(Interval timeStep, GraphicsContext gc) {

	}

	/**
	 * Return the x-coordinate of the position.
	 *
	 * @return the x-coordinate of the position.
	 */
	public double getPositionX() {
		return rectangle.getX();
	}

	/**
	 * Set the new x-coordinate.
	 *
	 * @param newPosition
	 *            the new x-coordinate.
	 */
	public void setPositionX(double newPosition) {
		rectangle = new Rectangle2D.Double(
				newPosition,
				rectangle.getY(),
				rectangle.getWidth(),
				rectangle.getHeight());
	}

	/**
	 * Return the y-coordinate of the position.
	 *
	 * @return the y-coordinate of the position.
	 */
	public double getPositionY() {
		return rectangle.getY();
	}

	/**
	 * Set the new y-coordinate.
	 *
	 * @param newPosition
	 *            the new y-coordinate.
	 */
	public void setPositionY(double newPosition) {
		rectangle = new Rectangle2D.Double(
				rectangle.getX(),
				newPosition,
				rectangle.getWidth(),
				rectangle.getHeight());
	}

	/**
	 * Return the width of the entity.
	 *
	 * @return the width of the entity.
	 */
	public double getWidth() {
		return rectangle.getWidth();
	}

	/**
	 * Return the height of the entity.
	 *
	 * @return the height of the entity.
	 */
	public double getHeight() {
		return rectangle.getHeight();
	}

	/**
	 * Return the bounding box of the entity.
	 *
	 * @return the bounding box of the entity.
	 */
	public Rectangle2D getBoundingBox() {
		return rectangle;
	}

	/**
	 * Return true of the other entity intersects this entity, otherwise false.
	 *
	 * @param other
	 *            entity to perform the intersection test against.
	 * @return true of the other entity intersects this entity, otherwise false.
	 */
	public boolean intersects(DrawableEntity other) {
		return rectangle.intersects(other.getBoundingBox());
	}
}
