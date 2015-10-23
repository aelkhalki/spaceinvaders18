package nl.delftelectronics.spaceinvaders.core.scenes;

import javafx.scene.Scene;
import nl.delftelectronics.spaceinvaders.core.GameInformation;
import nl.delftelectronics.spaceinvaders.core.entities.ColoredShip;
import nl.delftelectronics.spaceinvaders.core.entities.Ship;

import java.awt.geom.Rectangle2D;

/**
 * A PlayScene where multiple players can play.
 */
public class MultiplayerPlayScene extends PlayScene {
    private static final int NUMBER_OF_PLAYERS = 2;
    private static final double DISTANCE_BETWEEN_PLAYERS = 200;

    /**
     * Construct a multiplayer PlayScene.
     *
     * @param scene the JavaFX scene to bind to.
     */
    public MultiplayerPlayScene(Scene scene) {
        super(scene, NUMBER_OF_PLAYERS);
        gameInformation.setMultiplayer(true);
    }

    /**
     * Construct a multiplayer PlayScene with an existing GameInformation
     *
     * @param scene           the JavaFX scene to bind to.
     * @param gameInformation the existing GameInformation
     */
    public MultiplayerPlayScene(Scene scene, GameInformation gameInformation) {
        super(scene, gameInformation);
    }

    @Override
    protected Ship makeShip(Rectangle2D position, int westBoundary, int eastBoundary,
                            GameInformation gameInformation) {
        return new ColoredShip(position, westBoundary, eastBoundary, gameInformation);
    }
}
