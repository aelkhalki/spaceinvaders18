package nl.delftelectronics.spaceinvaders.core.scenes;

import javafx.scene.Scene;
import nl.delftelectronics.spaceinvaders.core.GameInformation;
import nl.delftelectronics.spaceinvaders.core.PlayingKeysFactory;
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
        super(scene);
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
    protected void addShip() {
        PlayingKeysFactory keyFactory = new PlayingKeysFactory();
        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            int shipPositionX = (int) (fieldWidth * SHIP_MARGIN_FROM_LEFT
                    + DISTANCE_BETWEEN_PLAYERS * i);
            int shipPositionY = (int) (fieldHeight * (1 - SHIP_MARGIN_FROM_BOTTOM));
            Ship ship = new Ship(new Rectangle2D.Double(shipPositionX, shipPositionY,
                    ENTITY_DIMENSION, ENTITY_DIMENSION), 0, fieldWidth, gameInformation);
            ship.setPlayingKeys(keyFactory.next());
            addEntity(ship);
            System.out.println(ship.getPositionX());
        }
    }
}
