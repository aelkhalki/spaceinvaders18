/**
 * 
 */
package nl.delftelectronics.spaceinvaders.core.entities;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles creating enemies for a specified block
 * @author Max
 */
public class EnemyFactory {
	private EnemyBlock block;
	private int fieldWidth;
	private int fieldHeight;

	private static final int SMALL_ENEMY_ROWS = 1;
	private static final int MEDIUM_ENEMY_ROWS = 2;
	private static final int LARGE_ENEMY_ROWS = 2;
	private static final int ENEMY_COLUMNS = 12;
	private static final int ENTITY_DIMENSION = 100;
	private static final double COLUMN_WIDTH = 100;
	private static final double ROW_HEIGHT = 90;
	private static final double ENEMY_OFFSET_X = 10;

	/**
	 * Creates a new enemy factory
	 * 
	 * @param block
	 *            The block the enemies should belong to
	 * @param fieldWidth
	 *            The width of the playing field
	 * @param fieldHeight
	 *            The height of the playing field
	 */
	public EnemyFactory(EnemyBlock block, int fieldWidth, int fieldHeight) {
		this.block = block;
		this.fieldWidth = fieldWidth;
		this.fieldHeight = fieldHeight;
	}

	/**
	 * Creates a small enemy
	 * 
	 * @param column
	 *            The column to place the enemy in
	 * @param row
	 *            The row to place the enemy in
	 * @return A small enemy
	 */
	public Enemy createSmall(int column, int row) {
		return new SmallEnemy(calculatePosition(column, row), 0, fieldWidth, fieldHeight, block);
	}

	/**
	 * Creates a small enemy
	 * 
	 * @param column
	 *            The column to place the enemy in
	 * @param row
	 *            The row to place the enemy in
	 * @return A small enemy
	 */
	public Enemy createMedium(int column, int row) {
		return new MediumEnemy(calculatePosition(column, row), 0, fieldWidth, fieldHeight, block);
	}

	/**
	 * Creates a small enemy
	 * 
	 * @param column
	 *            The column to place the enemy in
	 * @param row
	 *            The row to place the enemy in
	 * @return A small enemy
	 */
	public Enemy createLarge(int column, int row) {
		return new LargeEnemy(calculatePosition(column, row), 0, fieldWidth, fieldHeight, block);
	}
	
	private Rectangle2D calculatePosition(int column, int row) {
		return new Rectangle2D.Double(ENEMY_OFFSET_X + COLUMN_WIDTH * column,
				ROW_HEIGHT * row, ENTITY_DIMENSION,
				ENTITY_DIMENSION);
	}

	/**
	 * Creates a full block of enemies and returns the result
	 * 
	 * @return The generated enemies
	 */
	public List<Enemy> createBlock() {
		ArrayList<Enemy> result = new ArrayList<Enemy>();

		for (int column = 0; column < ENEMY_COLUMNS; column++) {
			int row = 0;
			for (int i = 0; i < SMALL_ENEMY_ROWS; i++) {
				result.add(createSmall(column, row++));
			}

			for (int i = 0; i < MEDIUM_ENEMY_ROWS; i++) {
				result.add(createMedium(column, row++));
			}

			for (int i = 0; i < LARGE_ENEMY_ROWS; i++) {
				result.add(createLarge(column, row++));
			}
		}

		return result;
	}
}
