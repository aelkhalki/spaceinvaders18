package nl.delftelectronics.spaceinvaders.core.entities;

import java.awt.Rectangle;

/**
 * A Projectile is an object that can be thrown or fired.
 */
public interface Projectile {
    /**
     * The area that is affected by the impact.
     *
     * @return the area that is affected by the impact.
     */
    Rectangle impactArea();

    /**
     * Update the position of the projectile.
     */
    void updatePosition();
}
