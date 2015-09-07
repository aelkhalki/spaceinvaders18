package nl.delftelectronics.spaceinvaders.gui;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import nl.delftelectronics.spaceinvaders.core.Actor;
import nl.delftelectronics.spaceinvaders.core.Ship;

public class GUI extends Application {
    private static final Integer FPS = 24;
    private static final Integer WINDOW_WIDTH = 1024;
    private static final Integer WINDOW_HEIGHT = 768;
    private static final Double SHIP_MARGIN_FROM_LEFT = 5 / 100.0;
    private static final Double SHIP_MARGIN_FROM_BOTTOM = 10 / 100.0;
    private static final String WINDOW_TITLE = "Space Invaders";
    private static final String SHIP_FILENAME = "resources/ship.png";

    private ArrayList<Sprite> sprites = new ArrayList<Sprite>();

    private GraphicsContext gc;
    private ArrayList<String> input;
    private Actor shipActor;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle(WINDOW_TITLE);

        Group root = new Group();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        Canvas canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
        root.getChildren().add(canvas);

        input = new ArrayList<String>();

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

        gc = canvas.getGraphicsContext2D();

        Integer shipPositionX = (int) (WINDOW_WIDTH * SHIP_MARGIN_FROM_LEFT);
        Integer shipPositionY = (int) (WINDOW_HEIGHT * (1 - SHIP_MARGIN_FROM_BOTTOM));

        Image shipImage = new Image(getClass().getClassLoader().getResourceAsStream(SHIP_FILENAME));
        shipActor = new Ship(shipPositionX, shipPositionY);
        Sprite ship = new Sprite(shipActor, shipImage);
        sprites.add(ship);

        new AnimationTimer() {
            private Long previousNanoTime = System.nanoTime();

            public void handle(long currentNanoTime) {
                double elapsedTime = (currentNanoTime - previousNanoTime) / 1000000000.0;
                if (elapsedTime < 1 / FPS) {
                    return;
                } else {
                    previousNanoTime = currentNanoTime;
                }
                // render
                if (input.contains("LEFT")) {
                    shipActor.moveLeft();
                } else if (input.contains("RIGHT")) {
                    shipActor.moveRight();
                }
                gc.clearRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

                for (Sprite sprite : sprites) {
                    sprite.render(gc);
                }
            }
        }.start();

        primaryStage.show();
    }
}
