/**
 * 
 */
package nl.delftelectronics.spaceinvaders.core;

import junit.framework.TestCase;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Max
 *
 */
public class EntityTest extends TestCase {

	/**
	 * Test method for {@link nl.delftelectronics.spaceinvaders.core.DrawableEntity#destroy()}.
	 */
	public void testDestroy() {
		EntityDestroyedListener listener1 = mock(EntityDestroyedListener.class);
		EntityDestroyedListener listener2 = mock(EntityDestroyedListener.class);
		Entity e = new Entity();
		
		e.addDestroyedListener(listener1);
		e.addDestroyedListener(listener2);
		
		e.removeDestroyedListener(listener2);
		e.destroy();
		
		// A removed event listener should not fire
		verify(listener1, times(1)).entityDestroyed(e);
		verify(listener2, times(0)).entityDestroyed(e);
		
		e.destroy();
		
		// Event listeners should only fire once
		verify(listener1, times(1)).entityDestroyed(e);
		verify(listener2, times(0)).entityDestroyed(e);
		
		e.addDestroyedListener(listener2);
		// Added event listeners should fire instantly after adding if the entity is destroyed
		verify(listener2, times(1)).entityDestroyed(e);
	}
}
