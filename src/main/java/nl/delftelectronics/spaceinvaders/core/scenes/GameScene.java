/**
 * 
 */
package nl.delftelectronics.spaceinvaders.core.scenes;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.Instant;
import org.joda.time.Interval;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import nl.delftelectronics.spaceinvaders.core.Collidable;
import nl.delftelectronics.spaceinvaders.core.collisions.CollisionStrategy;
import nl.delftelectronics.spaceinvaders.core.collisions.SortedCollisionAlgorithm;
import nl.delftelectronics.spaceinvaders.core.entities.DrawableEntity;
import nl.delftelectronics.spaceinvaders.core.entities.Entity;
import nl.delftelectronics.spaceinvaders.core.entities.EntityDestroyedListener;
import nl.delftelectronics.spaceinvaders.core.entities.PreUpdatable;

/**
 * Manages a set of entities
 * 
 * @author Max
 *
 */
public class GameScene implements EntityDestroyedListener {
	private List<Entity> entities = new ArrayList<Entity>();
	private List<Entity> deletes = new ArrayList<Entity>();
	private List<Entity> additions = new ArrayList<Entity>();
	private Instant lastUpdate;
	private Instant lastDraw;
	private boolean hasLoadedGraphics = false;
	private GraphicsContext graphicsContext;
	private CollisionStrategy collisionStrategy;
	public Scene scene;

	/**
	 * Creates a new GameScene with a given width and height
	 * 
	 * @param scene
	 *            The scene to attach to
	 */
	public GameScene(Scene scene) {
		this.scene = scene;
		collisionStrategy = new SortedCollisionAlgorithm();
	}
	
	/**
	 * Sets the algorithm used to compute collisions
	 * @param algorithm
	 *            The algorithm to use
	 */
	public void setCollisionAlgorithm(CollisionStrategy algorithm) {
		this.collisionStrategy = algorithm;
	}

	/**
	 * Calls the update function on the contained entities Afterwards the
	 * entities that fired a destroy event are removed from the scene
	 */
	public void update() {
		if (lastUpdate == null) {
			lastUpdate = new Instant();
		}

		Instant now = new Instant();
		Interval delta = new Interval(lastUpdate, now);
		lastUpdate = now;

		handleAdditions();

		// Pre updates
		for (Entity e : entities) {
			if (e instanceof PreUpdatable) {
				((PreUpdatable) e).preUpdate(delta);
			}
		}

		// Updates
		for (Entity e : entities) {
			e.update(delta);
		}

		handleDeletions();
		
		collisionStrategy.update(entities);
	}

	/**
	 * Removes entities scheduled for deletion
	 */
	protected void handleDeletions() {
		for (Entity e : deletes) {
			entities.remove(e);
		}
		deletes.clear();
	}

	/**
	 * Adds added entities to the entity list
	 */
	protected void handleAdditions() {
		for (Entity e : additions) {
			entities.add(e);
		}
		additions.clear();
	}

	/**
	 * Allows the DrawableEntities to draw themselves
	 * 
	 * @param gc
	 *            The graphicsContext to draw in
	 */
	public void draw(GraphicsContext gc) {

		handleAdditions();

		if (!hasLoadedGraphics) {
			hasLoadedGraphics = true;
			for (Entity e : entities) {
				if (e instanceof DrawableEntity) {
					((DrawableEntity) e).initializeGraphics(gc);
				}
			}
		}

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
	}

	/**
	 * Computes collisions for Collidable entities and returns the list of
	 * Entities you're colliding with
	 * 
	 * @param collidee
	 *            The Entity to use as reference
	 * @return The list of Entities it collides with
	 */
	public List<Entity> getCollisions(Collidable collidee) {
		return collisionStrategy.getCollisions(collidee);
	}
	
	/**
	 * Returns a list of all current entities
	 * @return the list of entities
	 */
	public List<Entity> getEntities() {
		ArrayList<Entity> result = new ArrayList<Entity>();
		result.addAll(entities);
		return result;
	}

	/**
	 * Adds an entity to the scene
	 * 
	 * @param entity
	 *            The entity to add
	 */
	public void addEntity(Entity entity) {
		additions.add(entity);
		entity.initialize(this);
		entity.addDestroyedListener(this);

		if (hasLoadedGraphics && entity instanceof DrawableEntity) {
			((DrawableEntity) entity).initializeGraphics(graphicsContext);
		}
	}

	/**
	 * Is called when Destroy() is called on a contained entity This schedules
	 * the entity to be removed
	 * 
	 * @param entity
	 *            The entity that destroyed itself
	 */
	public void entityDestroyed(Entity entity) {
		deletes.add(entity);
	}
}
