package nl.delftelectronics.spaceinvaders.core.scenes;

import javafx.scene.Scene;

/**
 * This scene is used for playing a multiplayer game by multiple instances of the game.
 */
public class OnlineMultiplayerPlayScene extends MultiplayerPlayScene {

    /**
     * Create an online multiplayer play scene. This scene does not handle the connection to the
     * server and client (use {@see #ServerThread} or {@see #ServerThread} respectively).
     * @param scene JavaFX scene to bind to
     */
    public OnlineMultiplayerPlayScene(Scene scene) {
        super(scene);
    }

    /**
     * This update method does nothing. This method is called by the clients and the server, but we
     * don't want all of them to update the scene (only the server should do this). This is why
     * there is a separate method {@link #serverUpdate()} that should only be called by the server
     * to actually update the scene.
     */
    @Override
    public void update() {
    }

    /**
     * The actual update method. This method should only be called by the server, because the
     * clients should not update their scene themselves, as they should only copy entities from
     * the scene of the server.
     */
    public void serverUpdate() {
        super.update();
    }
}
