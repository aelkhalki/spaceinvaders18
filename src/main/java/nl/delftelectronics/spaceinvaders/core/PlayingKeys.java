package nl.delftelectronics.spaceinvaders.core;

/**
 * A set of keys that a player can use to play the game.
 */
public class PlayingKeys {
    private String moveLeftKey;
    private String moveRightKey;
    private String fireBulletKey;
    private String fireBombKey;

    /**
     * Construct the PlayingKeys by passing the appropriate String representations of the keys.
     *
     * @param moveLeftKey   key used for moving left
     * @param moveRightKey  key used for moving right
     * @param fireBulletKey key used for firing a bullet
     * @param fireBombKey   key used for firing a bomb
     */
    public PlayingKeys(String moveLeftKey, String moveRightKey, String fireBulletKey,
                       String fireBombKey) {
        this.moveLeftKey = moveLeftKey;
        this.moveRightKey = moveRightKey;
        this.fireBulletKey = fireBulletKey;
        this.fireBombKey = fireBombKey;
    }

    /**
     * Return the key used for moving left.
     *
     * @return the key used for moving left
     */
    public String getMoveLeftKey() {
        return moveLeftKey;
    }

    /**
     * Set the key used for moving left.
     *
     * @param moveLeftKey the key used for moving left
     */
    public void setMoveLeftKey(String moveLeftKey) {
        this.moveLeftKey = moveLeftKey;
    }

    /**
     * Return the key used for moving right.
     *
     * @return the key used for moving right
     */
    public String getMoveRightKey() {
        return moveRightKey;
    }

    /**
     * Set the key used for moving right.
     *
     * @param moveRightKey the key used for moving right
     */
    public void setMoveRightKey(String moveRightKey) {
        this.moveRightKey = moveRightKey;
    }

    /**
     * Set the key used for firing a bullet.
     *
     * @return the key used for firing a bullet
     */
    public String getFireBulletKey() {
        return fireBulletKey;
    }

    /**
     * Set the key used for firing a bullet.
     *
     * @param fireBulletKey he key used for firing a bullet
     */
    public void setFireBulletKey(String fireBulletKey) {
        this.fireBulletKey = fireBulletKey;
    }

    /**
     * Set the key used for firing a bomb.
     *
     * @return the key used for firing a bomb
     */
    public String getFireBombKey() {
        return fireBombKey;
    }

    /**
     * Set the key used for firing a bomb.
     *
     * @param fireBombKey he key used for firing a bomb
     */
    public void setFireBombKey(String fireBombKey) {
        this.fireBombKey = fireBombKey;
    }
}
