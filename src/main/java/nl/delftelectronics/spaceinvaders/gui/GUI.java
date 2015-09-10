package nl.delftelectronics.spaceinvaders.gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import nl.delftelectronics.spaceinvaders.core.Engine;
import nl.delftelectronics.spaceinvaders.core.GameScene;
import nl.delftelectronics.spaceinvaders.core.DrawableEntity;
import nl.delftelectronics.spaceinvaders.core.Actor;
import nl.delftelectronics.spaceinvaders.core.AutomaticMovable;
import nl.delftelectronics.spaceinvaders.core.BoundaryReachedException;
import nl.delftelectronics.spaceinvaders.core.Bullet;
import nl.delftelectronics.spaceinvaders.core.Direction;
import nl.delftelectronics.spaceinvaders.core.Enemy;
import nl.delftelectronics.spaceinvaders.core.DrawableEntity;
import nl.delftelectronics.spaceinvaders.core.LargeEnemy;
import nl.delftelectronics.spaceinvaders.core.MediumEnemy;
import nl.delftelectronics.spaceinvaders.core.Ship;
import nl.delftelectronics.spaceinvaders.core.SmallEnemy;
import nl.delftelectronics.spaceinvaders.core.EnemyReachedBottomException;

public class GUI extends Application {
    private static final Integer FPS = 24;
    private static final Integer WINDOW_WIDTH = 1680;
    private static final Integer WINDOW_HEIGHT = 1050;
    private static final String WINDOW_TITLE = "Space Invaders";

    private static final String SHIP_FILENAME = "/ship.png";
    private static final String BULLET_FILENAME = "/ufo.png";
    private static final String SMALL_ENEMY_FILENAME = "/small_enemy.png";
    private static final String MEDIUM_ENEMY_FILENAME = "/medium_enemy.png";
    private static final String LARGE_ENEMY_FILENAME = "/large_enemy.png";

    private Scene scene;
    private ArrayList<String> inputs = new ArrayList<String>();
    private HashMap<DrawableEntity, Sprite> sprites = new HashMap<DrawableEntity, Sprite>();

    private GraphicsContext gc;
    private ArrayList<String> input;
    private Actor shipActor;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        setWindowTitle(primaryStage, WINDOW_TITLE);
        final GraphicsContext gc = initializeScene(primaryStage, WINDOW_WIDTH, WINDOW_HEIGHT);

        final Engine engine = new Engine(WINDOW_WIDTH, WINDOW_HEIGHT, new GameScene(scene));

        gc.setFill(Color.RED);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        Font font = Font.font("Arial", FontWeight.BOLD, 48);
        gc.setFont(font);


        gc.fillText("START GAME", 100, 100);
        gc.fillText("QUIT GAME", 100, 500);
        final Rectangle startGameArea = new Rectangle(0, 0, 500, 100);
        final Rectangle quitGameArea = new Rectangle(0, 400, 500, 100);

        final boolean[] started = { false };

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

        engine.startGame(100, 100, 100, 100);
        addEntities(engine.getAddedEntities());

        new AnimationTimer() {
            private Long previousNanoTime = System.nanoTime();

            public void handle(long currentNanoTime) {
                if (!started[0]) {
                    return;
                } else {}
                double elapsedTime = (currentNanoTime - previousNanoTime) / 1000000000.0;
                if (elapsedTime < 1 / FPS) {
                    return;
                } else {
                    previousNanoTime = currentNanoTime;
                }

                engine.update();


                if (inputs.contains("LEFT")) {
                    engine.playerMoveLeft();
                }
                if (inputs.contains("RIGHT")) {
                    engine.playerMoveRight();
                }

                if (inputs.contains("SPACE")) {
                    engine.playerShootBullet();
                }

                addEntities(engine.getAddedEntities());
                removeEntities(engine.getRemovedEntities());

                engine.clearChangedEntities();

                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
                gc.setFill(Color.RED);
                //for (Sprite sprite : sprites.values()) {
                //    sprite.render(gc);
                //}
                
                engine.draw(gc);
                
                gc.fillText(String.format("Lives: %d", engine.getLives()), 60, 50);
                gc.fillText(String.format("Points: %d", engine.getPoints()), 900, 50);

                if (!engine.isInProgress()) {
                    gc.fillText("GAME OVER!", 600, 500);
                    gc.strokeText("GAME OVER!", 600, 500);
                    stop();
                }
            }
        }.start();

        primaryStage.show();
    }

    public void listenToKeyInput(Scene scene, final Collection<String> input) {
        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        if (!input.contains(code))
                            input.add(code);
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

    public void setWindowTitle(Stage stage, String windowTitle) {
        stage.setTitle(windowTitle);
    }

    public GraphicsContext initializeScene(Stage stage, int windowWidth, int windowHeight) {
        Group root = new Group();
        this.scene = new Scene(root);
        stage.setScene(scene);

        Canvas canvas = new Canvas(windowWidth, windowHeight);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        return gc;
    }

    public Sprite createSprite(DrawableEntity entity, String imageFilename) {
        Image image = new Image(imageFilename);
        Sprite sprite = new Sprite(entity, image);
        sprites.put(entity, sprite);
        return sprite;
    }

    public void addEntities(Collection<DrawableEntity> entities) {
        for (DrawableEntity entity : entities) {
            createSprite(entity, "/ship.png");
        }
    }

    public void removeEntities(Collection<DrawableEntity> entities) {
        for (DrawableEntity entity : entities) {
            sprites.remove(entity);
        }
    }
}
