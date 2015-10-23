package nl.delftelectronics.spaceinvaders.core.scenes;

import javafx.scene.Scene;
import nl.delftelectronics.spaceinvaders.core.Engine;
import nl.delftelectronics.spaceinvaders.core.GameInformation;
import nl.delftelectronics.spaceinvaders.core.entities.LabelClickedListener;
import nl.delftelectronics.spaceinvaders.core.entities.LabelEntity;

import java.awt.geom.Rectangle2D;

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
    private LabelEntity saveGameLabel;
    private LabelEntity continueLabel;
    private GameInformation gameInformation;

    /**
     * Create the store scene, and the accompanying labels.
     *
     * @param scene           the JavaFX scene to bind to
     * @param gameInformation the information and values about the current game
     */
    public StoreScene(Scene scene, GameInformation gameInformation) {
        super(scene);
        this.gameInformation = gameInformation;

        //CHECKSTYLE.OFF: MagicNumber
        pointsLabel = new LabelEntity(new Rectangle2D.Double(50, 50, 1000, 100),
        		"Points: " + gameInformation.getPoints());
        addEntity(pointsLabel);
        lifeLabel = new LabelEntity(new Rectangle2D.Double(50, 150, 1000, 100),
"Get a life (1000 points). Current lives: "
                + gameInformation.getLives());
        lifeLabel.addClickedListener(this);
        addEntity(lifeLabel);
        bombLabel = new LabelEntity(new Rectangle2D.Double(50, 250, 1000, 100),
        		"Get a bomb (250 points). Current bombs: "
                + gameInformation.getBombs());

        bombLabel.addClickedListener(this);
        addEntity(bombLabel);
        barricadeLabel = new LabelEntity(new Rectangle2D.Double(50, 350, 1000, 100),
        		"Restore barricades (500 points).");
        barricadeLabel.addClickedListener(this);
        addEntity(barricadeLabel);
        saveGameLabel = new LabelEntity(new Rectangle2D.Double(50, 450, 1000, 100),
        		"Save game.");
        saveGameLabel.addClickedListener(this);
        addEntity(saveGameLabel);
        continueLabel = new LabelEntity(new Rectangle2D.Double(50, 650, 1000, 100),
        		"Continue");
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
        if (lifeLabel.equals(label) && gameInformation.getPoints() >= LIFE_COST) {
            gameInformation.incrementLives();
            lifeLabel.setText("Get a life (1000 points). Current lives: "
                    + gameInformation.getLives());
            gameInformation.subtractPoints(LIFE_COST);
        } else if (bombLabel.equals(label) && gameInformation.getPoints() >= BOMB_COST) {
            gameInformation.incrementBombs();
            bombLabel.setText("Get a bomb (250 points). Current bombs: "
                    + gameInformation.getBombs());
            gameInformation.subtractPoints(BOMB_COST);
        } else if (barricadeLabel.equals(label) && gameInformation.getPoints() >= BARRICADE_COST) {
            gameInformation.setBarricadeRectangles(PlayScene.createBarricades());
            barricadeLabel.setText("Restore barricades (500 points). Done!");
            gameInformation.subtractPoints(BARRICADE_COST);
        } else if (saveGameLabel.equals(label)) {
            gameInformation.save();
            saveGameLabel.setText("Save game. Done!");
        } else if (continueLabel.equals(label)) {
            continueGame();
        }
        pointsLabel.setText("Points: " + gameInformation.getPoints());
    }

    /**
     * Terminate the store and go to the next level.
     */
    public void continueGame() {
        gameInformation.incrementLevel();
        GameScene gs;
        if (gameInformation.isMultiplayer()) {
            gs = new MultiplayerPlayScene(scene, gameInformation);
        } else {
            gs = new PlayScene(scene, gameInformation);
        }
        Engine.getInstance().setScene(gs);
    }

}
