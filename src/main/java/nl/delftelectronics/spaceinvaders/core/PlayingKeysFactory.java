package nl.delftelectronics.spaceinvaders.core;

import java.util.LinkedList;
import java.util.Queue;

/**
 * This Factory generates unique PlayingKeys that each player can use.
 */
public class PlayingKeysFactory {
    Queue<PlayingKeys> playingKeysQueue = new LinkedList<PlayingKeys>();

    /**
     * Construct a PlayingKeysFactory.
     */
    public PlayingKeysFactory() {
        playingKeysQueue.add(new PlayingKeys("A", "D", "W", "SHIFT"));
        playingKeysQueue.add(new PlayingKeys("LEFT", "RIGHT", "UP", "NUMPAD0"));
    }

    /**
     * Return the PlayingKeys for the next player.
     * @return the PlayingKeys for the next player
     */
    public PlayingKeys next() {
        return playingKeysQueue.remove();
    }
}
