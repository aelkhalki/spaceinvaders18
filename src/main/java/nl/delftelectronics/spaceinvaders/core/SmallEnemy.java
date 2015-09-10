package nl.delftelectronics.spaceinvaders.core;

public class SmallEnemy extends Enemy {
    private static final String FILENAME = "/small_enemy.png";
    private static final Integer points = 40;

    public SmallEnemy(Integer positionX, Integer positionY, Integer width, Integer height, Integer westBoundary,
            Integer eastBoundary, Integer southBoundary, Direction movingDirection) {
        super(positionX, positionY, width, height, westBoundary, eastBoundary, southBoundary, movingDirection, FILENAME);
    }

    public SmallEnemy(Integer positionX, Integer positionY, Integer width, Integer height, Integer westBoundary,
            Integer eastBoundary, Integer southBoundary) {
        super(positionX, positionY, width, height, westBoundary, eastBoundary, southBoundary, FILENAME);
    }

    @Override
    public Integer getPoints() {
        return points;
    }
}
