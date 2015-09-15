package nl.delftelectronics.spaceinvaders.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javafx.scene.canvas.GraphicsContext;
import com.google.common.collect.Iterators;

/**
 * The Engine is the main class of this game. The most of the game logic is performed in this class.
 */
public class Engine {
	
    private static Engine instance;
    private List<String> inputs = new ArrayList<String>();

    private GameScene currentScene;

    /**
     * Create the engine, which handles the game.
     *
     * @param fieldWidth  the width of the playing field.
     * @param fieldHeight the height of the playing field.
     * @param startScene  the scene.
     */
    public Engine(int fieldWidth, int fieldHeight, GameScene startScene) {
        this.currentScene = startScene;
        Engine.instance = this;
    }
    
    /**
     * Returns the last instance of the Engine
     * @return the last instance of the Engine
     */
    public static Engine getInstance() {
    	return instance;
    }
    
    public Ship getShip() {
    	if (currentScene instanceof PlayScene) {
    		return ((PlayScene) currentScene).ship;
    	}
    	return null;
    }
    
    /**
     * Tests whether a specified key is currently pressed
     * @param key  the key to test.
     * @return
     */
    public boolean isKeyPressed(String key) {
    	return inputs.contains(key);
    }

    /**
     * Return the number of lives the player has left.
     *
     * @return the number of lives the player has left.
     */
    public int getLives() {
    	if (getShip() == null) {
    		return 0;
    	}
    	
        return getShip().getLives();
    }

    /**
     * Return the number of points the player has accumulated.
     *
     * @return the number of points the player has accumulated.
     */
    public int getPoints() {
    	if (currentScene instanceof PlayScene) {
    		return ((PlayScene) currentScene).getPoints();
    	} else {
    		return 0;
    	}
    }

    /**
     * Create the ship and the enemies.
     *
     * @param shipWidth   the width of the ship (in pixels).
     * @param shipHeight  the height of the ship (in pixels).
     * @param enemyWidth  the width of the enemy (in pixels).
     * @param enemyHeight the height of the enemy (in pixels).
     */
    public void startGame(int shipWidth, int shipHeight, int enemyWidth, int enemyHeight, List<String> inputs) {
        currentScene = new PlayScene(currentScene.scene, shipWidth, shipHeight, enemyWidth, enemyHeight);
        this.inputs = inputs;
    }

    /**
     * Update the positions of all drawn entities.
     */
    public void update() {
        currentScene.update();
    }
    
    

    /**
     * Draw all drawable entities in the scene.
     *
     * @param gc the graphics context used to display output.
     */
    public void draw(GraphicsContext gc) {
        currentScene.draw(gc);
    }

    /**
     * True if the game is in progress, otherwise false.
     *
     * @return true if the game is in progress, otherwise false.
     */
    public boolean isInProgress() {
        return getLives() > 0;
    }
}
