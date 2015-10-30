//CHECKSTYLE.OFF: MagicNumber
package nl.delftelectronics.spaceinvaders.core.threads;

import junit.framework.TestCase;
import nl.delftelectronics.spaceinvaders.core.Sprite;
import nl.delftelectronics.spaceinvaders.core.entities.Entity;
import nl.delftelectronics.spaceinvaders.core.entities.SpriteEntity;
import nl.delftelectronics.spaceinvaders.core.scenes.GameScene;
import nl.delftelectronics.spaceinvaders.gui.GUI;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.mock;

public class ClientThreadTest extends TestCase {
    public void testCreateEntities() throws IOException {
        Sprite sprite = new Sprite("/shipGreen.png", 2, 3, 4, 5);
        Collection<Sprite> spriteCollection = new ArrayList<Sprite>();
        spriteCollection.add(sprite);

        GameScene gameScene = mock(GameScene.class);
        ServerSocket serverSocket = new ServerSocket(46513);
        ClientThread clientThread = new ClientThread("127.0.0.1", 46513, gameScene);
        List<Entity> spriteEntities = clientThread.createEntities(spriteCollection);
        serverSocket.close();
        assertEquals("/shipGreen.png", ((SpriteEntity) spriteEntities.get(0)).getSpriteFilename());
    }
}
