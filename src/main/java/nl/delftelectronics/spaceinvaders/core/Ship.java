package nl.delftelectronics.spaceinvaders.core;

/**
 * The ship is the playable character in the game. It can only move horizontally and shoot
 * bullets up.
 */
public class Ship extends Actor {
    private static final String FILENAME = "/ship.png";

    /**
     * Create a Ship with the initial position and size.
     *
     * @param positionX initial x-position of the Ship.
     * @param positionY initial y-position of the Ship.
     * @param width     width of the Ship.
     * @param height    height of the Ship.
     */
    public Ship(Integer positionX, Integer positionY, Integer width, Integer height) {
        super(positionX, positionY, width, height);
    }

    /**
     * Create a bullet in the position of the ship, facing up.
     *
     * @return a new bullet in the position of the ship, facing up.
     */
    public Bullet shoot() {
        return new Bullet(getPositionX() + getWidth() / 2, getPositionY(), Bullet.WIDTH,
                Bullet.HEIGHT, Direction.NORTH);
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
