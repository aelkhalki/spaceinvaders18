package nl.delftelectronics.spaceinvaders.core.entities;

import nl.delftelectronics.spaceinvaders.core.Audio;
import nl.delftelectronics.spaceinvaders.core.Collidable;
import nl.delftelectronics.spaceinvaders.core.Engine;
import nl.delftelectronics.spaceinvaders.core.GameInformation;
import nl.delftelectronics.spaceinvaders.core.Logger;
import nl.delftelectronics.spaceinvaders.core.PlayingKeys;
import nl.delftelectronics.spaceinvaders.core.scenes.PlayScene;
import org.joda.time.Interval;

import java.awt.geom.Rectangle2D;

/**
 * The ship is the playable character in the game. It can only move horizontally and shoot
 * bullets up.
 */
public class Ship extends Actor implements Collidable {
    public static final int INITIAL_LIVES = 3;
    private static final String FILENAME = "/ship.png";
    private GameInformation gameInformation;
    private long lastBulletFire = 0;
    private static final double BULLET_FIRE_TIME_DELAY = 1000000000.0; // nanoseconds

    private PlayingKeys playingKeys;

    /**
     * Create a Ship with the initial position and size.
     *
     * @param position        position of the sprite
     * @param westBoundary    westernmost boundary of the playing field.
     * @param eastBoundary    easternmost boundary of the playing field.
     * @param gameInformation information about the current game.
     */
    public Ship(Rectangle2D position, int westBoundary,
                int eastBoundary, GameInformation gameInformation) {
        super(position, FILENAME, westBoundary, eastBoundary);
        this.gameInformation = gameInformation;
    }

    /**
     * Create a bullet in the position of the ship, facing up.
     *
     * @return a new bullet in the position of the ship, facing up.
     */
    public Bullet shoot() {
        double x = getPositionX() + getWidth() / 2;
        Logger.info("%s fired a Bullet at (%f, %f) in the direction North",
                this.getClass().toString(),
                x, getPositionY());
        return new Bullet(new Rectangle2D.Double(x, getPositionY(), Bullet.WIDTH,
                Bullet.HEIGHT), true);
    }

    /**
     * Order the ship to shoot a bullet.
     *
     * @param soundEffect true if a sound effect has to be played.
     */
    public void playerShootBullet(boolean soundEffect) {
        long currentNanoTime = System.nanoTime();
        if (currentNanoTime - lastBulletFire > BULLET_FIRE_TIME_DELAY) {
            lastBulletFire = currentNanoTime;
            Bullet bullet = shoot();
            scene.addEntity(bullet);
            if (soundEffect) {
                Audio.playBulletSound();
            }
        }
    }

    /**
     * Order the ship to shoot a bullet, with the sound effect.
     */
    public void playerShootBullet() {
        playerShootBullet(true);
    }

    /**
     * Decrements the lives left on the ship
     */
    public void hit() {
        if (gameInformation.hasLives()) {
            gameInformation.decrementLives();
        }

        if (!gameInformation.hasLives() && scene instanceof PlayScene) {
            PlayScene s = (PlayScene) scene;
            s.lose();
        }
    }

    /**
     * Create a bomb in the position of the ship, facing upwards.
     *
     * @return a new bomb in the position of the ship, facing upwards.
     */
    public Bomb shootBomb() {
        double x = getPositionX() + getWidth() / 2;
        Logger.info("%s fired a Bomb at (%f, %f) in the direction North",
                this.getClass().toString(),
                x, getPositionY());
        return new Bomb(new Rectangle2D.Double(x, getPositionY(), Bomb.WIDTH,
                Bomb.HEIGHT));
    }

    /**
     * Order the ship to shoot a bomb.
     *
     * @param soundEffect true if a soundEffect has to be played.
     */
    public void playerShootBomb(boolean soundEffect) {
        if (!gameInformation.hasBombs()) {
            return;
        }
        long currentNanoTime = System.nanoTime();
        if (currentNanoTime - lastBulletFire > BULLET_FIRE_TIME_DELAY) {
            gameInformation.decrementBombs();
            lastBulletFire = currentNanoTime;
            Bomb bomb = shootBomb();
            scene.addEntity(bomb);
            if (soundEffect) {
                Audio.playBombSound();
            }
        }
    }

    /**
     * Order the ship to shoot a bomb, without sounds effects.
     */
    public void playerShootBomb() {
        playerShootBomb(true);
    }

    @Override
    public void update(Interval delta) {
        super.update(delta);

        Engine engine = Engine.getInstance();
        if (engine.isKeyPressed(playingKeys.getFireBulletKey())) {
            playerShootBullet();
        }
        if (engine.isKeyPressed(playingKeys.getFireBombKey())) {
            playerShootBomb();
        }

        if (engine.isKeyPressed(playingKeys.getMoveRightKey())) {
            moveRight(delta);
        }
        if (engine.isKeyPressed(playingKeys.getMoveLeftKey())) {
            moveLeft(delta);
        }
    }

    /**
     * Set the playing keys for this player/ship.
     *
     * @param playingKeys the playing keys for this player/ship.
     */
    public void setPlayingKeys(PlayingKeys playingKeys) {
        this.playingKeys = playingKeys;
    }

    /**
     * Return the filename of the ship sprite.
     *
     * @return the filename of the ship sprite.
     */
    public String getSpriteFilename() {
        return FILENAME;
    }
}
