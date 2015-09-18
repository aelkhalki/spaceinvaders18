package nl.delftelectronics.spaceinvaders.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import javafx.scene.canvas.GraphicsContext;
import com.google.common.collect.Iterators;

/**
 * The Engine is the main class of this game. The most of the game logic is performed in this class.
 */
public class Engine {
    private static final int SMALL_ENEMY_ROWS = 1;
    private static final int MEDIUM_ENEMY_ROWS = 2;
    private static final int LARGE_ENEMY_ROWS = 2;
    private static final int ENEMY_COLUMNS = 12;
    private static final double SHIP_MARGIN_FROM_LEFT = 0.05;          // ratio
    private static final double SHIP_MARGIN_FROM_BOTTOM = 0.1;         // ratio
    private static final int ENEMY_MARGIN_FROM_TOP = 10;               // pixels
    private static final int UFO_MARGIN_FROM_TOP = 100;                // pixels
    private static final int REDUCED_ENEMY_HEIGHT = 90;                // pixels
    private static final double BULLET_FIRE_TIME_DELAY = 1000000000.0; // nanoseconds
    private static final double UFO_CHANCE = 0.002;                    // ratio
    private static final double ENEMY_FIRE_CHANCE = 0.0001;            // ratio
    private static final int STARTING_LIVES = 3;
    private static final int STARTING_POINTS = 0;

    private int fieldWidth;
    private int fieldHeight;
    private long currentNanoTime = System.nanoTime();
    private long previousBulletFireTime = 0;
    private Random random = new Random();
    private int lives = STARTING_LIVES;
    private int points = STARTING_POINTS;

    private boolean reachedBottom = false;

    private GameScene currentScene;

    private Ship ship;
    private ArrayList<Enemy> enemies;
    private ArrayList<Bullet> playerBullets;
    private ArrayList<Bullet> enemyBullets;
    private ArrayList<Ufo> ufos;

    private ArrayList<DrawableEntity> addedEntities = new ArrayList<DrawableEntity>();
    private ArrayList<DrawableEntity> removedEntities = new ArrayList<DrawableEntity>();

    /**
     * Create the engine, which handles the game.
     *
     * @param fieldWidth  the width of the playing field.
     * @param fieldHeight the height of the playing field.
     * @param startScene  the scene.
     */
    public Engine(int fieldWidth, int fieldHeight, GameScene startScene) {
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
        this.currentScene = startScene;

        this.playerBullets = new ArrayList<Bullet>();
        this.enemyBullets = new ArrayList<Bullet>();
        this.ufos = new ArrayList<Ufo>();
    }

    /**
     * Returns the ship (the player).
     *
     * @return the ship (the player).
     */
    public Ship getShip() {
        return ship;
    }

    /**
     * Return the number of lives the player has left.
     *
     * @return the number of lives the player has left.
     */
    public int getLives() {
        return lives;
    }

    /**
     * Return the number of points the player has accumulated.
     *
     * @return the number of points the player has accumulated.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Create the ship and the enemies.
     *
     * @param shipWidth   the width of the ship (in pixels).
     * @param shipHeight  the height of the ship (in pixels).
     * @param enemyWidth  the width of the enemy (in pixels).
     * @param enemyHeight the height of the enemy (in pixels).
     */
    public void startGame(int shipWidth, int shipHeight, int enemyWidth, int enemyHeight) {
        int shipPositionX = (int) (fieldWidth * SHIP_MARGIN_FROM_LEFT);
        int shipPositionY = (int) (fieldHeight * (1 - SHIP_MARGIN_FROM_BOTTOM));

        this.ship = new Ship(shipPositionX, shipPositionY, shipWidth, shipHeight, 0, fieldWidth);
        addedEntities.add(ship);
        currentScene.addEntity(this.ship);
        this.enemies = createEnemies(enemyWidth, enemyHeight);

        for (Enemy e : this.enemies) {
            currentScene.addEntity(e);
        }
    }

    /**
     * Create the enemies.
     *
     * @param enemyWidth  width of an enemy.
     * @param enemyHeight height of an enemy.
     * @return the list of enemies.
     */
    public ArrayList<Enemy> createEnemies(int enemyWidth, int enemyHeight) {
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        for (int column = 0; column < ENEMY_COLUMNS; column++) {
            int currentRow = 0;
            for (int smallEnemyRow = 0; smallEnemyRow < SMALL_ENEMY_ROWS; smallEnemyRow++) {
                Enemy smallEnemy = new SmallEnemy(ENEMY_MARGIN_FROM_TOP + enemyWidth * column,
                        REDUCED_ENEMY_HEIGHT * currentRow, enemyWidth,
                        enemyHeight, 0, fieldWidth, fieldHeight);
                enemies.add(smallEnemy);
                currentRow++;
                addedEntities.add(smallEnemy);
            }
            for (int mediumEnemyRow = 0; mediumEnemyRow < MEDIUM_ENEMY_ROWS; mediumEnemyRow++) {
                Enemy mediumEnemy = new MediumEnemy(ENEMY_MARGIN_FROM_TOP + enemyWidth * column,
                        REDUCED_ENEMY_HEIGHT * currentRow, enemyWidth,
                        enemyHeight, 0, fieldWidth, fieldHeight);
                enemies.add(mediumEnemy);
                currentRow++;
                addedEntities.add(mediumEnemy);
            }
            for (int largeEnemyRow = 0; largeEnemyRow < LARGE_ENEMY_ROWS; largeEnemyRow++) {
                Enemy largeEnemy = new LargeEnemy(ENEMY_MARGIN_FROM_TOP + enemyWidth * column,
                        REDUCED_ENEMY_HEIGHT * currentRow, enemyWidth,
                        enemyHeight, 0, fieldWidth, fieldHeight);
                enemies.add(largeEnemy);
                currentRow++;
                addedEntities.add(largeEnemy);
            }
        }
        return enemies;
    }

    /**
     * Order the ship to shoot a bullet.
     */
    public void playerShootBullet() {
        currentNanoTime = System.nanoTime();
        if (currentNanoTime - previousBulletFireTime > BULLET_FIRE_TIME_DELAY) {
            previousBulletFireTime = currentNanoTime;
            Bullet bullet = ship.shoot();
            playerBullets.add(bullet);
            addedEntities.add(bullet);
            currentScene.addEntity(bullet);
        }
    }

    /**
     * Order the ship to shoot a bomb.
     */
    public void playerShootBomb() {
        currentNanoTime = System.nanoTime();
        if (currentNanoTime - previousBulletFireTime > BULLET_FIRE_TIME_DELAY) {
            previousBulletFireTime = currentNanoTime;
            Bomb bomb = ship.shootBomb();
            playerBullets.add(bomb);
            addedEntities.add(bomb);
            currentScene.addEntity(bomb);
        }
    }

    /**
     * Order the ship to move left.
     */
    public void playerMoveLeft() {
        ship.moveLeft();
    }

    /**
     * Order the ship to move right.
     */
    public void playerMoveRight() {
        ship.moveRight();
    }

    /**
     * Update the positions of all drawn entities.
     */
    public void update() {
        updateEnemyPositions();
        updatePlayerBullets();
        updateEnemyBullets();
        updateUfos();

        if (random.nextDouble() < UFO_CHANCE) {
            createUfo();
        }

        currentScene.update();
    }

    /**
     * Update the positions of the enemies.
     */
    public void updateEnemyPositions() {
        updateEnemyPositions(this.enemies);
    }

    /**
     * Update the positions of the enemies and randomly create an enemy bullet.
     *
     * @param enemies the enemies in the game.
     */
    public void updateEnemyPositions(Collection<Enemy> enemies) {
        boolean moveDown = false;
        for (Enemy enemy : enemies) {
            try {
                enemy.updatePosition();
                if (random.nextDouble() < ENEMY_FIRE_CHANCE) {
                    createEnemyBullet(enemy);
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
    }

    /**
     * Create an enemy bullet, starting from the position of the enemy and facing downwards.
     *
     * @param enemy the enemy that has fired the bullet.
     */
    public void createEnemyBullet(Enemy enemy) {
        Bullet enemyBullet = new Bullet(enemy.getPositionX(), enemy.getPositionY(), Bullet.WIDTH,
                Bullet.HEIGHT, Direction.SOUTH);
        enemyBullets.add(enemyBullet);
        addedEntities.add(enemyBullet);
        currentScene.addEntity(enemyBullet);
        Logger.info("%s fired a %s at (%d, %d) in the direction %s",
                getClass().getSimpleName(),
                enemyBullet.getClass().getSimpleName(),
                enemyBullet.getPositionX(), enemyBullet.getPositionY(),
                enemyBullet.getDirection());
    }

    /**
     * Update the position of the player bullets, and handle collisions.
     */
    public void updatePlayerBullets() {
        updatePlayerBullets(this.enemies);
    }

    /**
     * Update the position of the player bullets, and handle collisions.
     *
     * @param enemies the enemies in the game
     */
    public void updatePlayerBullets(Collection<Enemy> enemies) {
        // This set will contain all enemies that are hit or within blast radius.
        Set<Enemy> enemiesToRemove = new HashSet<Enemy>();
        Iterator<Bullet> playerProjectileIterator = playerBullets.iterator();
        while (playerProjectileIterator.hasNext()) {
            Bullet playerProjectile = playerProjectileIterator.next();
            playerProjectile.updatePosition();

            // Because we want to loop over all enemies, including ufos, we create a single
            // iterator from both iterators.
            Iterator<Enemy> regularEnemyIterator = enemies.iterator();
            Iterator<Ufo> ufoIterator = ufos.iterator();
            Iterator<Enemy> enemyIterator = Iterators.concat(regularEnemyIterator, ufoIterator);
            boolean intersection = false;
            Set<Enemy> enemiesInRadiusOfThisBullet = new HashSet<Enemy>();
            while (enemyIterator.hasNext()) {
                Enemy enemy = enemyIterator.next();
                boolean inRadius = playerProjectile.impactArea().intersects(enemy.getBoundingBox());
                if (playerProjectile.intersects(enemy)) {
                    intersection = true;
                    for (Enemy enemyToRemove : enemiesInRadiusOfThisBullet) {
                        enemiesToRemove.add(enemyToRemove);
                        Logger.info("%s is hit by a %s at (%d, %d)",
                                enemy.getClass().getSimpleName(),
                                playerProjectile.getClass().getSimpleName(),
                                enemy.getPositionX(), enemy.getPositionY());
                    }
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
                    Logger.info("%s is hit by a %s at (%d, %d)",
                            enemy.getClass().getSimpleName(),
                            playerProjectile.getClass().getSimpleName(),
                            enemy.getPositionX(), enemy.getPositionY());
                }
            }
            if (intersection) {
                // The bullet has hit an enemy, so it gets removed.
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
    }

    /**
     * Update the positions of the enemy bullets, and handle collisions.
     */
    public void updateEnemyBullets() {
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

    /**
     * Create an ufo, that flies from one (randomly decided) side to the other. It is worth
     * between 100 and 1000 points if shot.
     */
    public void createUfo() {
        Integer randomBoundary;
        Direction randomDirection;
        if (random.nextBoolean()) {
            randomBoundary = 0;
            randomDirection = Direction.EAST;
        } else {
            randomBoundary = fieldWidth;
            randomDirection = Direction.WEST;
        }
        Ufo ufo = new Ufo(randomBoundary, UFO_MARGIN_FROM_TOP, 0, fieldWidth, randomDirection);
        ufos.add(ufo);
        addedEntities.add(ufo);
        currentScene.addEntity(ufo);
    }

    /**
     * Update the positions of the ufos.
     */
    public void updateUfos() {
        for (Ufo ufo : ufos) {
            ufo.updatePosition();
        }
    }

    /**
     * Draw all drawable entities in the scene.
     *
     * @param gc the graphics context used to display output.
     */
    public void draw(GraphicsContext gc) {
        currentScene.draw(gc);
    }

    /**
     * The entities that are removed can be requested with getAddedEntities() and
     * getRemovedEntities(). If these entities are handled by the client, then the lists should
     * be cleared. This is done with this method.
     */
    public void clearChangedEntities() {
        addedEntities.clear();
        removedEntities.clear();
    }

    /**
     * Return the entities added to the game.
     *
     * @return the entities added to the game.
     */
    public Collection<DrawableEntity> getAddedEntities() {
        return addedEntities;
    }

    /**
     * Return the entities removed from the game.
     *
     * @return the entities removed from the game.
     */
    public Collection<DrawableEntity> getRemovedEntities() {
        return removedEntities;
    }

    /**
     * True if the game is in progress, otherwise false.
     *
     * @return true if the game is in progress, otherwise false.
     */
    public boolean isInProgress() {
        return !reachedBottom && lives > 0 && !enemies.isEmpty();
    }
}
