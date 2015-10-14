package nl.delftelectronics.spaceinvaders.core;

import nl.delftelectronics.spaceinvaders.core.entities.Barricade;
import nl.delftelectronics.spaceinvaders.core.entities.BarricadeDestroyedListener;
import nl.delftelectronics.spaceinvaders.core.scenes.StoreScene;

import java.io.*;
import java.util.Collection;
import java.util.Optional;

public class GameInformation implements BarricadeDestroyedListener, Serializable {
    public static String SAVEGAME_FILENAME = "spaceinvaders.savefile";
    private int points;
    private int lives;
    private int bombs;
    private int level;
    private Collection<Rectangle> barricadeRectangles;

    public GameInformation(int points, int lives, int bombs, int level,
                           Collection<Rectangle> barricadeRectangles) {
        this.points = points;
        this.lives = lives;
        this.bombs = bombs;
        this.level = level;
        this.barricadeRectangles = barricadeRectangles;
    }

    public void save() {
        save(SAVEGAME_FILENAME);
    }

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
            Logger.error("The save game file could not be written to. Do you have the correct " +
                    "permissions? Or perhaps you're trying to serialize an unserializable object?");
        }
    }

    public static Optional<GameInformation> load() {
        return load(SAVEGAME_FILENAME);
    }

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

    public void incrementLevel() {
        level++;
    }

    public boolean hasLives() {
        return lives != 0;
    }

    public void incrementLives() {
        lives++;
    }

    public void decrementLives() {
        lives--;
    }

    public boolean hasBombs() {
        return bombs != 0;
    }

    public void incrementBombs() {
        bombs++;
    }

    public void decrementBombs() {
        bombs--;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public void subtractPoints(int points) {
        this.points -= points;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getBombs() {
        return bombs;
    }

    public void setBombs(int bombs) {
        this.bombs = bombs;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public Collection<Rectangle> getBarricadeRectangles() {
        return barricadeRectangles;
    }

    public void setBarricadeRectangles(Collection<Rectangle> barricadeRectangles) {
        this.barricadeRectangles = barricadeRectangles;
    }

    public void barricadeDestroyed(Barricade barricade) {
        Rectangle rectangle = new Rectangle(barricade.getPositionX(), barricade.getPositionY(),
                barricade.getWidth(), barricade.getHeight());
        barricadeRectangles.remove(rectangle);
    }
}
