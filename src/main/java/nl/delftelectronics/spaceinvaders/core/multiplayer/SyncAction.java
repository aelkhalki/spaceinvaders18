/**
 * 
 */
package nl.delftelectronics.spaceinvaders.core.multiplayer;

import nl.delftelectronics.spaceinvaders.core.scenes.GameScene;

/**
 * @author Max
 * Used to synchronize client scene state from the server
 *
 */
public interface SyncAction {
	public void Apply(GameScene scene);
}
