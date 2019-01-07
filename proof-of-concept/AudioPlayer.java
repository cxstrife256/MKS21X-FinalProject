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
  boolean is_looped;

  public AudioPlayer(String filepath, boolean is_looped) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
    this.filepath = filepath;
    this.is_looped = is_looped;

    audioInputStream = AudioSystem.getAudioInputStream(new File(this.filepath).getAbsoluteFile());

    clip = AudioSystem.getClip();
    clip.open(audioInputStream);

    if(this.is_looped) { clip.loop(Clip.LOOP_CONTINUOUSLY); }

  }

  public void play() {
		clip.start();

	}

  public void stop() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		clip.stop();
		clip.close();

	}

  public void resetAudioStream() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		audioInputStream = AudioSystem.getAudioInputStream(new File(filepath).getAbsoluteFile());
		clip.open(audioInputStream);

		if(this.is_looped) { clip.loop(Clip.LOOP_CONTINUOUSLY); }

	}

}
