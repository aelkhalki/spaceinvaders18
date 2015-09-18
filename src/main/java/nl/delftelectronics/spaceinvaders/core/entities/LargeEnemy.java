package nl.delftelectronics.spaceinvaders.core.entities;

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
     * @param block           the block of enemies this enemy belongs to.
     */
    public LargeEnemy(double positionX, double positionY, double width, double height,
                      Integer westBoundary, Integer eastBoundary, Integer southBoundary,
                      EnemyBlock block) {
        super(positionX, positionY, width, height, westBoundary, eastBoundary, southBoundary,
                block, FILENAME);
    }

    @Override
    public Integer getPoints() {
        return POINTS;
    }
}
