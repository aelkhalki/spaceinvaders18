package nl.delftelectronics.spaceinvaders.core;

/**
 * A Displayable is an object which can be displayed on the screen, so it needs to have a
 * (filename of) the sprite.
 */
public interface Displayable {
    /**
     * Return the filename of the image used to display the object.
     *
     * @return the filename of the image used to display the object.
     */
    String getSpriteFilename();
}
