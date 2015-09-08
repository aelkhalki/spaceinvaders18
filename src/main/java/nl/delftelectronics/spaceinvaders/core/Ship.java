package nl.delftelectronics.spaceinvaders.core;

public class Ship extends Actor {
    private static final String FILENAME = "/ship.png";

    public Ship(Integer positionX, Integer positionY, Integer width, Integer height) {
        super(positionX, positionY, width, height);
    }

    public Bullet shoot() {
        return new Bullet(getPositionX() + getWidth()/2, getPositionY(), 3, 10, Direction.NORTH);
    }

    public String getSpriteFilename() {
        return FILENAME;
    }
}
