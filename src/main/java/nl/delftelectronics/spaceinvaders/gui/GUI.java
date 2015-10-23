package nl.delftelectronics.spaceinvaders.gui;

import java.awt.Point;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import nl.delftelectronics.spaceinvaders.core.Audio;
import nl.delftelectronics.spaceinvaders.core.Engine;
import nl.delftelectronics.spaceinvaders.core.animationtimers.UpdateSceneAnimationTimer;
import nl.delftelectronics.spaceinvaders.core.scenes.GameScene;
import nl.delftelectronics.spaceinvaders.core.scenes.MenuScene;

/**
 * The GUI is the runnable class of this game. It handles the frames and the
 * graphics.
 */
public class GUI extends Application {
	public static final int FPS = 24;
	private static final int WINDOW_WIDTH = 1680;
	private static final int WINDOW_HEIGHT = 1050;
	private static final String WINDOW_TITLE = "Space Invaders";
	private static final String DEFAULT_SERVER_IP = "127.0.0.1";
	private static final int DEFAULT_SERVER_PORT = 1255;

	public static String serverIp;
	public static int serverPort;

	private Scene scene;

	private Engine engine;

	/**
	 * Launch the game.
	 *
	 * @param args
	 *            command line arguments (not used).
	 */
	public static void main(String[] args) {
		// If the arguments (ip and port of the server are provided, use them.
		if (args.length == 2) {
			serverIp = args[0];
			serverPort = Integer.parseInt(args[1]);
		} else {
			serverIp = DEFAULT_SERVER_IP;
			serverPort = DEFAULT_SERVER_PORT;
		}
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		setWindowTitle(primaryStage, WINDOW_TITLE);
		GraphicsContext gc = initializeScene(primaryStage, WINDOW_WIDTH, WINDOW_HEIGHT);

		Audio.playBackgroundSound();

		engine = new Engine(new GameScene(scene));

		scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				engine.addClick(new Point((int) e.getX(), (int) e.getY()));
			}
		});

		listenToKeyInput();

		engine.setScene(new MenuScene(scene));

		AnimationTimer at = new UpdateSceneAnimationTimer(engine, gc, FPS, WINDOW_WIDTH,
				WINDOW_HEIGHT);
		at.start();

		primaryStage.show();
	}

	/**
	 * Listen to keyboard button presses.
	 */
	public void listenToKeyInput() {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();
				engine.keyDown(code);
			}
		});

		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();
				engine.keyUp(code);
			}
		});
	}

	/**
	 * Set the title of the window
	 *
	 * @param stage
	 *            current stage
	 * @param windowTitle
	 *            title of the window.
	 */
	public void setWindowTitle(Stage stage, String windowTitle) {
		stage.setTitle(windowTitle);
	}

	/**
	 * Initialize the scene by creating graphics context.
	 *
	 * @param stage
	 *            current stage.
	 * @param windowWidth
	 *            width of the window.
	 * @param windowHeight
	 *            height of the window.
	 * @return the new graphics context.
	 */
	public GraphicsContext initializeScene(Stage stage, int windowWidth, int windowHeight) {
		Group root = new Group();
		this.scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		stage.setScene(scene);

		Canvas canvas = new Canvas(windowWidth, windowHeight);
		root.getChildren().add(canvas);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		return gc;
	}
}
