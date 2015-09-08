package nl.delftelectronics.spaceinvaders.core;

import nl.delftelectronics.spaceinvaders.gui.GUI;

public abstract class Actor extends Entity {
    protected static final Integer MOVING_SPEED = 5;

    public Actor(Integer positionX, Integer positionY, Integer width, Integer height) {
        super(positionX, positionY, width, height);
    }

    public void moveLeft() {
    	if(0 < getPositionX()){
        setPositionX(getPositionX() - MOVING_SPEED);
    	}
    }

    public void moveRight() {
    	if(GUI.getWindowWidth() - getWidth()/2 > getPositionX())
        setPositionX(getPositionX() + MOVING_SPEED);
    }
}
