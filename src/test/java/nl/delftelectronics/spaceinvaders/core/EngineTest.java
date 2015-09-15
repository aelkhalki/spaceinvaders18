package nl.delftelectronics.spaceinvaders.core;

import junit.framework.TestCase;

import org.joda.time.Interval;
import org.mockito.ArgumentCaptor;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;

/**
 * Test the Engine class.
 */
public class EngineTest extends TestCase {
    private static final int DIMENSION = 100;

    /**
     * Add the enemies. Check if the right amount of enemies are added to the scene.
     */
    public void testEnemiesAdded() {
        final int numberOfEnemies = 60;
        GameScene gameScene = mock(GameScene.class);
        Engine engine = new Engine(DIMENSION, DIMENSION, gameScene);
        engine.startGame(DIMENSION, DIMENSION, DIMENSION, DIMENSION, new ArrayList<String>());
        verify(gameScene, times(numberOfEnemies)).addEntity(isA(Enemy.class));
    }

    /**
     * Let the update its position. Check if the right method to update the enemy position is
     * called.
     *
     * @throws EnemyReachedBottomException enemy reached the bottom of the playing field.
     * @throws BoundaryReachedException    enemy reached a boundary of the playing field.
     */
    public void testEnemyPositionUpdate() throws EnemyReachedBottomException,
            BoundaryReachedException {
        GameScene gameScene = mock(GameScene.class);
        Collection<Enemy> enemies = new ArrayList<Enemy>();
        Enemy enemy = mock(Enemy.class);
        enemies.add(enemy);

        Engine engine = new Engine(DIMENSION, DIMENSION, gameScene);
        engine.startGame(DIMENSION, DIMENSION, DIMENSION, DIMENSION, new ArrayList<String>());
        engine.update();
        verify(enemy, times(1)).update(any(Interval.class));
    }


    /**
     * Let an enemy bullet and the player collide. Check if the player loses a life.
     */
    public void testEnemyBulletAndShipCollision() {
        GameScene gameScene = mock(GameScene.class);
        Engine engine = new Engine(DIMENSION, DIMENSION, gameScene);
        engine.startGame(DIMENSION, DIMENSION, DIMENSION, DIMENSION, new ArrayList<String>());
        Enemy enemy = mock(Enemy.class);
        when(enemy.getPositionX()).thenReturn(0);
        when(enemy.getPositionY()).thenReturn(0);
        engine.getShip().setPositionX(0);
        engine.getShip().setPositionY(0);
        enemy.fire();
        engine.update();
        assertEquals(2, engine.getLives());
    }

    /**
     * Let the enemy shoot a bullet and check the bullet is added to the scene.
     */
    public void testEnemyShootBullet() {
        GameScene gameScene = mock(GameScene.class);
        Engine engine = new Engine(DIMENSION, DIMENSION, gameScene);
        engine.startGame(DIMENSION, DIMENSION, DIMENSION, DIMENSION, new ArrayList<String>());
        Enemy enemy = mock(Enemy.class);
        enemy.fire();
        verify(gameScene, times(1)).addEntity(isA(Bullet.class));
    }
}
