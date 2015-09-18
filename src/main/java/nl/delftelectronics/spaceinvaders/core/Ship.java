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
     * @param positionX    initial x-position of the Ship.
     * @param positionY    initial y-position of the Ship.
     * @param width        width of the Ship.
     * @param height       height of the Ship.
     * @param westBoundary westernmost boundary of the playing field.
     * @param eastBoundary easternmost boundary of the playing field.
     */
    public Ship(Integer positionX, Integer positionY, Integer width, Integer height,
                Integer westBoundary, Integer eastBoundary) {
        super(positionX, positionY, width, height, FILENAME, westBoundary, eastBoundary);
    }

    /**
     * Create a bullet in the position of the ship, facing up.
     *
     * @return a new bullet in the position of the ship, facing up.
     */
    public Bullet shoot() {
        Bullet bullet = new Bullet(getPositionX() + getWidth() / 2, getPositionY(), Bullet.WIDTH,
                Bullet.HEIGHT, Direction.NORTH);
        Logger.info("%s fired a %s at (%d, %d) in the direction %s",
                getClass().getSimpleName(),
                bullet.getClass().getSimpleName(),
                bullet.getPositionX(), bullet.getPositionY(),
                bullet.getDirection());
        return bullet;
    }

    /**
     * Create a bomb in the position of the ship, facing upwards.
     *
     * @return a new bomb in the position of the ship, facing upwards.
     */
    public Bomb shootBomb() {
        Bomb bullet = new Bomb(getPositionX() + getWidth() / 2, getPositionY(), Bomb.WIDTH,
                Bomb.HEIGHT, Direction.NORTH);
        Logger.info("%s fired a %s at (%d, %d) in the direction %s",
                getClass().getSimpleName(),
                bullet.getClass().getSimpleName(),
                bullet.getPositionX(), bullet.getPositionY(),
                bullet.getDirection());
        return bullet;
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
