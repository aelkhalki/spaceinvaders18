/**
 * 
 */
package nl.delftelectronics.spaceinvaders.core.multiplayer;

import nl.delftelectronics.spaceinvaders.core.entities.Entity;
import nl.delftelectronics.spaceinvaders.core.scenes.GameScene;

/**
 * @author Max
 * Represents a destroyed entity
 */
public class DestroyEntityAction implements SyncAction {
	int identifier;
	
	/**
	 * Creates a new destroyed entity action
	 * @param identifier The identifier to destroy
	 */
	public DestroyEntityAction(int identifier) {
		this.identifier = identifier;
	}
	
	/* (non-Javadoc)
	 * @see nl.delftelectronics.spaceinvaders.core.multiplayer.SyncAction#Apply(nl.delftelectronics.spaceinvaders.core.scenes.GameScene)
	 */
	public void Apply(GameScene scene) {
		for(Entity e : scene.getEntities()) {
			if (e instanceof MultiplayerEntity) {
				MultiplayerEntity m = (MultiplayerEntity)e;
				
				if (m.identifier == this.identifier)
					m.destroy();
			}
		}
	}

}
