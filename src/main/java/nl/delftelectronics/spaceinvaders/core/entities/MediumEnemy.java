package nl.delftelectronics.spaceinvaders.core.entities;

import java.awt.geom.Rectangle2D;

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
     * @param position        position of the sprite
     * @param westBoundary    westernmost boundary of the playing field.
     * @param eastBoundary    easternmost boundary of the playing field.
     * @param southBoundary   southernmost boundary of the playing field.
     * @param block 		  the block of enemies this enemy belongs to.
     */
    public MediumEnemy(Rectangle2D position,
                       Integer westBoundary, Integer eastBoundary, Integer southBoundary,
                       EnemyBlock block) {
        super(position, westBoundary, eastBoundary, southBoundary,
                block, FILENAME);
    }

    @Override
    public Integer getPoints() {
        return POINTS;
    }
}
