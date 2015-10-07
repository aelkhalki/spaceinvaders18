//CHECKSTYLE.OFF: MagicNumber
package nl.delftelectronics.spaceinvaders.core.scenes;

import junit.framework.TestCase;
import static org.mockito.Mockito.mock;

import javafx.scene.Scene;

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
        playScene.win();
        playScene.win();
        assertEquals(3, playScene.getCurrentLevel());
    }
}
