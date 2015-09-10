package nl.delftelectronics.spaceinvaders.core;

import java.awt.Rectangle;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javax.swing.event.EventListenerList;

import org.joda.time.Interval;

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
	
	public void Draw(Interval timeStep, GraphicsContext gc) {
		
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

	public boolean intersects(DrawableEntity other) {
		return rectangle.intersects(other.rectangle);
	}
}
