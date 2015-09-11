package nl.delftelectronics.spaceinvaders.core;

/**
 * A LargeEnemy is a large Enemy, worth 10 points.
 */
public class LargeEnemy extends Enemy {
    private static final String FILENAME = "/large_enemy.png";
    private static final Integer POINTS = 10;

    /**
     * Create a LargeEnemy with the initial position, the size, the boundaries of the playing field
     * and the initial direction of the Enemy.
     *
     * @param positionX       initial x-position of the LargeEnemy.
     * @param positionY       initial y-position of the LargeEnemy.
     * @param width           width of the LargeEnemy.
     * @param height          height of the LargeEnemy.
     * @param westBoundary    westernmost boundary of the playing field.
     * @param eastBoundary    easternmost boundary of the playing field.
     * @param southBoundary   southernmost boundary of the playing field.
     * @param movingDirection initial direction of the LargeEnemy.
     */
    public LargeEnemy(Integer positionX, Integer positionY, Integer width, Integer height,
                      Integer westBoundary, Integer eastBoundary, Integer southBoundary,
                      Direction movingDirection) {
        super(positionX, positionY, width, height, westBoundary, eastBoundary, southBoundary,
                movingDirection, FILENAME);
    }

    /**
     * Create a LargeEnemy with the initial position, the size, the boundaries of the playing field.
     * The initial direction is set to east.
     *
     * @param positionX     initial x-position of the LargeEnemy.
     * @param positionY     initial y-position of the LargeEnemy.
     * @param width         width of the LargeEnemy.
     * @param height        height of the LargeEnemy.
     * @param westBoundary  westernmost boundary of the playing field.
     * @param eastBoundary  easternmost boundary of the playing field.
     * @param southBoundary southernmost boundary of the playing field.
     */
    public LargeEnemy(Integer positionX, Integer positionY, Integer width, Integer height,
                      Integer westBoundary, Integer eastBoundary, Integer southBoundary) {
        super(positionX, positionY, width, height, westBoundary, eastBoundary, southBoundary, FILENAME);
    }

    @Override
    public Integer getPoints() {
        return POINTS;
    }
}
