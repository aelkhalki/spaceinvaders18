package spaceinvaders.core;

public class MediumEnemy extends Enemy {
    private static final Integer points = 20;

    public MediumEnemy(Integer positionX, Integer positionY, Integer westBoundary, Integer eastBoundary,
            Direction movingDirection) {
        super(positionX, positionY, westBoundary, eastBoundary, movingDirection);
    }

    public MediumEnemy(Integer positionX, Integer positionY, Integer westBoundary, Integer eastBoundary) {
        super(positionX, positionY, westBoundary, eastBoundary);
    }

    @Override
    public Integer getPoints() {
        return points;
    }
}
