package nl.delftelectronics.spaceinvaders.core.entities;

/**
 * A SmallEnemy is a small Enemy, worth 40 points.
 */
public class SmallEnemy extends Enemy {
    private static final String FILENAME = "/small_enemy.png";
    private static final Integer POINTS = 40;

    /**
     * Create an SmallEnemy with the initial position, the size, the boundaries of the playing
     * field and the initial direction of the SmallEnemy.
     *
     * @param positionX       initial x-position of the SmallEnemy.
     * @param positionY       initial y-position of the SmallEnemy.
     * @param width           width of the SmallEnemy.
     * @param height          height of the SmallEnemy.
     * @param westBoundary    westernmost boundary of the playing field.
     * @param eastBoundary    easternmost boundary of the playing field.
     * @param southBoundary   southernmost boundary of the playing field.
     * @param block 		  the block of enemies it belongs to.
     */
    public SmallEnemy(Integer positionX, Integer positionY, Integer width, Integer height,
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
