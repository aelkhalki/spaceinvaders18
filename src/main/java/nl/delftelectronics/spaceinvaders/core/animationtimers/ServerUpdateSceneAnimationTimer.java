package nl.delftelectronics.spaceinvaders.core.animationtimers;

import javafx.animation.AnimationTimer;
import nl.delftelectronics.spaceinvaders.core.scenes.OnlineMultiplayerPlayScene;
import nl.delftelectronics.spaceinvaders.core.threads.ServerThread;

/**
 * Update the scene and set the entities to the server thread cyclically.
 */
public class ServerUpdateSceneAnimationTimer extends AnimationTimer {
    private static final double SECOND = 1000000000.0;

    private long previousNanoTime = 0;
    private OnlineMultiplayerPlayScene omPlayScene;
    private ServerThread serverThread;
    private int fps;

    /**
     * Create an AnimationTimer for updating the scene as the server.
     *
     * @param omPlayScene  scene that contains the entities
     * @param serverThread thread used for sending the entities to the client
     * @param fps          number of times a cycle should be run per second
     */
    public ServerUpdateSceneAnimationTimer(OnlineMultiplayerPlayScene omPlayScene,
                                           ServerThread serverThread,
                                           int fps) {
        this.omPlayScene = omPlayScene;
        this.serverThread = serverThread;
        this.fps = fps;
    }

    @Override
    public void handle(long currentNanoTime) {
        double elapsedTime = (currentNanoTime - previousNanoTime) / SECOND;
        if (elapsedTime < 1 / (double) fps) {
            previousNanoTime = currentNanoTime;
            return;
        }
        omPlayScene.serverUpdate();
        serverThread.setEntities(omPlayScene.getEntities());
    }
}
