/**
 * 
 */
package nl.delftelectronics.spaceinvaders.core.entities;

import javax.swing.event.EventListenerList;
import org.joda.time.Interval;

import nl.delftelectronics.spaceinvaders.core.scenes.GameScene;

/**
 * Represents a game Entity
 * @author Max
 */
public class Entity {
	private EventListenerList destroyedEvent;
	private Boolean destroyed;
	protected GameScene scene;
	
	/**
	 * Creates a new default Entity
	 */
	public Entity() {
		this.destroyedEvent = new EventListenerList();
		this.destroyed = false;
	}
	
	/**
	 * Called when this Entity is added to a GameScene
	 * @param scene The scene the Entity is added to
	 */
	public void initialize(GameScene scene) {
		this.scene = scene;
	}
	
	/**
	 * Called every update frame
	 * This should not be used to update graphics, only update things like movement
	 * @param timeStep The time since the last update call
	 */
	public void update(Interval timeStep) {
		
	}
	
	/**
	 * Adds a listener to the destroy event
	 * @param listener the listener to add
	 */
	public void addDestroyedListener(EntityDestroyedListener listener) {
		synchronized (destroyedEvent) {
			this.destroyedEvent.add(EntityDestroyedListener.class, listener);
			if (destroyed) {
				listener.entityDestroyed(this);
			}
		}
	}

	/**
	 * Removes a listener from the destroy event
	 * @param listener the listener to remove
	 */
	public void removeDestroyedListener(EntityDestroyedListener listener) {
		synchronized (destroyedEvent) {
			this.destroyedEvent.remove(EntityDestroyedListener.class, listener);
		}
	}

	/**
	 * Notifies subscribed listeners of the destroy event
	 * This will only notify listeners once
	 */
	public void destroy() {
		synchronized (destroyedEvent) {
			if (destroyed) {
				return;
			}
			destroyed = true;
			
			EntityDestroyedListener[] listeners =
					destroyedEvent.getListeners(EntityDestroyedListener.class);
			for (EntityDestroyedListener e : listeners) {
				e.entityDestroyed(this);
			}
		}
	}

	public boolean isDestroyed() {
		return destroyed;
	}
}
