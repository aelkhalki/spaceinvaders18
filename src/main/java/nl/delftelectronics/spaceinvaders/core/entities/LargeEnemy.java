package nl.delftelectronics.spaceinvaders.core.entities;

import java.awt.geom.Rectangle2D;

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
     * @param position        position of the sprite
     * @param westBoundary    westernmost boundary of the playing field.
     * @param eastBoundary    easternmost boundary of the playing field.
     * @param southBoundary   southernmost boundary of the playing field.
     * @param block           the block of enemies this enemy belongs to.
     */
    public LargeEnemy(Rectangle2D position,
                      int westBoundary, int eastBoundary, int southBoundary,
                      EnemyBlock block) {
        super(position, westBoundary, eastBoundary, southBoundary,
                block, FILENAME);
    }

    @Override
    public Integer getPoints() {
        return POINTS;
    }
}
