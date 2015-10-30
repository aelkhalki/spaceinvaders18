//CHECKSTYLE.OFF: MagicNumber
package nl.delftelectronics.spaceinvaders.core.threads;

import junit.framework.TestCase;
import nl.delftelectronics.spaceinvaders.core.Sprite;
import nl.delftelectronics.spaceinvaders.core.entities.Entity;
import nl.delftelectronics.spaceinvaders.core.entities.SpriteEntity;
import nl.delftelectronics.spaceinvaders.core.scenes.GameScene;

import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ServerThreadTest extends TestCase {
    public void testCreateSprites() throws IOException {
        SpriteEntity e = new SpriteEntity(new Rectangle2D.Double(10, 10, 10, 10), "/shipGreen.png");
        Collection<Entity> entities = new ArrayList<Entity>();
        entities.add(e);

        ServerSocket serverSocket = new ServerSocket(46513);
        ServerThread serverThread = new ServerThread(serverSocket, entities);
        Collection<Sprite> sprites = serverThread.createSprites(entities);
        serverSocket.close();
        assertEquals("/shipGreen.png", sprites.iterator().next().getFilename());
    }

    public void testInteractionWithThreads() throws IOException {
        GameScene gameScene = mock(GameScene.class);
        ServerSocket serverSocket = new ServerSocket(46513);
        ServerThread serverThread = new ServerThread(serverSocket, new ArrayList<Entity>());
        ClientThread clientThread = new ClientThread("127.0.0.1", 46513, gameScene);
        serverThread.start();
        clientThread.start();
        verify(gameScene, times(1)).setEntities(any(List.class));
        serverSocket.close();
    }
}
