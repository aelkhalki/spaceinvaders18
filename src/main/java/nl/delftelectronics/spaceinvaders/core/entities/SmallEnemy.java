package nl.delftelectronics.spaceinvaders.core.entities;

import java.awt.geom.Rectangle2D;

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
     * @param position        position of the sprite
     * @param westBoundary    westernmost boundary of the playing field.
     * @param eastBoundary    easternmost boundary of the playing field.
     * @param southBoundary   southernmost boundary of the playing field.
     * @param block 		  the block of enemies it belongs to.
     */
    public SmallEnemy(Rectangle2D position,
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
