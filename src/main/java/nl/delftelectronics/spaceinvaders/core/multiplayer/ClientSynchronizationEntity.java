/**
 * 
 */
package nl.delftelectronics.spaceinvaders.core.multiplayer;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.joda.time.Interval;

import nl.delftelectronics.spaceinvaders.core.entities.Entity;
import nl.delftelectronics.spaceinvaders.core.entities.EntityDestroyedListener;
import nl.delftelectronics.spaceinvaders.core.entities.PreUpdatable;
import nl.delftelectronics.spaceinvaders.core.entities.SpriteEntity;
import nl.delftelectronics.spaceinvaders.core.scenes.GameScene;

/**
 * @author Max
 *
 */
public class ClientSynchronizationEntity extends Entity implements EntityDestroyedListener, PostUpdatable {
	
	HashMap<Entity, Integer> mapping = new HashMap<Entity, Integer>();
	HashMap<Entity, Point2D> lastPositions = new HashMap<Entity, Point2D>();
	HashMap<Entity, Point2D> speeds = new HashMap<Entity, Point2D>();
	int nextIdentifier = 0;
	
	List<SyncAction> queuedActions = new ArrayList<SyncAction>();
	
	public void AddEntity(Entity e) {
		if (e instanceof SpriteEntity) {
			synchronized(mapping) {
				int id = nextIdentifier++;
				queuedActions.add(new CreateEntityAction((SpriteEntity) e, id));
				mapping.put(e, id);
				lastPositions.put(e, positionFromEntity((SpriteEntity)e));
				speeds.put(e, new Point2D.Double(0, 0));
			}
			e.addDestroyedListener(this);
		}
	}
	
	/**
	 * Converts a SpriteEntity to it's position
	 * @param e The SpriteEntity to get the position out
	 * @return The position
	 */
	private Point2D positionFromEntity(SpriteEntity e) {
		return new Point2D.Double(e.getPositionX(), e.getPositionY());
	}

	public void entityDestroyed(Entity entity) {
		synchronized(mapping) {
			int id = mapping.get(entity);
			queuedActions.add(new DestroyEntityAction(id));
			mapping.remove(entity);
		}
	}

	public void postUpdate(Interval delta) {
		for(Entity e : scene.getEntities()) {
			if (e instanceof SpriteEntity) {
				SpriteEntity sprite = (SpriteEntity)e;
				
				Point2D delta = new Point2D.Double(speeds.get(key), y)
			}
		}
	}
}