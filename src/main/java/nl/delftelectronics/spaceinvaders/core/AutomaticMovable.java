package nl.delftelectronics.spaceinvaders.core;

/**
 * Every object that should be able to move by itself (i.e. should not depend on user input),
 * should implement AutomaticMovable.
 */
public interface AutomaticMovable {
    /**
     * Let the object update its position.
     *
     * @throws BoundaryReachedException    thrown iff reached one or multiple boundaries of the
     *                                     playing field.
     * @throws EnemyReachedBottomException thrown iff one or more enemies have reached the bottom
     *                                     of the playing field.
     */
    void updatePosition() throws BoundaryReachedException, EnemyReachedBottomException;
}
