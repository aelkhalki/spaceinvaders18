/**
 * 
 */
package nl.delftelectronics.spaceinvaders.core.entities;

import java.util.EventListener;

/**
 * @author Max
 *
 */
public interface LabelClickedListener extends EventListener {
	/**
     * Called then a label is clicked.
     *
     * @param entity the label that was clicked.
     */
    void labelClicked(LabelEntity label);
}
