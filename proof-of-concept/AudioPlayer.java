import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer {

  Clip clip;
  AudioInputStream audioInputStream;

  static String filepath;

  public AudioPlayer() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
    audioInputStream = AudioSystem.getAudioInputStream(new File(filepath).getAbsoluteFile());

    clip = AudioSystem.getClip();
    clip.open(audioInputStream);

  }

  public void play() {
		clip.start();

	}

}
