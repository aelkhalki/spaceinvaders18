package nl.delftelectronics.spaceinvaders.core;

/**
 * A MediumEnemy is a medium sized Enemy, worth 20 points.
 */
public class MediumEnemy extends Enemy {
    private static final String FILENAME = "/medium_enemy.png";
    private static final Integer POINTS = 20;

    /**
     * Create an MediumEnemy with the initial position, the size, the boundaries of the playing
     * field and the initial direction of the MediumEnemy.
     *
     * @param positionX       initial x-position of the MediumEnemy.
     * @param positionY       initial y-position of the MediumEnemy.
     * @param width           width of the MediumEnemy.
     * @param height          height of the MediumEnemy.
     * @param westBoundary    westernmost boundary of the playing field.
     * @param eastBoundary    easternmost boundary of the playing field.
     * @param southBoundary   southernmost boundary of the playing field.
     * @param movingDirection initial direction of the MediumEnemy.
     */
    public MediumEnemy(Integer positionX, Integer positionY, Integer width, Integer height,
                       Integer westBoundary, Integer eastBoundary, Integer southBoundary,
                       Direction movingDirection) {
        super(positionX, positionY, width, height, westBoundary, eastBoundary, southBoundary,
                movingDirection, FILENAME);
    }

    /**
     * Create an MediumEnemy with the initial position, the size, the boundaries of the playing
     * field. The initial direction is set to east.
     *
     * @param positionX     initial x-position of the MediumEnemy.
     * @param positionY     initial y-position of the MediumEnemy.
     * @param width         width of the MediumEnemy.
     * @param height        height of the MediumEnemy.
     * @param westBoundary  westernmost boundary of the playing field.
     * @param eastBoundary  easternmost boundary of the playing field.
     * @param southBoundary southernmost boundary of the playing field.
     */
    public MediumEnemy(Integer positionX, Integer positionY, Integer width, Integer height,
                       Integer westBoundary, Integer eastBoundary, Integer southBoundary) {
        super(positionX, positionY, width, height, westBoundary, eastBoundary, southBoundary, FILENAME);
    }

    @Override
    public Integer getPoints() {
        return POINTS;
    }
}
