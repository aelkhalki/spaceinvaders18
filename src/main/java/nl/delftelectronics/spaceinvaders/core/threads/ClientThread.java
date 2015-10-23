package nl.delftelectronics.spaceinvaders.core.threads;

import nl.delftelectronics.spaceinvaders.core.Engine;
import nl.delftelectronics.spaceinvaders.core.Logger;
import nl.delftelectronics.spaceinvaders.core.Sprite;
import nl.delftelectronics.spaceinvaders.core.entities.Entity;
import nl.delftelectronics.spaceinvaders.core.entities.SpriteEntity;
import nl.delftelectronics.spaceinvaders.core.scenes.GameScene;

import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Manages the connection with the server.
 */
public class ClientThread extends Thread {
    private String serverIp;
    private int serverPort;
    private Socket clientSocket;
    private GameScene gameScene;

    /**
     * Create a Thread and Socket.
     *
     * @param serverIp   IP address of the server
     * @param serverPort port of the server
     * @param gameScene  the GameScene to add entities to
     * @throws IOException thrown if a connection cannot be established with the server
     */
    public ClientThread(String serverIp, int serverPort, GameScene gameScene) throws IOException {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
        this.gameScene = gameScene;

        this.clientSocket = new Socket(serverIp, serverPort);
    }

    @Override
    public void run() {
        while (true) {
            try {
                List<Entity> entities = receiveEntities();
                gameScene.setEntities(entities);
            } catch (IOException e) {
                Logger.error("Cannot connect or read from the server.");
                e.printStackTrace();
            }
            try {
                sendInputs();
            } catch (IOException e) {
                Logger.error("Cannot connect or write to the server.");
            }
        }
    }

    /**
     * Send the inputs of the client to the server.
     *
     * @throws IOException thrown if the inputs cannot be written to the server
     */
    public void sendInputs() throws IOException {
        OutputStream os = clientSocket.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);
        Collection<String> inputs = new ArrayList<String>(Engine.getInstance().getInputs());
        oos.writeObject(inputs);
    }

    /**
     * Read the sprites from the server and subsequently create SpriteEntities.
     *
     * @return the entities sent by the server
     * @throws IOException if the entities cannot be read from the server
     */
    public List<Entity> receiveEntities() throws IOException {
        Collection<Sprite> sprites = receiveSprites();
        return createEntities(sprites);
    }

    /**
     * Create SpriteEntities from the sprites of the server.
     *
     * @param sprites sprites used to create SpriteEntities.
     * @return SpriteEntities created from the sprites
     */
    public List<Entity> createEntities(Collection<Sprite> sprites) {
        List<Entity> entities = new ArrayList<Entity>();
        for (Sprite s : sprites) {
            Rectangle2D spriteRectangle = new Rectangle2D.Double(
                    s.getPositionX(),
                    s.getPositionY(),
                    s.getWidth(),
                    s.getHeight());
            SpriteEntity se = new SpriteEntity(spriteRectangle, s.getFilename());
            se.loadImage();
            entities.add(se);
        }
        return entities;
    }

    /**
     * Read the sprites from the server.
     *
     * @return sprites read from the server
     * @throws IOException if the sprites cannot be read from the server
     */
    @SuppressWarnings("unchecked")
    public Collection<Sprite> receiveSprites() throws IOException {
        InputStream is = clientSocket.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(is);
        try {
            return (Collection<Sprite>) ois.readObject();
        } catch (ClassNotFoundException e) {
            Logger.error("Cannot read data from the server. Ensure that the client and the server "
                    + "have the same version.");
            return new ArrayList<Sprite>();
        }
    }
}
