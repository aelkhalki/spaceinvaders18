/**
 * 
 */
package nl.delftelectronics.spaceinvaders.core;

import java.util.Random;

import javafx.scene.Scene;

/**
 * @author Max
 *
 */
public class PlayScene extends GameScene {
    private static final int SMALL_ENEMY_ROWS = 1;
    private static final int MEDIUM_ENEMY_ROWS = 2;
    private static final int LARGE_ENEMY_ROWS = 2;
    private static final int ENEMY_COLUMNS = 12;
    private static final double SHIP_MARGIN_FROM_LEFT = 0.05;          // ratio
    private static final double SHIP_MARGIN_FROM_BOTTOM = 0.1;         // ratio
    private static final int ENEMY_MARGIN_FROM_TOP = 10;               // pixels
    private static final int UFO_MARGIN_FROM_TOP = 100;                // pixels
    private static final int REDUCED_ENEMY_HEIGHT = 90;                // pixels
    private static final double UFO_CHANCE = 0.002;                    // ratio
    private static final double ENEMY_FIRE_CHANCE = 0.0001;            // ratio
    private static final int STARTING_LIVES = 3;
    private static final int STARTING_POINTS = 0;

	private int points = 0;
	public int enemyCount = 0;
	private int fieldWidth;
    private int fieldHeight;
    private Random random = new Random();
    public Direction enemyDirection = Direction.EAST;
	public Ship ship;
    
	/**
	 * @param scene
	 */
	public PlayScene(Scene scene, int enemyWidth, int enemyHeight, int shipWidth, int shipHeight) {
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
                Enemy smallEnemy = new SmallEnemy(10 + 100 * column, 90 * currentRow, enemyWidth, enemyHeight, 0,
                        fieldWidth, fieldHeight, block);
                addEntity(smallEnemy);
                currentRow++;
            }
            for (int mediumEnemyRow = 0; mediumEnemyRow < MEDIUM_ENEMY_ROWS; mediumEnemyRow++) {
                Enemy mediumEnemy = new MediumEnemy(10 + 100 * column, 90 * currentRow, enemyWidth, enemyHeight, 0,
                        fieldWidth, fieldHeight, block);
                addEntity(mediumEnemy);
                currentRow++;
            }
            for (int largeEnemyRow = 0; largeEnemyRow < LARGE_ENEMY_ROWS; largeEnemyRow++) {
                Enemy largeEnemy = new LargeEnemy(10 + 100 * column, 90 * currentRow, enemyWidth, enemyHeight, 0,
                        fieldWidth, fieldHeight, block);
                addEntity(largeEnemy);
                currentRow++;
            }
        }
		
		ship = new Ship(shipPositionX, shipPositionY, shipWidth, shipHeight, 0, fieldWidth);
        addEntity(ship);
	}
	
	/**
     * Create an ufo, that flies from one (randomly decided) side to the other. It is worth
     * between 100 and 1000 points if shot.
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
    
    public int getPoints() {
    	return points;
    }
    
    public void addPoints(int points) {
    	if (points < 0) {
    		return;
    	}
    	this.points += points;
    }
    
    @Override
    public void update() {
    	super.update();
    	
    	if (random.nextDouble() < UFO_CHANCE) {
            createUfo();
        }
    }

	public void lose() {
		
	}
	
	public void win() {
		
	}
}
