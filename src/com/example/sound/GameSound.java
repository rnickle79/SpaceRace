package com.example.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.InputStream;

public class GameSound {

    public void playMusic(String file){
        new Thread(()-> {
             try(AudioInputStream stream = AudioSystem.getAudioInputStream(getClass().getResource(file))){
                Clip clip = AudioSystem.getClip();
                clip.open(stream);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

    }

    public void playSound(String file){
        new Thread(()-> {
            try(AudioInputStream stream = AudioSystem.getAudioInputStream(getClass().getResource(file))){
                Clip clip = AudioSystem.getClip();
                clip.open(stream);
                clip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
