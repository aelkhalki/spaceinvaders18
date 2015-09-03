package spaceinvaders.core;

public interface AutomaticMovable {
    public void updatePosition() throws BoundaryReachedException;
}
