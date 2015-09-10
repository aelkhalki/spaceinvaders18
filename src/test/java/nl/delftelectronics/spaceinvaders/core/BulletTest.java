package nl.delftelectronics.spaceinvaders.core;

import org.joda.time.Interval;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * @author Max
 *
 */
public class BulletTest extends TestCase {

	/**
	 * Test method for {@link nl.delftelectronics.spaceinvaders.core.Bullet#update(org.joda.time.Interval)}.
	 */
	public void testUpdate() {
		Bullet bullet = new Bullet(10, 10, 10, 10, Direction.NORTH);
		Assert.assertEquals((long) 10, (long) bullet.getPositionY());
		bullet.update(new Interval(0, 1000));
		Assert.assertEquals((long) 10 - Bullet.MOVING_SPEED, (long) bullet.getPositionY());
		
		Bullet down = new Bullet(10, 10, 10, 10, Direction.SOUTH);
		Assert.assertEquals((long) 10, (long) down.getPositionY());
		bullet.update(new Interval(0, 1000));
		Assert.assertEquals((long) 10 + Bullet.MOVING_SPEED, (long) down.getPositionY());
	}

}
