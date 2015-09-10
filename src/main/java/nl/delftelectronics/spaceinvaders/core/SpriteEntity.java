/**
 * 
 */
package nl.delftelectronics.spaceinvaders.core;

import org.joda.time.Interval;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author Max
 *
 */
public abstract class SpriteEntity extends DrawableEntity {
	protected Image image;
	
	/**
	 * @param positionX
	 * @param positionY
	 * @param width
	 * @param height
	 * @param spriteName
	 */
	public SpriteEntity(Integer positionX, Integer positionY, Integer width, Integer height, String spriteName) {
		super(positionX, positionY, width, height);
		
		image = new Image(spriteName);
	}

	@Override
	public void Draw(Interval timeStep, GraphicsContext gc) {
		gc.drawImage(image, getPositionX(), getPositionY(), getWidth(), getHeight());
	}
}
