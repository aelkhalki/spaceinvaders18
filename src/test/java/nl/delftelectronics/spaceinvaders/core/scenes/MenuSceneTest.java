//CHECKSTYLE.OFF: MagicNumber
package nl.delftelectronics.spaceinvaders.core.scenes;

import junit.framework.TestCase;
import nl.delftelectronics.spaceinvaders.core.Engine;

import static org.mockito.Mockito.mock;

import nl.delftelectronics.spaceinvaders.core.entities.LabelEntity;

import javafx.scene.Scene;

import java.awt.geom.Rectangle2D;

public class MenuSceneTest extends TestCase {

    public void testLabelsInScene() {
        Scene javaFxScene = mock(Scene.class);
        MenuScene ms = new MenuScene(javaFxScene);
        LabelEntity startGame = new LabelEntity(new Rectangle2D.Double(0, 0, 0, 0), "START GAME");
        ms.update();
        assertTrue(ms.getEntities().contains(startGame));
    }

    public void testNullLabelClicked() {
        Scene javaFxScene = mock(Scene.class);
        MenuScene ms = new MenuScene(javaFxScene);
        Engine.getInstance().setScene(ms);

        ms.labelClicked(null);
        assertTrue(Engine.getInstance().getScene() instanceof MenuScene);
    }

    public void testStartServerChangesScene() {
        Scene javaFxScene = mock(Scene.class);
        MenuScene ms = new MenuScene(javaFxScene);
        ms.startServer();

        assertTrue(Engine.getInstance().getScene() instanceof OnlineMultiplayerPlayScene);
    }

    public void testConnectToServerChangesScene() {
        Scene javaFxScene = mock(Scene.class);
        MenuScene ms = new MenuScene(javaFxScene);
        ms.connectToServer();

        assertTrue(Engine.getInstance().getScene() instanceof OnlineMultiplayerPlayScene);
    }
}
