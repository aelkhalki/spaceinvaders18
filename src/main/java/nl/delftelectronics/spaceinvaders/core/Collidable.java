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
	double getPositionX();
	/**
	 * The y position of the rectangle
	 * @return the y position of the rectangle
	 */
	double getPositionY();
	/**
	 * The width of the rectangle
	 * @return the width of the rectangle
	 */
	double getWidth();
	/**
	 * The height of the rectangle
	 * @return the height of the rectangle
	 */
	double getHeight();
}
