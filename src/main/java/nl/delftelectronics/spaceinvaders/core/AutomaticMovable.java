package nl.delftelectronics.spaceinvaders.core;

public interface AutomaticMovable {
    public void updatePosition() throws BoundaryReachedException, EnemyReachedBottomException;
}
