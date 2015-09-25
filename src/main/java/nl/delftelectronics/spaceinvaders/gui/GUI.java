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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import nl.delftelectronics.spaceinvaders.core.Audio;
import nl.delftelectronics.spaceinvaders.core.Engine;
import nl.delftelectronics.spaceinvaders.core.Logger;
import nl.delftelectronics.spaceinvaders.core.Logger.LogLevel;
import nl.delftelectronics.spaceinvaders.core.scenes.GameScene;
import nl.delftelectronics.spaceinvaders.core.scenes.MenuScene;

/**
 * The GUI is the runnable class of this game. It handles the frames and the
 * graphics.
 */
public class GUI extends Application {
	private static final Integer FPS = 24;
	private static final Integer WINDOW_WIDTH = 1680;
	private static final Integer WINDOW_HEIGHT = 1050;
	private static final String WINDOW_TITLE = "Space Invaders";
	private static final double SECOND = 1000000000.0;

	private Scene scene;

	private GraphicsContext gc;
	private Engine engine;

	/**
	 * Launch the game.
	 *
	 * @param args
	 *            command line arguments (not used).
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		setWindowTitle(primaryStage, WINDOW_TITLE);
		gc = initializeScene(primaryStage, WINDOW_WIDTH, WINDOW_HEIGHT);

		Audio.playBackgroundSound();

		engine = new Engine(new GameScene(scene));

		scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				engine.addClick(new Point((int) e.getX(), (int) e.getY()));
			}
		});

		listenToKeyInput();

		engine.setScene(new MenuScene(scene));

		AnimationTimer at = createAnimationTimer(engine, gc);
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

	/**
	 * Create the animation timer that shows the game to the window.
	 *
	 * @param engine
	 *            the engine containing game logic.
	 * @param gc
	 *            the graphics context.
	 * @return the animation timer that shows the game to the window.
	 */
	public AnimationTimer createAnimationTimer(final Engine engine, final GraphicsContext gc) {
		return new AnimationTimer() {
			private Long previousNanoTime = System.nanoTime();
			final int[] livesStatusPosition = { 60, 50 };
			final int[] pointsStatusPosition = { 900, 50 };
			final int[] gameOverPosition = { 600, 500 };

			public void handle(long currentNanoTime) {
				double elapsedTime = (currentNanoTime - previousNanoTime) / SECOND;
				if (elapsedTime < 1 / (double) FPS) {
					previousNanoTime = currentNanoTime;
					return;
				}
				engine.update();

				gc.setFill(Color.BLACK);
				gc.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
				gc.setFill(Color.RED);

				engine.draw(gc);
			}
		};
	}
}
