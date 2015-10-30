//CHECKSTYLE.OFF: MagicNumber
package nl.delftelectronics.spaceinvaders.core.entities;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import junit.framework.TestCase;
import nl.delftelectronics.spaceinvaders.core.Engine;
import nl.delftelectronics.spaceinvaders.core.scenes.GameScene;
import org.joda.time.Interval;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static org.mockito.Mockito.mock;

public class LabelEntityTest extends TestCase {

    public void testDrawAndUpdateAndClick() {
        Canvas canvas = new Canvas(100, 100);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Scene javaFxScene = mock(Scene.class);

        GameScene gameScene = new GameScene(javaFxScene);
        LabelEntity labelEntity = new LabelEntity(new Rectangle2D.Double(10, 10, 10, 10), "HELLO");
        gameScene.addEntity(labelEntity);
        Engine.getInstance().setScene(gameScene);
        labelEntity.draw(new Interval(0, 1), gc);

        DummyLabelClickedListener labelClickedListener = new DummyLabelClickedListener();
        labelEntity.addClickedListener(labelClickedListener);
        Engine.getInstance().addClick(new Point(15, 15));
        labelEntity.update(new Interval(0, 1));

        assertTrue(labelClickedListener.clicked);
    }

    private class DummyLabelClickedListener implements LabelClickedListener {
        public boolean clicked = false;
        /**
         * Called when a label is clicked.
         *
         * @param label the label that was clicked.
         */
        public void labelClicked(LabelEntity label) {
            clicked = true;
        }
    }
}
