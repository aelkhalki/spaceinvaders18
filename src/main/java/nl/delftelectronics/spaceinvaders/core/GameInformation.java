package nl.delftelectronics.spaceinvaders.core;

import nl.delftelectronics.spaceinvaders.core.entities.Barricade;
import nl.delftelectronics.spaceinvaders.core.entities.BarricadeDestroyedListener;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
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
    private Collection<Rectangle2D> barricadeRectangles;

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
                           Collection<Rectangle2D> barricadeRectangles) {
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
            save(fos);
        } catch (IOException e) {
            Logger.error("The save game file could not be written to. Do you have the correct"
                    + "permissions?");
        }
    }

    /**
     * Save the game information to the filename.
     *
     * @param os OutputStream to write to
     */
    public void save(OutputStream os) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(this);
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
        try {
            File file = new File("." + File.separator, filename);
            FileInputStream fin = new FileInputStream(file);
            return load(fin);
        } catch (FileNotFoundException e) {
            Logger.error("The save game file could not be found.");
            return Optional.empty();
        }
    }

    /**
     * Load the game information from the filename.
     *
     * @param is input stream of the game information
     * @return the loaded game information
     */
    public static Optional<GameInformation> load(InputStream is) {
        Optional<GameInformation> gi = Optional.empty();
        try {
            ObjectInputStream ois = new ObjectInputStream(is);
            gi = Optional.of((GameInformation) ois.readObject());
        } catch (IOException e) {
            Logger.error("The save game file could not be accessed.");
        } catch (ClassNotFoundException e) {
            Logger.error("Apparently, a class that is stored in the save file is not available. "
                    + "Perhaps the save file is from an older version of the game.");
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
    public Collection<Rectangle2D> getBarricadeRectangles() {
        return barricadeRectangles;
    }

    /**
     * Set the collection of rectangles of the barricades.
     *
     * @param barricadeRectangles collection of rectangles of the barricades
     */
    public void setBarricadeRectangles(Collection<Rectangle2D> barricadeRectangles) {
        this.barricadeRectangles = barricadeRectangles;
    }

    /**
     * Called when a barricade is destroyed. The barricade is removed from the game information.
     *
     * @param barricade barricade that is destroyed.
     */
    public void barricadeDestroyed(Barricade barricade) {
        Rectangle2D rectangle = new Rectangle2D.Double(barricade.getPositionX(),
                barricade.getPositionY(), barricade.getWidth(), barricade.getHeight());
        barricadeRectangles.remove(rectangle);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GameInformation that = (GameInformation) o;

        if (points != that.points) {
            return false;
        }
        if (lives != that.lives) {
            return false;
        }
        if (bombs != that.bombs) {
            return false;
        }
        if (level != that.level) {
            return false;
        }
        return !(barricadeRectangles != null ?
                !barricadeRectangles.equals(that.barricadeRectangles) :
                that.barricadeRectangles != null);

    }

    @Override
    public int hashCode() {
        // CHECKSTYLE:OFF - MagicNumber
        int result = points;
        result = 31 * result + lives;
        result = 31 * result + bombs;
        result = 31 * result + level;
        result = 31 * result + (barricadeRectangles != null ? barricadeRectangles.hashCode() : 0);
        return result;
        // CHECKSTYLE:ON
    }
}
