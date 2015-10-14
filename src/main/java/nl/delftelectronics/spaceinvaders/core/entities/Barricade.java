package nl.delftelectronics.spaceinvaders.core.entities;

import nl.delftelectronics.spaceinvaders.core.Collidable;

import javax.swing.event.EventListenerList;

/**
 * A Barricade is a unit of defense. Bullets cannot pass through a Barricade, but the Barricade
 * is removed when it is hit by a bullet.
 */
public class Barricade extends SpriteEntity implements Collidable {
    private static final String FILENAME = "/barricade.png";

    private EventListenerList destroyedEvent;

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
        this.destroyedEvent = new EventListenerList();
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
        this.destroyedEvent = new EventListenerList();
    }

    /**
     * Adds a listener to the destroy event
     * @param listener the listener to add
     */
    public void addDestroyedListener(BarricadeDestroyedListener listener) {
        synchronized (destroyedEvent) {
            this.destroyedEvent.add(BarricadeDestroyedListener.class, listener);
            if (isDestroyed()) {
                listener.barricadeDestroyed(this);
            }
        }
    }

    /**
     * Destroy the barricade.
     */
    public void hit() {
        destroy();

        BarricadeDestroyedListener[] listeners =
                destroyedEvent.getListeners(BarricadeDestroyedListener.class);
        for (BarricadeDestroyedListener e : listeners) {
            e.barricadeDestroyed(this);
        }
    }
}
