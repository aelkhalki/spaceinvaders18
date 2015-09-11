package nl.delftelectronics.spaceinvaders.core;

import junit.framework.TestCase;
import org.mockito.ArgumentCaptor;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

/**
 * Test the Engine class.
 */
public class EngineTest extends TestCase {
    /**
     * Add the enemies. Check if the right amount of enemies are added to the scene.
     */
    public void testEnemiesAdded() {
        GameScene gameScene = mock(GameScene.class);
        Engine engine = new Engine(10000, 10000, gameScene);
        engine.startGame(100, 100, 100, 100);
        verify(gameScene, times(60)).addEntity(isA(Enemy.class));
    }

    /**
     * Shoot a bullet. Check if it is added to the scene.
     */
    public void testPlayerShootBullet() {
        GameScene gameScene = mock(GameScene.class);
        Engine engine = new Engine(10000, 10000, gameScene);
        engine.startGame(100, 100, 100, 100);
        engine.playerShootBullet();
        verify(gameScene, times(1)).addEntity(isA(Bullet.class));
    }

    /**
     * Shoot a bomb. Check if it is added to the scene.
     */
    public void testPlayerShootBomb() {
        GameScene gameScene = mock(GameScene.class);
        Engine engine = new Engine(10000, 10000, gameScene);
        engine.startGame(100, 100, 100, 100);
        engine.playerShootBomb();
        verify(gameScene, times(1)).addEntity(isA(Bomb.class));
    }

    /**
     * Let the update its position. Check if the right method to update the enemy position is
     * called.
     *
     * @throws EnemyReachedBottomException
     * @throws BoundaryReachedException
     */
    public void testEnemyPositionUpdate() throws EnemyReachedBottomException, BoundaryReachedException {
        GameScene gameScene = mock(GameScene.class);
        Collection<Enemy> enemies = new ArrayList<Enemy>();
        Enemy enemy = mock(Enemy.class);
        enemies.add(enemy);
        Engine engine = new Engine(10000, 10000, gameScene);
        engine.startGame(100, 100, 100, 100);
        engine.updateEnemyPositions(enemies);
        verify(enemy, times(1)).updatePosition();
    }

    /**
     * Let a player bullet update its position. Check if the movement is correct.
     */
    public void testPlayerBulletsPositionUpdate() {
        GameScene gameScene = mock(GameScene.class);
        Engine engine = new Engine(10000, 10000, gameScene);
        engine.startGame(100, 100, 100, 100);
        ArgumentCaptor<Bullet> bulletCaptor = ArgumentCaptor.forClass(Bullet.class);
        reset(gameScene);
        engine.playerShootBullet();
        verify(gameScene).addEntity(bulletCaptor.capture());
        Bullet bullet = bulletCaptor.getValue();
        Integer oldYPosition = bullet.getPositionY();
        Integer expectedNewYPosition = oldYPosition - Bullet.MOVING_SPEED;
        engine.updatePlayerBullets();
        assertEquals(expectedNewYPosition, bullet.getPositionY());
    }

    /**
     * Let a player bullet and an enemy collide. Check if the bullet gets removed.
     */
    public void testPlayerBulletAndEnemyCollision_BulletRemoval() {
        GameScene gameScene = mock(GameScene.class);
        Engine engine = new Engine(100, 150, gameScene);
        engine.startGame(100, 100, 100, 100);
        ArgumentCaptor<Bullet> bulletCaptor = ArgumentCaptor.forClass(Bullet.class);
        reset(gameScene);
        engine.playerShootBullet();
        verify(gameScene).addEntity(bulletCaptor.capture());
        Bullet bullet = bulletCaptor.getValue();
        engine.updatePlayerBullets();
        assertTrue(engine.getRemovedEntities().contains(bullet));
    }

    /**
     * Let a player bullet and an enemy collide. Check if the enemy gets removed.
     */
    public void testPlayerBulletAndEnemyCollision_EnemyRemoval() {
        GameScene gameScene = mock(GameScene.class);
        Engine engine = new Engine(100, 150, gameScene);
        engine.startGame(100, 100, 100, 100);
        engine.playerShootBullet();
        Collection<Enemy> enemies = new ArrayList<Enemy>();
        Enemy enemy = mock(Enemy.class);
        Rectangle boundingBox = new Rectangle(0, 0, 100, 150);
        when(enemy.getBoundingBox()).thenReturn(boundingBox);
        enemies.add(enemy);
        engine.updatePlayerBullets(enemies);
        verify(enemy, times(1)).destroy();
    }

    /**
     * Let an enemy bullet and the player collide. Check if the player loses a life.
     */
    public void testEnemyBulletAndShipCollision() {
        GameScene gameScene = mock(GameScene.class);
        Engine engine = new Engine(100, 100, gameScene);
        engine.startGame(100, 100, 100, 100);
        Enemy enemy = mock(Enemy.class);
        when(enemy.getPositionX()).thenReturn(0);
        when(enemy.getPositionY()).thenReturn(0);
        engine.getShip().setPositionX(0);
        engine.getShip().setPositionY(0);
        engine.createEnemyBullet(enemy);
        engine.updateEnemyBullets();
        assertEquals(2, engine.getLives());
    }

    /**
     * Let the enemy shoot a bullet and check the bullet is added to the scene.
     */
    public void testEnemyShootBullet() {
        GameScene gameScene = mock(GameScene.class);
        Engine engine = new Engine(10000, 10000, gameScene);
        engine.startGame(100, 100, 100, 100);
        Enemy enemy = mock(Enemy.class);
        engine.createEnemyBullet(enemy);
        verify(gameScene, times(1)).addEntity(isA(Bullet.class));
    }
}
