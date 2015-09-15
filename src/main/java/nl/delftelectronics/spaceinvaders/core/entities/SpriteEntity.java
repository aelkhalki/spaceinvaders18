/**
 *
 */
package nl.delftelectronics.spaceinvaders.core.entities;

import org.joda.time.Interval;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author Max
 */
public abstract class SpriteEntity extends DrawableEntity {
	protected Image image;
	protected String spriteName;
	
	/**
     * @param positionX  the x-coordinate of the position of the sprite entity.
     * @param positionY  the y-coordinate of the position of the sprite entity.
     * @param width      the width of the sprite entity.
     * @param height     the height of the sprite entity.
     * @param spriteName the filename of the sprite.
     */
	public SpriteEntity(int positionX, int positionY, int width, int height, String spriteName) {
		super(positionX, positionY, width, height);
		
		this.spriteName = spriteName;
	}
	
	@Override
	public void initializeGraphics(GraphicsContext gc) {
		super.initializeGraphics(gc);
		image = new Image(spriteName);
	}

	@Override
	public void draw(Interval timeStep, GraphicsContext gc) {
		gc.drawImage(image, getPositionX(), getPositionY(), getWidth(), getHeight());
	}
}
