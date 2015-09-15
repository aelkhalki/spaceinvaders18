/**
 * 
 */
package nl.delftelectronics.spaceinvaders.core;

/**
 * @author Max
 * Represents a rectangle that can collide with other Collidables
 */
public interface Collidable {
	/**
	 * The x position of the rectangle
	 * @return the y position of the rectangle
	 */
	int getPositionX();
	/**
	 * The y position of the rectangle
	 * @return the y position of the rectangle
	 */
	int getPositionY();
	/**
	 * The width of the rectangle
	 * @return the width of the rectangle
	 */
	int getWidth();
	/**
	 * The height of the rectangle
	 * @return the height of the rectangle
	 */
	int getHeight();
}
