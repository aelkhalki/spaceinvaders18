package nl.delftelectronics.spaceinvaders.gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import nl.delftelectronics.spaceinvaders.core.Actor;
import nl.delftelectronics.spaceinvaders.core.AutomaticMovable;
import nl.delftelectronics.spaceinvaders.core.BoundaryReachedException;
import nl.delftelectronics.spaceinvaders.core.Bullet;
import nl.delftelectronics.spaceinvaders.core.Direction;
import nl.delftelectronics.spaceinvaders.core.Enemy;
import nl.delftelectronics.spaceinvaders.core.Entity;
import nl.delftelectronics.spaceinvaders.core.LargeEnemy;
import nl.delftelectronics.spaceinvaders.core.MediumEnemy;
import nl.delftelectronics.spaceinvaders.core.Ship;
import nl.delftelectronics.spaceinvaders.core.SmallEnemy;
import nl.delftelectronics.spaceinvaders.core.EnemyReachedBottomException;

public class GUI extends Application {
    private static final Integer FPS = 24;
    private static final Integer WINDOW_WIDTH = 1680;
    private static final Integer WINDOW_HEIGHT = 1050;
    private static final Integer SMALL_ENEMY_ROWS = 1;
    private static final Integer MEDIUM_ENEMY_ROWS = 2;
    private static final Integer LARGE_ENEMY_ROWS = 2;
    private static final Integer ENEMY_COLUMNS = 12;
    private static final Double SHIP_MARGIN_FROM_LEFT = 5 / 100.0;
    private static final Double SHIP_MARGIN_FROM_BOTTOM = 10 / 100.0;
    private static final String WINDOW_TITLE = "Space Invaders";

    private static final String SHIP_FILENAME = "/resources/ship.png";
    private static final String BULLET_FILENAME = "/resources/ufo.png";
    private static final String SMALL_ENEMY_FILENAME = "/resources/small_enemy.png";
    private static final String MEDIUM_ENEMY_FILENAME = "/resources/medium_enemy.png";
    private static final String LARGE_ENEMY_FILENAME = "/resources/large_enemy.png";

    private Collection<Sprite> sprites = new ArrayList<Sprite>();
    private List<Sprite> enemies = new ArrayList<Sprite>();
    private Collection<AutomaticMovable> npcs = new ArrayList<AutomaticMovable>();
    private Collection<Sprite> bullets = new ArrayList<Sprite>();
    private Collection<Sprite> enemyBullets = new ArrayList<Sprite>();

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

        Image shipImage = new Image(SHIP_FILENAME);
        final Ship shipActor = new Ship(shipPositionX, shipPositionY);

        final Sprite ship = new Sprite(shipActor, shipImage);
        sprites.add(ship);

        Image smallEnemyImage = new Image(SMALL_ENEMY_FILENAME);
        Image mediumEnemyImage = new Image(MEDIUM_ENEMY_FILENAME);
         Image largeEnemyImage = new Image(LARGE_ENEMY_FILENAME);
        for (int column = 0; column < ENEMY_COLUMNS; column++) {
            for (int smallEnemyRow = 0; smallEnemyRow < SMALL_ENEMY_ROWS; smallEnemyRow++) {
                int row = smallEnemyRow;
                Enemy smallEnemyActor = new SmallEnemy(10 + 100 * column, 90 * row, 0, WINDOW_WIDTH,
                        WINDOW_HEIGHT);
                Sprite smallEnemy = new Sprite(smallEnemyActor, smallEnemyImage);
                sprites.add(smallEnemy);
                enemies.add(smallEnemy);
                npcs.add(smallEnemyActor);
            }
            for (int mediumEnemyRow = 0; mediumEnemyRow < MEDIUM_ENEMY_ROWS; mediumEnemyRow++) {
                int row = SMALL_ENEMY_ROWS + mediumEnemyRow;
                Enemy mediumEnemyActor = new MediumEnemy(10 + 100 * column, 90 * row, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
                Sprite mediumEnemy = new Sprite(mediumEnemyActor, mediumEnemyImage);
                sprites.add(mediumEnemy);
                enemies.add(mediumEnemy);
                npcs.add(mediumEnemyActor);
            }
            for (int largeEnemyRow = 0; largeEnemyRow < LARGE_ENEMY_ROWS; largeEnemyRow++) {
                int row = SMALL_ENEMY_ROWS + MEDIUM_ENEMY_ROWS + largeEnemyRow;
                Enemy largeEnemyActor = new LargeEnemy(10 + 100 * column, 90 * row, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
                Sprite largeEnemy = new Sprite(largeEnemyActor, largeEnemyImage);
                sprites.add(largeEnemy);
                enemies.add(largeEnemy);
                npcs.add(largeEnemyActor);
            }
        }

        final Image bulletImage = new Image(BULLET_FILENAME);

        gc.setFill(Color.RED);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        Font font = Font.font("Arial", FontWeight.BOLD, 48);
        gc.setFont(font);

        final Rectangle2D startGame = new Rectangle2D(0, 0, 500, 100);
        final Rectangle2D quitGame = new Rectangle2D(0, 400, 500, 100);

        final boolean[] started = { false };

        scene.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent e) {
                        if (startGame.contains(e.getX(), e.getY())) {
                            started[0] = true;
                        } else if (quitGame.contains(e.getX(), e.getY())) {
                            System.exit(0);
                        }
                    }
                });

        new AnimationTimer() {
            private Long previousNanoTime = System.nanoTime();
            private Long previousBulletFireTime = System.nanoTime();
            private Random random = new Random();
            private Integer lives = 3;
            private Integer points = 0;

            public void handle(long currentNanoTime) {
                if (!started[0]) {
                    gc.fillText("START GAME", 100, 100);
                    gc.fillText("QUIT GAME", 100, 500);
                    return;
                }
                double elapsedTime = (currentNanoTime - previousNanoTime) / 1000000000.0;
                if (elapsedTime < 1 / FPS) {
                    return;
                } else {
                    previousNanoTime = currentNanoTime;
                }

                boolean moveDown = false;
                boolean endGame = false;

                for (AutomaticMovable npc : npcs) {
                    try {
                        npc.updatePosition();
                    } catch (BoundaryReachedException e) {
                        moveDown = true;
                    } catch (EnemyReachedBottomException e) {
                        endGame = true;
                    }
                }

                if (moveDown) {
                    for (Sprite enemySprite : enemies) {
                        Enemy enemy = (Enemy) enemySprite.getEntity();
                        enemy.moveDown();
                    }
                }

                for (Sprite enemySprite : enemies) {
                    if (random.nextDouble() < 0.00005) {
                        Entity enemy = enemySprite.getEntity();
                        Bullet enemyBullet = new Bullet(enemy.getPositionX(), enemy.getPositionY(),
                        		Direction.SOUTH);
                        Sprite enemyBulletSprite = new Sprite(enemyBullet, bulletImage);
                        sprites.add(enemyBulletSprite);
                        enemyBullets.add(enemyBulletSprite);
                        npcs.add(enemyBullet);
                    }
                }
                Iterator<Sprite> enemyBulletIterator = enemyBullets.iterator();
                while (enemyBulletIterator.hasNext()) {
                    Sprite bullet = enemyBulletIterator.next();
                    if (bullet.intersects(ship)) {
                        enemyBulletIterator.remove();
                        sprites.remove(bullet);
                        npcs.remove(bullet);
                        lives--;
                    }
                }
                Iterator<Sprite> bulletIterator = bullets.iterator();
                while (bulletIterator.hasNext()) {
                    Sprite bullet = bulletIterator.next();
                    Iterator<Sprite> enemyIterator = enemies.iterator();
                    while (enemyIterator.hasNext()) {
                        Sprite enemySprite = enemyIterator.next();
                        if (bullet.intersects(enemySprite)) {
                            Enemy enemy = (Enemy) enemySprite.getEntity();
                            bulletIterator.remove();
                            enemyIterator.remove();
                            npcs.remove(enemy);
                            npcs.remove(bullet.getEntity());
                            sprites.remove(enemySprite);
                            sprites.remove(bullet);
                            points += enemy.getPoints();
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

                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
                gc.setFill(Color.RED);
                for (Sprite sprite : sprites) {
                    sprite.render(gc);
                }
                gc.fillText(String.format("Lives: %d", lives), 60, 50);
                gc.fillText(String.format("Points: %d", points), 900, 50);

                if (lives <= 0 || enemies.isEmpty() || endGame) {
                    gc.fillText("GAME OVER!", 600, 500);
                    gc.strokeText("GAME OVER!", 600, 500);
                    stop();
                }
            }
        }.start();

        primaryStage.show();
    }
}
