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
import nl.delftelectronics.spaceinvaders.core.entities.DrawableEntity;
import nl.delftelectronics.spaceinvaders.core.entities.Entity;
import nl.delftelectronics.spaceinvaders.core.entities.EntityDestroyedListener;
import nl.delftelectronics.spaceinvaders.core.entities.PreUpdatable;

import java.awt.Rectangle;

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
	public Scene scene;

	/**
	 * Creates a new GameScene with a given width and height
	 * 
	 * @param scene
	 *            The scene to attach to
	 */
	public GameScene(Scene scene) {
		this.scene = scene;
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

		for (Entity e : additions) {
			entities.add(e);
		}
		additions.clear();

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

		for (Entity e : deletes) {
			entities.remove(e);
		}
		deletes.clear();
	}

	/**
	 * Allows the DrawableEntities to draw themselves
	 * 
	 * @param gc
	 *            The graphicsContext to draw in
	 */
	public void draw(GraphicsContext gc) {

		for (Entity e : additions) {
			entities.add(e);
		}
		additions.clear();

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
		ArrayList<Entity> result = new ArrayList<Entity>();

		Rectangle referenceRectangle = new Rectangle((int)collidee.getPositionX(), (int)collidee.getPositionY(),
				(int)collidee.getWidth(), (int)collidee.getHeight());

		for (Entity candidate : entities) {
			if (candidate == collidee) {
				continue;
			}
			if (candidate instanceof Collidable) {
				Collidable c = (Collidable) candidate;
				Rectangle testRectangle = new Rectangle((int)c.getPositionX(), (int)c.getPositionY(),
						(int)c.getWidth(), (int)c.getHeight());

				if (referenceRectangle.intersects(testRectangle)) {
					result.add(candidate);
				}
			}
		}

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
