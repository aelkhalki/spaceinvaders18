package spaceinvaders.core;

public class Bullet extends Entity implements AutomaticMovable {
    private static final Integer MOVING_SPEED = 5;
    private Direction direction;

    public Bullet(Integer positionX, Integer positionY, Direction direction) {
        super(positionX, positionY);
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public void updatePosition() {
        if (direction == Direction.NORTH) {
            setPositionY(getPositionY() - MOVING_SPEED);
        } else if (direction == Direction.SOUTH) {
            setPositionY(getPositionY() + MOVING_SPEED);
        }
    }
}
