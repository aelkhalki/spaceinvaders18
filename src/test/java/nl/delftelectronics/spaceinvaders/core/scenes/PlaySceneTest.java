//CHECKSTYLE.OFF: MagicNumber
package nl.delftelectronics.spaceinvaders.core.scenes;

import junit.framework.TestCase;
import static org.mockito.Mockito.mock;

import javafx.scene.Scene;
import nl.delftelectronics.spaceinvaders.core.Engine;

/**
 * Tests the PlayScene class
 */
public class PlaySceneTest extends TestCase {

    /**
     * Test method for {@link PlayScene#getCurrentLevel()}. Tests if the initial level is set to
     * one.
     */
    public void testInitialLevel() {
        PlayScene playScene = new PlayScene(mock(Scene.class));
        assertEquals(1, playScene.getCurrentLevel());
    }

    /**
     * Test method for {@link PlayScene#getCurrentLevel()}. Tests if the level increases.
     */
    public void testLevelIncrease() {
        PlayScene playScene = new PlayScene(mock(Scene.class));
        Engine.getInstance().setScene(playScene);
        playScene.win();
        StoreScene ss = (StoreScene) Engine.getInstance().getScene();
        ss.continueGame();
        PlayScene nextPlayScene = (PlayScene) Engine.getInstance().getScene();
        assertEquals(2, nextPlayScene.getCurrentLevel());
    }
}
