package nl.delftelectronics.spaceinvaders.core;

public class Ship extends Actor {
    private static final String FILENAME = "/ship.png";

    public Ship(Integer positionX, Integer positionY, Integer width, Integer height, Integer westBoundary,
            Integer eastBoundary) {
        super(positionX, positionY, width, height);
    }

    public Bullet shoot() {
        return new Bullet(getPositionX(), getPositionY(), getWidth(), getHeight(), Direction.NORTH);
    }

    public String getSpriteFilename() {
        return FILENAME;
    }
}
