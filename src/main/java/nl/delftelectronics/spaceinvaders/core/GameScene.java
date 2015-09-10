/**
 * 
 */
package nl.delftelectronics.spaceinvaders.core;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.Instant;
import org.joda.time.Interval;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

/**
 * Manages a set of entities
 * @author Max
 *
 */
public class GameScene implements EntityDestroyedListener {
	private List<Entity> entities = new ArrayList<Entity>();
	private List<Entity> deletes = new ArrayList<Entity>();
	private Instant lastUpdate;
	private Instant lastDraw;
	protected Scene scene;
	
	/**
	 * Creates a new GameScene with a given width and height
	 * @param scene The scene to attach to
	 */
	public GameScene(Scene scene) {
		this.scene = scene;
	}
	
	/**
	 * Calls the update function on the contained entities
	 * Afterwards the entities that fired a destroy event are removed from the scene
	 */
	public void update() {
		if (lastUpdate == null) {
			lastUpdate = new Instant();
		}
		
		Instant now = new Instant();
		Interval delta = new Interval(lastUpdate, now);
		lastUpdate = now;
		
		for (Entity e : entities) {
			e.update(delta);
		}
		
		for (Entity e : deletes) {
			entities.remove(e);
		}
		deletes.clear();
	}
	
	/**
	 * Allows the DrawableEntities to draw themselves
	 * @param gc The graphicsContext to draw in
	 */
	public void draw(GraphicsContext gc) {
		if (lastDraw == null) {
			lastDraw = new Instant();
		}
		
		Instant now = new Instant();
		Interval delta = new Interval(lastDraw, now);
		lastDraw = now;
		
		for (Entity e : entities) {
			if (e instanceof DrawableEntity) {
				((DrawableEntity) e).draw(delta, gc);
			}
		}
		
		for (Entity e : deletes) {
			entities.remove(e);
			deletes.remove(e);
		}
	}
	
	/**
	 * Adds an entity to the scene
	 * @param entity The entity to add
	 */
	public void addEntity(Entity entity) {
		entities.add(entity);
		entity.initialize(this);
		entity.addDestroyedListener(this);
	}

	/**
	 * Is called when Destroy() is called on a contained entity
	 * This schedules the entity to be removed
	 * @param entity The entity that destroyed itself
	 */
	public void entityDestroyed(Entity entity) {
		deletes.add(entity);
	}
}
