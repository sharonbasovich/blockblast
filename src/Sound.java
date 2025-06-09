package src;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import java.net.URL;

public class Sound {

    // an audio data interface
    private Clip clip;

    // array containing sound urls
    private URL soundURL[] = new URL[5];

    // volume tracker
    private float volume = 0.0f; // 0.0f is max volume, negative is quieter

    public Sound() {
        // get all the sound files and save them to the array
        soundURL[0] = getClass().getResource("lobby.wav");
        soundURL[1] = getClass().getResource("place.wav");
        soundURL[2] = getClass().getResource("start.wav");
        soundURL[3] = getClass().getResource("pickup.wav");
        soundURL[4] = getClass().getResource("button.wav");
    }

    public void setFile(int i) {
        try {
            // if tracks 0 or 3 are chosen, set a custom volume for them
            // otherwise, reset to normal
            if (i == 0) {
                volume = -20.0f;
            } else if (i == 3) {
                volume = -10.0f;
            } else {
                volume = 0.0f;
            }

            // set the audio input stream to the correct sound file
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            // load the sound to the audio system
            clip = AudioSystem.getClip();
            clip.open(ais);

            // adjust the volume using the volume variable
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume);
        } catch (Exception e) {
        }
    }

    public void play(int i) {
        // when asked to play a file, prepare the file using helper method then start
        // and loop the sound
        setFile(i);
        clip.start();
        loop();
    }

    public void playOnce(int i) {
        // same as previous, but don't loop
        setFile(i);
        clip.start();
    }

    public void loop() {
        // helper method to abstract looping
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        // stop looping playback
        clip.stop();
    }
}
