package nl.delftelectronics.spaceinvaders.core;

public class MediumEnemy extends Enemy {
    private static final Integer points = 20;

    public MediumEnemy(Integer positionX, Integer positionY, Integer westBoundary, Integer eastBoundary,
            Integer southBoundary, Direction movingDirection) {
        super(positionX, positionY, westBoundary, eastBoundary, southBoundary, movingDirection);
    }

    public MediumEnemy(Integer positionX, Integer positionY, Integer westBoundary, Integer eastBoundary,
            Integer southBoundary) {
        super(positionX, positionY, westBoundary, eastBoundary, southBoundary);
    }

    @Override
    public Integer getPoints() {
        return points;
    }
}
