package nl.delftelectronics.spaceinvaders.gui;

import java.util.ArrayList;
import java.util.Collection;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import nl.delftelectronics.spaceinvaders.core.Engine;
import nl.delftelectronics.spaceinvaders.core.GameScene;

/**
 * The GUI is the runnable class of this game. It handles the frames and the graphics.
 */
public class GUI extends Application {
    private static final Integer FPS = 24;
    private static final Integer WINDOW_WIDTH = 1680;
    private static final Integer WINDOW_HEIGHT = 1050;
    private static final String WINDOW_TITLE = "Space Invaders";
    private static final Integer ENTITY_DIMENSION = 100;
    private static final double SECOND = 1000000000.0;
    private static final Integer START_GAME_AREA_X = 0;
    private static final Integer START_GAME_AREA_Y = 0;
    private static final Integer START_GAME_AREA_WIDTH = 500;
    private static final Integer START_GAME_AREA_HEIGHT = 100;
    private static final Integer QUIT_GAME_AREA_X = 0;
    private static final Integer QUIT_GAME_AREA_Y = 400;
    private static final Integer QUIT_GAME_AREA_WIDTH = 500;
    private static final Integer QUIT_GAME_AREA_HEIGHT = 100;

    private Scene scene;
    private ArrayList<String> inputs = new ArrayList<String>();

    /**
     * Launch the game.
     *
     * @param args command line arguments (not used).
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        setWindowTitle(primaryStage, WINDOW_TITLE);
        final GraphicsContext gc = initializeScene(primaryStage, WINDOW_WIDTH, WINDOW_HEIGHT);
        final Engine engine = new Engine(WINDOW_WIDTH, WINDOW_HEIGHT, new GameScene(scene));

        final int lineWidth = 2;
        final int fontSize = 48;
        gc.setFill(Color.RED);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(lineWidth);
        Font font = Font.font("Arial", FontWeight.BOLD, fontSize);
        gc.setFont(font);

        final int[] startGameCoordinates = {100, 100};
        final int[] quitGameCoordinates = {100, 500};
        gc.fillText("START GAME", startGameCoordinates[0], startGameCoordinates[1]);
        gc.fillText("QUIT GAME", quitGameCoordinates[0], quitGameCoordinates[1]);

        final Rectangle startGameArea = new Rectangle(START_GAME_AREA_X, START_GAME_AREA_Y,
                START_GAME_AREA_WIDTH, START_GAME_AREA_HEIGHT);

        final Rectangle quitGameArea = new Rectangle(QUIT_GAME_AREA_X, QUIT_GAME_AREA_Y,
                QUIT_GAME_AREA_WIDTH, QUIT_GAME_AREA_HEIGHT);

        final boolean[] started = {false};

        scene.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent e) {
                        if (startGameArea.contains(e.getX(), e.getY())) {
                            started[0] = true;
                        } else if (quitGameArea.contains(e.getX(), e.getY())) {
                            System.exit(0);
                        }
                    }
                });

        listenToKeyInput(scene, inputs);

        engine.startGame(ENTITY_DIMENSION, ENTITY_DIMENSION, ENTITY_DIMENSION, ENTITY_DIMENSION);
        AnimationTimer at = createAnimationTimer(started, engine, gc);
        at.start();

        primaryStage.show();
    }

    /**
     * Listen to keyboard button presses.
     *
     * @param scene current scene.
     * @param input collection of input.
     */
    public void listenToKeyInput(Scene scene, final Collection<String> input) {
        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        if (!input.contains(code)) {
                            input.add(code);
                        }
                    }
                });

        scene.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        input.remove(code);
                    }
                });
    }

    /**
     * Set the title of the window
     *
     * @param stage       current stage
     * @param windowTitle title of the window.
     */
    public void setWindowTitle(Stage stage, String windowTitle) {
        stage.setTitle(windowTitle);
    }

    /**
     * Initialize the scene by creating graphics context.
     *
     * @param stage        current stage.
     * @param windowWidth  width of the window.
     * @param windowHeight height of the window.
     * @return the new graphics context.
     */
    public GraphicsContext initializeScene(Stage stage, int windowWidth, int windowHeight) {
        Group root = new Group();
        this.scene = new Scene(root);
        stage.setScene(scene);

        Canvas canvas = new Canvas(windowWidth, windowHeight);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        return gc;
    }

    /**
     * Create the animation timer that shows the game to the window.
     *
     * @param started pointer to a boolean which signals if the game has started
     * @param engine  the engine containing game logic.
     * @param gc      the graphics context.
     * @return the animation timer that shows the game to the window.
     */
    public AnimationTimer createAnimationTimer(final boolean[] started, final Engine engine,
                                               final GraphicsContext gc) {
        return new AnimationTimer() {
            private Long previousNanoTime = System.nanoTime();
            final int[] livesStatusPosition = {60, 50};
            final int[] pointsStatusPosition = {900, 50};
            final int[] gameOverPosition = {600, 500};

            public void handle(long currentNanoTime) {
                double elapsedTime = (currentNanoTime - previousNanoTime) / SECOND;
                if (!started[0] || elapsedTime < 1 / FPS) {
                    previousNanoTime = currentNanoTime;
                    return;
                }
                engine.update();

                handleInput(engine);

                engine.clearChangedEntities();

                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
                gc.setFill(Color.RED);

                engine.draw(gc);

                gc.fillText(String.format("Lives: %d", engine.getLives()), livesStatusPosition[0],
                        livesStatusPosition[1]);
                gc.fillText(String.format("Points: %d", engine.getPoints()),
                        pointsStatusPosition[0], pointsStatusPosition[1]);
                if (!engine.isInProgress()) {
                    gc.fillText("GAME OVER!", gameOverPosition[0], gameOverPosition[1]);
                    gc.strokeText("GAME OVER!", gameOverPosition[0], gameOverPosition[1]);
                    stop();
                }
            }
        };
    }

    /**
     * Perform the correct action based on the input.
     *
     * @param engine the engine containing the game logic.
     */
    public void handleInput(Engine engine) {
        if (inputs.contains("LEFT")) {
            engine.playerMoveLeft();
        }
        if (inputs.contains("RIGHT")) {
            engine.playerMoveRight();
        }
        if (inputs.contains("SPACE")) {
            engine.playerShootBullet();
        }
        if (inputs.contains("X")) {
            engine.playerShootBomb();
        }
    }
}

