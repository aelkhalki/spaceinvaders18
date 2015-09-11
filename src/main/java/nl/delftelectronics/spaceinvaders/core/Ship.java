package nl.delftelectronics.spaceinvaders.core;

/**
 * The ship is the playable character in the game. It can only move horizontally and shoot
 * bullets up.
 */
public class Ship extends Actor implements Collidable {
    private static final String FILENAME = "/ship.png";
    protected int lives = 3;

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
        return new Bullet(getPositionX() + getWidth() / 2, getPositionY(), Bullet.WIDTH,
                Bullet.HEIGHT, true);
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
    	} else {
    		return;
    	}
    	
    	if (scene instanceof PlayScene) {
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
        return new Bomb(getPositionX() + getWidth() / 2, getPositionY(), Bomb.WIDTH,
                Bomb.HEIGHT, Direction.NORTH);
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
