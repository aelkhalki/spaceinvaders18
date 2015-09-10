package nl.delftelectronics.spaceinvaders.core;

public class Ship extends Actor {
    private static final String FILENAME = "/ship.png";

    public Ship(Integer positionX, Integer positionY, Integer width, Integer height) {
        super(positionX, positionY, width, height, FILENAME);
    }

    public Bullet shoot() {
        return new Bullet(getPositionX(), getPositionY(), getWidth(), getHeight(), Direction.NORTH);
    }
}
