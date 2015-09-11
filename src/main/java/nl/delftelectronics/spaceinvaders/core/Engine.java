package nl.delftelectronics.spaceinvaders.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;

public class Engine {
    private static final int SMALL_ENEMY_ROWS = 1;
    private static final int MEDIUM_ENEMY_ROWS = 2;
    private static final int LARGE_ENEMY_ROWS = 2;
    private static final int ENEMY_COLUMNS = 12;
    private static final double SHIP_MARGIN_FROM_LEFT = 5 / 100.0;
    private static final double SHIP_MARGIN_FROM_BOTTOM = 10 / 100.0;

    private int fieldWidth;
    private int fieldHeight;
    private long currentNanoTime = System.nanoTime();
    private long previousBulletFireTime = 0;
    private Random random = new Random();
    private int lives = 3;
    private int points = 0;
    private boolean reachedBottom = false;
    
    private GameScene currentScene;

    private Ship ship;
    private ArrayList<Enemy> enemies;
    private ArrayList<Bullet> playerBullets;
    private ArrayList<Bullet> enemyBullets;

    private ArrayList<DrawableEntity> addedEntities = new ArrayList<DrawableEntity>();
    private ArrayList<DrawableEntity> removedEntities = new ArrayList<DrawableEntity>();

    public Engine(int fieldWidth, int fieldHeight, GameScene startScene) {
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
        this.currentScene = startScene;

        this.playerBullets = new ArrayList<Bullet>();
        this.enemyBullets = new ArrayList<Bullet>();
    }

    public Ship getShip() {
        return ship;
    }

    public int getLives() {
        return lives;
    }

    public int getPoints() {
        return points;
    }

    public void startGame(int shipWidth, int shipHeight, int enemyWidth, int enemyHeight) {
        int shipPositionX = (int) (fieldWidth * SHIP_MARGIN_FROM_LEFT);
        int shipPositionY = (int) (fieldHeight * (1 - SHIP_MARGIN_FROM_BOTTOM));

        this.ship = new Ship(shipPositionX, shipPositionY, shipWidth, shipHeight);
        addedEntities.add(ship);
        currentScene.addEntity(this.ship);
        this.enemies = createEnemies(enemyWidth, enemyHeight);
        
        for(Enemy e : this.enemies) {
        	currentScene.addEntity(e);
        }
    }

    public ArrayList<Enemy> createEnemies(int enemyWidth, int enemyHeight) {
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        for (int column = 0; column < ENEMY_COLUMNS; column++) {
            int currentRow = 0;
            for (int smallEnemyRow = 0; smallEnemyRow < SMALL_ENEMY_ROWS; smallEnemyRow++) {
                Enemy smallEnemy = new SmallEnemy(10 + 100 * column, 90 * currentRow, enemyWidth, enemyHeight, 0,
                        fieldWidth, fieldHeight);
                enemies.add(smallEnemy);
                currentRow++;
                addedEntities.add(smallEnemy);
            }
            for (int mediumEnemyRow = 0; mediumEnemyRow < MEDIUM_ENEMY_ROWS; mediumEnemyRow++) {
                Enemy mediumEnemy = new MediumEnemy(10 + 100 * column, 90 * currentRow, enemyWidth, enemyHeight, 0,
                        fieldWidth, fieldHeight);
                enemies.add(mediumEnemy);
                currentRow++;
                addedEntities.add(mediumEnemy);
            }
            for (int largeEnemyRow = 0; largeEnemyRow < LARGE_ENEMY_ROWS; largeEnemyRow++) {
                Enemy largeEnemy = new LargeEnemy(10 + 100 * column, 90 * currentRow, enemyWidth, enemyHeight, 0,
                        fieldWidth, fieldHeight);
                enemies.add(largeEnemy);
                currentRow++;
                addedEntities.add(largeEnemy);
            }
        }
        return enemies;
    }

    public void playerShootBullet() {
        currentNanoTime = System.nanoTime();
        if (currentNanoTime - previousBulletFireTime > 1000000000.0) {
            previousBulletFireTime = currentNanoTime;
            Bullet bullet = ship.shoot();
            playerBullets.add(bullet);
            addedEntities.add(bullet);
            currentScene.addEntity(bullet);
        }
    }

    public void playerMoveLeft() {
        ship.moveLeft();
    }

    public void playerMoveRight() {
        ship.moveRight();
    }

    public void update() {
    	currentScene.update();
    	
        boolean moveDown = false;

        for (Enemy enemy : enemies) {
            try {
                enemy.updatePosition();
                if (random.nextDouble() < 0.0001) {
                    Bullet enemyBullet = new Bullet(enemy.getPositionX(), enemy.getPositionY(), 3, 10,
                            Direction.SOUTH);
                    enemyBullets.add(enemyBullet);
                    addedEntities.add(enemyBullet);
                    currentScene.addEntity(enemyBullet);
                }
            } catch (BoundaryReachedException e) {
                moveDown = true;
            } catch (EnemyReachedBottomException e) {
                reachedBottom = true;
            }
        }
        if (moveDown) {
            for (Enemy enemy : enemies) {
                enemy.moveDown();
            }
        }

        Iterator<Bullet> playerBulletIterator = playerBullets.iterator();
        while (playerBulletIterator.hasNext()) {
            Bullet playerBullet = playerBulletIterator.next();
            Iterator<Enemy> enemyIterator = enemies.iterator();
            while (enemyIterator.hasNext()) {
                Enemy enemy = enemyIterator.next();
                if (playerBullet.intersects(enemy)) {
                    playerBulletIterator.remove();
                    enemyIterator.remove();
                    points += enemy.getPoints();
                    removedEntities.add(playerBullet);
                    removedEntities.add(enemy);
                    enemy.destroy();
                    playerBullet.destroy();
                }
            }
        }

        Iterator<Bullet> enemyBulletIterator = enemyBullets.iterator();
        while (enemyBulletIterator.hasNext()) {
            Bullet enemyBullet = enemyBulletIterator.next();
            if (enemyBullet.intersects(ship)) {
                enemyBulletIterator.remove();
                lives--;
                removedEntities.add(enemyBullet);
                enemyBullet.destroy();
            }
        }
    }
    
    public void draw(GraphicsContext gc) {
    	currentScene.draw(gc);
    }

    public void clearChangedEntities() {
        addedEntities.clear();
        removedEntities.clear();
    }

    public Collection<DrawableEntity> getAddedEntities() {
        return addedEntities;
    }

    public Collection<DrawableEntity> getRemovedEntities() {
        return removedEntities;
    }

    public boolean isInProgress() {
        return !reachedBottom && lives > 0 && !enemies.isEmpty();
    }
}
