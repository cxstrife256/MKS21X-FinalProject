import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.Key.Kind;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.Terminal.Color;
import com.googlecode.lanterna.terminal.TerminalSize;
import com.googlecode.lanterna.LanternaException;
import com.googlecode.lanterna.input.CharacterPattern;
import com.googlecode.lanterna.input.InputDecoder;
import com.googlecode.lanterna.input.InputProvider;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.KeyMappingProfile;

public class Driver {

  public static void putString(int row, int col, Terminal t, String str){
		t.moveCursor(row, col);
		for(int i=0; i<str.length(); i++) {
			t.putCharacter(str.charAt(i));
		}
	}

  public static void main(String[] args) {

    try {
			AudioPlayer CURSOR_READY = new AudioPlayer("../media/Effects/CURSOR_READY.wav", false);
      CURSOR_READY.play();

      // AudioPlayer CURSOR_MOVE = new AudioPlayer("../media/Effects/CURSOR_MOVE.wav", false);

      Terminal terminal = TerminalFacade.createTextTerminal();
  		terminal.enterPrivateMode();

      TerminalSize terminalSize = terminal.getTerminalSize();
  		terminal.setCursorVisible(false);

      int x = 0;
      int y = 0;

      while(true) {
        terminal.moveCursor(x, y);
        terminal.putCharacter('\u261B');

        Key key = terminal.readInput();

        if(key != null) {
          if(key.getKind() == Key.Kind.Escape) {
  					terminal.exitPrivateMode();
  					System.exit(0);
  				}

          if(key.getKind() == Key.Kind.ArrowUp) {
  					terminal.moveCursor(x, y);
  					terminal.putCharacter(' ');
  					y -= 1;
            // CURSOR_MOVE.play();
  				}

  				if(key.getKind() == Key.Kind.ArrowDown) {
  					terminal.moveCursor(x, y);
  					terminal.putCharacter(' ');
  					y += 1;
            // CURSOR_MOVE.play();
  				}
        }

        putString(0, 0, terminal, "  Item");
        putString(0, 1, terminal, "  Magic");
        putString(0, 3, terminal, "  Equip");
        putString(0, 4, terminal, "  Status");
        putString(0, 5, terminal, "  Order");
        putString(0, 6, terminal, "  Limit");
        putString(0, 7, terminal, "  Config");
        putString(0, 9, terminal, "  Save");

        // CURSOR_MOVE.stop();
        // CURSOR_MOVE.resetAudioStream();

      }

		} catch (Exception e) { e.printStackTrace(); }

  }

}
