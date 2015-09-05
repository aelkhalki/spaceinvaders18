package spaceinvaders.core;

public class LargeEnemy extends Enemy {
    private static final Integer points = 10;

    public LargeEnemy(Integer positionX, Integer positionY, Integer westBoundary, Integer eastBoundary,
            Integer southBoundary, Direction movingDirection) {
        super(positionX, positionY, westBoundary, eastBoundary, southBoundary, movingDirection);
    }

    public LargeEnemy(Integer positionX, Integer positionY, Integer westBoundary, Integer eastBoundary,
            Integer southBoundary) {
        super(positionX, positionY, westBoundary, eastBoundary, southBoundary);
    }

    @Override
    public Integer getPoints() {
        return points;
    }
}
