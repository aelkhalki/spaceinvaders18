package spaceinvaders.core;

public class Ship extends Actor {
    private static final String FILENAME = "spaceinvaders/gui/resources/ship.png";

    public Ship(Integer positionX, Integer positionY, Integer width, Integer height) {
        super(positionX, positionY, width, height);
    }

    public Bullet shoot() {
        return new Bullet(getPositionX(), getPositionY(), getWidth(), getHeight(), Direction.NORTH);
    }

    public String getSpriteFilename() {
        return FILENAME;
    }
}
