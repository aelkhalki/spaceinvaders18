/**
 * 
 */
package nl.delftelectronics.spaceinvaders.core.multiplayer;

import java.awt.geom.Rectangle2D;

import nl.delftelectronics.spaceinvaders.core.entities.SpriteEntity;
import nl.delftelectronics.spaceinvaders.core.scenes.GameScene;

/**
 * @author Max
 * An action representing a newly created entity
 */
public class CreateEntityAction implements SyncAction {

	int identifier;
	Rectangle2D rectangle;
	String sprite;

	/**
	 * Creates a new creation action from an existent entity
	 * @param entity the existing entity
	 * @param identifier the identifier the entity should get
	 */
	public CreateEntityAction(SpriteEntity entity, int identifier) {
		this.identifier = identifier;
		this.rectangle = entity.getRectangle();
		this.sprite = entity.getSpriteFilename();
	}
	
	/* (non-Javadoc)
	 * @see nl.delftelectronics.spaceinvaders.core.multiplayer.SyncAction#Apply(nl.delftelectronics.spaceinvaders.core.scenes.GameScene)
	 */
	public void Apply(GameScene scene) {
		MultiplayerEntity entity = new MultiplayerEntity(rectangle, sprite, identifier);
		scene.addEntity(entity);
	}
}
