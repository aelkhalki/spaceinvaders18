/**
 * 
 */
package nl.delftelectronics.spaceinvaders.core.entities;

import java.util.EventListener;

/**
 * Used to handle clicks on labels
 * @author Max
 *
 */
public interface LabelClickedListener extends EventListener {
	/**
	 * Called when a label is clicked.
	 *
	 * @param label
	 *            the label that was clicked.
	 */
	void labelClicked(LabelEntity label);
}
