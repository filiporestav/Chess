package GUI;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class BackgroundMusic {

    private Clip clip;
    private FloatControl volumeControl;

    public BackgroundMusic(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);

            clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            // Get the volume control from the clip
            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

        } catch (UnsupportedAudioFileException e) {
            // Handle UnsupportedAudioFileException
            System.err.println("Unsupported audio file format: " + e.getMessage());
            e.printStackTrace(); // Log the stack trace if needed
            // Take appropriate action, e.g., show an error message to the user
        } catch (IOException | LineUnavailableException e) {
            // Handle other exceptions
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip != null && !clip.isRunning()) {
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public void setVolume(float volume) {
        if (volumeControl != null) {
            volumeControl.setValue(volume);
        }
    }
}

