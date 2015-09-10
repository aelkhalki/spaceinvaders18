/**
 * 
 */
package nl.delftelectronics.spaceinvaders.core;

import javax.swing.event.EventListenerList;
import javafx.scene.Scene;
import org.joda.time.Interval;

/**
 * @author Max
 *
 */
public class Entity {
	private EventListenerList destroyedEvent;
	private Boolean destroyed;
	protected GameScene scene;
	
	public Entity() {
		this.destroyedEvent = new EventListenerList();
		this.destroyed = false;
	}
	
	public void initialize(GameScene scene) {
		this.scene = scene;
	}
	
	public void update(Interval timeStep) {
		
	}
	
	/**
	 * Adds a listener to the destroy event
	 * @param listener the listener to add
	 */
	public void addDestroyedListener(EntityDestroyedListener listener) {
		synchronized(destroyedEvent){
			this.destroyedEvent.add(EntityDestroyedListener.class, listener);
			if (destroyed)
				listener.entityDestroyed(this);
		}
	}

	/**
	 * Removes a listener from the destroy event
	 * @param listener the listener to remove
	 */
	public void removeDestroyedListener(EntityDestroyedListener listener) {
		synchronized(destroyedEvent) {
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
			
			EntityDestroyedListener[] listeners = destroyedEvent.getListeners(EntityDestroyedListener.class);
			for(EntityDestroyedListener e : listeners) {
				e.entityDestroyed(this);
			}
		}
	}
	
	
}
