package nl.delftelectronics.spaceinvaders.core.entities;

import java.util.EventListener;

/**
 * Listens for when a barricade is destroyed.
 */
public interface BarricadeDestroyedListener extends EventListener {
    /**
     * Called when a barricade is destroyed.
     *
     * @param barricade the destroyed barricade.
     */
    void barricadeDestroyed(Barricade barricade);
}
