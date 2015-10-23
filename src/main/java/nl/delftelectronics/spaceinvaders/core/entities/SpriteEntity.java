/**
 *
 */
package nl.delftelectronics.spaceinvaders.core.entities;

import java.awt.geom.Rectangle2D;

import org.joda.time.Interval;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author Max
 */
public class SpriteEntity extends DrawableEntity {
	protected Image image;
	protected String spriteName;
	
	/**
     * @param position   the position of the sprite
     * @param spriteName the filename of the sprite.
     */
	public SpriteEntity(Rectangle2D position, String spriteName) {
		super(position);
		
		this.spriteName = spriteName;
	}
	
	@Override
	public void initializeGraphics(GraphicsContext gc) {
		super.initializeGraphics(gc);
		image = new Image(spriteName);
	}

	/**
	 * Load the image from the file (specified by the filename) to this object.
	 */
	public void loadImage() {
		image = new Image(spriteName);
	}

	@Override
	public void draw(Interval timeStep, GraphicsContext gc) {
		gc.drawImage(image, getPositionX(), getPositionY(), getWidth(), getHeight());
	}

	/**
	 * Return the filename from where the image (sprite) is loaded from.
	 *
	 * @return the filename from where the image (sprite) is loaded from.
	 */
	public String getSpriteFilename() {
		return spriteName;
	}
}
