package nl.delftelectronics.spaceinvaders.core.entities;

import nl.delftelectronics.spaceinvaders.core.Collidable;

/**
 * A Barricade is a unit of defense. Bullets cannot pass through a Barricade, but the Barricade
 * is removed when it is hit by a bullet.
 */
public class Barricade extends SpriteEntity implements Collidable {
    private static final String FILENAME = "/barricade.png";

    /**
     * Create a Barricade at the specified position.
     *
     * @param positionX  the x-coordinate of the position of the barricade.
     * @param positionY  the y-coordinate of the position of the barricade.
     * @param width      the width of the barricade.
     * @param height     the height of the barricade.
     * @param spriteName the filename of the sprite.
     */
    public Barricade(double positionX, double positionY, double width, double height,
                     String spriteName) {
        super(positionX, positionY, width, height, spriteName);
    }

    /**
     * Create a Barricade at the specified position.
     *
     * @param positionX the x-coordinate of the position of the barricade.
     * @param positionY the y-coordinate of the position of the barricade.
     * @param width     the width of the barricade.
     * @param height    the height of the barricade.
     */
    public Barricade(double positionX, double positionY, double width, double height) {
        super(positionX, positionY, width, height, FILENAME);
    }

    /**
     * Destroy the barricade.
     */
    public void hit() {
        destroy();
    }
}
