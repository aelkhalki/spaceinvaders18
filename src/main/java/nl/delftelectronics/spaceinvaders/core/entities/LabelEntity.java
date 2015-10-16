/**
 * 
 */
package nl.delftelectronics.spaceinvaders.core.entities;

import java.awt.geom.Rectangle2D;

import javax.swing.event.EventListenerList;

import org.joda.time.Interval;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.geometry.VPos;
import nl.delftelectronics.spaceinvaders.core.Collidable;
import nl.delftelectronics.spaceinvaders.core.Engine;
import javafx.scene.canvas.GraphicsContext;

/**
 * Displays a text and handles detects mouse clicks on it.
 * 
 * @author Max
 *
 */
public class LabelEntity extends DrawableEntity implements Collidable {

	private EventListenerList clickedEvent;
	//CHECKSTYLE.OFF: MagicNumber
	protected Font font = Font.font("Arial", FontWeight.BOLD, 48);
	//CHECKSTYLE.ON: MagicNumber
	protected String text;

	/**
	 * Creates a new LabelEntity
	 * 
	 * @param position
	 * 			  position of the sprite
	 * @param text
	 *            The text to display
	 */
	public LabelEntity(Rectangle2D position, String text) {
		super(position);

		this.text = text;
		clickedEvent = new EventListenerList();
	}

	/**
	 * Draws the text
	 * @param delta The amount of time elapsed since the last frame
	 * @param gc The graphics context to draw in
	 */
	@Override
	public void draw(Interval delta, GraphicsContext gc) {
		final int lineWidth = 2;
		gc.setFill(Color.RED);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(lineWidth);
		gc.setTextAlign(TextAlignment.LEFT);
		gc.setTextBaseline(VPos.TOP);
		gc.setFont(font);
		gc.fillText(text, getPositionX(), getPositionY());
	}

	/**
	 * Detects clicks
	 * @param delta  The amount of time elapsed since the last update
	 */
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

	/**
	 * Adds a click listener to the label
	 * @param listener The listener to add
	 */
	public void addClickedListener(LabelClickedListener listener) {
		clickedEvent.add(LabelClickedListener.class, listener);
	}

	/**
	 * Removes a click listener from the label
	 * @param listener  The listener to remove
	 */
	public void removeClickedListener(LabelClickedListener listener) {
		clickedEvent.remove(LabelClickedListener.class, listener);
	}

	/**
	 * Sets the displayed text
	 * @param text The text to display
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * Gets the displayed text
	 * @return the displayed text
	 */
	public String getText() {
		return text;
	}
}
