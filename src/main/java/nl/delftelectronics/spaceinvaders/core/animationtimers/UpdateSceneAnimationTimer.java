package nl.delftelectronics.spaceinvaders.core.animationtimers;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import nl.delftelectronics.spaceinvaders.core.Engine;

/**
 * Update the scene (via the engine), and draw the scene to the window.
 */
public class UpdateSceneAnimationTimer extends AnimationTimer {
    private static final double SECOND = 1000000000.0;

    private Engine engine;
    private GraphicsContext gc;
    private long previousNanoTime = 0;
    private int fps;
    private int windowWidth;
    private int windowHeight;

    /**
     * Create an AnimationTimer used for updating and drawing the scene cyclically.
     *
     * @param engine       engine used to update the scene
     * @param gc           GraphicsContext used for drawing the scene
     * @param fps          number of times the scene should be updated per second
     * @param windowWidth  width of the window
     * @param windowHeight height of the window
     */
    public UpdateSceneAnimationTimer(Engine engine, GraphicsContext gc, int fps, int windowWidth,
                                     int windowHeight) {
        this.engine = engine;
        this.gc = gc;
        this.fps = fps;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    }

    @Override
    public void handle(long currentNanoTime) {
        double elapsedTime = (currentNanoTime - previousNanoTime) / SECOND;
        if (elapsedTime < 1 / (double) fps) {
            previousNanoTime = currentNanoTime;
            return;
        }
        engine.update();

        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, windowWidth, windowHeight);
        gc.setFill(Color.RED);

        engine.draw(gc);
    }
}
