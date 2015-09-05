package spaceinvaders.core;

public class SmallEnemy extends Enemy {
    private static final Integer points = 40;

    public SmallEnemy(Integer positionX, Integer positionY, Integer westBoundary, Integer eastBoundary,
            Direction movingDirection) {
        super(positionX, positionY, westBoundary, eastBoundary, movingDirection);
    }

    public SmallEnemy(Integer positionX, Integer positionY, Integer westBoundary, Integer eastBoundary) {
        super(positionX, positionY, westBoundary, eastBoundary);
    }

    @Override
    public Integer getPoints() {
        return points;
    }
}
