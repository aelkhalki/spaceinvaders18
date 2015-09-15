/**
 * 
 */
package nl.delftelectronics.spaceinvaders.core;

/**
 * @author Max
 * Represents a non-entity collidable.
 */
public class DummyCollidable implements Collidable {
	private int positionX;
	private int positionY;
	private int width;
	private int height;
	
	/**
	 * Creates a new DummyCollidable
	 * @param positionX The x position of the collidable
	 * @param positionY The y position of the collidable
	 * @param width The width of the collidable
	 * @param height The height of the collidable
	 */
	public DummyCollidable(int positionX, int positionY,
			int width, int height) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Returns the x position
	 */
	public int getPositionX() {
		return positionX;
	}

	/**
	 * Returns the y position
	 */
	public int getPositionY() {
		return positionY;
	}

	/**
	 * Returns the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Returns the height
	 */
	public int getHeight() {
		return height;
	}

}
