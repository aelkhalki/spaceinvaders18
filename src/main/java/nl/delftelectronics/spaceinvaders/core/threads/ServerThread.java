package nl.delftelectronics.spaceinvaders.core.threads;

import nl.delftelectronics.spaceinvaders.core.Engine;
import nl.delftelectronics.spaceinvaders.core.Logger;
import nl.delftelectronics.spaceinvaders.core.Sprite;
import nl.delftelectronics.spaceinvaders.core.entities.Entity;
import nl.delftelectronics.spaceinvaders.core.entities.SpriteEntity;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Sends entities and receives inputs from the client.
 */
public class ServerThread extends Thread {
    private ServerSocket serverSocket;
    private Collection<Entity> entities;
    private Collection<String> oldKeys;
    private Socket socket;

    /**
     * Create a ServerThread using a preexisting socket.
     *
     * @param serverSocket preexisting server socket
     * @param entities     entities in the scene
     */
    public ServerThread(ServerSocket serverSocket, Collection<Entity> entities) {
        this.serverSocket = serverSocket;
        this.entities = entities;
        this.oldKeys = new ArrayList<String>();
    }

    /**
     * Update the entities that are in the scene.
     *
     * @param entities entities in the scene
     */
    public void setEntities(Collection<Entity> entities) {
        this.entities = entities;
    }

    @Override
    public void run() {
        try {
            socket = serverSocket.accept();
        } catch (IOException e) {
            Logger.error("Cannot accept connections.");
        }
        while (true) {
            sendSprites();
            receiveKeys();
        }
    }

    /**
     * Send the sprites to the client.
     */
    public void sendSprites() {
        try {
            OutputStream s1out = socket.getOutputStream();
            ObjectOutputStream dos = new ObjectOutputStream(s1out);

            List<Entity> entityCopy = new ArrayList<Entity>(entities);
            Collection<Sprite> sprites = new ArrayList<Sprite>();
            for (Entity e : entityCopy) {
                if (e instanceof SpriteEntity) {
                    SpriteEntity se = (SpriteEntity) e;
                    Sprite s = new Sprite(
                            se.getSpriteFilename(),
                            se.getBoundingBox().getX(),
                            se.getBoundingBox().getY(),
                            se.getBoundingBox().getWidth(),
                            se.getBoundingBox().getHeight()
                    );
                    sprites.add(s);
                }
            }
            dos.writeObject(sprites);
        } catch (IOException e) {
            Logger.error("Cannot write to the client.");
        }
    }

    /**
     * Read the inputs from the client.
     *
     * @return collection of inputs from the client
     */
    @SuppressWarnings("unchecked")
    public Collection<String> receiveKeys() {
        try {
            InputStream s1in = socket.getInputStream();
            ObjectInputStream dis = new ObjectInputStream(s1in);
            try {
                Collection<String> keys = (Collection<String>) dis.readObject();
                for (String keyUp : oldKeys) {
                    Engine.getInstance().keyUp(keyUp);
                }
                for (String keyDown : keys) {
                    Engine.getInstance().keyDown(keyDown);
                }
                oldKeys = keys;
            } catch (ClassNotFoundException e) {
                Logger.error("Cannot read data from the client. Ensure that the client and the "
                        + "server have the same version.");
            }
        } catch (IOException e) {
            Logger.error("Cannot read from the client.");
        }
        return new ArrayList<String>();
    }
}