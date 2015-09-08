package nl.delftelectronics.spaceinvaders.core;

public class Ship extends Actor {
    public Ship(Integer positionX, Integer positionY) {
        super(positionX, positionY);
    }

    public Bullet shoot() {
        return new Bullet(getPositionX(), getPositionY(), Direction.NORTH);
    }
}
