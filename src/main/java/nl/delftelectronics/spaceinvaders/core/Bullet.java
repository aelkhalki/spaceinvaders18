package nl.delftelectronics.spaceinvaders.core;

import org.joda.time.Interval;

public class Bullet extends SpriteEntity implements AutomaticMovable {
    public static final Integer MOVING_SPEED = 15;
    private static final String FILENAME = "/ufo.png";
    private Direction direction;

    public Bullet(Integer positionX, Integer positionY, Integer width, Integer height, Direction direction) {
        super(positionX, positionY, width, height, FILENAME);
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }
    
    @Override
    public void update(Interval delta) {
    	updatePosition();
    }

    public void updatePosition() {
        if (direction == Direction.NORTH) {
            setPositionY(getPositionY() - MOVING_SPEED);
        } else if (direction == Direction.SOUTH) {
            setPositionY(getPositionY() + MOVING_SPEED);
        }
    }
}
