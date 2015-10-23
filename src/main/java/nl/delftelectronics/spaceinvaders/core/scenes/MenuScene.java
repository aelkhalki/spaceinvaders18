/**
 *
 */
package nl.delftelectronics.spaceinvaders.core.scenes;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import nl.delftelectronics.spaceinvaders.core.Engine;
import nl.delftelectronics.spaceinvaders.core.GameInformation;
import nl.delftelectronics.spaceinvaders.core.Logger;
import nl.delftelectronics.spaceinvaders.core.animationtimers.ServerUpdateSceneAnimationTimer;
import nl.delftelectronics.spaceinvaders.core.threads.ClientThread;
import nl.delftelectronics.spaceinvaders.core.threads.ServerThread;
import nl.delftelectronics.spaceinvaders.core.entities.LabelClickedListener;
import nl.delftelectronics.spaceinvaders.core.entities.LabelEntity;
import nl.delftelectronics.spaceinvaders.gui.GUI;

import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Optional;

/**
 * Used to display the game menu
 *
 * @author Max
 */
public class MenuScene extends GameScene implements LabelClickedListener {

	private LabelEntity startButton;
	private LabelEntity loadGameButton;
	private LabelEntity quitButton;
	private LabelEntity startMultiplayerButton;
	private LabelEntity startServerButton;
	private LabelEntity connectToServerButton;

	/**
	 * Creates a new MenuScene
	 *
	 * @param scene The javaFX scene to attach to
	 */
	public MenuScene(Scene scene) {
		super(scene);

		//CHECKSTYLE.OFF: MagicNumber - Don't want to import a layout engine
		startButton = new LabelEntity(new Rectangle2D.Double(100, 100, 500, 100), "START GAME");
		startButton.addClickedListener(this);
		addEntity(startButton);
		loadGameButton = new LabelEntity(new Rectangle2D.Double(100, 250, 500, 100), "LOAD GAME");
		loadGameButton.addClickedListener(this);
		addEntity(loadGameButton);
		quitButton = new LabelEntity(new Rectangle2D.Double(100, 400, 500, 100), "QUIT GAME");
		quitButton.addClickedListener(this);
		addEntity(quitButton);
		startMultiplayerButton = new LabelEntity(new Rectangle2D.Double(100, 550, 500, 100),
				"START LOCAL MULTIPLAYER");
		startMultiplayerButton.addClickedListener(this);
		addEntity(startMultiplayerButton);
		startServerButton = new LabelEntity(new Rectangle2D.Double(100, 700, 500, 100),
				"START SERVER");
		startServerButton.addClickedListener(this);
		addEntity(startServerButton);
		connectToServerButton = new LabelEntity(new Rectangle2D.Double(100, 850, 500, 100),
				"CONNECT TO SERVER");
		connectToServerButton.addClickedListener(this);
		addEntity(connectToServerButton);
		//CHECKSTYLE.ON: MagicNumber
	}

	/**
	 * Handles all button clicks in the scene.
	 *
	 * @param label The clicked label
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
			} else {
				loadGameButton.setText("LOAD GAME (failed)");
			}
		}
		if (label == startButton) {
			Engine engine = Engine.getInstance();
			engine.setScene(new PlayScene(engine.getScene().scene));
		}
		if (label == startMultiplayerButton) {
			Engine engine = Engine.getInstance();
			engine.setScene(new MultiplayerPlayScene(engine.getScene().scene));
		}
		if (label == startServerButton) {
			startServer();
		}
		if (label == connectToServerButton) {
			connectToServer();
		}
	}

	/**
	 * Create a server thread.
	 */
	public void startServer() {
		try {
			ServerSocket socket = new ServerSocket(GUI.serverPort);
			Engine engine = Engine.getInstance();
			OnlineMultiplayerPlayScene omPlayScene = new OnlineMultiplayerPlayScene(
					engine.getScene().scene
			);
			engine.setScene(omPlayScene);
			ServerThread serverThread = new ServerThread(socket, omPlayScene.getEntities());
			serverThread.start();
			AnimationTimer at = new ServerUpdateSceneAnimationTimer(omPlayScene, serverThread,
					GUI.FPS);
			at.start();
		} catch (IOException e) {
			Logger.error("Cannot create the server at %s:%d", GUI.serverIp, GUI.serverPort);
		}
	}

	/**
	 * Create a client thread.
	 */
	public void connectToServer() {
		String serverIp = GUI.serverIp;
		int serverPort = GUI.serverPort;

		try {
			Engine engine = Engine.getInstance();
			final OnlineMultiplayerPlayScene omPlayScene = new OnlineMultiplayerPlayScene(
					engine.getScene().scene
			);

			ClientThread clientThread = new ClientThread(serverIp, serverPort, omPlayScene);
			clientThread.start();

			engine.setScene(omPlayScene);
		} catch (IOException e) {
			Logger.error("Cannot connect to the server at %s:%d", serverIp, serverPort);
			connectToServerButton.setText("CONNECT TO SERVER (failed)");
		}
	}
}
