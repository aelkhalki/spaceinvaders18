/**
 * 
 */
package nl.delftelectronics.spaceinvaders.core.scenes;

import javafx.scene.Scene;
import nl.delftelectronics.spaceinvaders.core.Engine;
import nl.delftelectronics.spaceinvaders.core.entities.LabelClickedListener;
import nl.delftelectronics.spaceinvaders.core.entities.LabelEntity;

/**
 * @author Max
 *
 */
public class MenuScene extends GameScene implements LabelClickedListener {

	LabelEntity startButton;
	LabelEntity quitButton;
	
	/**
	 * @param scene
	 */
	public MenuScene(Scene scene) {
		super(scene);
		
		startButton = new LabelEntity(100, 100, 500, 100, "START GAME");
		startButton.addClickedListener(this);
		addEntity(startButton);
		quitButton = new LabelEntity(100, 500, 500, 100, "QUIT GAME");
		quitButton.addClickedListener(this);
		addEntity(quitButton);
	}

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
