/**
 * 
 */
package nl.delftelectronics.spaceinvaders.core;

/**
 * @author Max
 *
 */
public class DummyCollidable implements Collidable {
	private int positionX;
	private int positionY;
	private int width;
	private int height;
	
	public DummyCollidable(int positionX, int positionY,
			int width, int height) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.width = width;
		this.height = height;
	}
	
	public int getPositionX() {
		return positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
