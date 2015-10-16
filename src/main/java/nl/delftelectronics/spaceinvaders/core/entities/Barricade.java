package nl.delftelectronics.spaceinvaders.core.entities;

import java.awt.geom.Rectangle2D;

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
     * @param position the position of the sprite
     * @param spriteName the filename of the sprite.
     */
    public Barricade(Rectangle2D position,
                     String spriteName) {
        super(position, spriteName);
    }

    /**
     * Create a Barricade at the specified position.
     *
     * @param position  the position of the sprite
     */
    public Barricade(Rectangle2D position) {
        super(position, FILENAME);
    }

    /**
     * Destroy the barricade.
     */
    public void hit() {
        destroy();
    }
}
