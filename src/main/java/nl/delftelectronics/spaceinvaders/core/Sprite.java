package nl.delftelectronics.spaceinvaders.core;

import java.io.Serializable;

/**
 * Represents a sprite, using only the filename of the image, the position and dimension.
 */
public class Sprite implements Serializable {
    private String filename;
    private double positionX;
    private double positionY;
    private double width;
    private double height;

    /**
     * Create s sprite, using only the filename of the image, the position and dimension.
     *
     * @param filename  filename of the image
     * @param positionX x-position of the sprite
     * @param positionY y-position of the sprite
     * @param width     width of the sprite
     * @param height    height of the sprite
     */
    public Sprite(String filename, double positionX, double positionY, double width,
                  double height) {
        this.filename = filename;
        this.positionX = positionX;
        this.positionY = positionY;
        this.width = width;
        this.height = height;
    }

    /**
     * Return the filename of the image.
     *
     * @return the filename of the image
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Return the x-position of the sprite.
     *
     * @return the x-position of the sprite
     */
    public double getPositionX() {
        return positionX;
    }

    /**
     * Return the y-position of the sprite.
     *
     * @return the y-position of the sprite
     */
    public double getPositionY() {
        return positionY;
    }

    /**
     * Return the width of the sprite.
     *
     * @return the width of the sprite
     */
    public double getWidth() {
        return width;
    }

    /**
     * Return the height of the sprite.
     *
     * @return the height of the sprite
     */
    public double getHeight() {
        return height;
    }
}
