package spaceinvaders.core;

public class Bullet extends Entity implements AutomaticMovable {
    private static final Integer MOVING_SPEED = 15;
    private static final String FILENAME = "spaceinvaders/gui/resources/ufo.png";
    private Direction direction;

    public Bullet(Integer positionX, Integer positionY, Integer width, Integer height, Direction direction) {
        super(positionX, positionY, width, height);
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

    public String getSpriteFilename() {
        return FILENAME;
    }
}
