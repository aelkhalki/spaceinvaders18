/**
 * 
 */
package nl.delftelectronics.spaceinvaders.core.entities;

import javax.swing.event.EventListenerList;

import org.joda.time.Interval;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import nl.delftelectronics.spaceinvaders.core.Collidable;
import nl.delftelectronics.spaceinvaders.core.Engine;
import javafx.scene.canvas.GraphicsContext;

/**
 * @author Max
 *
 */
public class LabelEntity extends DrawableEntity implements Collidable {

	private EventListenerList clickedEvent;
	protected Font font = Font.font("Arial", FontWeight.BOLD, 48);
	protected String text;
	
	/**
	 * @param positionX
	 * @param positionY
	 * @param width
	 * @param height
	 */
	public LabelEntity(int positionX, int positionY, int width, int height, String text) {
		super(positionX, positionY, width, height);
		
		this.text = text;
		clickedEvent = new EventListenerList();
	}

	@Override
	public void draw(Interval delta, GraphicsContext gc) {
		final int lineWidth = 2;
        gc.setFill(Color.RED);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(lineWidth);
        gc.setFont(font);
        gc.fillText(text, getPositionX(), getPositionY());
	}
	
	@Override
	public void update(Interval delta) {
		Engine engine = Engine.getInstance();
		if (engine.wasClicked(this)) {
			LabelClickedListener[] listeners = 
					clickedEvent.getListeners(LabelClickedListener.class);
			for (LabelClickedListener l : listeners) {
				l.labelClicked(this);
			}
		}
	}
	
	public void addClickedListener(LabelClickedListener listener) {
		clickedEvent.add(LabelClickedListener.class, listener);
	}
	
	public void removeClickedListener(LabelClickedListener listener) {
		clickedEvent.remove(LabelClickedListener.class, listener);
	}
}
