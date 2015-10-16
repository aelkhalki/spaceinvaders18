/**
 * 
 */
package nl.delftelectronics.spaceinvaders.core.scenes;

import java.awt.geom.Rectangle2D;

import javafx.scene.Scene;
import nl.delftelectronics.spaceinvaders.core.Engine;
import nl.delftelectronics.spaceinvaders.core.entities.LabelClickedListener;
import nl.delftelectronics.spaceinvaders.core.entities.LabelEntity;

/**
 * Used to display the game menu
 * @author Max
 *
 */
public class MenuScene extends GameScene implements LabelClickedListener {

	LabelEntity startButton;
	LabelEntity quitButton;
	
	/**
	 * Creates a new MenuScene
	 * @param scene The javaFX scene to attach to
	 */
	public MenuScene(Scene scene) {
		super(scene);
		
		//CHECKSTYLE.OFF: MagicNumber - Don't want to import a layout engine
		startButton = new LabelEntity(new Rectangle2D.Double(100, 100, 500, 100), "START GAME");
		startButton.addClickedListener(this);
		addEntity(startButton);
		quitButton = new LabelEntity(new Rectangle2D.Double(100, 500, 500, 100), "QUIT GAME");
		quitButton.addClickedListener(this);
		addEntity(quitButton);
		//CHECKSTYLE.ON: MagicNumber
	}

	/**
	 * Handles all button clicks in the scene.
	 * @param label  The clicked label
	 */
	public void labelClicked(LabelEntity label) {
		if (label == quitButton) {
			System.exit(0);
		}
		if (label == startButton) {
			Engine engine = Engine.getInstance();
			engine.setScene(new PlayScene(engine.getScene().scene));
		}
	}

}
