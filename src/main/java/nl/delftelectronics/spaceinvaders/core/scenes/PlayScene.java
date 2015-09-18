/**
 * 
 */
package nl.delftelectronics.spaceinvaders.core.scenes;

import java.util.Random;

import javafx.scene.Scene;
import nl.delftelectronics.spaceinvaders.core.entities.Enemy;
import nl.delftelectronics.spaceinvaders.core.entities.EnemyBlock;
import nl.delftelectronics.spaceinvaders.core.entities.LabelEntity;
import nl.delftelectronics.spaceinvaders.core.entities.LargeEnemy;
import nl.delftelectronics.spaceinvaders.core.entities.MediumEnemy;
import nl.delftelectronics.spaceinvaders.core.entities.Ship;
import nl.delftelectronics.spaceinvaders.core.entities.SmallEnemy;
import nl.delftelectronics.spaceinvaders.core.entities.Ufo;

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

	private int points = 0;
	public int enemyCount = 0;
	private int fieldWidth;
	private int fieldHeight;
	private Random random = new Random();
	public Ship ship;
	private boolean finished = false;
	private LabelEntity scoreLabel;
	private LabelEntity livesLabel;

	/**
	 * Builds a new PlayScene
	 * @param scene The javaFX scene to attach to
	 */
	public PlayScene(Scene scene) {
		super(scene);
		
		fieldWidth = (int) scene.getWidth();
		fieldHeight = (int) scene.getHeight();

		EnemyBlock block = new EnemyBlock();
		addEntity(block);

		int shipPositionX = (int) (fieldWidth * SHIP_MARGIN_FROM_LEFT);
		int shipPositionY = (int) (fieldHeight * (1 - SHIP_MARGIN_FROM_BOTTOM));

		for (int column = 0; column < ENEMY_COLUMNS; column++) {
			int currentRow = 0;
			for (int smallEnemyRow = 0; smallEnemyRow < SMALL_ENEMY_ROWS; smallEnemyRow++) {
				Enemy smallEnemy = new SmallEnemy(10 + 100 * column, 90 * currentRow,
						ENTITY_DIMENSION, ENTITY_DIMENSION, 0, fieldWidth, fieldHeight, block);
				addEntity(smallEnemy);
				currentRow++;
				enemyCount++;
			}
			for (int mediumEnemyRow = 0; mediumEnemyRow < MEDIUM_ENEMY_ROWS; mediumEnemyRow++) {
				Enemy mediumEnemy = new MediumEnemy(10 + 100 * column, 90 * currentRow,
						ENTITY_DIMENSION, ENTITY_DIMENSION, 0, fieldWidth, fieldHeight, block);
				addEntity(mediumEnemy);
				currentRow++;
				enemyCount++;
			}
			for (int largeEnemyRow = 0; largeEnemyRow < LARGE_ENEMY_ROWS; largeEnemyRow++) {
				Enemy largeEnemy = new LargeEnemy(10 + 100 * column, 90 * currentRow,
						ENTITY_DIMENSION, ENTITY_DIMENSION, 0, fieldWidth, fieldHeight, block);
				addEntity(largeEnemy);
				currentRow++;
				enemyCount++;
			}
		}

		ship = new Ship(shipPositionX, shipPositionY,
				ENTITY_DIMENSION, ENTITY_DIMENSION, 0, fieldWidth);
		addEntity(ship);
		
		//CHECKSTYLE:OFF - MagicNumber
		scoreLabel = new LabelEntity(30,30,0,0, "Score: " + points);
		livesLabel = new LabelEntity(400, 30, 0, 0, "Lives: " + ship.getLives());
		//CHECKSTYLE:ON - MagicNumber
		
		addEntity(scoreLabel);
		addEntity(livesLabel);
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
		
		scoreLabel.setText("Score: " + points);
	}

	@Override
	public void update() {
		if (finished) {
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
		LabelEntity gameOver = new LabelEntity(200, 200, 0, 0, "GAME OVER!");
		addEntity(gameOver);
	}

	/**
	 * Called when an entity thinks the player has won
	 */
	public void win() {
		if (finished) {
			return;
		}

		finished = true;
		LabelEntity wonGame = new LabelEntity(200, 200, 0, 0, "YOU WON!");
		addEntity(wonGame);
	}
}
