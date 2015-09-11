package nl.delftelectronics.spaceinvaders.core;

import java.awt.Rectangle;

/**
 * A Bomb is a projectile that can be shot by an object. If a bullet hits an Enemy, it also hits
 * adjacent enemies. A Bomb has a position and a direction.
 */
public class Bomb extends Bullet {
    private static final Integer IMPACT_RADIUS = 150;
    private static final String FILENAME = "/ufo.png";
    public static final Integer WIDTH = 100;
    public static final Integer HEIGHT = 70;

    /**
     * Create a Bomb with an initial position, size and direction.
     *
     * @param positionX initial x-position.
     * @param positionY initial y-position.
     * @param width     width of the Bomb.
     * @param height    height of the Bomb.
     * @param direction direction of the Bomb.
     */
    public Bomb(Integer positionX, Integer positionY, Integer width, Integer height,
                Direction direction) {
        super(positionX, positionY, width, height, direction);
    }

    /**
     * Return the bounding box of the impact area.
     *
     * @return the bounding box of the impact area.
     */
    public Rectangle impactArea() {
        return new Rectangle(getPositionX() - IMPACT_RADIUS / 2, getPositionY() - IMPACT_RADIUS / 2,
                IMPACT_RADIUS * 2,
                IMPACT_RADIUS * 2);
    }

    /**
     * Return the filename of the bomb sprite.
     *
     * @return the filename of the bomb sprite.
     */
    public String getSpriteFilename() {
        return FILENAME;
    }
}
