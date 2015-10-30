//CHECKSTYLE.OFF: MagicNumber
package nl.delftelectronics.spaceinvaders.core;

import junit.framework.TestCase;
import nl.delftelectronics.spaceinvaders.core.entities.Barricade;
import org.junit.Test;

import java.awt.geom.Rectangle2D;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Max
 *
 */
public class GameInformationTest extends TestCase {
    public void testSaveAndLoad() {
        Collection<Rectangle2D> rectangles = new ArrayList<Rectangle2D>();
        GameInformation gi = new GameInformation(10, 20, 30, 40, rectangles);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        gi.save(baos);
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        GameInformation loadedGi = GameInformation.load(bais).get();
        assertEquals(loadedGi, gi);
    }

    public void testBarricadeDestroyed() {
        Rectangle2D r = new Rectangle2D.Double(1, 2, 3, 4);
        Barricade b = mock(Barricade.class);
        Collection<Rectangle2D> rectangles = new ArrayList<Rectangle2D>();
        rectangles.add(r);
        GameInformation gi = new GameInformation(10, 20, 30, 40, rectangles);

        when(b.getPositionX()).thenReturn(1.0);
        when(b.getPositionY()).thenReturn(2.0);
        when(b.getWidth()).thenReturn(3.0);
        when(b.getHeight()).thenReturn(4.0);
        gi.entityDestroyed(b);
        assertTrue(gi.getBarricadeRectangles().isEmpty());
    }

    public void testEquals() {
        Rectangle2D r = mock(Rectangle2D.class);
        Collection<Rectangle2D> rectangles = new ArrayList<Rectangle2D>();
        rectangles.add(r);
        GameInformation gi = new GameInformation(10, 20, 30, 40, rectangles);

        Collection<Rectangle2D> rectangles2 = new ArrayList<Rectangle2D>();
        rectangles2.add(r);
        GameInformation gi2 = new GameInformation(10, 20, 30, 40, rectangles2);

        assertEquals(gi, gi2);
    }

    public void testEqualsDifferingPoints() {
        Rectangle2D r = mock(Rectangle2D.class);
        Collection<Rectangle2D> rectangles = new ArrayList<Rectangle2D>();
        rectangles.add(r);
        GameInformation gi = new GameInformation(10, 20, 30, 40, rectangles);

        Rectangle2D r2 = mock(Rectangle2D.class);
        Collection<Rectangle2D> rectangles2 = new ArrayList<Rectangle2D>();
        rectangles2.add(r2);
        GameInformation gi2 = new GameInformation(15, 20, 30, 40, rectangles2);

        assertNotEquals(gi, gi2);
    }

    public void testEqualsDifferingLives() {
        Rectangle2D r = mock(Rectangle2D.class);
        Collection<Rectangle2D> rectangles = new ArrayList<Rectangle2D>();
        rectangles.add(r);
        GameInformation gi = new GameInformation(10, 20, 30, 40, rectangles);

        Rectangle2D r2 = mock(Rectangle2D.class);
        Collection<Rectangle2D> rectangles2 = new ArrayList<Rectangle2D>();
        rectangles2.add(r2);
        GameInformation gi2 = new GameInformation(10, 25, 30, 40, rectangles2);

        assertNotEquals(gi, gi2);
    }

    public void testEqualsDifferingBombs() {
        Rectangle2D r = mock(Rectangle2D.class);
        Collection<Rectangle2D> rectangles = new ArrayList<Rectangle2D>();
        rectangles.add(r);
        GameInformation gi = new GameInformation(10, 20, 30, 40, rectangles);

        Rectangle2D r2 = mock(Rectangle2D.class);
        Collection<Rectangle2D> rectangles2 = new ArrayList<Rectangle2D>();
        rectangles2.add(r2);
        GameInformation gi2 = new GameInformation(10, 20, 35, 40, rectangles2);

        assertNotEquals(gi, gi2);
    }

    public void testEqualsDifferingLevel() {
        Rectangle2D r = mock(Rectangle2D.class);
        Collection<Rectangle2D> rectangles = new ArrayList<Rectangle2D>();
        rectangles.add(r);
        GameInformation gi = new GameInformation(10, 20, 30, 40, rectangles);

        Rectangle2D r2 = mock(Rectangle2D.class);
        Collection<Rectangle2D> rectangles2 = new ArrayList<Rectangle2D>();
        rectangles2.add(r2);
        GameInformation gi2 = new GameInformation(10, 20, 30, 45, rectangles2);

        assertNotEquals(gi, gi2);
    }

    public void testHashCode() {
        Collection<Rectangle2D> rectangles = new ArrayList<Rectangle2D>();
        GameInformation gi = new GameInformation(10, 20, 30, 40, rectangles);

        GameInformation gi2 = new GameInformation(10, 20, 30, 40, rectangles);

        assertEquals(gi.hashCode(), gi2.hashCode());
    }

    @Test(expected=IOException.class)
    public void testSaveErrorWhenWrongFilename() {
        Collection<Rectangle2D> rectangles = new ArrayList<Rectangle2D>();
        GameInformation gi = new GameInformation(10, 20, 30, 40, rectangles);

        gi.save("");
    }

    @Test(expected=IOException.class)
    public void testLoadErrorWhenWrongFilename() {
        Collection<Rectangle2D> rectangles = new ArrayList<Rectangle2D>();
        GameInformation gi = new GameInformation(10, 20, 30, 40, rectangles);

        gi.load("");
    }
}
