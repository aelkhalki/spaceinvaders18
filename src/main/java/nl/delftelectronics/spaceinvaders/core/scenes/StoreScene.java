package nl.delftelectronics.spaceinvaders.core.scenes;

import javafx.scene.Scene;
import nl.delftelectronics.spaceinvaders.core.Engine;
import nl.delftelectronics.spaceinvaders.core.entities.Entity;
import nl.delftelectronics.spaceinvaders.core.entities.LabelClickedListener;
import nl.delftelectronics.spaceinvaders.core.entities.LabelEntity;

import java.util.Collection;

/**
 * The scene that contains the store.
 */
public class StoreScene extends GameScene implements LabelClickedListener {
    private static final int LIFE_COST = 1000;
    private static final int BOMB_COST = 250;
    private static final int BARRICADE_COST = 500;
    private LabelEntity pointsLabel;
    private LabelEntity lifeLabel;
    private LabelEntity bombLabel;
    private LabelEntity barricadeLabel;
    private LabelEntity continueLabel;
    private Collection<Entity> barricades;
    private int lives;
    private int bombs;
    private int level;
    private int points;

    /**
     * Create the store scene, and the accompanying labels.
     * @param scene the JavaFX scene
     * @param lives the number of lives from the previous level
     * @param points the number of points from the previous level
     * @param bombs the number of bombs from the previous level
     * @param level the previous level
     * @param barricades the remnants of the barricades from the previous level
     */
    public StoreScene(Scene scene, int lives, int points, int bombs, int level,
                      Collection<Entity> barricades) {
        super(scene);
        this.lives = lives;
        this.points = points;
        this.bombs = bombs;
        this.level = level;
        this.barricades = barricades;

        //CHECKSTYLE.OFF: MagicNumber
        pointsLabel = new LabelEntity(50, 50, 1000, 100, "Points: " + points);
        addEntity(pointsLabel);
        lifeLabel = new LabelEntity(50, 150, 1000, 100, "Get a life (1000 points). Current lives: "
                + lives);
        lifeLabel.addClickedListener(this);
        addEntity(lifeLabel);
        bombLabel = new LabelEntity(50, 250, 1000, 100, "Get a bomb (250 points). Current bombs: "
                + bombs);
        bombLabel.addClickedListener(this);
        addEntity(bombLabel);
        barricadeLabel = new LabelEntity(50, 350, 1000, 100, "Restore barricades (500 points).");
        barricadeLabel.addClickedListener(this);
        addEntity(barricadeLabel);
        continueLabel = new LabelEntity(50, 450, 1000, 100, "Continue");
        continueLabel.addClickedListener(this);
        addEntity(continueLabel);
        //CHECKSTYLE.ON: MagicNumber
    }

    /**
     * Called when a label is clicked.
     *
     * @param label the label that was clicked.
     */
    public void labelClicked(LabelEntity label) {
        if (lifeLabel.equals(label) && points >= LIFE_COST) {
            lifeLabel.setText("Get a life (1000 points). Current lives: " + ++lives);
            points -= LIFE_COST;
        } else if (bombLabel.equals(label) && points >= BOMB_COST) {
            bombLabel.setText("Get a bomb (250 points). Current bombs: " + ++bombs);
            points -= BOMB_COST;
        } else if (barricadeLabel.equals(label) && points >= BARRICADE_COST) {
            barricades = null;
            barricadeLabel.setText("Restore barricades (500 points). Done!");
            points -= BARRICADE_COST;
        } else if (continueLabel.equals(label)) {
            continueGame();
        }
        pointsLabel.setText("Points: " + points);
    }

    /**
     * Terminate the store and go to the next level.
     */
    public void continueGame() {
        GameScene gs = new PlayScene(scene, lives, bombs, points, level + 1, barricades);
        Engine.getInstance().setScene(gs);
    }

}
