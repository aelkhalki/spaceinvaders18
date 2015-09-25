package nl.delftelectronics.spaceinvaders.core;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ali on 9/25/15.
 */
public class Audio {
    private static Collection<MediaPlayer> mps = new ArrayList<MediaPlayer>();

    private static String bombSoundFilename = "/bomb_sound.mp3";
    private static String backgroundSoundFilename = "/background_sound.mp3";

    public static void playBombSound() {
        playSound(bombSoundFilename);
    }

    public static void playBackgroundSound() {
        playSound(backgroundSoundFilename);
    }

    private static void playSound(final String filename) {
        Media media = new Media(Audio.class.getResource(filename).toString());
        MediaPlayer mp = new MediaPlayer(media);
        mp.play();
        mps.add(mp);
    }
}
