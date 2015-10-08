/**
 * 
 */
package nl.delftelectronics.spaceinvaders.core.scenes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import javafx.scene.Scene;
import nl.delftelectronics.spaceinvaders.core.Engine;
import nl.delftelectronics.spaceinvaders.core.entities.*;

/**
 * The scene that contains and builds the game
 * @author Max
 *
 */
public class PlayScene extends GameScene {
	private static final int SMALL_ENEMY_ROWS = 1;
	private static final int MEDIUM_ENEMY_ROWS = 2;
	private static final int LARGE_ENEMY_ROWS = 2;
	private static final int ENEMY_COLUMNS = 12;
	private static final double SHIP_MARGIN_FROM_LEFT = 0.05; // ratio
	private static final double SHIP_MARGIN_FROM_BOTTOM = 0.1; // ratio
	private static final int UFO_MARGIN_FROM_TOP = 100; // pixels
	private static final double UFO_CHANCE = 0.002; // ratio
	private static final int ENTITY_DIMENSION = 100;
	private static final double COLUMN_WIDTH = 100;
	private static final double ROW_HEIGHT = 90;
	private static final double ENEMY_OFFSET_X = 10;

	private int points = 0;
	public int enemyCount = 0;
	private int currentLevel = 1;
	private int fieldWidth;
	private int fieldHeight;
	private Random random = new Random();
	public Ship ship;
	private boolean finished = false;
	private LabelEntity scoreLabel;
	private LabelEntity livesLabel;
	private LabelEntity levelLabel;
	private Collection<Entity> barricades = new ArrayList<Entity>();

	/**
	 * Builds a new PlayScene
	 * @param scene The javaFX scene to attach to
	 */
	public PlayScene(Scene scene) {
		this(scene, Ship.INITIAL_LIVES, 0, 0, 1, null);
	}

	public PlayScene(Scene scene, int lives, int bombs, int points, int level,
					 Collection<Entity> barricades) {
		super(scene);
		this.points = points;
		this.currentLevel = level;

		if (scene != null) {
			fieldWidth = (int) scene.getWidth();
			fieldHeight = (int) scene.getHeight();
		}

		EnemyBlock block = new EnemyBlock();
		addEntity(block);

		int shipPositionX = (int) (fieldWidth * SHIP_MARGIN_FROM_LEFT);
		int shipPositionY = (int) (fieldHeight * (1 - SHIP_MARGIN_FROM_BOTTOM));

		createEnemies(block);

		ship = new Ship(shipPositionX, shipPositionY,
				ENTITY_DIMENSION, ENTITY_DIMENSION, 0, fieldWidth);
		addEntity(ship);
		ship.setLives(lives);
		ship.setBombs(bombs);

		//CHECKSTYLE.OFF: MagicNumber - Don't want to layout automatically
		scoreLabel = new LabelEntity(30, 30, 0, 0, "Score: " + points);
		livesLabel = new LabelEntity(400, 30, 0, 0, "Lives: " + ship.getLives());
		levelLabel = new LabelEntity(700, 30, 0, 0, "Level: " + currentLevel);
		//CHECKSTYLE.ON: MagicNumber

		if (barricades == null) {
			createBarricades();
		} else {
			for (Entity e : barricades) {
				addEntity(e);
				this.barricades.add(e);
			}
		}

		addEntity(scoreLabel);
		addEntity(livesLabel);
		addEntity(levelLabel);
	}

	/**
	 * Creates the columns of enemies and adds them to the specified block
	 * @param block  The block to add the enemies to
	 */
	private void createEnemies(EnemyBlock block) {
		for (int column = 0; column < ENEMY_COLUMNS; column++) {
			int currentRow = 0;
			for (int smallEnemyRow = 0; smallEnemyRow < SMALL_ENEMY_ROWS; smallEnemyRow++) {
				Enemy smallEnemy = new SmallEnemy(ENEMY_OFFSET_X + COLUMN_WIDTH * column,
						ROW_HEIGHT * currentRow, ENTITY_DIMENSION, ENTITY_DIMENSION, 0,
						fieldWidth, fieldHeight, block);
				addEntity(smallEnemy);
				currentRow++;
				enemyCount++;
			}
			for (int mediumEnemyRow = 0; mediumEnemyRow < MEDIUM_ENEMY_ROWS; mediumEnemyRow++) {
				Enemy mediumEnemy = new MediumEnemy(ENEMY_OFFSET_X + COLUMN_WIDTH * column,
						ROW_HEIGHT * currentRow, ENTITY_DIMENSION, ENTITY_DIMENSION, 0,
						fieldWidth, fieldHeight, block);
				addEntity(mediumEnemy);
				currentRow++;
				enemyCount++;
			}
			for (int largeEnemyRow = 0; largeEnemyRow < LARGE_ENEMY_ROWS; largeEnemyRow++) {
				Enemy largeEnemy = new LargeEnemy(ENEMY_OFFSET_X + COLUMN_WIDTH * column,
						ROW_HEIGHT * currentRow, ENTITY_DIMENSION, ENTITY_DIMENSION, 0,
						fieldWidth, fieldHeight, block);
				addEntity(largeEnemy);
				currentRow++;
				enemyCount++;
			}
		}
	}

	/**
	 * Create four barricades in front of the player and add them to the scene.
	 */
	public void createBarricades() {
		//CHECKSTYLE.OFF: MagicNumber - Don't want to layout automatically
		for (int x = 100; x <= 300; x += 25) {
			for (int y = 700; y <= 800; y += 25) {
				Barricade b = new Barricade(x, y, 25, 25);
				addEntity(b);
				barricades.add(b);
			}
		}

		for (int x = 500; x <= 700; x += 25) {
			for (int y = 700; y <= 800; y += 25) {
				Barricade b = new Barricade(x, y, 25, 25);
				addEntity(b);
				barricades.add(b);
			}
		}

		for (int x = 900; x <= 1100; x += 25) {
			for (int y = 700; y <= 800; y += 25) {
				Barricade b = new Barricade(x, y, 25, 25);
				addEntity(b);
				barricades.add(b);
			}
		}

		for (int x = 1300; x <= 1500; x += 25) {
			for (int y = 700; y <= 800; y += 25) {
				Barricade b = new Barricade(x, y, 25, 25);
				addEntity(b);
				barricades.add(b);
			}
		}
		//CHECKSTYLE.ON: MagicNumber
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
	 * @return The amount of points scored
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * Adds a positive amount of points to the player score
	 * @param points  The amount of points the player scored
	 */
	public void addPoints(int points) {
		if (points < 0) {
			return;
		}
		this.points += points;
		
		scoreLabel.setText("Score: " + this.points);
	}

	@Override
	public void update() {
		if (finished) {
			handleAdditions();
			handleDeletions();
			return;
		}

		super.update();

		if (random.nextDouble() < UFO_CHANCE) {
			createUfo();
		}
		
		livesLabel.setText("Lives: " + ship.getLives());
	}

	/**
	 * Called when an entity thinks the player has lost
	 */
	public void lose() {
		if (finished) {
			return;
		}

		finished = true;
		//CHECKSTYLE.OFF: MagicNumber - Don't want to layout automatically
		LabelEntity gameOver = new LabelEntity(200, 200, 0, 0, "GAME OVER!");
		//CHECKSTYLE.ON: MagicNumber
		addEntity(gameOver);
	}

	/**
	 * Called when an entity thinks the player has won
	 */
	public void win() {
		if (finished) {
			return;
		} else {
			GameScene gs = new StoreScene(scene, ship.getLives(), getPoints(), ship.getBombs(),
					currentLevel, barricades);
			Engine.getInstance().setScene(gs);
		}
	}

	/**
	 * Return the current level.
	 * @return the current level
	 */
	public int getCurrentLevel() {
		return currentLevel;
	}
}
