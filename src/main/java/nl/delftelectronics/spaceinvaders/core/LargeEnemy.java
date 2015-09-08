package nl.delftelectronics.spaceinvaders.core;

public class LargeEnemy extends Enemy {
    private static final String FILENAME = "/large_enemy.png";
    private static final Integer points = 10;

    public LargeEnemy(Integer positionX, Integer positionY, Integer width, Integer height, Integer westBoundary,
            Integer eastBoundary, Integer southBoundary, Direction movingDirection) {
        super(positionX, positionY, width, height, westBoundary, eastBoundary, southBoundary, movingDirection);
    }

    public LargeEnemy(Integer positionX, Integer positionY, Integer width, Integer height, Integer westBoundary,
            Integer eastBoundary, Integer southBoundary) {
        super(positionX, positionY, width, height, westBoundary, eastBoundary, southBoundary);
    }

    @Override
    public Integer getPoints() {
        return points;
    }

    public String getSpriteFilename() {
        return FILENAME;
    }
}
