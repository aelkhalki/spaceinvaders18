/**
 * 
 */
package nl.delftelectronics.spaceinvaders.core.multiplayer;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import org.joda.time.Interval;

import nl.delftelectronics.spaceinvaders.core.entities.SpriteEntity;

/**
 * @author Max
 *
 */
public class MultiplayerEntity extends SpriteEntity {
	
	int identifier;
	Point2D speed;
	
	public MultiplayerEntity(Rectangle2D position, String spriteName, int identifier) {
		super(position, spriteName);
		this.identifier = identifier;
		this.speed = new Point2D.Double(0,0);
	}
	
	public int getIdentifier() {
		return identifier;
	}
	
	@Override
	public void update(Interval timeStep) {
		super.update(timeStep);
		this.setPositionX(this.getPositionX() + speed.getX() * (double)timeStep.toDurationMillis() / 1000);
		this.setPositionY(this.getPositionY() + speed.getY() * (double)timeStep.toDurationMillis() / 1000);
	}
}
