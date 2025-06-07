package src;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import java.net.URL;

public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];
    // Set volume (range in decibels, e.g., -80.0f to 6.0f)

    // Example: Set volume to 50% of max (you can change this value)
    float volume = 0.0f; // 0.0f is max volume, negative is quieter
    

    public Sound() {
        soundURL[0] = getClass().getResource("lobby.wav");
        soundURL[1] = getClass().getResource("place.wav");

        // soundURL[0] = getClass().getResource("C:/School/CS12/cpt/SFX/lobby.wav");

        if (soundURL[0] == null) {
            System.out.println("Sound file not found!");
        } else {
            System.out.println("FOUND");
        }
        System.out.println("here");
        System.out.println(soundURL[0]);
    }

    public void setFile(int i) {
        try {
            if (i == 0) {
                volume = -20.0f; // 0.0f is max volume, negative is quieter
            } else {
                volume = 0.0f; // 0.0f is max volume, negative is quieter

                
            }
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            gainControl.setValue(volume);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public void play(int i) {
        setFile(i);
        clip.start();
        loop();
    }

    public void playOnce(int i) {
        setFile(i);
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}
