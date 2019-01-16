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

import java.util.ArrayList;

public class Game {

  public static void putString(int row, int col, Terminal t, String str){
		t.moveCursor(row, col);
		for(int i=0; i<str.length(); i++) {
			t.putCharacter(str.charAt(i));
		}
	}

  public static void main(String[] args) {

    ArrayList<Squishy> Players = new ArrayList<Squishy>();

    // instance of Player "Cloud"
    //                        name     hp     atk  dex  vit  mag  spt  lck  mnp  lvl
    Player Cloud = new Player("Cloud", 314,   20,  6,   16,  19,  17,  14,  54,  6  );
    // add Cloud to list of Players
    Players.add(Cloud);

//  Player Barret = new Player("Barret",   222,  15,  5,  13,  11,  9,  13,  15,  1  );
//  Players.add(Barret);
//  to be added later yehh

    ArrayList<Squishy> enemies = new ArrayList<Squishy>();

    // instances of two MilitaryPolice as they are the first enemies that Cloud encounters
    MilitaryPolice Emmet = new MilitaryPolice(30, 6, 50, 4, 0, 0, 4);
    MilitaryPolice Bernard = new MilitaryPolice(30, 6, 50, 4, 0, 0, 4);

    Enemies.add(Emmet);
    Enemies.add(Bernard);

    // int map
    Map map = new Map();

    // should default to first room

    // System.out.println(map);

    Terminal terminal = TerminalFacade.createTextTerminal();
    terminal.enterPrivateMode();

    TerminalSize terminalSize = terminal.getTerminalSize();
    terminal.setCursorVisible(false);

    int x = 0;
    int y = 0;

    while(true) {

    }

  }

}
