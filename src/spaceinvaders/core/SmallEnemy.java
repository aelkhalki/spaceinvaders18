package spaceinvaders.core;

public class SmallEnemy extends Enemy {
    private static final Integer points = 40;

    public SmallEnemy(Integer positionX, Integer positionY, Integer westBoundary, Integer eastBoundary,
            Integer southBoundary, Direction movingDirection) {
        super(positionX, positionY, westBoundary, eastBoundary, southBoundary, movingDirection);
    }

    public SmallEnemy(Integer positionX, Integer positionY, Integer westBoundary, Integer eastBoundary,
            Integer southBoundary) {
        super(positionX, positionY, westBoundary, eastBoundary, southBoundary);
    }

    @Override
    public Integer getPoints() {
        return points;
    }
}
