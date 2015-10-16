/**
 * 
 */
package nl.delftelectronics.spaceinvaders.core.scenes;

import javafx.scene.Scene;
import nl.delftelectronics.spaceinvaders.core.Engine;
import nl.delftelectronics.spaceinvaders.core.GameInformation;
import nl.delftelectronics.spaceinvaders.core.entities.LabelClickedListener;
import nl.delftelectronics.spaceinvaders.core.entities.LabelEntity;

import java.util.Optional;

/**
 * Used to display the game menu
 * @author Max
 *
 */
public class MenuScene extends GameScene implements LabelClickedListener {

	private LabelEntity startButton;
	private LabelEntity loadGameButton;
	private LabelEntity quitButton;
	
	/**
	 * Creates a new MenuScene
	 * @param scene The javaFX scene to attach to
	 */
	public MenuScene(Scene scene) {
		super(scene);
		
		//CHECKSTYLE.OFF: MagicNumber - Don't want to import a layout engine
		startButton = new LabelEntity(100, 100, 500, 100, "START GAME");
		startButton.addClickedListener(this);
		addEntity(startButton);
		loadGameButton = new LabelEntity(100, 300, 500, 100, "LOAD GAME");
		loadGameButton.addClickedListener(this);
		addEntity(loadGameButton);
		quitButton = new LabelEntity(100, 500, 500, 100, "QUIT GAME");
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
		if (label == loadGameButton) {
			Optional<GameInformation> gi = GameInformation.load();
			if (gi.isPresent()) {
				Engine engine = Engine.getInstance();
				engine.setScene(new StoreScene(scene, gi.get()));
			}
		}
		if (label == startButton) {
			Engine engine = Engine.getInstance();
			engine.setScene(new PlayScene(engine.getScene().scene));
		}
	}

}
