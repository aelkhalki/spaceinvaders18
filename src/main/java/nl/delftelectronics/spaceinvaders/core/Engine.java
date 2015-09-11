package nl.delftelectronics.spaceinvaders.core;

import java.util.*;

import javafx.scene.canvas.GraphicsContext;

public class Engine {
    
    
    private static Engine instance;

    
    private long currentNanoTime = System.nanoTime();
    private long previousBulletFireTime = 0;
    private Random random = new Random();
    private int lives = 3;
    private int points = 0;
    private boolean reachedBottom = false;

    private GameScene currentScene;
    private Ship ship;

    public Engine(int fieldWidth, int fieldHeight, GameScene startScene) {
        this.currentScene = startScene;
        Engine.instance = this;
    }
    
    /**
     * Returns the last instance of the Engine
     * @return the last instance of the Engine
     */
    public static Engine getInstance() {
    	return instance;
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
        currentScene = new PlayScene(currentScene.scene, shipWidth, shipHeight, enemyWidth, enemyHeight);
    }

    public ArrayList<Enemy> createEnemies(int enemyWidth, int enemyHeight) {
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        return enemies;
    }

    public void playerShootBullet() {
        currentNanoTime = System.nanoTime();
        if (currentNanoTime - previousBulletFireTime > 1000000000.0) {
            previousBulletFireTime = currentNanoTime;
            Bullet bullet = ship.shoot();
            currentScene.addEntity(bullet);
        }
    }

    public void playerShootBomb() {
        currentNanoTime = System.nanoTime();
        if (currentNanoTime - previousBulletFireTime > 1000000000.0) {
            previousBulletFireTime = currentNanoTime;
            Bomb bomb = ship.shootBomb();
            currentScene.addEntity(bomb);
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
    }
    
    /*
    public void updatePlayerBullets(Collection<Enemy> enemies) {
        Set<Enemy> enemiesToRemove = new HashSet<Enemy>();
        Iterator<Bullet> playerProjectileIterator = playerBullets.iterator();
        while (playerProjectileIterator.hasNext()) {
            Bullet playerProjectile = playerProjectileIterator.next();
            playerProjectile.updatePosition();
            Iterator<Enemy> enemyIterator = enemies.iterator();
            boolean intersection = false;
            Set<Enemy> enemiesInRadiusOfThisBullet = new HashSet<Enemy>();
            while (enemyIterator.hasNext()) {
                Enemy enemy = enemyIterator.next();
                boolean inRadius = playerProjectile.impactArea().intersects(enemy.getBoundingBox());
                if (playerProjectile.intersects(enemy)) {
                    intersection = true;
                    enemiesToRemove.addAll(enemiesInRadiusOfThisBullet);
                }
                if (inRadius && !intersection) {
                    // This enemy within in the impact radius of the bullet. Should the bullet
                    // actually hit an enemy, then the enemy will be removed.
                    enemiesInRadiusOfThisBullet.add(enemy);
                }
                if (inRadius && intersection) {
                    // This enemy within in the impact radius of the bullet, and the bullet has
                    // hit an enemy, so this enemy should disappear.
                    enemiesToRemove.add(enemy);
                }
            }
            if (intersection) {
                playerProjectileIterator.remove();
                removedEntities.add(playerProjectile);
                playerProjectile.destroy();
            }
        }
        for (Enemy enemy : enemiesToRemove) {
            points += enemy.getPoints();
            removedEntities.add(enemy);
            enemies.remove(enemy);
            enemy.destroy();
        }
    }*/

    public void draw(GraphicsContext gc) {
        currentScene.draw(gc);
    }

    public boolean isInProgress() {
        return !reachedBottom && lives > 0;
    }
}
