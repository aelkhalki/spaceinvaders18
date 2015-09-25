package nl.delftelectronics.spaceinvaders.core.entities;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import nl.delftelectronics.spaceinvaders.core.Audio;
import org.joda.time.Interval;

import nl.delftelectronics.spaceinvaders.core.Collidable;
import nl.delftelectronics.spaceinvaders.core.Engine;
import nl.delftelectronics.spaceinvaders.core.Logger;
import nl.delftelectronics.spaceinvaders.core.scenes.PlayScene;

/**
 * The ship is the playable character in the game. It can only move horizontally and shoot
 * bullets up.
 */
public class Ship extends Actor implements Collidable {
    private static final String FILENAME = "/ship.png";
    //CHECKSTYLE.OFF: MagicNumber
    protected int lives = 3;
    //CHECKSTYLE.ON: MagicNumber
    private long lastBulletFire = 0;
    private static final double BULLET_FIRE_TIME_DELAY = 1000000000.0; // nanoseconds

    /**
     * Create a Ship with the initial position and size.
     *
     * @param positionX    initial x-position of the Ship.
     * @param positionY    initial y-position of the Ship.
     * @param width        width of the Ship.
     * @param height       height of the Ship.
     * @param westBoundary westernmost boundary of the playing field.
     * @param eastBoundary easternmost boundary of the playing field.
     */
    public Ship(int positionX, int positionY, int width, int height,
                int westBoundary, int eastBoundary) {
        super(positionX, positionY, width, height, FILENAME, westBoundary, eastBoundary);
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
        return new Bullet(x, getPositionY(), Bullet.WIDTH,
                Bullet.HEIGHT, true);
    }
    
    /**
     * Order the ship to shoot a bullet.
     */
    public void playerShootBullet() {
        long currentNanoTime = System.nanoTime();
        if (currentNanoTime - lastBulletFire > BULLET_FIRE_TIME_DELAY) {
        	lastBulletFire = currentNanoTime;
            Bullet bullet = shoot();
            scene.addEntity(bullet);
            Audio.playBulletSound();
        }
    }
    
    /**
     * Gets the current amount of lives of the ship
     * @return the amount of lives
     */
    public int getLives() {
    	return lives;
    }
    
    /**
     * Decrements the lives left on the ship
     */
    public void hit() {
    	if (lives > 0) {
    		lives--;
    	}
    	
    	if (lives == 0 && scene instanceof PlayScene) {
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
        return new Bomb(x, getPositionY(), Bomb.WIDTH,
                Bomb.HEIGHT);
    }
    
    /**
     * Order the ship to shoot a bomb.
     */
    public void playerShootBomb() {
        long currentNanoTime = System.nanoTime();
        if (currentNanoTime - lastBulletFire > BULLET_FIRE_TIME_DELAY) {
        	lastBulletFire = currentNanoTime;
            Bomb bomb = shootBomb();
            scene.addEntity(bomb);
            Audio.playBombSound();
        }
    }
    
    @Override
    public void update(Interval delta) {
    	super.update(delta);
    	
    	Engine engine = Engine.getInstance();
    	if (engine.isKeyPressed("SPACE")) {
    		playerShootBullet();
    	}
    	if (engine.isKeyPressed("X")) {
    		playerShootBomb();
    	}
    	
    	if (engine.isKeyPressed("RIGHT")) {
    		moveRight(delta);
    	}
    	if (engine.isKeyPressed("LEFT")) {
    		moveLeft(delta);
    	}
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
