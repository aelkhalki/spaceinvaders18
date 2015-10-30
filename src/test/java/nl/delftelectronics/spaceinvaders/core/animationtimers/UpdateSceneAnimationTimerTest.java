//CHECKSTYLE.OFF: MagicNumber
package nl.delftelectronics.spaceinvaders.core.animationtimers;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import junit.framework.TestCase;
import nl.delftelectronics.spaceinvaders.core.Engine;
import nl.delftelectronics.spaceinvaders.core.scenes.GameScene;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class UpdateSceneAnimationTimerTest extends TestCase {
    public void testAnimationTimerCallingUpdate() {
        GameScene gameScene = mock(GameScene.class);
        Engine engine = Engine.getInstance();
        engine.setScene(gameScene);
        GraphicsContext gc = (new Canvas(100, 100)).getGraphicsContext2D();
        UpdateSceneAnimationTimer aTimer = new UpdateSceneAnimationTimer(engine, gc, 24, 100, 100);
        aTimer.handle(1000000000000L);
        verify(gameScene).update();
    }
}
