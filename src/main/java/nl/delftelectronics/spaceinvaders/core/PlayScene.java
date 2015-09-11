/**
 * 
 */
package nl.delftelectronics.spaceinvaders.core;

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
    private static final double SHIP_MARGIN_FROM_LEFT = 5 / 100.0;
    private static final double SHIP_MARGIN_FROM_BOTTOM = 10 / 100.0;
	
	public int enemyCount = 0;
	private int fieldWidth;
    private int fieldHeight;
	
	/**
	 * @param scene
	 */
	public PlayScene(Scene scene, int enemyWidth, int enemyHeight, int shipWidth, int shipHeight) {
		super(scene);
		
		fieldWidth = (int) scene.getWidth();
		fieldHeight = (int) scene.getHeight();
		
		int shipPositionX = (int) (fieldWidth * SHIP_MARGIN_FROM_LEFT);
        int shipPositionY = (int) (fieldHeight * (1 - SHIP_MARGIN_FROM_BOTTOM));
		
		for (int column = 0; column < ENEMY_COLUMNS; column++) {
            int currentRow = 0;
            for (int smallEnemyRow = 0; smallEnemyRow < SMALL_ENEMY_ROWS; smallEnemyRow++) {
                Enemy smallEnemy = new SmallEnemy(10 + 100 * column, 90 * currentRow, enemyWidth, enemyHeight, 0,
                        fieldWidth, fieldHeight);
                addEntity(smallEnemy);
                currentRow++;
            }
            for (int mediumEnemyRow = 0; mediumEnemyRow < MEDIUM_ENEMY_ROWS; mediumEnemyRow++) {
                Enemy mediumEnemy = new MediumEnemy(10 + 100 * column, 90 * currentRow, enemyWidth, enemyHeight, 0,
                        fieldWidth, fieldHeight);
                addEntity(mediumEnemy);
                currentRow++;
            }
            for (int largeEnemyRow = 0; largeEnemyRow < LARGE_ENEMY_ROWS; largeEnemyRow++) {
                Enemy largeEnemy = new LargeEnemy(10 + 100 * column, 90 * currentRow, enemyWidth, enemyHeight, 0,
                        fieldWidth, fieldHeight);
                addEntity(largeEnemy);
                currentRow++;
            }
        }
		
		Ship ship = new Ship(shipPositionX, shipPositionY, shipWidth, shipHeight);
        addEntity(ship);
	}

	public void lose() {
		
	}
	
	public void win() {
		
	}
}
