/**
 * 
 */
package nl.delftelectronics.spaceinvaders.core.multiplayer;

import java.awt.geom.Point2D;

import nl.delftelectronics.spaceinvaders.core.entities.Entity;
import nl.delftelectronics.spaceinvaders.core.scenes.GameScene;

/**
 * @author Max
 *
 */
public class UpdatedEntityAction implements SyncAction {
	int identifier;
	Point2D speed;
	Point2D position;
	
	public UpdatedEntityAction(int identifier, Point2D speed, Point2D position) {
		this.identifier = identifier;
		this.speed = speed;
		this.position = position;
	}
	
	/* (non-Javadoc)
	 * @see nl.delftelectronics.spaceinvaders.core.multiplayer.SyncAction#Apply(nl.delftelectronics.spaceinvaders.core.scenes.GameScene)
	 */
	public void Apply(GameScene scene) {
		for(Entity e : scene.getEntities()) {
			if (e instanceof MultiplayerEntity) {
				MultiplayerEntity m = (MultiplayerEntity)e;
				
				if (m.identifier == this.identifier) {
					m.speed = this.speed;
					m.setPositionX(position.getX());
					m.setPositionY(position.getY());
				}
			}
		}
	}
}
