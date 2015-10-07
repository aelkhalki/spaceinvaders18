package nl.delftelectronics.spaceinvaders.core;

import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Used to play music and sound effects.
 */
public final class Audio {
    private static Collection<MediaPlayer> mps = new ArrayList<MediaPlayer>();

    private static String bombSoundFilename = "/bomb_sound.mp3";
    private static String backgroundSoundFilename = "/background_sound.mp3";

    /**
     * Play the bullet sound effect.
     */
    public static void playBulletSound() {
        playSound(bombSoundFilename);
    }

    /**
     * Play the bomb sound effect.
     */
    public static void playBombSound() {
        playSound(bombSoundFilename);
    }

    /**
     * Play the background music.
     */
    public static void playBackgroundSound() {
        playSound(backgroundSoundFilename);
    }

    /**
     * Play a sound from a file.
     * @param filename filename of the sound.
     */
    private static void playSound(final String filename) {
        try {
            Media media = new Media(Audio.class.getResource(filename).toString());
            MediaPlayer mp = new MediaPlayer(media);
            mp.play();
            // Add Media Player to Collection so that it doesn't get garbage collected.
            mps.add(mp);
        } catch (MediaException e) {
            System.err.println("Could not instantiate Media Player");
        }
    }
}
