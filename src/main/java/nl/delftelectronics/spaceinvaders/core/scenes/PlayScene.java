/**
 *
 */
package nl.delftelectronics.spaceinvaders.core.scenes;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import javafx.scene.Scene;
import nl.delftelectronics.spaceinvaders.core.Engine;
import nl.delftelectronics.spaceinvaders.core.GameInformation;
import nl.delftelectronics.spaceinvaders.core.Rectangle;
import nl.delftelectronics.spaceinvaders.core.entities.*;
import org.joda.time.Interval;

/**
 * The scene that contains and builds the game
 *
 * @author Max
 */
public class PlayScene extends GameScene implements LabelClickedListener {
	private static final double SHIP_MARGIN_FROM_LEFT = 0.05; // ratio
	private static final double SHIP_MARGIN_FROM_BOTTOM = 0.1; // ratio
	private static final int UFO_MARGIN_FROM_TOP = 100; // pixels
	private static final double UFO_CHANCE = 0.002; // ratio
	private static final int ENTITY_DIMENSION = 100;

	public int enemyCount = 0;
	private GameInformation gameInformation;
	private int fieldWidth;
	private int fieldHeight;
	private Random random = new Random();
	public Ship ship;
	private boolean finished = false;
	private LabelEntity scoreLabel;
	private LabelEntity livesLabel;
	private LabelEntity levelLabel;
	private LabelEntity bombsLabel;
	private LabelEntity gameOver;

	/**
	 * Builds a new PlayScene
	 *
	 * @param scene The javaFX scene to attach to
	 */
	public PlayScene(Scene scene) {
		this(scene, new GameInformation(0, Ship.INITIAL_LIVES, 0, 1, createBarricades()));
	}

	/**
	 * Create a new PlayScene. The PlayScene is the scene where the player can play Space Invaders.
	 *
	 * @param scene the JavaFX scene
	 * @param gameInformation the information and values about the current game
	 */
	public PlayScene(Scene scene, GameInformation gameInformation) {
		super(scene);

		this.gameInformation = gameInformation;

		if (scene != null) {
			fieldWidth = (int) scene.getWidth();
			fieldHeight = (int) scene.getHeight();
		}

		EnemyBlock block = new EnemyBlock();
		addEntity(block);

		int shipPositionX = (int) (fieldWidth * SHIP_MARGIN_FROM_LEFT);
		int shipPositionY = (int) (fieldHeight * (1 - SHIP_MARGIN_FROM_BOTTOM));

		EnemyFactory f = new EnemyFactory(block, fieldWidth, fieldHeight);
		List<Enemy> enemies = f.createBlock();
		enemyCount = enemies.size();
		for (Enemy e : enemies) {
			addEntity(e);
		}

		ship = new Ship(shipPositionX, shipPositionY,
				ENTITY_DIMENSION, ENTITY_DIMENSION, 0, fieldWidth, gameInformation);
		addEntity(ship);

		//CHECKSTYLE.OFF: MagicNumber - Don't want to layout automatically
		scoreLabel = new LabelEntity(30, 30, 0, 0, "Score: " + gameInformation.getPoints());
		livesLabel = new LabelEntity(400, 30, 0, 0, "Lives: " + gameInformation.getLives());
		levelLabel = new LabelEntity(700, 30, 0, 0, "Level: " + gameInformation.getLevel());
		bombsLabel = new LabelEntity(1000, 30, 0, 0, "Bombs: " + gameInformation.getBombs());
		gameOver = new LabelEntity(550, 500, 400, 100, "BACK TO MAIN MENU");
		//CHECKSTYLE.ON: MagicNumber

		for (Rectangle r : gameInformation.getBarricadeRectangles()) {
			Barricade b = new Barricade(r.getX(), r.getY(), r.getWidth(), r.getHeight());
			b.addDestroyedListener(gameInformation);
			addEntity(b);
		}

		addEntity(scoreLabel);
		addEntity(livesLabel);
		addEntity(levelLabel);
		addEntity(bombsLabel);
	}

	/**
	 * Create four barricades in front of the player.
	 *
	 * @return the created barricades
	 */
	public static Collection<Rectangle> createBarricades() {
		Collection<Rectangle> barricades = new ArrayList<Rectangle>();
		//CHECKSTYLE.OFF: MagicNumber - Don't want to layout automatically
		for (int x = 100; x <= 300; x += 25) {
			for (int y = 700; y <= 800; y += 25) {
				barricades.add(new Rectangle(x, y, 25, 25));
			}
		}

		for (int x = 500; x <= 700; x += 25) {
			for (int y = 700; y <= 800; y += 25) {
				barricades.add(new Rectangle(x, y, 25, 25));
			}
		}

		for (int x = 900; x <= 1100; x += 25) {
			for (int y = 700; y <= 800; y += 25) {
				barricades.add(new Rectangle(x, y, 25, 25));
			}
		}

		for (int x = 1300; x <= 1500; x += 25) {
			for (int y = 700; y <= 800; y += 25) {
				barricades.add(new Rectangle(x, y, 25, 25));
			}
		}
		//CHECKSTYLE.ON: MagicNumber
		return barricades;
	}

	/**
	 * Create an ufo, that flies from one (randomly decided) side to the other.
	 * It is worth between 100 and 1000 points if shot.
	 */
	public void createUfo() {
		int randomBoundary;
		EnemyBlock ufoBlock = new EnemyBlock();

		if (random.nextBoolean()) {
			randomBoundary = 0;
		} else {
			randomBoundary = fieldWidth;
			ufoBlock.flip();
		}
		ufoBlock.preUpdate(null);

		Ufo ufo = new Ufo(randomBoundary, UFO_MARGIN_FROM_TOP, 0, fieldWidth, ufoBlock);
		addEntity(ufo);
	}

	/**
	 * Gets the current score for the player
	 *
	 * @return The amount of points scored
	 */
	public int getPoints() {
		return gameInformation.getPoints();
	}

	/**
	 * Adds a positive amount of points to the player score
	 *
	 * @param points The amount of points the player scored
	 */
	public void addPoints(int points) {
		if (points < 0) {
			return;
		}
		gameInformation.addPoints(points);

		scoreLabel.setText("Score: " + gameInformation.getPoints());
	}

	@Override
	public void update() {
		if (finished) {
			handleAdditions();
			handleDeletions();
			// Only the game over button needs to be updated.
			gameOver.update(new Interval(0,1));
			return;
		}

		super.update();

		if (random.nextDouble() < UFO_CHANCE) {
			createUfo();
		}

		livesLabel.setText("Lives: " + gameInformation.getLives());
		bombsLabel.setText("Bombs: " + gameInformation.getBombs());
	}

	/**
	 * Called when an entity thinks the player has lost
	 */
	public void lose() {
		if (finished) {
			return;
		}

		finished = true;
		gameOver.addClickedListener(this);
		addEntity(gameOver);
	}

	/**
	 * Called when an entity thinks the player has won
	 */
	public void win() {
		if (finished) {
			return;
		} else {
			GameScene gs = new StoreScene(scene, gameInformation);
			Engine.getInstance().setScene(gs);
		}
	}

	/**
	 * Return the current level.
	 *
	 * @return the current level
	 */
	public int getCurrentLevel() {
		return gameInformation.getLevel();
	}

	/**
	 * Called when a label is clicked.
	 *
	 * @param label the label that was clicked.
	 */
	public void labelClicked(LabelEntity label) {
		if (label == gameOver) {
			GameScene newScene = new MenuScene(scene);
			Engine.getInstance().setScene(newScene);
		}
	}
}
