/**
 * 
 */
package nl.delftelectronics.spaceinvaders.core;

/**
 * @author Max
 * Represents a non-entity collidable.
 */
public class DummyCollidable implements Collidable {
	private double positionX;
	private double positionY;
	private double width;
	private double height;
	
	/**
	 * Creates a new DummyCollidable
	 * @param positionX The x position of the collidable
	 * @param positionY The y position of the collidable
	 * @param width The width of the collidable
	 * @param height The height of the collidable
	 */
	public DummyCollidable(double positionX, double positionY,
			double width, double height) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Returns the x position
	 * @return the x position
	 */
	public double getPositionX() {
		return positionX;
	}

	/**
	 * Returns the y position
	 * @return the y position
	 */
	public double getPositionY() {
		return positionY;
	}

	/**
	 * Returns the width
	 * @return the width
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * Returns the height
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

}
