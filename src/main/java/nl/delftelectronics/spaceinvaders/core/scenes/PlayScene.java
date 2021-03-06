/**
 *
 */
package nl.delftelectronics.spaceinvaders.core.scenes;

import javafx.scene.Scene;
import nl.delftelectronics.spaceinvaders.core.Engine;
import nl.delftelectronics.spaceinvaders.core.GameInformation;
import nl.delftelectronics.spaceinvaders.core.PlayingKeysFactory;
import nl.delftelectronics.spaceinvaders.core.entities.Barricade;
import nl.delftelectronics.spaceinvaders.core.entities.Enemy;
import nl.delftelectronics.spaceinvaders.core.entities.EnemyBlock;
import nl.delftelectronics.spaceinvaders.core.entities.EnemyFactory;
import nl.delftelectronics.spaceinvaders.core.entities.LabelClickedListener;
import nl.delftelectronics.spaceinvaders.core.entities.LabelEntity;
import nl.delftelectronics.spaceinvaders.core.entities.Ship;
import nl.delftelectronics.spaceinvaders.core.entities.Ufo;
import org.joda.time.Interval;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * The scene that contains and builds the game
 *
 * @author Max
 */
public class PlayScene extends GameScene implements LabelClickedListener {
	private static final int NUMBER_OF_PLAYERS = 1;
	private static final double DISTANCE_BETWEEN_PLAYERS = 200;
	protected static final double SHIP_MARGIN_FROM_LEFT = 0.05; // ratio
	protected static final double SHIP_MARGIN_FROM_BOTTOM = 0.1; // ratio
	private static final int UFO_MARGIN_FROM_TOP = 100; // pixels
	private static final double UFO_CHANCE = 0.002; // ratio
	protected static final int ENTITY_DIMENSION = 100;

	public int enemyCount = 0;
	protected GameInformation gameInformation;
	protected int fieldWidth;
	protected int fieldHeight;
	private Random random = new Random();
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
		this(scene, new GameInformation(0, Ship.INITIAL_LIVES, 0, 1, createBarricades()),
				NUMBER_OF_PLAYERS);
	}

	/**
	 * Builds a new PlayScene with a number of players.
	 *
	 * @param scene The javaFX scene to attach to
     * @param numOfPlayers number of players participating in this game
	 */
	public PlayScene(Scene scene, int numOfPlayers) {
		this(scene, new GameInformation(0, Ship.INITIAL_LIVES, 0, 1, createBarricades()),
				numOfPlayers);
	}

    /**
     * Builds a new PlayScene with a single player.
     *
     * @param scene The javaFX scene to attach to
     * @param gameInformation the information and values about the current game
     */
	public PlayScene(Scene scene, GameInformation gameInformation) {
		this(scene, gameInformation, NUMBER_OF_PLAYERS);
	}

	/**
	 * Create a new PlayScene. The PlayScene is the scene where the player can play Space Invaders.
	 *
	 * @param scene the JavaFX scene
	 * @param gameInformation the information and values about the current game
	 * @param numOfPlayers number of players participating in this game
	 */
	public PlayScene(Scene scene, GameInformation gameInformation, int numOfPlayers) {
		super(scene);

		this.gameInformation = gameInformation;

		if (scene != null) {
			fieldWidth = (int) scene.getWidth();
			fieldHeight = (int) scene.getHeight();
		}

		addEnemies();

		addShips(numOfPlayers);

		//CHECKSTYLE.OFF: MagicNumber - Don't want to layout automatically
		scoreLabel = new LabelEntity(new Rectangle2D.Double(30, 30, 0, 0),
				"Score: " + gameInformation.getPoints());
		livesLabel = new LabelEntity(new Rectangle2D.Double(400, 30, 0, 0),
				"Lives: " + gameInformation.getLives());
		levelLabel = new LabelEntity(new Rectangle2D.Double(700, 30, 0, 0),
				"Level: " + gameInformation.getLevel());
		bombsLabel = new LabelEntity(new Rectangle2D.Double(1000, 30, 0, 0),
				"Bombs: " + gameInformation.getBombs());
		gameOver = new LabelEntity(new Rectangle2D.Double(550, 500, 400, 100),
				"BACK TO MAIN MENU");
		//CHECKSTYLE.ON: MagicNumber

		for (Rectangle2D r : gameInformation.getBarricadeRectangles()) {
			Barricade b = new Barricade(r);
			b.addDestroyedListener(gameInformation);
			addEntity(b);
		}
		addEntity(scoreLabel);
		addEntity(livesLabel);
		addEntity(levelLabel);
		addEntity(bombsLabel);
	}

	/**
	 * Adds enemy entities to the scene
	 */
	private void addEnemies() {
		EnemyBlock block = new EnemyBlock();
		addEntity(block);

		EnemyFactory f = new EnemyFactory(block, fieldWidth, fieldHeight);
		List<Enemy> enemies = f.createBlock();
		enemyCount = enemies.size();
		for (Enemy e : enemies) {
			addEntity(e);
		}
	}

	/**
	 * Create a ship (or multiple ships) that are added to the scene.
	 */
	private void addShips(int numOfPlayers) {
		PlayingKeysFactory keyFactory = new PlayingKeysFactory();
		for (int i = 0; i < numOfPlayers; i++) {
			int shipPositionX = (int) (fieldWidth * SHIP_MARGIN_FROM_LEFT
					+ DISTANCE_BETWEEN_PLAYERS * i);
			int shipPositionY = (int) (fieldHeight * (1 - SHIP_MARGIN_FROM_BOTTOM));
			Ship ship = makeShip(new Rectangle2D.Double(shipPositionX, shipPositionY,
					ENTITY_DIMENSION, ENTITY_DIMENSION), 0, fieldWidth, gameInformation);
			ship.setPlayingKeys(keyFactory.next());
			addEntity(ship);
			System.out.println(ship.getPositionX());
		}
	}

    /**
     * Create a playable ship
     *
     * @param position position of the ship
     * @param westBoundary westernmost boundary of the ship
     * @param eastBoundary easternmost boundary of the ship
     * @param gameInformation information about the current game
     * @return a newly created ship
     */
	protected Ship makeShip(Rectangle2D position, int westBoundary, int eastBoundary,
							GameInformation gameInformation) {
		return new Ship(position, westBoundary, eastBoundary, gameInformation);
	}

	/**
	 * Create four barricades in front of the player.
	 *
	 * @return the created barricades
	 */
	public static Collection<Rectangle2D> createBarricades() {
		Collection<Rectangle2D> barricades = new ArrayList<Rectangle2D>();
		//CHECKSTYLE.OFF: MagicNumber - Don't want to layout automatically
		for (int x = 100; x <= 300; x += 25) {
			for (int y = 700; y <= 800; y += 25) {
				barricades.add(new Rectangle2D.Double(x, y, 25, 25));
			}
		}

		for (int x = 500; x <= 700; x += 25) {
			for (int y = 700; y <= 800; y += 25) {
				barricades.add(new Rectangle2D.Double(x, y, 25, 25));
			}
		}

		for (int x = 900; x <= 1100; x += 25) {
			for (int y = 700; y <= 800; y += 25) {
				barricades.add(new Rectangle2D.Double(x, y, 25, 25));
			}
		}

		for (int x = 1300; x <= 1500; x += 25) {
			for (int y = 700; y <= 800; y += 25) {
				barricades.add(new Rectangle2D.Double(x, y, 25, 25));
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
			gameOver.update(new Interval(0, 1));
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
