package nl.delftelectronics.spaceinvaders.core.entities;

import nl.delftelectronics.spaceinvaders.core.GameInformation;

import java.awt.geom.Rectangle2D;

/**
 * A ColoredShip is ship with a unique color.
 */
public class ColoredShip extends Ship {
    private static final String[] SHIP_IMAGE_FILENAMES = new String[] {
            "/shipRed.png",
            "/shipYellow.png",
            "/shipGreen.png",
    };
    private static int filenameCounter = 0;

    /**
     * Create a colored Ship with the initial position and size.
     *
     * @param position        position of the sprite
     * @param westBoundary    westernmost boundary of the playing field.
     * @param eastBoundary    easternmost boundary of the playing field.
     * @param gameInformation information about the current game.
     */
    public ColoredShip(Rectangle2D position, int westBoundary, int eastBoundary,
                       GameInformation gameInformation) {
        super(position, SHIP_IMAGE_FILENAMES[filenameCounter++ % SHIP_IMAGE_FILENAMES.length],
                westBoundary, eastBoundary, gameInformation);
    }
}
