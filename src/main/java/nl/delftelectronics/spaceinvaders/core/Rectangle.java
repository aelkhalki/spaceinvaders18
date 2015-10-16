package nl.delftelectronics.spaceinvaders.core;

import java.io.Serializable;

/**
 * A Rectangle is a rectangular shape.
 */
public class Rectangle implements Serializable {
    private double x;
    private double y;
    private double width;
    private double height;

    /**
     * Constuct a rectangle.
     *
     * @param x      x-coordinate of the top left point of the rectangle
     * @param y      y-coordinate of the top left points of the rectangle
     * @param width  width of the rectangle
     * @param height height of the rectangle.
     */
    public Rectangle(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns the height of the rectangle.
     * @return the height of the rectangle
     */
    public double getHeight() {
        return height;
    }

    /**
     * Set the height of the rectangle.
     * @param height height of the rectangle
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Returns the x-coordinate of the top left point of the rectangle.
     * @return the x-coordinate of the top left point of the rectangle
     */
    public double getX() {
        return x;
    }

    /**
     * Set the x-coordinate of the top left point of the rectangle.
     * @param x the x-coordinate of the top left point of the rectangle
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Returns the y-coordinate of the top left point of the rectangle.
     * @return the y-coordinate of the top left point of the rectangle
     */
    public double getY() {
        return y;
    }

    /**
     * Set the y-coordinate of the top left point of the rectangle.
     * @param y the y-coordinate of the top left point of the rectangle
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Returns the width of the rectangle.
     * @return the width of the rectangle
     */
    public double getWidth() {
        return width;
    }

    /**
     * Set the width of the rectangle.
     * @param width the width of the rectangle
     */
    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Rectangle rectangle = (Rectangle) o;

        if (Double.compare(rectangle.x, x) != 0) {
            return false;
        }
        if (Double.compare(rectangle.y, y) != 0) {
            return false;
        }
        if (Double.compare(rectangle.width, width) != 0) {
            return false;
        }
        return Double.compare(rectangle.height, height) == 0;

    }

    @Override
    public int hashCode() {
        //CHECKSTYLE.OFF: MagicNumber
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(width);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(height);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
        //CHECKSTYLE.ON: MagicNumber
    }
}
