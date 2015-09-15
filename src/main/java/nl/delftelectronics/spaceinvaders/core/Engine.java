package nl.delftelectronics.spaceinvaders.core;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javafx.scene.canvas.GraphicsContext;
import nl.delftelectronics.spaceinvaders.core.scenes.GameScene;
import nl.delftelectronics.spaceinvaders.core.scenes.PlayScene;

import com.google.common.collect.Iterators;

/**
 * The Engine is the main class of this game. The most of the game logic is performed in this class.
 */
public class Engine {
	
    private static Engine instance;
    private List<String> inputs = new ArrayList<String>();
    private List<Point> clicks = new ArrayList<Point>();
    
    private GameScene currentScene;

    /**
     * Create the engine, which handles the game.
     *
     * @param fieldWidth  the width of the playing field.
     * @param fieldHeight the height of the playing field.
     * @param startScene  the scene.
     */
    public Engine(GameScene startScene, List<String> inputs) {
        this.currentScene = startScene;
        this.inputs = inputs;
        Engine.instance = this;
    }
    
    /**
     * Returns the last instance of the Engine
     * @return the last instance of the Engine
     */
    public static Engine getInstance() {
    	return instance;
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
     * Registers a click until the next update
     * @param position  the position that was clicked
     */
    public void addClick(Point position) {
    	clicks.add(position);
    }
    
    public void setScene(GameScene scene) {
    	currentScene = scene;
    }
    
    public GameScene getScene() {
    	return currentScene;
    }
    
    public boolean wasClicked(Collidable collider) {
    	Rectangle r = new Rectangle(collider.getPositionX(), collider.getPositionY(),
    			collider.getWidth(), collider.getHeight());
    	for (Point p : clicks) {
    		if (r.contains(p)) {
    			return true;
    		}
    	}
    	return false;
    }

    /**
     * Update the positions of all drawn entities.
     */
    public void update() {
        currentScene.update();
        clicks.clear();
    }
    
    /**
     * Draw all drawable entities in the scene.
     *
     * @param gc the graphics context used to display output.
     */
    public void draw(GraphicsContext gc) {
        currentScene.draw(gc);
    }
}
