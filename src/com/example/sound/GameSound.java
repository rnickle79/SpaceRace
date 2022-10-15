package com.example.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.InputStream;
import java.util.Objects;

public class GameSound {

    public static void playMusic(String file){
        new Thread(()-> {
            try {
                InputStream is = Objects.requireNonNull(GameSound.class.getResourceAsStream(file));
                AudioInputStream stream = AudioSystem.getAudioInputStream(is);
                Clip clip = AudioSystem.getClip();
                clip.open(stream);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

    }

    public static void playSound(String file){
        new Thread(()-> {
            try {
                Clip clip = AudioSystem.getClip();
                InputStream is = Objects.requireNonNull(GameSound.class.getResourceAsStream(file));
                AudioInputStream stream = AudioSystem.getAudioInputStream(is);
                clip.open(stream);
                clip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
