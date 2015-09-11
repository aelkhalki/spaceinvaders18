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

	public DrawableEntity(Integer positionX, Integer positionY, Integer width, Integer height) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.width = width;
		this.height = height;
		this.rectangle = new Rectangle(positionX, positionY, width, height);
	}
	
	/**
	 * Called when the entity should draw itself
	 * @param timeStep The interval since the last draw
	 * @param gc The context to draw in
	 */
	public void draw(Interval timeStep, GraphicsContext gc) {
		
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

	public Rectangle getBoundingBox() {
        return rectangle;
    }

	public boolean intersects(DrawableEntity other) {
		return rectangle.intersects(other.getBoundingBox());
	}
}
