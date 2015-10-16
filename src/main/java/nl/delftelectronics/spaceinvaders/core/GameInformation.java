package nl.delftelectronics.spaceinvaders.core;

import nl.delftelectronics.spaceinvaders.core.entities.Barricade;
import nl.delftelectronics.spaceinvaders.core.entities.BarricadeDestroyedListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

/**
 * Stores the information about the current game, i.e. the points, leves, bombs, levels and the
 * barricades.
 */
public class GameInformation implements BarricadeDestroyedListener, Serializable {
    public static final String SAVEGAME_FILENAME = "spaceinvaders.savefile";
    private int points;
    private int lives;
    private int bombs;
    private int level;
    private Collection<Rectangle> barricadeRectangles;

    /**
     * Create a GameInformation object.
     *
     * @param points              the number of points
     * @param lives               the number of lives
     * @param bombs               the number of bombs
     * @param level               the current level
     * @param barricadeRectangles a collection of rectangles of the barricades
     */
    public GameInformation(int points, int lives, int bombs, int level,
                           Collection<Rectangle> barricadeRectangles) {
        this.points = points;
        this.lives = lives;
        this.bombs = bombs;
        this.level = level;
        this.barricadeRectangles = barricadeRectangles;
    }

    /**
     * Save the game information to the default save game file location.
     */
    public void save() {
        save(SAVEGAME_FILENAME);
    }

    /**
     * Save the game information to the filename.
     *
     * @param filename filename to write to
     */
    public void save(String filename) {
        try {
            File file = new File("." + File.separator, filename);
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
        } catch (FileNotFoundException e) {
            Logger.error("The save game file could not be found, even after the file is created.");
        } catch (IOException e) {
            Logger.error("The save game file could not be written to. Do you have the correct"
                    + "permissions? Or perhaps you're trying to serialize an unserializable "
                    + "object?");
        }
    }

    /**
     * Load the game information from the default save game file location,
     *
     * @return the loaded game information
     */
    public static Optional<GameInformation> load() {
        return load(SAVEGAME_FILENAME);
    }

    /**
     * Load the game information from the filename.
     *
     * @param filename filename of the save game
     * @return the loaded game information
     */
    public static Optional<GameInformation> load(String filename) {
        Optional<GameInformation> gi = Optional.empty();
        FileInputStream fin = null;
        try {
            File file = new File("." + File.separator, filename);
            fin = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            Logger.error("The save game file could not be found.");
        }
        try {
            ObjectInputStream ois = new ObjectInputStream(fin);
            gi = Optional.of((GameInformation) ois.readObject());
        } catch (IOException e) {
            Logger.error("The save game file could not be accessed.");
        } catch (ClassNotFoundException e) {
            // This case should not even happen.
            Logger.error("Apparently, a class that is stored in the save file is not available.");
        }
        return gi;
    }

    /**
     * Increment the current level.
     */
    public void incrementLevel() {
        level++;
    }

    /**
     * Returns true iff lives is not zero.
     *
     * @return true iff lives is not zero
     */
    public boolean hasLives() {
        return lives != 0;
    }

    /**
     * Increment the number of lives.
     */
    public void incrementLives() {
        lives++;
    }

    /**
     * Decrement the number of lives.
     */
    public void decrementLives() {
        lives--;
    }

    /**
     * Returns true iff the player has any bombs.
     *
     * @return true iff the player has any bombs.
     */
    public boolean hasBombs() {
        return bombs != 0;
    }

    /**
     * Increment the number of bombs.
     */
    public void incrementBombs() {
        bombs++;
    }

    /**
     * Decrement the number of bombs.
     */
    public void decrementBombs() {
        bombs--;
    }

    /**
     * Add the given number of points.
     *
     * @param points number of points to add.
     */
    public void addPoints(int points) {
        this.points += points;
    }

    /**
     * Subtract the given number of points.
     *
     * @param points number of points to subtract.
     */
    public void subtractPoints(int points) {
        this.points -= points;
    }

    /**
     * Return the current number of points.
     *
     * @return the current number of points.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Return the current number of lives.
     *
     * @return the current number of lives.
     */
    public int getLives() {
        return lives;
    }

    /**
     * Return the current number of bombs.
     *
     * @return the current number of bombs.
     */
    public int getBombs() {
        return bombs;
    }

    /**
     * Return the current level.
     *
     * @return the current level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Return the collection of rectangles of the barricades.
     *
     * @return the collection of rectangles of the barricades
     */
    public Collection<Rectangle> getBarricadeRectangles() {
        return barricadeRectangles;
    }

    /**
     * Set the collection of rectangles of the barricades.
     *
     * @param barricadeRectangles collection of rectangles of the barricades
     */
    public void setBarricadeRectangles(Collection<Rectangle> barricadeRectangles) {
        this.barricadeRectangles = barricadeRectangles;
    }

    /**
     * Called when a barricade is destroyed. The barricade is removed from the game information.
     *
     * @param barricade barricade that is destroyed.
     */
    public void barricadeDestroyed(Barricade barricade) {
        Rectangle rectangle = new Rectangle(barricade.getPositionX(), barricade.getPositionY(),
                barricade.getWidth(), barricade.getHeight());
        barricadeRectangles.remove(rectangle);
    }
}
