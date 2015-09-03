package spaceinvaders.gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
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
import spaceinvaders.core.AutomaticMovable;
import spaceinvaders.core.Bullet;
import spaceinvaders.core.Enemy;
import spaceinvaders.core.MediumEnemy;
import spaceinvaders.core.Ship;

public class GUI extends Application {
    private static final Integer FPS = 24;
    private static final Integer WINDOW_WIDTH = 1680;
    private static final Integer WINDOW_HEIGHT = 1050;
    private static final Integer ENEMY_ROWS = 5;
    private static final Integer ENEMY_COLUMNS = 12;
    private static final Double SHIP_MARGIN_FROM_LEFT = 5 / 100.0;
    private static final Double SHIP_MARGIN_FROM_BOTTOM = 10 / 100.0;
    private static final String WINDOW_TITLE = "Space Invaders";
    private static final String SHIP_FILENAME = "spaceinvaders/gui/resources/ship.png";
    private static final String BULLET_FILENAME = "spaceinvaders/gui/resources/medium_enemy.png";
    private static final String MEDIUM_ENEMY_FILENAME = "spaceinvaders/gui/resources/medium_enemy.png";

    private Collection<Sprite> sprites = new ArrayList<Sprite>();
    private List<Sprite> enemies = new ArrayList<Sprite>();
    private Collection<AutomaticMovable> npcs = new ArrayList<AutomaticMovable>();
    private Collection<Sprite> bullets = new ArrayList<Sprite>();

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

        ArrayList<String> input = new ArrayList<String>();

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

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Integer shipPositionX = (int) (WINDOW_WIDTH * SHIP_MARGIN_FROM_LEFT);
        Integer shipPositionY = (int) (WINDOW_HEIGHT * (1 - SHIP_MARGIN_FROM_BOTTOM));

        Image shipImage = new Image(SHIP_FILENAME);
        Ship shipActor = new Ship(shipPositionX, shipPositionY);
        Sprite ship = new Sprite(shipActor, shipImage);
        sprites.add(ship);

        Image mediumEnemyImage = new Image(MEDIUM_ENEMY_FILENAME);
        for (int row = 0; row < ENEMY_ROWS; row++) {
            for (int column = 0; column < ENEMY_COLUMNS; column++) {
                Enemy mediumEnemyActor = new MediumEnemy(10 + 75 * column, 40 * row, 0, WINDOW_WIDTH);
                Sprite mediumEnemy = new Sprite(mediumEnemyActor, mediumEnemyImage);
                sprites.add(mediumEnemy);
                enemies.add(mediumEnemy);
                npcs.add(mediumEnemyActor);
            }
        }

        Image bulletImage = new Image(BULLET_FILENAME);

        new AnimationTimer() {
            private Long previousNanoTime = System.nanoTime();
            private Long previousBulletFireTime = System.nanoTime();

            public void handle(long currentNanoTime) {
                double elapsedTime = (currentNanoTime - previousNanoTime) / 1000000000.0;
                if (elapsedTime < 1 / FPS) {
                    return;
                } else {
                    previousNanoTime = currentNanoTime;
                }

                for (AutomaticMovable npc : npcs) {
                    npc.updatePosition();
                }

                Iterator<Sprite> bulletIterator = bullets.iterator();
                while (bulletIterator.hasNext()) {
                    Sprite bullet = bulletIterator.next();
                    Iterator<Sprite> enemyIterator = enemies.iterator();
                    while (enemyIterator.hasNext()) {
                        Sprite enemy = enemyIterator.next();
                        if (bullet.intersects(enemy)) {
                            bulletIterator.remove();
                            enemyIterator.remove();
                            npcs.remove(enemy.getEntity());
                            npcs.remove(bullet.getEntity());
                            sprites.remove(enemy);
                            sprites.remove(bullet);
                        }
                    }
                }

                if (input.contains("LEFT")) {
                    shipActor.moveLeft();
                }
                if (input.contains("RIGHT")) {
                    shipActor.moveRight();
                }
                if (input.contains("SPACE")) {
                    if (currentNanoTime - previousBulletFireTime > 1000000000.0) {
                        previousBulletFireTime = currentNanoTime;
                        Bullet bullet = shipActor.shoot();
                        Sprite bulletSprite = new Sprite(bullet, bulletImage);
                        sprites.add(bulletSprite);
                        npcs.add(bullet);
                        bullets.add(bulletSprite);
                    }
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
