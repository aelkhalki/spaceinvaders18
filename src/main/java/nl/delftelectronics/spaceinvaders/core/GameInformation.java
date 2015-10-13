package nl.delftelectronics.spaceinvaders.core;

import nl.delftelectronics.spaceinvaders.core.entities.Barricade;
import nl.delftelectronics.spaceinvaders.core.entities.Entity;

import java.io.Serializable;
import java.util.Collection;

public class GameInformation implements Serializable {
    private int points;
    private int lives;
    private int bombs;
    private int level;
    private Collection<Entity> savedEntities;

    public GameInformation(int points, int lives, int bombs, int level,
                           Collection<Entity> savedEntities) {
        this.points = points;
        this.lives = lives;
        this.bombs = bombs;
        this.level = level;
        this.savedEntities = savedEntities;
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

    public Collection<Entity> getSavedEntities() {
        return savedEntities;
    }

    public void setSavedEntities(Collection<Entity> savedEntities) {
        this.savedEntities = savedEntities;
    }
}
