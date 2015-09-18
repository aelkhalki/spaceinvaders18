package nl.delftelectronics.spaceinvaders.core;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import nl.delftelectronics.spaceinvaders.core.scenes.GameScene;

/**
 * The Engine is the main class of this game. The most of the game logic is
 * performed in this class.
 */
public class Engine {

	private static Engine instance;
	private List<String> inputs = new ArrayList<String>();
	private List<Point> clicks = new ArrayList<Point>();

	private GameScene currentScene;

	/**
	 * Create the engine, which handles the game.
	 *
	 * @param startScene
	 *            the scene.
	 */
	public Engine(GameScene startScene) {
		this.currentScene = startScene;
		Engine.instance = this;
	}

	/**
	 * Returns the last instance of the Engine
	 * 
	 * @return the last instance of the Engine
	 */
	public static Engine getInstance() {
		return instance;
	}

	/**
	 * Tests whether a specified key is currently pressed
	 * 
	 * @param key
	 *            the key to test.
	 * @return true if the key is pressed
	 */
	public boolean isKeyPressed(String key) {
		synchronized (inputs) {
			return inputs.contains(key);
		}
	}

	/**
	 * Registers a key as pressed
	 * 
	 * @param key
	 *            the key to register
	 */
	public void keyDown(String key) {
		synchronized (inputs) {
			if (!inputs.contains(key)) {
				inputs.add(key);
			}
		}
	}

	/**
	 * Registers a key as released
	 * 
	 * @param key
	 *            the key to release
	 */
	public void keyUp(String key) {
		synchronized (inputs) {
			inputs.remove(key);
		}
	}

	/**
	 * Registers a click until the next update
	 * 
	 * @param position
	 *            the position that was clicked
	 */
	public void addClick(Point position) {
		synchronized (clicks) {
			clicks.add(position);
		}
	}

	/**
	 * Sets the current scene
	 * 
	 * @param scene
	 *            the scene to display
	 */
	public void setScene(GameScene scene) {
		currentScene = scene;
	}

	/**
	 * Returns the currently displayed scene
	 * 
	 * @return the currently displayed scene
	 */
	public GameScene getScene() {
		return currentScene;
	}

	/**
	 * Tests whether there was a click between this and the previous update in
	 * the specified rectangle
	 * 
	 * @param collider
	 *            The rectangle to check
	 * @return Whether a click was registered
	 */
	public boolean wasClicked(Collidable collider) {
		Rectangle r = new Rectangle((int) collider.getPositionX(), (int) collider.getPositionY(),
				(int) collider.getWidth(), (int) collider.getHeight());
		synchronized (clicks) {
			for (Point p : clicks) {
				if (r.contains(p)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Update the positions of all drawn entities.
	 */
	public void update() {
		currentScene.update();
		synchronized (clicks) {
			clicks.clear();
		}
	}

	/**
	 * Draw all drawable entities in the scene.
	 *
	 * @param gc
	 *            the graphics context used to display output.
	 */
	public void draw(GraphicsContext gc) {
		currentScene.draw(gc);
	}
}
