package spaceinvaders.core;

public class LargeEnemy extends Enemy {
    private static final Integer points = 10;

    public LargeEnemy(Integer positionX, Integer positionY, Integer westBoundary, Integer eastBoundary,
            Direction movingDirection) {
        super(positionX, positionY, westBoundary, eastBoundary, movingDirection);
    }

    public LargeEnemy(Integer positionX, Integer positionY, Integer westBoundary, Integer eastBoundary) {
        super(positionX, positionY, westBoundary, eastBoundary);
    }

    @Override
    public Integer getPoints() {
        return points;
    }
}
