package spaceinvaders.core;

public class MediumEnemy extends Enemy {
    private static final Integer points = 20;

    public MediumEnemy(Integer positionX, Integer positionY, Direction movingDirection) {
        super(positionX, positionY, movingDirection);
    }

    public MediumEnemy(Integer positionX, Integer positionY) {
        super(positionX, positionY);
    }

    @Override
    public Integer getPoints() {
        return points;
    }
}
