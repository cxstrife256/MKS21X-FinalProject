import java.lang.*;

public class AudioTest {

  public static void main(String[] args) {

    try {
			AudioPlayer OPENING_BOMBING_MISSION = new AudioPlayer("../media/Music/FANFARE.wav");
			OPENING_BOMBING_MISSION.play();

      new java.util.Timer().schedule(
        new java.util.TimerTask() {
          @Override
          public void run() {
            try {
              OPENING_BOMBING_MISSION.stop();

            } catch (Exception e) { e.printStackTrace(); }

          }
        } , 10000
      );

      new java.util.Timer().schedule(
        new java.util.TimerTask() {
          @Override
          public void run() {
            try {
              OPENING_BOMBING_MISSION.resetAudioStream();
              OPENING_BOMBING_MISSION.play();

            } catch (Exception e) { e.printStackTrace(); }

          }
        } , 11000
      );

      new java.util.Timer().schedule(
        new java.util.TimerTask() {
          @Override
          public void run() {
            System.exit(0);

          }
        } , 14000
      );

		} catch (Exception e) { e.printStackTrace(); }

  }

}
